package com.dongni.billDelivery.manager;

import com.dongni.billDelivery.dao.BillShopRepository;
import com.dongni.billDelivery.entity.BillShop;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 店铺管理Manager对象
 */
@Service
public class BillShopManagerImpl implements BillShopManager {
   @Autowired
    private BillShopRepository billShopRepository;

    @Override
    public List<BillShop> findBillShopByName(String name) {
        return billShopRepository.findBillShopByNameLike(name);
    }

    @Override
    public BillShop createIfNotExist(String name) {
        List<BillShop> list = billShopRepository.findBillShopByName(name);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }

        BillShop billShop = new BillShop();
        billShop.setName(name);
        billShop.setCreateDate(new Date());

        billShopRepository.save(billShop);

        return billShop;
    }

    @Override
    public List<BillShop> findBillShop() {
        return billShopRepository.findAll();
    }

    @Override
    public BillShop getBillShop(Long shopId) {
        return billShopRepository.findById(shopId).get();
    }
}
