package com.dongni.billDelivery.params;

import java.io.Serializable;

/**
 * 核对订单参数
 */
public class BillOrderCheckParam implements Serializable {
    /**
     * 订单描述
     */
    private String detail ;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
