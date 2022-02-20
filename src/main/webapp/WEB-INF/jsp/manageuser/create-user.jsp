<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="com.demoipm.consts.URLConst" %>
<t:template>
    <jsp:attribute name="title">
        Create New User
    </jsp:attribute>
    <jsp:attribute name="header">
        <h1 class="text-warning text-center m-3">CREATE NEW USER</h1>
    </jsp:attribute>
    <jsp:attribute name="content">
        <c:if test="${response != null && response.hasError()}">
            <div class="alert alert-danger" role="alert">
                    ${response.getMessage()}
            </div>
        </c:if>
        <form:form modelAttribute="user" action="${pageContext.request.contextPath}/process-create-user" method="post">
            <div class="row">
                <div class="col-md-6">
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
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
                <div class="col-md-6">
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
                </div>
            </div>
        </form:form>
        </div>
    </jsp:attribute>
</t:template>