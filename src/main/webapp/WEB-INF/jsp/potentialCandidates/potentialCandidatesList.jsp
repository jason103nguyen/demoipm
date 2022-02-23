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
    		
    		var sortBy = document.getElementById('PCsort').value;
    		
    		var search = document.getElementById('search').value;
    		
    		var pageNo = document.getElementById('pageNo').value;
    		
    		var direction = document.getElementById('direction').value;

    		if (sortBy == "id" ){
    			window.location = 'view-potential-candidates-list?sortBy=id'+'&keySearch=' + search+'&pageNo='+pageNo+'&direction='+direction;  
    		} else{	
    			
    			document.getElementById('PCsort').value = sortBy;
    			
    			window.location = 'view-potential-candidates-list?sortBy='+sortBy+'&keySearch=' + search+'&pageNo='+pageNo+'&direction='+direction;  

    		}
    	}	
    
    </script>   
    
    
    <script>
			function myFunction() {

				const queryString = window.location.search;
				
				const urlParams = new URLSearchParams(queryString);
				
				const sortBy = urlParams.get('sortBy')
				
				const direction = urlParams.get('direction')
				
				if(sortBy == null && direction == null) {
					sortBy = "id";
					direction = "ASC"
				} else{	
					document.getElementById('PCsort').value = sortBy;
					
					document.getElementById('direction').value = direction;
				}
			}
	</script>  
            
</head>
<body onload="myFunction()">


<div class="container p-5 my-5 border">
    <div class="row">
        <div class="col-sm-3">
            <jsp:include page="../logoFpt.jsp" />
        </div>

        <div class="col-sm-9">
            <p class="h1 text-warning text-center">MANAGE POTENTIAL CANDIDATES</p>
            <div class="search">


            	<div class="SelectSort" id ="a">
            	
		            Sorted By <select id="PCsort" onchange="PotentialCandidatesSort()" >    
		           		 		<option value="id">ID</option>
		           		 		<option value="fullName">Full Name</option>
		           		 		<option value="email">Email</option>
		           		 		<option value="status">Status</option>
		           		 	</select>

		           		 	<select id="direction" onchange="PotentialCandidatesSort()">
		           		 	
		           		 		<option value="ASC">ASC</option>
		           		 	
		           		 		<option value="DESC">DESC</option>
	
		           		 	</select>
		           		 	
            	</div>
            	
				<div class="FlexSearch">
				
	           		 <form:form action="view-potential-candidates-list" method="get"> 

	           		 	<input id="search" name="keySearch" type="text" placeholder="Search ..." class="FilterSearch" value="${keySearch}">
	           		 	
	           		 	<input type="hidden" id="fieldSort" name="sortBy" value="${sortBy }">
	           		 	
	           		 	<input type="hidden" id="numberPageNo" name="pageNo" value="1">
	           		 	
	           		 	<input type="hidden" name="direction" value="${direction }">
		           		 	
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
						<td>ID</td>
						<td>Full Name</td>
						<td>National Identity Card</td>
						<td>Phone</td>
						<td>Email</td>
						<td>Status</td>
						<td>Details</td>
						<td>Interview</td>
						<td>Action</td>
					</tr>
				  </thead>
				  
				   <c:if test="${listCandidateDto.size() == 0 }" >
					   <tbody>
					   		<tr>
					   			<td colspan="9">
					   				<p style="color: red; text-align: center;">Not found candidate unsatisfactorily condition</p>
					   			</td>		
					   		</tr>   
					   </tbody>
				   </c:if>
				   <c:forEach var="listCandidateDto" items="${listCandidateDto }">
 
				  <tbody>
				    <tr>
				      <th scope="row">${listCandidateDto.id }</th>
				      <td>${listCandidateDto.fullName }</td>
						<td>${listCandidateDto.cmnd }</td>
						<td>${listCandidateDto.phone }</td>
						<td>${listCandidateDto.email }</td>
						
						<td>${listCandidateDto.status }</td>
						
						<td>
			
							<a href= "view-potential-candidates-info?id=${listCandidateDto.id }">
								<button type="submit" class="ButtonInfo"> Details </button> 
							</a>	

						</td>
						<td>
							<a href="interview/create?id=${listCandidateDto.id }">
								<button type="submit" class="ButtonInfo">Interview</button>
							</a>
						</td>
					
						<td>
			
							<a href= "delete-potential-candidates/${listCandidateDto.id }" onclick="deletePotentialCandidates(event, '${listCandidateDto.id }')">
								<button type="submit" class="ButtonDelete">Delete</button> 
							</a>	
							<%-- <a href= "update-potential-candidates?id=${listCandidateDto.id }">
								<button type="submit" class="ButtonUpdate">Update</button> 
							</a> --%>	
						</td>
				    </tr>
				  </tbody>
				 </c:forEach>
			</table>
			</div>	

			<nav aria-label="Page navigation example">
			<c:if test="${totalPage >= 1 }">
		  		<ul class="pagination">
						
					<c:if test="${currentPage > 1}">
		    			<li class="page-item"><a class="page-link" href="view-potential-candidates-list?pageNo=${currentPage - 1}&keySearch=${keySearch}&sortBy=${sortBy}&direction=${direction }">Previous</a></li>
		    		</c:if>		
		    		<c:forEach  begin="1" end="${totalPage }" var="i">	
    					<li  class="page-item" id="pageNo" value="${i}"><a class="page-link" href="view-potential-candidates-list?pageNo=${i}&keySearch=${keySearch}&sortBy=${sortBy}&direction=${direction }">${i}</a></li>	
					</c:forEach>
					
					<c:if test="${currentPage < totalPage}">
				    	<li class="page-item"><a class="page-link" href="view-potential-candidates-list?pageNo=${currentPage + 1}&keySearch=${keySearch}&sortBy=${sortBy}&direction=${direction }">Next</a></li>
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