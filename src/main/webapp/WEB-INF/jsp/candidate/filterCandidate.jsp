<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container border">
		<p>
			<u><strong>Chọn tuổi</strong></u>
		</p>
		<div class="row pb-2">
			<div class="col-sm-1">
				<label for="minAge" class="form-label">Từ</label>
			</div>
			<div class="col-sm-3">
				<input type="text" class="form-control" name="minAge" id="minAge">
			</div>
			<div class="col-sm-1">
				<label for="maxAge" class="form-label">Đến</label>
			</div>
			<div class="col-sm-3">
				<input type="text" class="form-control" name="maxAge" id="maxAge">
			</div>

		</div>

		<hr />
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

		</div>
</div>