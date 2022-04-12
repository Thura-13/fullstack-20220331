package com.jdc.shop.controller;

import java.io.IOException;import java.net.http.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdc.shop.domain.Product;
import com.jdc.shop.model.ProductModel;
import com.jdc.shop.model.SaleModel;
import com.jdc.shop.model.ShoppingCartModel;

@WebServlet({
	"/add-to-cart",
	"/cart-clear",
	"/cart-show",
	"/cart-plus",
	"/cart-minius"
})
public class ShoppingCartController extends AbstractHttpServletController{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		switch(req.getServletPath()) {
		case "/add-to-cart" -> addToCart(req,resp);
		case "/cart-clear" -> cartClear(req,resp);
		case "/cart-show" -> cartShow(req,resp);
		case "/cart-plus" -> cartChangeItemCount(req,resp);
		case "/cart-minius" -> cartChangeItemCount(req, resp);
		}
	}
	
	private void cartChangeItemCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		For Check Add Count Or Minius Count
		var plus = "/cart-plus".equals(req.getServletPath());
		
//		Get Product Id From Parameter
		String productId = req.getParameter("product");
		
//		Get Product Model
		ProductModel productModel = (ProductModel) getBean("productModel", ProductModel.class);
		
//		Get Product From Product Model By Id
		var product = productModel.findById(Integer.parseInt(productId));
		
//		Get ShoppingCart Model From Session Secope
		ShoppingCartModel model = (ShoppingCartModel) req.getSession().getAttribute("cart");
		
//		Update Data
		model.changeItemCount(plus,product);
		
//		Forward To My-cart.jsp
		req.getRequestDispatcher("/my-cart.jsp").forward(req, resp);
	}

	private void cartShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//		Forward Index.jsp
		
		req.getRequestDispatcher("/my-cart.jsp").forward(req, resp);
		
	}

	private void cartClear(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		
//		Get Shopping Cart From Session Scope
		
		ShoppingCartModel cart =(ShoppingCartModel) req.getSession().getAttribute("cart");
		
//		Clear Cart
		
		cart.clearCart();
		
//		Forward Index.jsp
		
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
		
	}
	
	private void addToCart(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		
//		Get Product Id From Parameter
		
		String productId = req.getParameter("product");
		
//		Get Product Model
		var productModel =(ProductModel) getBean("productModel", ProductModel.class);
		
//		Get Product From Product Model By Id
		
		Product p = productModel.findById(Integer.parseInt(productId));
		
//		Create Session Scope
		
		var session = req.getSession(true);
		
//		Get Shopping Cart Model From Session Scope
		
		ShoppingCartModel model = (ShoppingCartModel)session.getAttribute("cart");
		
		if(model == null) {
			model = getBean("shoppingCartModel", ShoppingCartModel.class);
			session.setAttribute("cart", model);
		}
		
		model.addSaleItem(p);
		
//		Forward Index.jsp
		
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	
	
}


