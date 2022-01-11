<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>No</th>
				<th>Full Name</th>
				<th>Round</th>
				<th>Time Interview</th>
				<th>Interviewer</th>
				<th>Interview</th>
				<th>Information</th>
				<th>Status</th>
			</tr>
		</thead>

		<tbody>

			<%
			int count = 1;
			%>
			<c:forEach items="${listCandidate}" var="candidate">
				<tr>
					<td><%=count%></td>
					<td>${candidate.fullName}</td>
					<td>
						<table class="table">
							<tbody>
								<c:forEach items="${candidate.listInterview}" var="interview">
									<tr>
										<td>${interview.round}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>

					<td>
						<table class="table">
							<tbody>
								<c:forEach items="${candidate.listInterview}" var="interview">
									<tr>
										<td>${interview.timeInterview}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>

					<td>
						<table class="table">
							<tbody>
								<c:forEach items="${candidate.listInterview}" var="interview">
									<tr>
										<td>${interview.nameInterviewer}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>

					<td>
						<table class="table">
							<tbody>
								<c:forEach items="${candidate.listInterview}" var="interview">
									<tr>
										<td class="pb-0">
											<form:form action="/report-interview/${interview.id}" method="get">
												<button type="submit" class="btn btn-warning btn-sm">Choose</button>
											</form:form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>

					<td>
						<form:form action="/view-candidate-information/${candidate.id}" method="get">
							<button type="submit" class="btn btn-warning m-1 bi bi-ticket-detailed">  Detail</button>
						</form:form>
					</td>

					<td>${candidate.status}</td>
				</tr>
				<%
				count++;
				%>
			</c:forEach>

		</tbody>

	</table>

	<c:if test="${empty listCandidate}">
		<td>There is no candidate</td>
	</c:if>
</div>