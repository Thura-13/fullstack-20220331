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
	
		<h3>Class For ${course.name }</h3>	
		
	<div>
		<c:url var="addNew" value="/class-edit">
			<c:param name="courseId" value="${course.id }"></c:param>
		</c:url>
		<a href="${addNew }" class="btn btn-primary">Add New Class</a>
	</div>
	
	<c:choose>
		
		<c:when test="${empty classes }">
			<div class="alert alert-warning mt-4">
				There is no class.Please Create New Class.
			</div>
		</c:when>
		
		<c:otherwise>
			<table class="table">
				
				<thead>
					<tr>
						<td>Class Id</td>
						<td>Teacher</td>
						<td>Start Date</td>
						<td>Course Name</td>
						<td>Course Fees</td>
						<td>Course Duration</td>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="c" items="${classes }">
					
						<tr>
							<td>${c.id }</td>
							<td>${c.teacher }</td>
							<td>${c.startDate }</td>
							<td>${c.course.name }</td>
							<td>${c.course.fees } MMK</td>
							<td>${c.course.duration } Months</td>
							<td>
								<c:url var="registrations" value="/registrations">
									<c:param name="classId" value="${c.id }"></c:param>
								</c:url>
								<a href="${ registrations}">Registration Students</a>
							</td>
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