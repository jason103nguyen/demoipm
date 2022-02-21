<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />

 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">


<title>Information Potential Candidates</title>
</head>
<body>


<div class="container mt-4 mb-4 p-3 d-flex justify-content-center">

<div class="logo"><img src="../img/LogoFPT.jpg" alt="logo fpt" width="30%"></div>
	
	<div><h1 class="Title" style="margin-top: 50px ">Info Potential Candidates</h1></div>
</div>


<div class="container mt-5">
    <div class="row d-flex justify-content-center">
        <div class="col-md-7">
            <div class="card p-3 py-4">
                <div class="content">
                
                <div class="group">
               		<span class="icon"><i class="fas fa-id-badge"></i></span>
					<div class="info"><b>ID:</b>  ${candidateDto.id}</div>
					
  				 </div>
  				 
  				 <hr width="100%" color="red" align="left" size="3px">
  				 
  				 <div class="group">
               		<span class="icon"><i class="fas fa-user-circle"></i></span>
					<div class="info"><b>Full Name:</b>  ${candidateDto.fullName}</div>
  				 </div>
  				 
  				  <hr width="100%" color="red" align="left" size="3px">
  				 
  				 <div class="group">
               		<span class="icon"><i class="fas fa-envelope-open-text"></i></span>
					<div class="info"><b>Email:</b> ${candidateDto.email}</div>
  				 </div>
  				 
  				  <hr width="100%" color="red" align="left" size="3px">
  				 
  				 <div class="group">
               		<span class="icon"><i class="fas fa-phone-square"></i></span>
					<div class="info"><b>Phone:</b>  ${candidateDto.phone}</div>
  				 </div>
  				 
  				  <hr width="100%" color="red" align="left" size="3px">
  				 
  				  <div class="group">
               		<span class="icon"><i class="fas fa-chart-line"></i></span>
					<div class="info"><b>Experience Year:</b>  ${candidateDto.experienceYear}</div>
  				 </div>
  				 
  				  <hr width="100%" color="red" align="left" size="3px">
                
                  <div class="group">
               		<span class="icon"><i class="fas fa-edit"></i></span>
					<div class="info"><b>Skill:</b> ${candidateDto.skill}</div>
  				 </div> 
  				 
  				  <hr width="100%" color="red" align="left" size="3px">  
  				 
  				   <div class="group">
               		<span class="icon"><i class="fas fa-wrench"></i></span>
					<div class="info"><b>Activity:</b> ${candidateDto.activity}</div>
  				 </div>
  				 
  				  <hr width="100%" color="red" align="left" size="3px">	
  				 
  				  <div class="group">
               		<span class="icon"><i class="fas fa-thermometer-three-quarters"></i></span>
					<div class="info"><b>Status:</b> ${candidateDto.status}</div>
  				 </div>	
  				 
  				  <hr width="100%" color="red" align="left" size="3px">
  				 
  				  <div class="group">
               		<span class="icon"><i class="fas fa-venus-mars"></i></span>
					<div class="info"><b>Gender:</b> ${candidateDto.sex}</div>
  				 </div>	
  				 
  				  <hr width="100%" color="red" align="left" size="3px">
  				 
  				 <div class="group">
               		<span class="icon"><i class="fas fa-calendar"></i></span>
					<div class="info"><b>Birth Day:</b> ${candidateDto.birthDay}</div>
  				 </div>
  				 
  				  <hr width="100%" color="red" align="left" size="3px">

                    <div class="buttons" style="margin-top: 20px"> <a href="view-potential-candidates-list?pageNo=${pageNo}&keySearch=${keySearch}&sortBy=${sortBy}&direction=${direction }"> <button class="btn btn-outline-primary px-4">Back</button></a> 
                    	
                    	  <a href="update-potential-candidates?id=${candidateDto.id}"> <button class="btn btn-outline-primary px-4">Update</button> </a>
                    
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>