package com.dongni.billDelivery.manager;

import com.dongni.billDelivery.entity.BillShop;

import java.util.List;

/**
 * 店铺管理Manager对象
 */
public interface BillShopManager {
    /**
     * 根据名称模糊过滤店铺
     *
     * @param name
     * @return
     */
    List<BillShop> findBillShopByName(String name);

    /**
     * 根据名称获取店铺，如果不存在则创建
     *
     * @param name
     * @return
     */
    BillShop createIfNotExist(String name);

    /**
     * 获取所有商家
     *
     * @return
     */
    List<BillShop> findBillShop();

    /**
     * 获取店铺
     *
     * @param shopId
     * @return
     */
    BillShop getBillShop(Long shopId);
}
