package com.jdc.shop.model.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.shop.domain.Product;
import com.jdc.shop.model.ProductModel;

public class ProductModelImpl implements ProductModel{

	private static final String SELECT_ALL = "select * from product";
	private static final String INSERT = "insert into product (category,name,price) values(?,?,?)";
	private DataSource dataSource;
	
	public ProductModelImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public List<Product> getAll() {
		var list = new ArrayList<Product>();
		
		try(var con = dataSource.getConnection()){
			var stat = con.prepareStatement(SELECT_ALL);
			
			var results = stat.executeQuery();
			
			while(results.next()) {
				var product = new Product();
				product.setId(results.getInt("id"));
				product.setCategory(results.getString("category"));
				product.setName(results.getString("name"));
				product.setPrice(results.getInt("price"));
				
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void saveProduct(Product product) {
		
		try(var con = dataSource.getConnection()){
			var stat = con.prepareStatement(INSERT);
			
			stat.setString(1, product.getCategory());
			stat.setString(2, product.getName());
			stat.setInt(3, product.getPrice());
			
			stat.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Product findById(int id) {
		
		for(Product p : getAll()) {
			if(p.getId() == id) {
				return p;
			}
		}
		return null;
	}

}
