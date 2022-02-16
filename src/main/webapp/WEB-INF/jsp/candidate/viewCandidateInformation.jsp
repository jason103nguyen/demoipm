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

	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>

<body>

	<div class="container p-3 my-3 border rounded" style="background-color:#F5F5DC;">

		<div class="row">

			<div class="col-sm-8">
				<p class="h1 text-warning">LIST OF CANDIDATES</p>

				<!-- Search information candidate -->
				<form action="/view-all-candidate" method="get">
					<div class="input-group mb-3 w-100">
						<input type="text" class="form-control" placeholder="Search name candidate" name="content"
							value="${candidateFilter.content}"></input>
						<button class="btn btn-success bi bi-search" type="submit"> Search</button>
					</div>
				</form>
			</div>

			<div class="col-sm-4">
				<img src="../img/logoFptNoBackground.png" alt="logo fpt" width="80%">
			</div>
		</div>

		<jsp:include page="tableCandidate.jsp" />

		<!-- Filter Candidate -->
		<form action="/view-all-candidate" method="get">
			<div class="container border-secondary rounded" style="background-color: #D2B48C">
				<div class="row">

					<!-- Filter by age -->
					<div class="col-sm-5">
						<div class="d-flex">
							<div class="p-2"><label for="minAge" class="form-label">From Age</label></div>
							<div class="p-2"><input type="text" class="form-control" name="minAge"
									value="${candidateFilter.minAge}" id="minAge"></input></div>
							<div class="p-2"><label for="maxAge" class="form-label">To </label></div>
							<div class="p-2"><input type="text" class="form-control" name="maxAge"
									value="${candidateFilter.maxAge}" id="maxAge"></input></div>
						</div>
					</div>

					<!-- Filter by skill -->
					<div class="col-sm-6">
						<div class="d-flex flex-wrap">
							<c:forEach items="${listSkill}" var="skill">
								<div class="p-4 py-1 form-check">
									<input type=checkbox class="form-check-input" id="check1" name="listSkills"
										value="${skill.id}" <c:if
										test="${candidateFilter.listSkills.contains(skill.id)}">
									checked
									</c:if>

									/>
									<label class="form-check-label">${skill.name}</label>
								</div>
							</c:forEach>
						</div>
					</div>

					<!-- Button apply filter -->
					<div class="col-sm-1 pt-3" align="center">
						<button class="btn btn-success bi bi-funnel" type="submit">Filter</button>
						<input type="hidden" name="content" value="${candidateFilter.content}"></input>
					</div>
				</div>
			</div>
		</form>

		<!--Pagination-->
		<div class="container mt-3">
			<ul class="pagination justify-content-center">
				<c:forEach var="page" begin="1" end="${totalPage}">
					<li class="page-item"><a class="page-link" href="/view-all-candidate?
						page=${page - 1}
						&content=${candidateFilter.content}
						&maxAge=${candidateFilter.maxAge}
						&minAge=${candidateFilter.minAge}
						<c:forEach items=" ${candidateFilter.listSkills}" var="skillPage">
							&listSkills=${skillPage}
						</c:forEach>
						">${page}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>

</html>