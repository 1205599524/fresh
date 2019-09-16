package com.dongni.billDelivery;

import com.dongni.billDelivery.dao.BillShopRepository;
import com.dongni.billDelivery.entity.BillShop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillDeliveryApplicationTests {
	@Autowired
	private BillShopRepository billShopRepository;

	@Test
	public void contextLoads() {
		BillShop billShop = new BillShop();
		billShop.setName("1");
		billShop.setCreateDate(new Date());
		billShopRepository.save(billShop);
		System.out.println(billShop.getId());
	}

}
