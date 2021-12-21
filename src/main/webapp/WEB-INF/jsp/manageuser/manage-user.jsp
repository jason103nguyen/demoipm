<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <title>Welcome</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
            rel="stylesheet">

    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
<div class="container p-5 my-5 border">
    <div class="row">
        <div class="col-sm-3">
            <jsp:include page="../logoFpt.jsp" />
        </div>

        <div class="col-sm-9">
            <h1>QUẢN LÝ USER</h1>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-sm-3">
            <jsp:include page="../menu.jsp" />
        </div>

        <div class="col-sm-9">
            <table>
                <tr>
                    <th>STT</th>
                    <th>Họ và tên</th>
                    <th>SĐT</th>
                    <th>Email</th>
                    <th>Username</th>
                    <th>Role</th>
                    <th>Hành động</th>
                </tr>
                <c:forEach items="${response.userList}" var="user" varStatus="theCount">
                    <tr>
                        <td>${theCount.count}</td>
                        <td>${user.fullName}</td>
                        <td>${user.phone}</td>
                        <td>${user.email}</td>
                        <td>${user.username}</td>
                        <td>
                            <c:forEach items="${user.roles}" var="role">
                                ${role}<br>
                            </c:forEach>
                        </td>
                        <td><a href="#">Cập nhật</a><a href="#">Xóa</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</div>
</body>

</html>