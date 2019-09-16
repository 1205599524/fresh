package com.dongni.billDelivery.dao;

import com.dongni.billDelivery.entity.BillCommodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜品Repository
 */
@Repository
public interface BillCommodityRepository extends JpaRepository<BillCommodity, Long> {
    /**
     * 获取启用的菜品
     *
     * @param enable
     * @return
     */
    List<BillCommodity> findBillCommodityByEnable(Boolean enable);

}
