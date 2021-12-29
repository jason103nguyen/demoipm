<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

<title>Info Potential Candidates</title>
</head>
<body>
<div class="header">

	<div class="logo"><img src="../img/LogoFPT.jpg" alt="logo fpt" width="50%"></div>
	
	<div><h1 class="Title">Info Potential Candidates</h1></div>
</div>
<a href="view-potential-candidates-list"><button style="margin-left: 100px; margin: 30px;" >Back</button> </a>

<div class="InfoCandidate">

	<div class="GroupInfo">
		<div class="flexInfo"><b>No.</b></div>
		<div class="value">${candidateDto.id}</div>
	</div>
	
	<div class="GroupInfo">
		<div class="flexInfo"><b>Name</b></div>
		<div class="value">${candidateDto.fullName}</div>
	</div>
	
	<div class="GroupInfo">
		<div class="flexInfo"><b>Email</b></div>
		<div class="value">${candidateDto.email}</div>
	</div>
	
	<div class="GroupInfo">
		<div class="flexInfo"><b>Phone</b></div>
		<div class="value">${candidateDto.phone}</div>
	</div>
	
	<div class="GroupInfo">
		<div class="flexInfo"><b>Experience</b></div>
		<div class="value">1</div>
	</div>
	
	<div class="GroupInfo">
		<div class="flexInfo"><b>Skill</b></div>
		<div class="value">Java</div>
	</div>
	
	<div class="GroupInfo">
		<div class="flexInfo"><b>Activity</b></div>
		<div class="value">2</div>
	</div>

</div>
</body>
</html>