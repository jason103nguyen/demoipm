<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="com.demoipm.consts.URLConst" %>
<!DOCTYPE HTML>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <title>Edit Skill</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function validateForm() {
            var name = document.getElementById("name").value;
            if(name.trim() == "") {
                alert("Input skill name are required!!");
                document.getElementById("name").style.borderColor = "red";
                document.getElementById("name").select();
                document.getElementById("name").value = "";
                return false;
            } else {
                document.getElementById("name").style.borderColor = "none";
            }
        }
    </script>
    <style>
        .required:after {
            content:" *";
            color: red;
        }
    </style>
</head>

<body class="bg-light">
<div class="container p-5 my-5 border">
    <div class="row">
        <div class="col-sm-3">
            <jsp:include page="../logoFpt.jsp" />
        </div>
        <div class="col-sm-9">
            <c:if test="${skill.id != 0}">
                <p class="h1 text-warning text-center">EDIT SKILL</p>
            </c:if>
            <c:if test="${skill.id == 0}">
                <p class="h1 text-warning text-center">CREATE SKILL</p>
            </c:if>

        </div>
    </div>

    <div class="row mt-3">
        <div class="col-sm-3">
            <jsp:include page="../menu.jsp" />
        </div>

        <div class="col-sm-9">
            <c:if test="${responseStatus != null}">
                <div class="alert alert-success" role="alert">
                        ${responseStatus}
                </div>
            </c:if>
            <c:if test="${responseError != null}">
                <div class="alert alert-danger" role="alert">
                        ${responseError}
                </div>
            </c:if>
            <form:form modelAttribute="skill" action="${pageContext.request.contextPath}${URLConst.EDIT_SKILL_PAGE_URL}" method="post" onsubmit="return validateForm()">
                <div class="mb-3" style="display: none">
                    <form:input path="id" type="text" />
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label fw-bold required">Skill name</label>
                    <form:input path="name" type="text" class="form-control" id="name"/>
                    <form:errors path="name" class="form-text text-danger fst-italic"></form:errors>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label fw-bold">Description</label>
                    <form:textarea path="description" type="text" class="form-control" id="description" rows="7"/>
                    <form:errors path="description" class="form-text text-danger fst-italic"></form:errors>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
                <a href="${pageContext.request.contextPath}${URLConst.MANAGE_SKILL_URL}?pageNo=${pageNo}&pageStart=${pageStart}&pageEnd=${pageEnd}"
                   class="btn btn-secondary">
                    Back
                </a>
            </form:form>

        </div>
    </div>

</div>
</body>

</html>