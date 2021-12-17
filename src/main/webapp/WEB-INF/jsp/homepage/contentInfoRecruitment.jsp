<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 
<div class="d-flex flex-wrap justify-content-between">
	<div class="card mb-1">
		<div class="card-body">
			<h4 class="card-title">Kỹ sư lập trình nhúng</h4>
			<p class="card-text">Skill: C, C++</p>
			<a href="#" class="card-link">Chi tiết</a>
		</div>
	</div>

	<div class="card mb-1">
		<div class="card-body">
			<h4 class="card-title">Kỹ sư lập trình java web backend</h4>
			<p class="card-text">Skill: SQL, Java, Spring boot...</p>
			<a href="#" class="card-link">Chi tiết</a>
		</div>
	</div>

	<div class="card mb-1">
		<div class="card-body">
			<h4 class="card-title">Kỹ sư lập trình java web backend</h4>
			<p class="card-text">Skill: SQL, Java, Spring boot...</p>
			<a href="#" class="card-link">Chi tiết</a>
		</div>
	</div>

	<div class="card mb-1">
		<div class="card-body">
			<h4 class="card-title">Kỹ sư lập trình java web backend</h4>
			<p class="card-text">Skill: SQL, Java, Spring boot...</p>
			<a href="#" class="card-link">Chi tiết</a>
		</div>
	</div>

</div>
 -->

<div class="d-flex flex-wrap justify-content-between">

	<c:forEach items="${listRecruitment}" var="recruitment">
		<div class="card mb-1">
			<div class="card-body">
				<h4 class="card-title">Ngày ${recruitment.startRecruitment}</h4>
				<p class="card-text">Ngành: ${recruitment.career.name}</p>
				<c:forEach items="${recruitment.listSkill}" var="skill">
					<span>${skill.name}, </span>
				</c:forEach>
				<br/><a href="#" class="card-link">Chi tiết</a>
			</div>
		</div>
	</c:forEach>

</div>
