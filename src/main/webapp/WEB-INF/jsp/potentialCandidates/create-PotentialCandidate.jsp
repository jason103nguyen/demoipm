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

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_add_update_potentialCandidates.css"> 

</head>
<body>

	<div class="header">	
			<div class="col-sm-3" style="margin: 20px"><jsp:include page="../logoFpt.jsp" /></div>
			
			<div><h1 class="Title" style="margin-top: 10px; color: #339966;">CREATE POTENTIAL CANDIDATE</h1></div>		
	</div>

<div class="signup-form">

	<form:form action="add-new-potential-candidates" modelAttribute="candidateDto" method="post">

		<p style="color:#333;">Please fill in this form to potential candidate!</p>
			
		<hr>
		
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
				<form:select class="form-control" path="status" >
					<form:option value="Close">Close</form:option>
					<form:option value="Open">Open</form:option>
					<form:option value="Inprocess">Inprocess</form:option>
				</form:select>
				<form:errors path="status" cssClass="error"/><br/>
			</div>
        </div>
        
        <div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"><i class="fas fa-venus-mars"></i></span>	
				<form:select class="form-control" path="sex" >
					<form:option value="Male">Male</form:option>
					<form:option value="Female">Female</form:option>
				</form:select>
				<form:errors path="sex" cssClass="error"/><br/>
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
				<form:select class="form-control" path="activity" >
					<form:option value="Running">Running</form:option>
					<form:option value="Pause">Pause</form:option>
					<form:option value="Stop">Stop</form:option>
				</form:select>
				<form:errors path="activity" cssClass="error"/><br/>
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
				<form:select class="form-control" path="skill" >
					<form:option value="Java">Java</form:option>
					<form:option value="C">C</form:option>
					<form:option value="Python">Python</form:option>
					<form:option value="JavaScript">JavaScript</form:option>
					<form:option value="PHP">PHP</form:option>
					<form:option value="Swift">Swift</form:option>
					<form:option value="C#">C#</form:option>
					<form:option value="Ruby">Ruby</form:option>
					<form:option value="Objective-C">Objective-C</form:option>
				</form:select>
				<form:errors path="skill" cssClass="error"/><br/>
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