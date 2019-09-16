package com.dongni.billDelivery.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单小项
 */
public class BillItemVO implements Serializable {
    /**
     * 菜品Id
     */
    private Long commodityId;
    /**
     * 菜品名称
     */
    private String commodityName;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 数量
     */
    private BigDecimal amount;
    /**
     * 单位
     */
    private String unit;
    /**
     * 总价
     */
    private BigDecimal total;

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
