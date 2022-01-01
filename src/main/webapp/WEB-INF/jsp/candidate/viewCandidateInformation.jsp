<!DOCTYPE HTML>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<title>Welcome</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
	<form action="/view-all-candidate" method="get">
	<div class="container p-3 my-3 border rounded" style="background-color:#F5F5DC;">
		
		<div class="row">
		
		  <div class="col-sm-8">
		  	<jsp:include page="title.jsp" />
		  	<jsp:include page="search.jsp" />
		  </div>
		  
		  <div class="col-sm-4">
		  	<img src="../img/logoFptNoBackground.png" alt="logo fpt" width="80%">
		  </div>
		</div>

		<jsp:include page="tableCandidate.jsp" />

		<jsp:include page="filterCandidate.jsp" />

		<jsp:include page="pagination.jsp" />
	</div>
	</form>
</body>

</html>