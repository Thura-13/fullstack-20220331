package com.jdc.shop.model;

import java.util.List;

import com.jdc.shop.domain.Product;
import com.jdc.shop.domain.SaleItem;

public interface ShoppingCartModel {

	void addSaleItem(Product product);
	List<SaleItem> getAll();
	SaleItem findSaleItemByProductId(int productId);
	int getTotalCount();
	void clearCart();
	void changeItemCount(boolean plus, Product product);
	int getTotalAmount();
}
