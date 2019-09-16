package com.dongni.billDelivery.manager;

import com.dongni.billDelivery.entity.BillCommodity;
import com.google.common.collect.Multimap;

import java.util.List;

/**
 * 菜品Manager
 */
public interface BillCommodityManager {
    /**
     * 获取菜品[单位->菜品]键值对
     *
     * @return
     */
    Multimap<String, BillCommodity> findUnitMultimap();

    /**
     * 获取所有菜品
     *
     * @return
     */
    List<BillCommodity> findAllBillCommodity();

    /**
     * 获取菜品
     *
     * @param id
     * @return
     */
    BillCommodity getBillCommodity(Long id);
}
