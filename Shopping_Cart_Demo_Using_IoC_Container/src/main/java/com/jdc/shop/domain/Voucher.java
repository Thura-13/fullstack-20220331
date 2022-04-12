package com.jdc.shop.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Voucher {
	
	private int id;
	private LocalDate saleDateTime;
	private String customer;
	private List<SaleItem> saleItems;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getSaleDateTime() {
		return saleDateTime;
	}
	public void setSaleDateTime(LocalDate saleDateTime) {
		this.saleDateTime = saleDateTime;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public List<SaleItem> getSaleItems() {
		return saleItems;
	}
	public void setSaleItems(List<SaleItem> saleItems) {
		this.saleItems = saleItems;
	}
	
	public int totalItemCount() {
		int totalItemCount = 0;
		for(var item : saleItems) {
			totalItemCount +=item.getCount();
		}
		return totalItemCount;
	}
	
	public int totalAmount() {
		int totalAmount =0;
		for(var item : saleItems) {
			totalAmount += item.getProduct().getPrice();
		}
		return totalAmount;
	}
	
	
}
