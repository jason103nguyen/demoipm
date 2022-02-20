<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
		rel="stylesheet">
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
</head>

<body class="bg-secondary">

	<div class="container border bg-light py-2 mt-3 w-25 text-center rounded">
		<h1>Member Login</h1>
		
     <!-- /login?error=true -->
     <c:if test="${param.error == 'true'}">
         <div style="color:red;margin:10px 0px;">
                Unable to login. Check your username or password.
         </div>
    </c:if>
		
		<p>
			New here? Click <a href="/register">here</a> to register.
		</p>

		<form:form action="/j_spring_security_check" method="post">
			<div class="mb-3 mt-3">
				<input
					type="text" class="form-control"
					placeholder="Username" name="username">
			</div>
			<div class="mb-3">
				<input
					type="password" class="form-control"
					placeholder="Password" name="password">
			</div>
			
			<div class="mb-3">
				<input type="checkbox" name="remember-me" /> Remember Me? 
			</div>

			<button type="submit" class="btn btn-success container-fluid">Login</button>
		</form:form>
		
	</div>

</body>
</html>
