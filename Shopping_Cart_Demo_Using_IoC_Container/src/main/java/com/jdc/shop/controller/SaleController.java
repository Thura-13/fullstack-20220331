package com.jdc.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdc.shop.domain.SaleItem;
import com.jdc.shop.model.SaleModel;
import com.jdc.shop.model.ShoppingCartModel;

@WebServlet({ "/check-out","/sale-history","/sale-detail" })
public class SaleController extends AbstractHttpServletController {

	private static final long serialVersionUID = 1L;
	
	private SaleModel saleModel;
	
	@Override
	public void init() throws ServletException {
		saleModel = getBean("saleModel", SaleModel.class);
		getServletContext().setAttribute("saleModel", saleModel);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case "/sale-history" -> showSaleHistory(req,resp);
		case "/sale-detail" -> showSaleDetail(req,resp);
		}
	}
	


	private void showSaleDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		
		var model = (SaleModel) getServletContext().getAttribute("saleModel");
		
		var voucher = model.findById(Integer.parseInt(id));
		
		req.setAttribute("voucher",voucher);
		
		getServletContext().getRequestDispatcher("/sale-detail.jsp").forward(req, resp);
	}

	private void showSaleHistory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var model =(SaleModel) getServletContext().getAttribute("saleModel");
		
		var list = model.getSaleHistoryList();
		
		req.setAttribute("list", list);
		
		getServletContext().getRequestDispatcher("/sale-list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		Get Customer From Parameter
		String customer = req.getParameter("customer");

//		Get Shopping Cart Model From Session Scope
		ShoppingCartModel model = (ShoppingCartModel) req.getSession().getAttribute("cart");

//		Get SaleItem List
		var saleItemList = model.getAll();

//		Create Voucher
		int id = saleModel.createVoucher(customer, saleItemList);

//		Clear Shopping Cart
		model.clearCart();
		req.getSession().removeAttribute("cart");

//		Redirect Sale Detail jsp
		resp.sendRedirect(getServletContext().getContextPath().concat("/sale-detail?id="+id));
	}

}
