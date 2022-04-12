<%@page import="com.jdc.shop.domain.SaleItem"%>
<%@page import="com.jdc.shop.model.ShoppingCartModel"%>
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

		<h2>My Shopping Cart List</h2>
		<table class="table mt-2">
			<thead>
				<tr>
					<th>Id</th>
					<th>Category</th>
					<th>Product Name</th>
					<th>Unit Price</th>
					<th>Total Count</th>
					<th>Amount</th>
				</tr>
			</thead>
			<tbody>
				<%
				ShoppingCartModel cart = (ShoppingCartModel) session.getAttribute("cart");
				%>

				<%
				for (SaleItem item : cart.getAll()) {
				%>
				<tr>
					<td><%=item.getId()%></td>
					<td><%=item.getProduct().getCategory()%></td>
					<td><%=item.getProduct().getName()%></td>
					<td><%=item.getProduct().getPrice()%></td>
					<td><a href="cart-plus?product=<%=item.getProduct().getId()%>">+</a>
						<%=item.getCount()%> <a
						href="cart-minius?product=<%=item.getProduct().getId()%>">-</a></td>
					<td><%=item.getTotalPrice()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<hr />
		<div class="d-flex justify-content-evenly">

			<h3>Total</h3>
			<h4><%=cart.getTotalAmount()%></h4>
		</div>
		<hr />
		<div class="mt-4">
			<h2>Check Out</h2>
			<form action="check-out" method="post">
				<div class="row">
					<div class="col-md-4">
						<div class="input-group mb-3">
							<input type="text" name="customer" class="form-control"
								placeholder="Enter Customer Name"/>
							<button class="btn btn-outline-secondary" type="submit"
								>Check Out</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>