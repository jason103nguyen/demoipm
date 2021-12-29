<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="list-group">
	<div class="list-group-item list-group-item-light d-flex justify-content-between fw-bold text-uppercase">
		<sec:authorize access="isAuthenticated()">
			Hello, <sec:authentication property="name"/>
			<a href="${pageContext.request.contextPath}/logout">Logout</a>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">
			Hello, Guest
		</sec:authorize>
	</div>
	<a href="#" class="list-group-item list-group-item-primary">QUẢN LÝ
		ỨNG VIÊN</a> <a href="#"
		class="list-group-item list-group-item-action list-group-item-warning">QUẢN
		LÝ NGÀNH NGHỀ</a> <a href="#"
		class="list-group-item list-group-item-action list-group-item-primary">QUẢN
		LÝ TUYỂN DỤNG</a> <a href="#"
		class="list-group-item list-group-item-action list-group-item-warning">QUẢN
		LÝ KĨ NĂNG</a> <a href="#"
		class="list-group-item list-group-item-action list-group-item-primary">QUẢN
		LÝ QUÁ TRÌNH TUYỂN DỤNG</a> <a href="#"
		class="list-group-item list-group-item-action list-group-item-warning">QUẢN
		LÝ USER</a>
</div>