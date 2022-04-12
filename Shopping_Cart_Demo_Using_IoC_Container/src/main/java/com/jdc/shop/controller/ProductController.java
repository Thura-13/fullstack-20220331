package com.jdc.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;

import com.jdc.shop.domain.Product;
import com.jdc.shop.listener.SpringContainerManager;
import com.jdc.shop.model.ProductModel;

@WebServlet({ "/products", "/product-save", "/product-edit" })
public class ProductController extends AbstractHttpServletController {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		var page = switch (req.getServletPath()) {
		case "/product-edit" -> "/product-edit.jsp";
		default -> {

				var productModel = (ProductModel) getBean("productModel", ProductModel.class);
				getServletContext().setAttribute("products", productModel.getAll());
			
				yield "/index.jsp";
		}
		};

		getServletContext().getRequestDispatcher(page).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		Get Data From Parameter
		String category = req.getParameter("category");
		String name = req.getParameter("name");
		int price = Integer.parseInt(req.getParameter("price"));

		var model = (ProductModel) getBean("productModel", ProductModel.class);
	
//		Create New Product And Save Product

			model.saveProduct(new Product(category, name, price));

//				Redirect Index.jsp

			resp.sendRedirect("/products");
		}

	}

