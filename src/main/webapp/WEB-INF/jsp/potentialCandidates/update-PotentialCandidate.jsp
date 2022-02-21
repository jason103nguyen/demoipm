<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>Insert title here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />



<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_add_update_potentialCandidates.css"> 

</head>
<body>

<div class="header">	
			<div class="logo"><img src="../img/LogoFPT.jpg" alt="logo fpt" width="40%"></div>
			
			<div><h1 class="Title" style="color: #00DD00">UPDATE POTENTIAL CANDIDATE</h1></div>		
	</div>

<div class="signup-form">

	<form:form action="update-potential-candidates" modelAttribute="candidateDto" method="post">

		<p style="color:#333;">Please fill in this form to potential candidate!</p>
			
		<hr>
		
		
		<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"><i class="fas fa-user-circle"></i></span>
				<form:input type="text" path="id" id="id"  disabled="disabled" class="form-control"/> <form:errors path="id" cssClass="error"/><br/>
			</div>
        </div>
		
		<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"><i class="fas fa-user-circle"></i></span>
				<form:input type="text" path="fullName" placeholder="Full Name..." class="form-control"/> <form:errors path="fullName" cssClass="error"/><br/>
			</div>
        </div>
 
        <div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"><i class="fas fa-phone-square"></i></span>
				<form:input type="number" path="phone" class="form-control" placeholder="Phone..."/> <form:errors path="phone" cssClass="error"/><br/>
			</div>
        </div>
        
        <div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"><i class="fas fa-envelope-open-text"></i></span>
				<form:input type="email" path="email" class="form-control" placeholder="Email..."/> <form:errors path="email" cssClass="error"/><br/>
			</div>
        </div>
        
		<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"><i class="fas fa-calendar"></i></span>
				<form:input type="date" path="birthDay" class="form-control"/> <form:errors path="birthDay" cssClass="error"/><br/>
			</div>
        </div>
		
		<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"><i class="fas fa-thermometer-three-quarters"></i></span>
				<form:input type="text" path="status" class="form-control" placeholder="Status..."/> <form:errors path="status" cssClass="error"/><br/>
			</div>
        </div>
        
        <div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"><i class="fas fa-venus-mars"></i></span>
				<form:input type="text" path="sex" class="form-control" placeholder="Gender..."/> <form:errors path="sex" cssClass="error"/><br/>
			</div>
        </div>
        
        <div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"><i class="fas fa-id-badge"></i></span>
				<form:input type="text" path="cmnd" class="form-control" placeholder="National Identity Card..."/> <form:errors path="cmnd" cssClass="error"/><br/>
			</div>
        </div>
        
        <div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"><i class="fas fa-wrench"></i></span>
				<form:input type="text" path="activity" class="form-control" placeholder="Activity..."/> <form:errors path="activity" cssClass="error"/><br/>
			</div>
        </div>
        
         <div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"><i class="fas fa-chart-line"></i></span>
				<form:input type="number" path="experienceYear" class="form-control" placeholder="Experience Year..."/> <form:errors path="experienceYear" cssClass="error"/><br/>
			</div>
        </div>

		<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"><i class="fas fa-edit"></i></span>
				<form:input type="text" path="skill" class="form-control" placeholder="Skill..."/> <form:errors path="skill" cssClass="error"/><br/>
			</div>
        </div>
		
		<div class="form-group">
			<button type="submit" value="submit" class="btn btn-primary btn-lg">Submit</button> 
			<a href="view-potential-candidates-list?pageNo=${pageNo}&keySearch=${keySearch}&sortBy=${sortBy}&direction=${direction }" class="btn btn-primary btn-lg" style="float: right;"> Back</a>
		</div>
			
	</form:form>
</div>
</body>
</html>