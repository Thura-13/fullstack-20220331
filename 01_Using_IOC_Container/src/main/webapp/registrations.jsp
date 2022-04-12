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
	<div class="container mt-4">
		
		<h2>Using IoC Container</h2>
		
		<h3>Registration Student ${openClass.course.name }</h3>
		
		<div>
			<c:url var="addNew" value="/registration-edit">
				<c:param name="classId" value="${openClass.id }"></c:param>
			</c:url>
			<a href="${addNew }" class="btn btn-primary mt-3">Add Student</a>
		</div>
		
		<c:choose>
			<c:when test="${empty registration }">
				<div class="alert alert-warning mt-4">
				 No student for this class.Please add student.
				</div>
			</c:when>
			<c:otherwise>
				<table class="table">
					
					<thead>
						<tr>
							<th>Student</th>
							<th>Phone</th>
							<th>Email</th>
							<th>Course Name</th>
							<th>Teacher</th>
							<th>Start Date</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var="r" items="${registration }">
							<tr>
								<td>${r.student }</td>
								<td>${r.phone }</td>
								<td>${r.email }</td>
								<td>${r.openClass.course.name}</td>
								<td>${r.openClass.teacher }</td>
								<td>${r.openClass.startDate }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<a href="/">Home</a>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>