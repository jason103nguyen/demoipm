<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container mt-3">
	<ul class="pagination justify-content-center">
		<c:forEach var="page" begin="1" end="${totalPage}">
			<button type=submit name="page" value="${page-1}" class="btn btn-success">${page}</button>
		</c:forEach>
	</ul>
	<p></p>
</div>