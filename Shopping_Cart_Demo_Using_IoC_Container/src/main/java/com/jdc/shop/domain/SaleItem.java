package com.jdc.shop.domain;

public class SaleItem {

	private int id;
	private Product product;
	private int count;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getTotalPrice() {
		return count*getProduct().getPrice();
	}
	public void addCount() {
		 ++count;
	}
	public void changeCount(boolean plus) {
		
		if(plus) {
			++count;
		}else {
			--count;
		}
	}
	
	public int totalAmount() {
		return count * getProduct().getPrice();
	}

}
