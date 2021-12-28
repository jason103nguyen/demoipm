<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
    <title>Manage User</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
            rel="stylesheet">

    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>
<div class="container p-5 my-5 border">
    <div class="row">
        <div class="col-sm-3">
            <jsp:include page="../logoFpt.jsp"/>
        </div>

        <div class="col-sm-9">
            <p class="h1 text-warning text-center">MANAGE USER</p>
            <div class="d-flex flex-row justify-content-between align-items-end">
                <a href="${pageContext.request.contextPath}/create-user-page" class="btn btn-primary">Add new</a>
                <form action="${pageContext.request.contextPath}/manage-user" method="get">
                    <div class="d-flex align-items-end">
                        <div>
                            <input type="text" class="form-control" id="search" placeholder="Input keyword..."
                                   name="searchWord" value="${response.getSearchWord()}">
                            <input type="number" name="entriesNo" value="${response.getEntriesNo()}" hidden>
                            <input type="number" name="pageNo" value="${response.getCurrentPage()}" hidden>
                        </div>

                        <div>
                            <button type="submit" class="btn btn-secondary">Search</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-sm-3">
            <jsp:include page="../menu.jsp"/>
        </div>

        <div class="col-sm-9">
            <c:if test="${response.userList != null && response.userList.size() > 0}">
                <nav aria-label="...">
                    <ul class="pagination">
                        <li class="page-item disabled">
                            <a class="page-link" tabindex="-1" aria-disabled="true">Entries</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/manage-user?entriesNo=1&searchWord=${response.getSearchWord()}">1</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/manage-user?entriesNo=2&searchWord=${response.getSearchWord()}">2</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/manage-user?entriesNo=3&searchWord=${response.getSearchWord()}">3</a>
                        </li>
                    </ul>
                </nav>
            </c:if>

            <table class="table table-bordered">
                <tr class="text-center">
                    <th>No.</th>
                    <th>Fullname</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Username</th>
                    <th>Role</th>
                    <th colspan="2">Action</th>
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
                        <td class="align-middle">
                            <a href="${pageContext.request.contextPath}/update-user-page?username=${user.username}">Edit</a></td>
                        <td class="align-middle">
                            <a href="#" onclick="deleteUser(event, '${user.username}')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${response.userList == null || response.userList.size() == 0}">
                    <tr>
                        <td class="text-center" colspan="8">No data available</td>
                    </tr>
                </c:if>
            </table>

            <c:if test="${response.userList != null && response.userList.size() > 0}">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <li class="page-item ${response.getCurrentPage() == 1 ? 'disabled' : ''}">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/manage-user?entriesNo=${response.getEntriesNo()}&pageNo=${response.getCurrentPage()-1}&searchWord=${response.getSearchWord()}"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <c:forEach begin="1" end="${response.getTotalPage()}" varStatus="pageNo">
                            <li class="page-item ${response.getCurrentPage() == pageNo.count ? 'active' : ''}">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/manage-user?entriesNo=${response.getEntriesNo()}&pageNo=${pageNo.count}&searchWord=${response.getSearchWord()}">${pageNo.count}</a>
                            </li>
                        </c:forEach>
                        <li class="page-item ${response.getCurrentPage() == response.getTotalPage() ? 'disabled' : ''}">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/manage-user?entriesNo=${response.getEntriesNo()}&pageNo=${response.getCurrentPage()+1}&searchWord=${response.getSearchWord()}"
                               aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </c:if>
        </div>
    </div>

</div>

<script>
    function deleteUser(event, username) {
        event.preventDefault();
        Swal.fire({
            title: 'Do you want to delete the user?',
            showDenyButton: true,
            confirmButtonText: 'Yes',
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = "${pageContext.request.contextPath}/delete-user?username=" + username;
            }
        })
    }
</script>
</body>

</html>