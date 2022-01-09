<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div>

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