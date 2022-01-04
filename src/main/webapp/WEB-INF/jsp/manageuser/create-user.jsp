<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <title>Create New User</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
            rel="stylesheet">

    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body class="bg-light">
<div class="container p-5 my-5 border">
    <div class="row">
        <div class="col-sm-3">
            <jsp:include page="../logoFpt.jsp" />
        </div>

        <div class="col-sm-9">
            <p class="h1 text-warning text-center">CREATE NEW USER</p>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-sm-3">
            <jsp:include page="../menu.jsp" />
        </div>

        <div class="col-sm-9">
            <c:if test="${response != null && response.hasError()}">
                <div class="alert alert-danger" role="alert">
                    ${response.getMessage()}
                </div>
            </c:if>
            <form:form modelAttribute="user" action="${pageContext.request.contextPath}/process-create-user" method="post">
                <div class="mb-3">
                    <label for="fullName" class="form-label fw-bold">Fullname</label>
                    <form:input path="fullName" type="text" class="form-control" id="fullName"/>
                    <form:errors path="fullName" class="form-text text-danger fst-italic"></form:errors>
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label fw-bold">Phone</label>
                    <form:input path="phone" type="number" class="form-control" id="phone"/>
                    <form:errors path="phone" class="form-text text-danger fst-italic"></form:errors>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label fw-bold">Email</label>
                    <form:input path="email" type="email" class="form-control" id="email"/>
                    <form:errors path="email" class="form-text text-danger fst-italic"></form:errors>
                </div>
                <div class="mb-3">
                    <label for="username" class="form-label fw-bold">Username</label>
                    <form:input path="username" type="text" class="form-control" id="username"/>
                    <form:errors path="username" class="form-text text-danger fst-italic"></form:errors>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label fw-bold">Password</label>
                    <form:input path="password" type="password" class="form-control" id="password"/>
                    <form:errors path="password" class="form-text text-danger fst-italic"></form:errors>
                </div>
                <div class="mb-3">
                    <label for="roleSelect" class="form-label fw-bold">Roles</label>
                    <div id="roleSelect">
                        <c:forEach items="${roles}" var="role">
                            <div class="form-check">
                                <c:if test="${user.roles.contains(role)}">
                                    <input name="roles" class="form-check-input" type="checkbox" value="${role}" id="${role}" checked />
                                </c:if>
                                <c:if test="${!user.roles.contains(role)}">
                                    <input name="roles" class="form-check-input" type="checkbox" value="${role}" id="${role}" />
                                </c:if>

                                <label class="form-check-label" for="${role}">
                                        ${role}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
            </form:form>

        </div>
    </div>

</div>
</body>

</html>