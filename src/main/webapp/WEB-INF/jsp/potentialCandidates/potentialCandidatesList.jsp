<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>List Potential Candidates</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
            
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/StyleList.css">        
            
</head>
<body>

<div class="container p-5 my-5 border">
    <div class="row">
        <div class="col-sm-3">
            <jsp:include page="../logoFpt.jsp" />
        </div>

        <div class="col-sm-9">
            <p class="h1 text-warning text-center">MANAGE POTENTIAL CANDIDATES</p>
            <div class="search">
            	
            	<div class="SelectSort">
	            	<form:form action="#" method="get">
		           		 Sorted By	<select><option>A - Z</option><option>Z - A</option></select>
					</form:form>	
            	</div>
            	
				<div class="FlexSearch">
	           		 <form:form action="view-potential-candidates-list" method="get"> 
	           		 	<input name="keySearch" type="text" placeholder="Search ..." class="FilterSearch">		
						<button type="submit" class="ButtonSearch">Search</button>
					</form:form>	
					
				</div>	
            </div>  
            
            <form:form action="#" method="get"> 					
				<button type="submit" class="ButtonAddNew">Add New</button> 	
			</form:form>   
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-sm-3">
            <jsp:include page="../menu.jsp" />
        </div>
        <div class="col-sm-9">
            <c:if test="${response == null || !response.hasError()}">
           <div class="ReponTable">
            <table class="table">
				  <thead>
				    <tr>
						<td>No.</td>
						<td>Full Name</td>
						<td>National Identity Card</td>
						<td>Phone</td>
						<td>Email</td>
						<td>Info</td>
						<td>Interview</td>
						<td>Status</td>
						<td>Activity</td>
					</tr>
				  </thead>
				   <c:forEach var="listCandidateDto" items="${listCandidateDto }">
				  <tbody>
				    <tr>
				      <th scope="row">${listCandidateDto.id }</th>
				      <td>${listCandidateDto.fullName }</td>
						<td>${listCandidateDto.cmnd }</td>
						<td>${listCandidateDto.phone }</td>
						<td>${listCandidateDto.email }</td>
						<td>
										
							<a href= "view-potential-candidates-info?id=${listCandidateDto.id }">
								<button type="submit" class="ButtonInfo"> Info </button> 
							</a>	
							
						</td>
						<td>
							<form:form action="#" method="get"> 					
								<button type="submit" class="ButtonInfo">Interview</button> 	
							</form:form>
						</td>
						<td>${listCandidateDto.status }</td>
						<td>
							<form:form action="#" method="get"> 					
								<button type="submit" class="ButtonDelete">Delete</button> 	
							</form:form>
							
							<form:form action="#" method="get"> 
								<button type="submit" class="ButtonUpdate">Update</button> 
							</form:form>
						</td>
				    </tr>
				  </tbody>
				 </c:forEach>
			</table>
			</div>	
			<nav aria-label="Page navigation example">
		  		<ul class="pagination">
		    		<li class="page-item"><a class="page-link" href="#">Previous</a></li>
		   			<li class="page-item"><a class="page-link" href="#">1</a></li>
				    <li class="page-item"><a class="page-link" href="#">2</a></li>
				    <li class="page-item"><a class="page-link" href="#">3</a></li>
				    <li class="page-item"><a class="page-link" href="#">Next</a></li>
		  		</ul>
		</nav>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>