package com.dongni.billDelivery.params;

import com.dongni.billDelivery.vo.BillItemVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * 生成订单参数
 */
public class BillOrderParams {
    /**
     * 店家名称
     */
    private String shopName;
    /**
     * 订单详情
     */
    private String detail;
    /**
     * 总价
     */
    private BigDecimal total;
    /**
     * 订单小项
     */
    private List<BillItemVO> items;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

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
