package com.dongni.billDelivery.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单核对返回值
 */
public class BillOrderCheckVO implements Serializable {
    /**
     * 总价
     */
    private BigDecimal total = BigDecimal.ZERO;
    /**
     * 订单详情
     */
    private List<BillItemVO> items = new ArrayList<>();

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<BillItemVO> getItems() {
        return items;
    }

    public void setItems(List<BillItemVO> items) {
        this.items = items;
    }
}
