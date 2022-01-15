<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.demoipm.consts.URLConst" %>
<!DOCTYPE HTML>
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

	<title>Recruitment News</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs5/jq-3.6.0/dt-1.11.3/datatables.min.css"/>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">

	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/v/bs5/jq-3.6.0/dt-1.11.3/datatables.min.js"></script>
</head>

<body class="bg-light">
	<div class="container p-5 my-5 border">
		<div class="row">
			<div class="col-sm-3">
				<jsp:include page="../logoFpt.jsp" />
			</div>

			<div class="col-sm-9 d-flex flex-column align-items-center justify-content-center">
				<p class="h1 text-warning text-center">RECRUITMENT NEWS</p>

				<div id="carousel-career" class="carousel slide w-100" data-bs-ride="carousel">
					<div class="carousel-inner">
						<c:forEach items="${careerList}" var="career" varStatus="count">
							<div class="carousel-item ${count.index == 0 ? 'active' : ""}" data-bs-interval="3000">
								<div class="card bg-warning">
									<div class="card-body text-center">
										<h5 class="card-title text-success"><i class="bi bi-align-end"></i>&nbsp;Top Career&nbsp;<i class="bi bi-align-start"></i></h5>
										<h3 class="card-title text-primary"><i class="bi bi-stars"></i>&nbsp;${career.name}&nbsp;<i class="bi bi-stars"></i></h3>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<button class="carousel-control-prev" type="button" data-bs-target="#carousel-career" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button" data-bs-target="#carousel-career" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
			</div>
		</div>

		<div class="row mt-3">
			<div class="col-sm-3">
				<jsp:include page="../menu.jsp"/>
			</div>
			<div class="col-sm-9">
				<div class="row">
				<div class="col-6">
					<table id="search-result" class="table table-danger table-bordered table-hover">
						<thead>
						<th class="p-0"><input id="search-word" class="form-control rounded-0" class="border-none"
											   type="text" placeholder="Search job or career..."></th>
						</thead>
					</table>
				</div>
				<div class="col-6 bg-white">
					<div class="py-2 border-bottom">
						<h3>
							<span id="recruitment-detail-quantity"></span>
							<span id="recruitment-detail-job"></span>
						</h3>
						<p id="recruitment-detail-career" class="text-secondary"></p>
						<button class="w-100 btn btn-danger">Apply</button>
					</div>
					<div class="py-2 border-bottom">
						<div id="recruitment-detail-skills" class="d-flex">
						</div>
						<div class="text-secondary mt-2">
							<i class="bi bi-cash-coin"></i>&nbsp;
							<span id="recruitment-detail-min"></span>&nbsp;~&nbsp;<span id="recruitment-detail-max"></span>&nbsp;USD
						</div>
						<div class="text-secondary">
							<i class="bi bi-calendar-check"></i>&nbsp;
							<span id="recruitment-detail-start"></span>&nbsp;~&nbsp;<span id="recruitment-detail-end"></span>
						</div>
					</div>
					<div class="py-2">
						<h5>Description</h5>
						<p id="recruitment-detail-career-description" class="text-secondary"></p>
					</div>
				</div>
				</div>
			</div>
		</div>

	</div>

<script>

	let datatable = $('#search-result').removeAttr('width').DataTable({
		serverSide: true,
		ajax: {
			url: '${pageContext.request.contextPath}${URLConst.API_GET_RECRUITMENT_BY_CONDITION_URL}',
			method: 'POST',
			dataSrc: 'data',
			contentType: "application/json",
			data: function (request) {
				request.search.value = $('#search-word').val();
				request.length = 5;
				return JSON.stringify(request);
			}
		},
		ordering: false,
		searching: false,
		lengthChange: false,
		bPaginate: false,
		info: false,
		columnDefs: [
			{
				"width": "100%",
				"targets": 0,
				"render": (data, type, row, meta) => {
					let content = `<div onclick="viewDetail(event, ` + row.id + `)" class="p-2 btn w-100 bg-white">
						<h5 class="text-success">` + row.job + ` (Qty: ` + row.quantity + `)</h5>
						<i class="m-0 text-primary">` + row.career + `</i>
					</div>`;

					if (meta.row === 0) {
						viewDetail(event, row.id);
					}
					return content;
				}
			},
		],
	});

	$('#search-word').on( 'keyup click', function () {
		datatable.search($('#search-word').val()).draw();
	} );

	function viewDetail(event, id) {
		event.preventDefault();
		$.ajax({
			url: "${pageContext.request.contextPath}${URLConst.API_GET_RECRUITMENT_DETAIL_URL}?id=" + id,
			data: {
				format: 'json'
			},
			error: function() {
			},
			success: function(data) {
				$("#recruitment-detail-career").text(data.career);
				$("#recruitment-detail-career-description").text(data.careerDescription);
				$("#recruitment-detail-job").text(data.job);
				$("#recruitment-detail-quantity").text(data.quantity);
				$("#recruitment-detail-min").text(data.minSalary);
				$("#recruitment-detail-max").text(data.maxSalary);
				$("#recruitment-detail-start").text(data.startDate);
				$("#recruitment-detail-end").text(data.endDate);
				$("#recruitment-detail-skills").empty();
				data.skills.forEach(skill => {
					let skillBtn = `<h5 class="me-2 my-0"><span class="badge bg-light text-dark border">` + skill + `</span></h5>`;
					$("#recruitment-detail-skills").append(skillBtn);
				});
			},
			type: 'GET'
		});
	}
</script>
</body>

</html>