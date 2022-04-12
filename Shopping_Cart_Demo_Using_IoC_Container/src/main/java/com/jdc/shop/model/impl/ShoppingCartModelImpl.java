package com.jdc.shop.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.jdc.shop.domain.Product;
import com.jdc.shop.domain.SaleItem;
import com.jdc.shop.model.ShoppingCartModel;

public class ShoppingCartModelImpl implements ShoppingCartModel{


	
	List<SaleItem> list = new ArrayList<SaleItem>();
	
	@Override
	public List<SaleItem> getAll() {
		return new ArrayList<SaleItem>(list);
	}

	@Override
	public void addSaleItem(Product product) {
		
		SaleItem item = findSaleItemByProductId(product.getId());
		
		if(item == null) {
			item = new SaleItem();
			item.setId(getAll().size()+1);
			item.setProduct(product);
			list.add(item);
		}
		
		item.addCount();
	}

	@Override
	public SaleItem findSaleItemByProductId(int productId) {
		for(var s : getAll()) {
			if(s.getProduct().getId() == productId) {
				return s;
			}
		}
		return null;
	}

	@Override
	public int getTotalCount() {
		int count = 0;
		for(var item : list) {
			count += item.getCount();
		}
		return count;
	}
	
	@Override
	public void clearCart() {
		list.clear();
	}
	
	@Override
	public void changeItemCount(boolean plus, Product product) {
		
		var saleItem = findSaleItemByProductId(product.getId());
		
		if(saleItem != null) {
			saleItem.changeCount(plus);
			
			if(saleItem.getCount() == 0) {
				list.remove(saleItem);
			}
		}
		
	}
	
	@Override
	public int getTotalAmount() {
		int totalAmount = 0;
		for(var item : list) {
			totalAmount += item.getTotalPrice();
		}
		return totalAmount;
	}

	

	
	

	
	

}
