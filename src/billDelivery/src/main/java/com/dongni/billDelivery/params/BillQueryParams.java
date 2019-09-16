package com.dongni.billDelivery.params;

import java.io.Serializable;
import java.util.List;

/**
 * 订单查询过滤条件
 */
public class BillQueryParams implements Serializable {
    /**
     * 按菜品查询
     */
    public static int QUERY_TYPE_COMMODITY = 0;
    /**
     * 按店铺查询
     */
    public static int QUERY_TYPE_SHOP = 1;

    /**
     * 开始日期
     */
    private String startDate;
    /**
     * 结束日期
     */
    private String endDate;
    /**
     * 0=按店铺查询, 1=按菜品查询
     */
    private Integer type;
    /**
     * 0=按店铺查询：店铺Id
     */
    private Long shopId;
    /**
     * 0=按店铺查询：菜品Id合集
     */
    private List<Long> commodityIds;
    /**
     * 1=按菜品查询：菜品Id
     */
    private Long commodityId;
    /**
     * 1=按菜品查询：店铺Id
     */
    private List<Long> shopIds;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public List<Long> getCommodityIds() {
        return commodityIds;
    }

    public void setCommodityIds(List<Long> commodityIds) {
        this.commodityIds = commodityIds;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public List<Long> getShopIds() {
        return shopIds;
    }

    public void setShopIds(List<Long> shopIds) {
        this.shopIds = shopIds;
    }
}
