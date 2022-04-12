package com.jdc.shop.model;

import java.util.List;

import com.jdc.shop.domain.SaleItem;
import com.jdc.shop.domain.Voucher;

public interface SaleModel {

	int createVoucher(String customer,List<SaleItem> saleItems);
	List<Voucher> getSaleHistoryList();
	Voucher findById(int id);
}
