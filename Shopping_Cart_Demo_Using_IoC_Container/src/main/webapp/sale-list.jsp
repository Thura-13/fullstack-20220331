<%@page import="com.jdc.shop.domain.SaleItem"%>
<%@page import="java.util.List"%>
<%@page import="com.jdc.shop.domain.Voucher"%>
<%@page import="com.jdc.shop.model.SaleModel"%>
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
		<h2>Sale History</h2>

		<%
		List<Voucher> list = (List<Voucher>) request.getAttribute("list");
		%>

		<%
		if (list.isEmpty()) {
		%>
		<div class="alert alert-warning mt-4">No Data</div>

		<%
		} else {
		%>
		<table class="table">
			<thead>
				<tr>
					<th>Voucher Id</th>
					<th>Customer</th>
					<th>Sale Date</th>
					<th>Item Count</th>
					<th>Total Amount</th>
					<td>Action</td>
				</tr>
			</thead>
			<tbody>
				<%
				for (Voucher v : list) {
				%>
				<tr>
					<td><%=v.getId()%></td>
					<td><%=v.getCustomer()%></td>
					<td><%=v.getSaleDateTime()%></td>
					<td><%=v.totalItemCount()%></td>
					<td><%=v.totalAmount()%></td>
					<td><a href="sale-detail?id=<%=v.getId()%>">Sale Detail</a></td>
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