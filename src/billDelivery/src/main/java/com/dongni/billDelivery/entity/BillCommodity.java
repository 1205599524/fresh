package com.dongni.billDelivery.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 货品实体对象
 */
@Entity
@Table(name="bill_commodity")
public class BillCommodity implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "com.dongni.billDelivery.UUIDIdentifierGenerator")
    @Column
    private Long id;
    /**
     * 编码
     */
    @Column
    private String serialNo;

    /**
     * 名称
     */
    @Column
    private String name;
    /**
     * 规格
     */
    @Column
    private String specification;
    /**
     * 单位
     */
    @Column
    private String unit;
    /**
     * 单价
     */
    @Column
    private BigDecimal price;
    /**
     * 成本价格
     */
    @Column
    private String costPrice;
    /**
     * 是否启用
     */
    @Column
    private Boolean enable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
