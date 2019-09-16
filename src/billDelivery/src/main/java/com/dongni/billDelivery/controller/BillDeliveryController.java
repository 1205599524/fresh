package com.dongni.billDelivery.controller;

import com.dongni.billDelivery.entity.BillCommodity;
import com.dongni.billDelivery.entity.BillItem;
import com.dongni.billDelivery.entity.BillShop;
import com.dongni.billDelivery.manager.BillCommodityManager;
import com.dongni.billDelivery.manager.BillOrderManager;
import com.dongni.billDelivery.manager.BillShopManager;
import com.dongni.billDelivery.params.BillOrderCheckParam;
import com.dongni.billDelivery.params.BillOrderParams;
import com.dongni.billDelivery.params.BillQueryParams;
import com.dongni.billDelivery.vo.BillOrderCheckVO;
import com.dongni.billDelivery.vo.TotalShopVO;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 生鲜配送Controller
 */
@RestController
public class BillDeliveryController {
    @Autowired
    private BillCommodityManager billCommodityManager;
    @Autowired
    private BillShopManager billShopManager;
    @Autowired
    private BillOrderManager billOrderManager;

    /**
     * 获取所有菜品
     *
     * @return
     */
    @RequestMapping(path = "/billCommodity/list", method = RequestMethod.GET)
    public List<BillCommodity> listBillCommodity() {
        return billCommodityManager.findAllBillCommodity();
    }

    /**
     * 获取所有商家
     *
     * @return
     */
    @RequestMapping(path = "/billShop/list", method = RequestMethod.GET)
    public List<BillShop> listBillShop() {
        return billShopManager.findBillShop();
    }

    /**
     * 核对订单
     *
     * @param checkParam
     * @return
     */
    @RequestMapping(path = "/billOrder/check", method = RequestMethod.POST)
    public BillOrderCheckVO checkOrder(@RequestBody BillOrderCheckParam checkParam) {
        return billOrderManager.checkOrder(checkParam);
    }

    /**
     * 生成订单
     *
     * @param orderParams
     * @return
     */
    @RequestMapping(path = "/billOrder", method = RequestMethod.POST)
    public Long createOrder(@RequestBody BillOrderParams orderParams) {
        return billOrderManager.createBillOrder(orderParams).getId();
    }

    /**
     * 店铺维度总汇总
     *
     * @return
     */
    @RequestMapping(path = "/billShop/total", method = RequestMethod.GET)
    public TotalShopVO totalShop() {
        return billOrderManager.getTotalShopVO();
    }

    /**
     * 订单查询
     *
     * @param queryParams
     * @return
     */
    @RequestMapping(path = "/billShop/query", method = RequestMethod.POST)
    public List<Map<String, Object>> billQuery(@RequestBody BillQueryParams queryParams) {
        Date startDate = parseDate(queryParams.getStartDate());
        Date endDate = parseDate(queryParams.getEndDate());
        Integer type = queryParams.getType();
        if (Objects.equals(type, BillQueryParams.QUERY_TYPE_COMMODITY)) { // 按菜品
            List<BillItem> billItems = billOrderManager.findBillItem(startDate, endDate, queryParams.getCommodityId(), queryParams.getShopIds());
            ImmutableListMultimap<String, BillItem> multimap = Multimaps.index(billItems, input -> input.getShopId() + "_" + input.getPrice());

            BillCommodity billCommodity = billCommodityManager.getBillCommodity(queryParams.getCommodityId());
            List<Map<String, Object>> data = Lists.newArrayListWithCapacity(multimap.size());
            for (String key : multimap.keys()) {
                BigDecimal total = BigDecimal.ZERO;
                BigDecimal amount = BigDecimal.ZERO;
                for (BillItem item : multimap.get(key)) {
                    total = total.add(item.getTotal());
                    amount = amount.add(item.getAmount());
                }

                String[] strings = StringUtils.split(key, "_");
                Long shopId = Long.parseLong(strings[0]);
                BigDecimal price = new BigDecimal(strings[1]);

                Map<String, Object> map = Maps.newHashMap();
                map.put("shopId", shopId);
                map.put("shopName", billShopManager.getBillShop(shopId).getName());
                map.put("amount", amount);
                map.put("price", price);
                map.put("total", total);
                map.put("unit", billCommodity.getUnit());
                data.add(map);
            }
            return data;
        } else { // 按商店
            List<BillItem> billItems = billOrderManager.findBillItem(startDate, endDate, queryParams.getCommodityIds(), queryParams.getShopId());
            ImmutableListMultimap<String, BillItem> multimap = Multimaps.index(billItems, input -> input.getCommodityId() + "_" + input.getPrice());
            List<Map<String, Object>> data = Lists.newArrayListWithCapacity(multimap.size());
            for (String key : multimap.keys()) {
                BigDecimal total = BigDecimal.ZERO;
                BigDecimal amount = BigDecimal.ZERO;
                for (BillItem item : multimap.get(key)) {
                    total = total.add(item.getTotal());
                    amount = amount.add(item.getAmount());
                }

                String[] strings = StringUtils.split(key, "_");
                Long commodityId = Long.parseLong(strings[0]);
                BigDecimal price = new BigDecimal(strings[1]);

                Map<String, Object> map = Maps.newHashMap();
                map.put("commodityId", commodityId);
                BillCommodity billCommodity = billCommodityManager.getBillCommodity(commodityId);
                map.put("commodityName", billCommodity.getName());
                map.put("unit", billCommodity.getUnit());
                map.put("amount", amount);
                map.put("price", price);
                map.put("total", total);
                data.add(map);
            }
            return data;
        }
    }

    /**
     * 日期转换
     *
     * @param dateStr
     * @return
     */
    private Date parseDate(String dateStr) {
        try {
            return DateUtils.parseDate(dateStr, "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
