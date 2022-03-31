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
		
		<h3>Registration Student</h3>
		
		<c:url var="saveStudent" value="/registration-save">
			<c:param name="classId" value="${openClass.id}"></c:param>
		</c:url>
		<form action="${saveStudent }" method="post">
			<div class="row">
				<div class="col-6">
					<label for="studentName">Student Name</label>
					<input type="text" id="studentName" name="studentName" placeholder="Enter Student Name" class="form-control" />
				</div>
			</div>
			<div class="row mt-3">
				<div class="col-6">
					<label for="phone">Phone</label>
					<input type="text" id="phone" name="phone" placeholder="Enter Student Phone" class="form-control" />
				</div>
			</div>
			<div class="row mt-3">
				<div class="col-6">
					<label for="email">Email</label>
					<input type="text" id="email" name="email" placeholder="Enter Student Email" class="form-control" />
				</div>
			</div>
			<div class="mt-4">
				<button type="submit" class="btn btn-primary">Save Student</button>
			</div>
		</form>
	</div>
</body>
</html>