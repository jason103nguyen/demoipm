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
	<div class="container p-5 my-5 border">
		<div class="row">
			<div class="col-sm-3">
				<jsp:include page="../logoFpt.jsp" />
			</div>

			<div class="col-sm-9">
				<jsp:include page="titleInfoRecruitment.jsp" />
				<div>
					<jsp:include page="search.jsp" />
				</div>
			</div>
		</div>

		<div class="row mt-3">
			<div class="col-sm-3">
				<jsp:include page="../menu.jsp" />
			</div>

			<div class="col-sm-9">
				<jsp:include page="contentInfoRecruitment.jsp" />
			</div>
		</div>

	</div>
</body>

</html>