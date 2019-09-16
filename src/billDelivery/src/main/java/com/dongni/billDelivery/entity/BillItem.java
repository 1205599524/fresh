package com.dongni.billDelivery.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单小项
 */
@Entity
@Table(name = "bill_item")
public class BillItem implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "com.dongni.billDelivery.UUIDIdentifierGenerator")
    @Column
    private Long id;
    /**
     * 店铺Id
     */
    @Column
    private Long shopId;
    /**
     * 订单Id
     */
    @Column
    private Long orderId;
    /**
     * 菜品Id
     */
    @Column
    private Long commodityId;
    /**
     * 单价
     */
    @Column
    private BigDecimal price;
    /**
     * 数量
     */
    @Column
    private BigDecimal amount;
    /**
     * 总价
     */
    @Column
    private BigDecimal total;
    /**
     * 创建时间
     */
    @Column
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
