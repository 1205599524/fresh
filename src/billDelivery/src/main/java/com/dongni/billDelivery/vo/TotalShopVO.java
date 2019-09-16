package com.dongni.billDelivery.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 按店铺总汇总
 */
public class TotalShopVO implements Serializable {
    /**
     * 总汇总
     */
    private BigDecimal total = BigDecimal.ZERO;

    private List<ShopVO> items = new ArrayList<>();

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<ShopVO> getItems() {
        return items;
    }

    public void setItems(List<ShopVO> items) {
        this.items = items;
    }

    public static class ShopVO implements Serializable {
        /**
         * 店铺ID
         */
        private Long id;
        /**
         * 店铺名称
         */
        private String name;
        /**
         * 店铺总金额
         */
        private BigDecimal total;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getTotal() {
            return total;
        }

        public void setTotal(BigDecimal total) {
            this.total = total;
        }
    }
}
