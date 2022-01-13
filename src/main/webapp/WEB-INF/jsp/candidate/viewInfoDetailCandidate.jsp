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
	<div class="container p-3 my-3 border rounded" style="background-color:#F5F5DC;">
		<jsp:include page="fragTitleInfoDetailCandidate.jsp" />
		<jsp:include page="fragTableInfoDetailCandidate.jsp" />
		
	</div>
</body>

</html>