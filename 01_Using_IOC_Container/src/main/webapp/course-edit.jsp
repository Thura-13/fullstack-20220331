<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
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
	<div class="container mt-3">
		<h3>Add New Course</h3>

		<c:url var="save" value="/course-save"></c:url>
		<form action="${save }" method="post">
			<div class="mt-2">

			<div class="row">
				<div class="col-6">
					<label for="courseName">Course Name</label>
					<input type="text" id="courseName" name="courseName" class="form-control">
				</div>
			</div>

			<div class="row mt-3">
				<div class="col-3">
					<label for="duration">Course Duration</label>
					<input type="number" id="duration" name="duration" class="form-control" >
				</div>
				<div class="col-3">
					<label for="fees">Course Fees</label>
					<input type="text" id="fees" name="fees" class="form-control">
				</div>
			</div>
			
			<div class="row mt-3">
				<div class="col-6">
					<label for="description">Course Description</label>
					<textarea rows="4" id="description" name="description" class="form-control"></textarea>
				</div>
			</div>
			
			<button type="submit" class="btn btn-success mt-4">Save Course</button>
		</div>
		</form>
	</div>
</body>
</html>