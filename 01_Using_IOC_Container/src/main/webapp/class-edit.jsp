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
	
		<h3>Add Class For ${course.name }</h3>	
		
		<c:url var="save" value="/class-save">
			<c:param name="courseId" value="${course.id }"></c:param>
		</c:url>
		
		
		<form action="${save }" method="post">
		
		<div class="row">
			<div class="col-4">
				<label for="">Course Id</label>
				<input type="text" value="${course.id }" class="form-control" readonly="readonly" />
			</div>
		</div>
		<div class="row">
			<div class="col-4">
				<label for="">Course Name</label>
				<input type="text" value="${course.name }" class="form-control" readonly="readonly" />
			</div>
		</div>
			<div class="row">
			<div class="col-4">
				<label for="teacherName">Teacher Name</label>	
				<input type="text" name="teacherName" class="form-control" />
			</div>
		</div>
		
		<div class="row mt-3">
			<div class="col-4">
				<label for="startDate">StartDate</label>	
				<input type="date" name="startDate" class="form-control" />
			</div>
		</div>
		
		<button type="submit" class="btn btn-primary mt-4">Save Class</button>
		</form>
		
		
		
		
	</div>
</body>
</html>