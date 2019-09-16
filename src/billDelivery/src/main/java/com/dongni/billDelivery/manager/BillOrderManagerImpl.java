package com.dongni.billDelivery.manager;

import com.dongni.billDelivery.dao.BillItemRepository;
import com.dongni.billDelivery.dao.BillOrderRepository;
import com.dongni.billDelivery.entity.BillCommodity;
import com.dongni.billDelivery.entity.BillItem;
import com.dongni.billDelivery.entity.BillOrder;
import com.dongni.billDelivery.params.BillOrderCheckParam;
import com.dongni.billDelivery.params.BillOrderParams;
import com.dongni.billDelivery.vo.BillItemVO;
import com.dongni.billDelivery.vo.BillOrderCheckVO;
import com.dongni.billDelivery.vo.TotalShopVO;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.text.StringTokenizer;
import org.apache.commons.text.matcher.StringMatcher;
import org.apache.commons.text.matcher.StringMatcherFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 订单管理Manager
 */
@Service
public class BillOrderManagerImpl implements BillOrderManager {
    @Autowired
    private BillShopManager billShopManager;
    @Autowired
    private BillCommodityManager billCommodityManager;
    @Autowired
    private BillItemRepository billItemRepository;
    @Autowired
    private BillOrderRepository billOrderRepository;

    @Override
    public BillOrder createBillOrder(BillOrderParams billOrderParams) {
        // 生成订单主信息
        BillOrder billOrder = new BillOrder();
        billOrder.setDetail(billOrderParams.getDetail());
        billOrder.setCreateDate(new Date());
        billOrder.setShopId(billShopManager.createIfNotExist(billOrderParams.getShopName()).getId());
        billOrder.setTotal(billOrderParams.getTotal());
        billOrderRepository.save(billOrder);

        // 生成订单详情
        List<BillItem> items = new ArrayList<>(billOrderParams.getItems().size());
        for (BillItemVO itemVO : billOrderParams.getItems()) {
            BillItem billItem = new BillItem();
            billItem.setShopId(billOrder.getShopId());
            billItem.setOrderId(billOrder.getId());
            billItem.setCommodityId(itemVO.getCommodityId());
            billItem.setPrice(itemVO.getPrice());
            billItem.setAmount(itemVO.getAmount());
            billItem.setTotal(itemVO.getTotal());
            billItem.setCreateDate(new Date());
            items.add(billItem);
        }
        billItemRepository.saveAll(items);

        return billOrder;
    }

    @Override
    public BillOrderCheckVO checkOrder(BillOrderCheckParam checkParam) {
        BillOrderCheckVO checkVO = new BillOrderCheckVO();
        // 获取当前系统的菜品
        Multimap<String, BillCommodity> multimap = billCommodityManager.findUnitMultimap();
        List<String> units = getUnits(multimap);
        // 生成清单
        StringMatcher stringMatcher = StringMatcherFactory.INSTANCE.charSetMatcher(',', '，');
        StringTokenizer stringTokenizer = new StringTokenizer(checkParam.getDetail(), stringMatcher);
        while (stringTokenizer.hasNext()) {
            String itemStr = stringTokenizer.next();
            BillCommodity commodity = choseCommodity(itemStr, units, multimap);
            if (commodity != null) {
                BillItemVO billItemVO = new BillItemVO();
                billItemVO.setCommodityId(commodity.getId());
                billItemVO.setCommodityName(commodity.getName());
                billItemVO.setPrice(commodity.getPrice());
                billItemVO.setUnit(commodity.getUnit());

                String amount = itemStr.substring(commodity.getName().length(), itemStr.lastIndexOf(commodity.getUnit()));
                billItemVO.setAmount(new BigDecimal(amount));
                billItemVO.setTotal(billItemVO.getPrice().multiply(billItemVO.getAmount()));

                checkVO.getItems().add(billItemVO);
                checkVO.setTotal(checkVO.getTotal().add(billItemVO.getTotal()));
            }
        }
        return checkVO;
    }

    @Override
    public TotalShopVO getTotalShopVO() {
        TotalShopVO totalShopVO = new TotalShopVO();
        List<Object[]> groupByShop = billOrderRepository.findGroupByShop();
        if (CollectionUtils.isNotEmpty(groupByShop)) {
            for (Object[] arr : groupByShop) {
                Long shopId = (Long)arr[0];
                BigDecimal total = new BigDecimal(arr[1].toString());
                TotalShopVO.ShopVO shopVO = new TotalShopVO.ShopVO();
                shopVO.setId(shopId);
                shopVO.setName(billShopManager.getBillShop(shopId).getName());
                shopVO.setTotal(total);
                totalShopVO.setTotal(totalShopVO.getTotal().add(total));
                totalShopVO.getItems().add(shopVO);
            }
        }
        return totalShopVO;
    }

    @Override
    public List<BillItem> findBillItem(Date startDate, Date endDate, Long commodityId, List<Long> shopIds) {
        boolean allShop = CollectionUtils.isEmpty(shopIds);
        return billItemRepository.findBillItemByCreateDateBetween(startDate, endDate).stream().filter(new java.util.function.Predicate<BillItem>() {
            @Override
            public boolean test(BillItem billItem) {
                return Objects.equals(commodityId, billItem.getCommodityId()) && (allShop || shopIds.contains(billItem.getShopId()));
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<BillItem> findBillItem(Date startDate, Date endDate, List<Long> commodityIds, Long shopId) {
        boolean allCommodity = CollectionUtils.isEmpty(commodityIds);
        return billItemRepository.findBillItemByCreateDateBetween(startDate, endDate).stream().filter(new java.util.function.Predicate<BillItem>() {
            @Override
            public boolean test(BillItem billItem) {
                return Objects.equals(shopId, billItem.getShopId()) && (allCommodity || commodityIds.contains(billItem.getCommodityId()));
            }
        }).collect(Collectors.toList());
    }

    /**
     * 获取菜品
     *
     * @param itemStr
     * @param units
     * @param multimap
     * @return
     */
    private BillCommodity choseCommodity(String itemStr, List<String> units, Multimap<String, BillCommodity> multimap) {
        for (String unit : units) {
            if (!itemStr.endsWith(unit)) {
                continue;
            }
            for (BillCommodity commodity : multimap.get(unit)) {
                if (itemStr.startsWith(commodity.getName())) {
                    return commodity;
                }
            }
        }
        return null;
    }

    /**
     * 获取单位
     *
     * @param multimap
     * @return
     */
    private List<String> getUnits(Multimap<String, BillCommodity> multimap) {
        List<String> units = Lists.newArrayList(multimap.keySet());
        units.sort((o1, o2) -> o2.length() - o1.length());
        return units;
    }
}
