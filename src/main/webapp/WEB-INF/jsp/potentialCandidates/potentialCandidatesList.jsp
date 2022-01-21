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
    
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>  
    
    <script>
    
    	function PotentialCandidatesSort(){
    		
    		var field = document.getElementById('PCsort').value;
    		
    		var search = document.getElementById('search').value;
    		
    		var pageNo = document.getElementById('pageNo').value;
    		
    		if (field == "all" && search == null){
    			location.href = 'view-potential-candidates-list';
    		} else{	
    			location.href = "view-potential-candidates-list?field="+field+"&keySearch=" + search+"&pageNo="+pageNo;
    		}
    	}
    
    </script>     
            
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
            	
		           <!-- Sorted By <select id="PCsort" onchange="PotentialCandidatesSort()">   
		           		 
		           		 		<option value="all">Choose</option>
		           		 		<option value="all">Full</option>
		           		 		<option value="fullName">Full Name A-Z</option>
		           		 		<option value="email">Email Z-A</option>

		           		 	</select> -->
            	</div>
            	
				<div class="FlexSearch">
				
	           		 <form:form action="view-potential-candidates-list" method="get"> 

	           		 	<input id="search" name="keySearch" type="text" placeholder="Search ..." class="FilterSearch" value="${keySearch}">
	           		 	
	           		 	 Sorted By <select id="PCsort" name="field">   
		           		 
		           		 			<option value = "id">Choose</option>
		           		 			<option value="fullName">Full Name A-Z</option>
		           		 			<option value="email">Email Z-A</option>
	           		 			</select>
		           		 	
						<button type="submit" class="ButtonSearch">Search</button>
						
						
						
					</form:form>	
					
				</div>	
            </div>  
            
            <form:form action="add-new-potential-candidates" method="get"> 	
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
			
							<a href= "delete-potential-candidates/${listCandidateDto.id }" onclick="deletePotentialCandidates(event, '${listCandidateDto.id }')">
								<button type="submit" class="ButtonDelete">Delete</button> 
							</a>	
							<a href= "update-potential-candidates?id=${listCandidateDto.id }">
								<button type="submit" class="ButtonUpdate">Update</button> 
							</a>	
						</td>
				    </tr>
				  </tbody>
				 </c:forEach>
			</table>
			</div>	

			<nav aria-label="Page navigation example">
			<c:if test="${totalPage > 1 }">
		  		<ul class="pagination">
						
					<c:if test="${currentPage > 1}">
		    			<li class="page-item"><a class="page-link" href="view-potential-candidates-list?pageNo=${currentPage - 1}&keySearch=${keySearch}">Previous</a></li>
		    		</c:if>	
		    		
		    		<c:forEach  begin="1" end="${totalPage}" var="i">	
    					<li  class="page-item" id="pageNo" value="${i}"><a class="page-link" href="view-potential-candidates-list?pageNo=${i}&keySearch=${keySearch}&field=${field}">${i}</a></li>	
					</c:forEach>
					
					<c:if test="${currentPage < totalPage}">
				    	<li class="page-item"><a class="page-link" href="view-potential-candidates-list?pageNo=${currentPage + 1}&keySearch=${keySearch}">Next</a></li>
				    </c:if>	
		  		</ul>
		  	</c:if>
		</nav>
            </c:if>
        </div>
    </div>
</div>

<script>
    function deletePotentialCandidates(event, id) {
        event.preventDefault();
        Swal.fire({
            title: 'Do you want to delete the Potential Candidate?',
            showDenyButton: true,
            confirmButtonText: 'Yes',
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = "${pageContext.request.contextPath}/delete-potential-candidates/" + id;
            }
        })
    }
</script>
</body>
</html>