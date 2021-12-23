<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container border">

	<form action="/filter-candidate-skill" method="get">
		<p>
			<u><strong>Chọn skill</strong></u>
		</p>
		<div class="d-flex flex-wrap">
			<c:forEach items="${listSkill}" var="skill">
				<div class="p-4 py-1 form-check">
					<input class="form-check-input" type="checkbox" id="check1"
						name="skillId" value="${skill.id}"> <label
						class="form-check-label">${skill.name}</label>
				</div>
			</c:forEach>
			<div class="p-2 form-check">
				<button type="submit" class="btn btn-primary mt-2">Áp dụng</button>
			</div>

		</div>
	</form>

</div>