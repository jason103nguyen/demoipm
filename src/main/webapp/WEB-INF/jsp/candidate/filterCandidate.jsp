<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<div class="row">
		<div class="col">
			<div class="d-flex">
				<div class="p-2"><label for="minAge" class="form-label">Tuổi từ</label></div>
				<div class="p-2"><input type="text" class="form-control" name="minAge" id="minAge"></div>
				<div class="p-2"><label for="maxAge" class="form-label">Đến </label></div>
				<div class="p-2"><input type="text" class="form-control" name="maxAge" id="maxAge"></div>
			</div>
		</div>
		
		<div class="col">
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
	</div>
</div>