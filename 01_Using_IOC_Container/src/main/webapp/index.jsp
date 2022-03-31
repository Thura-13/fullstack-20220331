<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
	<div>
		<div class="container mt-4">
		
			<h2>Using IoC Container</h2>
			
			<h3>Courses</h3>
			
			<a href="course-edit" class="btn btn-primary mt-3">Add New</a>
			
			<div class="mt-2">
			
			<c:choose>
				
				<c:when test="${empty data }">
					<div class="alert alert-warning">
						There is no course.Please Create New Course.
					</div>
				</c:when>
				
				<c:otherwise>
					<table class="table">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Duration</th>
						<th>Fees</th>
						<th>Description</th>
					</tr>
				</thead>
				<tbody>
				
				<c:forEach var="c" items="${ data }">
					<tr>
						<td>${c.id }</td>
						<td>${c.name }</td>
						<td>${c.duration } Months</td>
						<td>${c.fees } MMK</td>
						<td>${c.description }</td>
						<td>
							<c:url var="classes" value="/classes">
								<c:param name="courseId" value="${c.id }"></c:param>
							</c:url>
							<a href="${classes }">Open Class</a>
						</td>
						
					</tr>				
				</c:forEach>	
				</tbody>
			</table>
				</c:otherwise>
				
			</c:choose>

			</div>
			
		</div>
	</div>
	
</body>
</html>