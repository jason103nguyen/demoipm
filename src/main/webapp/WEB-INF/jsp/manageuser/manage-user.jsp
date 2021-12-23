<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <title>Quản lý User</title>

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
            <p class="h1 text-warning text-center">QUẢN LÝ USER</p>
            <div class="d-flex flex-row justify-content-between align-items-end">
                <a href="${pageContext.request.contextPath}/create-user-page" class="btn btn-primary">Thêm mới</a>
                <form action="${pageContext.request.contextPath}/manage-user" method="get">
                    <div class="d-flex align-items-end">
                        <div>
                            <input type="text" class="form-control" id="search" placeholder="Nhập từ khóa..."
                                            name="searchWord">
                        </div>

                        <div>
                            <button type="submit" class="btn btn-secondary">Tìm kiếm</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-sm-3">
            <jsp:include page="../menu.jsp" />
        </div>

        <div class="col-sm-9">
            <table class="table">
                <tr class="text-center">
                    <th>STT</th>
                    <th>Họ và tên</th>
                    <th>SĐT</th>
                    <th>Email</th>
                    <th>Username</th>
                    <th>Role</th>
                    <th colspan="2">Hành động</th>
                </tr>
                <c:forEach items="${response.userList}" var="user" varStatus="theCount">
                    <tr>
                        <td class="align-middle">${theCount.count}</td>
                        <td class="align-middle">${user.fullName}</td>
                        <td class="align-middle">${user.phone}</td>
                        <td class="align-middle">${user.email}</td>
                        <td class="align-middle">${user.username}</td>
                        <td class="align-middle">
                            <c:forEach items="${user.roles}" var="role">
                                ${role}<br>
                            </c:forEach>
                        </td>
                        <td class="align-middle"><a href="#">Cập nhật</a></td>
                        <td class="align-middle"><a href="#">Xóa</a></td>
                    </tr>
                </c:forEach>
                <c:if test="${response.userList == null || response.userList.size() == 0}">
                    <tr>
                        <td class="text-center" colspan="8">Không có dữ liệu</td>
                    </tr>
                </c:if>
            </table>
        </div>
    </div>

</div>
</body>

</html>