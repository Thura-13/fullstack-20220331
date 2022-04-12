<%@page import="com.jdc.shop.model.ShoppingCartModel"%>
<%@page import="com.jdc.shop.domain.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
	integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
	integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
	crossorigin="anonymous"></script>

</head>
<body>
	<div class="container mt-4">
		<h3>Products</h3>



		<div class="row">
			<div class="col-2">
				<a href="/product-edit" class="btn btn-primary mt-1">Add New</a>
			</div>
			
			<div class="col-2">
				<a href="sale-history">Sale History</a>
			</div>
			
			<div class="col-2">
				<%
				ShoppingCartModel cart = (ShoppingCartModel) session.getAttribute("cart");
				%>

				<p>
				
				<%
				if (cart != null && cart.getTotalCount() > 0) {
				%>
					<a href="/cart-show">Shopping Cart : <%=cart == null ? 0 : cart.getTotalCount()%></a>
				<%
				}
				%>
				</p>
			</div>
			
			<div class="col-2">
				<%
				if (cart != null && cart.getTotalCount() > 0) {
				%>
				<a href="/cart-clear">Clear Cart</a>
				<%
				}
				%>
			</div>

		</div>

		<%
		List<Product> productList = (List<Product>) application.getAttribute("products");
		%>

		<%
		if (productList.isEmpty()) {
		%>
		<div class="alert alert-warning mt-4">No Data.Please Add New Product
		</div>
		<%
		} else {
		%>
		<table class="table mt-2">
			<thead>
				<tr>
					<th>Id</th>
					<th>Category</th>
					<th>Product Name</th>
					<th>Price</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>

				<%
				for (Product p : productList) {
				%>
				<tr>
					<td><%=p.getId()%></td>
					<td><%=p.getCategory()%></td>
					<td><%=p.getName()%></td>
					<td><%=p.getPrice()%></td>
					<td><a href="/add-to-cart?product=<%=p.getId()%>">Add To
							Cart</a></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<%
		}
		%>

	</div>
</body>
</html>