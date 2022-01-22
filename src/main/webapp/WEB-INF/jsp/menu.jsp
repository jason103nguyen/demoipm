<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="list-group list-group-flush">
	<div class="m-0 h5 list-group-item list-group-item-info py-3 px-4 d-flex flex-xl-row flex-column align-items-center justify-content-between">
		<span class="text-center">
			Hello,&nbsp;
			<sec:authorize access="isAuthenticated()">
				<span id="username"><sec:authentication property="name"/></span>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				Guest
			</sec:authorize>
		</span>
		<sec:authorize access="isAuthenticated()">
			<span><a class="btn btn-danger btn-sm" onclick="logoutUser(event)">Logout</a></span>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">
			<span><a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/login">Login</a></span>
		</sec:authorize>
	</div>
	<a class="list-group-item list-group-item-action list-group-item-light py-3 px-4"><i class="me-2 bi bi-person-square"></i>Candidate</a>
	<a class="list-group-item list-group-item-action list-group-item-light py-3 px-4"><i class="me-2 bi bi-signpost-2-fill"></i>Career</a>
	<sec:authorize access="hasRole('ROLE_HR')">
		<a class="list-group-item list-group-item-action list-group-item-light py-3 px-4" href="${pageContext.request.contextPath}/manage-recruitment"><i class="me-2 bi bi-binoculars"></i>Recruitment</a>
	</sec:authorize>
	<a class="list-group-item list-group-item-action list-group-item-light py-3 px-4"><i class="me-2 bi bi-hammer"></i>Skill</a>
	<a class="list-group-item list-group-item-action list-group-item-light py-3 px-4"><i class="me-2 bi bi-envelope-check"></i>Interview</a>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<a class="list-group-item list-group-item-action list-group-item-light py-3 px-4" href="${pageContext.request.contextPath}/manage-user"><i class="me-2 bi bi-people"></i>User</a>
	</sec:authorize>

	<script>
		function logoutUser(event) {
			event.preventDefault();
			Swal.fire({
				title: 'Do you want to logout?',
				showDenyButton: true,
				confirmButtonText: 'Yes',
			}).then((result) => {
				if (result.isConfirmed) {
					window.location.href = "${pageContext.request.contextPath}/logout";
				}
			})
		}
	</script>
</div>
