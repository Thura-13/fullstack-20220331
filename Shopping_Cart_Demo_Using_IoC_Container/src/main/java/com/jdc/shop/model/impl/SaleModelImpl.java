package com.jdc.shop.model.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jdc.shop.domain.SaleItem;
import com.jdc.shop.domain.Voucher;
import com.jdc.shop.model.SaleModel;

public class SaleModelImpl implements SaleModel {
	
	List<Voucher> list = new ArrayList<Voucher>();

	@Override
	public int createVoucher(String coustomer, List<SaleItem> saleItems) {
		
		var voucher = new Voucher();
		voucher.setId(list.size()+1);
		voucher.setCustomer(coustomer);
		voucher.setSaleDateTime(LocalDate.now());
		voucher.setSaleItems(saleItems);
		list.add(voucher);
		return voucher.getId();
	}

	public List<Voucher> getSaleHistoryList() {
		return new ArrayList<Voucher>(list);
	}
	
	@Override
	public Voucher findById(int id) {
		for(Voucher v : list) {
			if(v.getId() == id) {
				return v;
			}
		}
		return null;
	}

}
