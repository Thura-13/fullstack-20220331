<%@page import="com.jdc.shop.domain.SaleItem"%>
<%@page import="com.jdc.shop.domain.Voucher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

</head>
<body>
	<div class="container mt-4">
		<h2>Sale Detail</h2>
		
		<% Voucher v = (Voucher) request.getAttribute("voucher"); %>
		
		<table class="table">
		
		<tr>
				<td>Id</td>
				<td><%=v.getId() %></td>
			</tr>
			<tr>
				<td>Customer</td>
				<td><%=v.getCustomer() %></td>
			</tr>
			<tr>
				<td>Sale Date</td>
				<td><%=v.getSaleDateTime() %></td>
			</tr>
		</table>
		
		<h2>Sale Item</h2>
		
		<table class="table">
			<thead>
				<tr>
					<th>Category</th>
				<th>Product</th>
				<th>Unit Price</th>
				<th>Item Count</th>
				<th>Total Amount</th>
				</tr>
			</thead>
			
			<tbody>
				<% for(SaleItem i : v.getSaleItems()){ 
				%>
				
				<tr>
					<td><%=i.getProduct().getCategory() %></td>
					<td><%=i.getProduct().getName() %></td>
					<td><%=i.getProduct().getPrice()%></td>
					<td><%=i.getCount() %></td>
					<td><%=i.totalAmount()%></td>
				</tr>
				<%
				
				}
				%>
			</tbody>
		</table>
		<a href="/index.jsp">Home</a>
	</div>
</body>
</html>