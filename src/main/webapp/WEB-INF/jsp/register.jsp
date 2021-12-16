<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8" />
	<title>Register</title>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
</head>

<body>
	<h1>Resister</h1>
	<div>
		<form:form modelAttribute="userApp" action="/register" method="post">
			Full name: <form:input path="fullName" /><br/>
			UserName: <form:input path="username" /><br/>
			Password: <form:input type="password" path="password" /><br/>
			Role: <form:input path="role" /><br/>
			
			<input type="submit" value="Save Changes" />
		</form:form>
	</div>
</body>
 
</html>