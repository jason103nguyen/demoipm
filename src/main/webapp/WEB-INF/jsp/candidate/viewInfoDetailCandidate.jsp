<!DOCTYPE HTML>
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@page contentType="text/html" pageEncoding="UTF-8" %>
	<title>Welcome</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>

	<!-- Bootstrap Icon -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
</head>

<body>
	<div class="container p-3 my-3 border rounded" style="background-color:#F5F5DC;">

		<div class="row">
			<div class="col-sm-8">
				<p class="h1 text-warning">CANDIDATE INFORMATION</p>
			</div>
			<div class="col-sm-4" align="right">
				<form action="/view-all-candidate" method="get">
					<button type="submit" class="btn btn-success bi bi-arrow-left-circle-fill"> Back to</button>
				</form>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-6">
				<table class="table table-striped">

					<tbody>
						<tr>
							<th>Full Name</th>
							<td>${candidate.fullName}</td>
						</tr>

						<tr>
							<th>Email</th>
							<td>${candidate.email}</td>
						</tr>

						<tr>
							<th>Phone</th>
							<td>${candidate.phone}</td>
						</tr>

						<tr>
							<th>Experience (year)</th>
							<td>${candidate.experienceYear}</td>
						</tr>

						<tr>
							<th>Skill</th>
							<td>${candidate.skill}</td>
						</tr>

						<tr>
							<th>Activity</th>
							<td>${candidate.activity}</td>
						</tr>
					</tbody>

				</table>
			</div>

			<div class="col-sm-6">
				<div class="row">
					<c:forEach items="${candidate.listInterview}" var="interview">

						<div class="col">
							<div>
								<b>Round:</b> ${interview.round} <br /> <b>Result:</b> ${interview.result} <br />
								<b>Note:</b>
								<div class="container border rounded" style="width: 100%; height: 200px;">
									${interview.note}
								</div>
							</div>
						</div>

					</c:forEach>
				</div>
			</div>
		</div>

	</div>
</body>

</html>