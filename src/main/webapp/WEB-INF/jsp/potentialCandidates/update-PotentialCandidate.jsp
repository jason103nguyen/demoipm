<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>Insert title here</title>

<style type="text/css">

.error{
	color: red;
}


</style>
</head>
<body>
<h1>add new potential candidate</h1>

	<form:form action="update-potential-candidates" modelAttribute="candidateDto" method="post" >
		
		<input value="${candidateDto.id}" disabled="disabled">
	
		ID<form:input type="text" path="id" id="id"  disabled="disabled"/> <form:errors path="id" cssClass="error"/><br/>
		
		User_Name<form:input type="text" path="fullName" id="fullName" /> <form:errors path="fullName" cssClass="error"/><br/>
		
		phone<form:input type="number" path="phone" id="phone" /> <form:errors path="phone" cssClass="error"/><br/>
		
		email<form:input type="email" path="email" id="email"/> <form:errors path="email" cssClass="error"/><br/>
		
		birthDay<form:input type="date" path="birthDay" id="birthDay"/> <form:errors path="birthDay" cssClass="error"/><br/>
		
		Status<form:input type="text" path="status" id="status"/> <form:errors path="status" cssClass="error"/><br/>
		
		GENDER<form:input type="text" path="sex" id="sex"/> <form:errors path="sex" cssClass="error"/><br/>
		
		cmnd<form:input type="text" path="cmnd" id="cmnd"/> <form:errors path="cmnd" cssClass="error"/><br/>
		
		activity<form:input type="text" path="activity" id="activity"/> <form:errors path="activity" cssClass="error"/><br/>
		
		experienceYear<form:input type="number" path="experienceYear" id="experienceYear"/> 
		<form:errors path="experienceYear" cssClass="error"/><br/>
		
		skill<form:input type="text" path="skill" id="skill"/> <form:errors path="skill" cssClass="error"/><br/>
		
		<input type="submit" value="submit" />
		
	</form:form>

</body>
</html>