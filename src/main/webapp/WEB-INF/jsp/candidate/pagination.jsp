<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container mt-3">
	<ul class="pagination justify-content-center">
		<c:forEach var="page" begin="1" end="${totalPage}">
			<li class="page-item"><a class="page-link"
				href="/view-all-candidate/${page-1}">${page}</a></li>
		</c:forEach>
	</ul>
</div>