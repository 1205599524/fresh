package com.dongni.billDelivery.manager;

import com.dongni.billDelivery.dao.BillCommodityRepository;
import com.dongni.billDelivery.entity.BillCommodity;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillCommodityManagerImpl implements BillCommodityManager {
    @Autowired
    private BillCommodityRepository billCommodityRepository;

    @Override
    public Multimap<String, BillCommodity> findUnitMultimap() {
        return Multimaps.index(billCommodityRepository.findBillCommodityByEnable(true), input -> input.getUnit());
    }

    @Override
    public List<BillCommodity> findAllBillCommodity() {
        return billCommodityRepository.findAll();
    }

    @Override
    public BillCommodity getBillCommodity(Long id) {
        return billCommodityRepository.getOne(id);
    }
}
