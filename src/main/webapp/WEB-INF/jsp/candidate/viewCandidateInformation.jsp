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
	<form action="/filter-candidate" method="get">
	<div class="container p-5 my-5 border">

		<jsp:include page="title.jsp" />
		<jsp:include page="search.jsp" />

		<jsp:include page="tableCandidate.jsp" />


		<div class="row">
			<div class="col-sm-4"><jsp:include page="filterCandidate.jsp" /></div>
		</div>

		<jsp:include page="pagination.jsp" />
	</div>
	</form>
</body>

</html>