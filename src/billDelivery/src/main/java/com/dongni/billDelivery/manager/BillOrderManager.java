package com.dongni.billDelivery.manager;

import com.dongni.billDelivery.entity.BillItem;
import com.dongni.billDelivery.entity.BillOrder;
import com.dongni.billDelivery.params.BillOrderCheckParam;
import com.dongni.billDelivery.params.BillOrderParams;
import com.dongni.billDelivery.vo.BillItemVO;
import com.dongni.billDelivery.vo.BillOrderCheckVO;
import com.dongni.billDelivery.vo.TotalShopVO;

import java.util.Date;
import java.util.List;

/**
 * 订单管理Manager
 */
public interface BillOrderManager {
    /**
     * 生成订单
     *
     * @param billOrderParams
     * @return
     */
    BillOrder createBillOrder(BillOrderParams billOrderParams);

    /**
     * 校验订单信息并生成订单清单
     *
     * @param checkParam
     * @return
     */
    BillOrderCheckVO checkOrder(BillOrderCheckParam checkParam);

    /**
     * 获取店铺维度汇总数据
     *
     * @return
     */
    TotalShopVO getTotalShopVO();

    /**
     * 获取订单清单
     *
     * @param startDate
     * @param endDate
     * @param commodityId
     * @param shopIds
     * @return
     */
    List<BillItem> findBillItem(Date startDate, Date endDate, Long commodityId, List<Long> shopIds);

    /**
     * 获取订单清单
     *
     * @param startDate
     * @param endDate
     * @param commodityIds
     * @param shopId
     * @return
     */
    List<BillItem> findBillItem(Date startDate, Date endDate, List<Long> commodityIds, Long shopId);
}
