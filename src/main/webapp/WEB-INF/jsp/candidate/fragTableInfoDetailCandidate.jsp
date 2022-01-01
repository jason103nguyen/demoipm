<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div>

	<table class="table table-dark table-striped text-white">

		<tbody>
			<tr>
				<th>Full Name</th>
				<td>${candidate.fullName}</td>
			</tr>
			
			<tr>
				<th>Email</th>
				<td>${candidate.email}</td>
			</tr>
			
			<tr>
				<th>Phone</th>
				<td>${candidate.phone}</td>
			</tr>
			
			<tr>
				<th>Experience</th>
				<td>${candidate.experienceYear}</td>
			</tr>
			
			<tr>
				<th>Skill</th>
				<td>${candidate.skill}</td>
			</tr>
			
			<tr>
				<th>Activity</th>
				<td>${candidate.activity}</td>
			</tr>
		</tbody>

	</table>
</div>