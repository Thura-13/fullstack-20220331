package com.jdc.shop.model;

import java.util.List;

import com.jdc.shop.domain.Product;

public interface ProductModel {

	List<Product> getAll();
	void saveProduct(Product product);
	Product findById(int id);
}
