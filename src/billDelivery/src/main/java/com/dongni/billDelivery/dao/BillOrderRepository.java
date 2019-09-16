package com.dongni.billDelivery.dao;

import com.dongni.billDelivery.entity.BillOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单Repository
 */
@Repository
public interface BillOrderRepository extends JpaRepository<BillOrder, Long> {
    /**
     * 获取店铺维度汇总数据
     *
     * @return
     */
    @Query("SELECT shopId, SUM(total) FROM BillOrder GROUP BY shopId")
    List findGroupByShop();
}
