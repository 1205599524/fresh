package com.dongni.billDelivery.dao;

import com.dongni.billDelivery.entity.BillShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 店铺Repository
 */
@Repository
public interface BillShopRepository extends JpaRepository<BillShop, Long> {
    /**
     * 根据名称模糊匹配店铺
     *
     * @param name
     * @return
     */
    List<BillShop> findBillShopByNameLike(String name);

    /**
     * 根据名称获取店铺
     *
     * @param name
     * @return
     */
    List<BillShop> findBillShopByName(String name);
}
