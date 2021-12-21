<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>STT</th>
				<th>Họ Và Tên</th>
				<th>Vòng</th>
				<th>Ngày giờ phỏng vấn</th>
				<th>Phỏng vấn</th>
				<th>Thông tin</th>
				<th>Trạng thái</th>
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
										<td class="pb-0"><form:form action="#" method="get">
												<button type="submit" name="" value=""
													class="btn btn-warning btn-sm">Chọn</button>
											</form:form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>

					<td><form:form action="#" method="get">
							<button type="submit" name="" value=""
								class="btn btn-warning m-1">Chi tiết</button>
						</form:form></td>
						
					<td>${candidate.status}</td>
				</tr>
				<%
				count++;
				%>
			</c:forEach>
		</tbody>

	</table>
</div>