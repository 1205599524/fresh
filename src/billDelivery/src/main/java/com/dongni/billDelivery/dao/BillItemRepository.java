package com.dongni.billDelivery.dao;

import com.dongni.billDelivery.entity.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 订单详情Repository
 */
@Repository
public interface BillItemRepository extends JpaRepository<BillItem, Long>, JpaSpecificationExecutor<BillItem> {
    /**
     * 根据时间段获取数据
     *
     * @param startDate
     * @param endDate
     * @return
     */
    List<BillItem> findBillItemByCreateDateBetween(Date startDate, Date endDate);
}
