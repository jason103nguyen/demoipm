<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.demoipm.consts.URLConst" %>
<%@page import="com.demoipm.consts.ViewConst" %>
<!DOCTYPE HTML>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
    <title>Manage Skill</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function onLoadTextSearch() {
            document.getElementById("txtSearch").value = '${searchKey}';
        };
    </script>
</head>

<body class="bg-light" onload="onLoadTextSearch();">
<div class="container p-5 my-5 border">
    <div class="row">
        <div class="col-sm-3">
            <jsp:include page="../logoFpt.jsp"/>
        </div>

        <div class="col-sm-9">
            <p class="h1 text-warning text-center">MANAGE SKILL</p>
            <div class="d-flex flex-row justify-content-between align-items-end">
                <a href="${pageContext.request.contextPath}${URLConst.EDIT_SKILL_PAGE_URL}" class="btn btn-primary">Add
                    new</a>
                <div class="d-flex align-items-end">
                    <div>
                        <input type="text" class="form-control" id="txtSearch" placeholder="Input keyword..."
                               name="searchWord" onchange="searchSkillName(event, this.value)">
                    </div>
                    <div>
                        <button class="btn btn-secondary" id="btnPage" disabled>Search</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-sm-3">
            <jsp:include page="../menu.jsp"/>
        </div>

        <div class="col-sm-9">

            <table class="table table-bordered table-striped">
                <tr class="text-center">
                    <th>No.</th>
                    <th>Skill Name</th>
                    <th style="text-align: center">Description</th>
                    <th colspan="2" style="text-align: center">Action</th>
                </tr>
                <c:forEach items="${skillList}" var="skill" varStatus="theCount">
                    <tr>
                        <td class="align-middle">${theCount.count}</td>
                        <td class="align-middle">${skill.name}</td>
                        <td class="align-middle" style="text-align: center">
                            <button type="button" class="btn-sm btn-info" data-toggle="tooltip" data-placement="right" title="${skill.description}" disabled>
                                Info
                            </button>
                        </td>
                        <td class="align-middle" style="text-align: center">
                            <a href="${pageContext.request.contextPath}${URLConst.EDIT_SKILL_PAGE_URL}?id=${skill.id}" class="btn-sm btn-warning">Edit</a>
                            | <a href="#"  class="btn-sm btn-danger" onclick="deleteSkill(event, '${skill.id}')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${skillList == null || skillList.size() == 0}">
                    <tr>
                        <td class="text-center" colspan="8">No data available</td>
                    </tr>
                </c:if>
            </table>

            <c:if test="${skillList != null && skillList.size() > 0}">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <li class="page-item ${pageStart == 1 ? 'disabled' : ''}">
                                <%--begin page--%>
                            <c:set var="pgEnd" value="${pageEnd >= totalPages ? (pageStart - 1) : (pageEnd - 5)}"/>
                            <a class="page-link"
                                    <c:if test="${searchKey == ''}">
                                        href="${pageContext.request.contextPath}${URLConst.MANAGE_SKILL_URL}?pageNo=${currentPage}&pageStart=${1}&pageEnd=${totalPages < 5 ? totalPages : 5}"
                                    </c:if>
                                    <c:if test="${searchKey != ''}">
                                        href="${pageContext.request.contextPath}${URLConst.SEARCH_SKILL_URL}?searchKey=${searchKey}&pageNo=${currentPage}&pageStart=${1}&pageEnd=${totalPages < 5 ? totalPages : 5}"
                                    </c:if>
                               aria-label="Previous">
                                <span class="glyphicon glyphicon-fast-backward" aria-hidden="true"></span>
                            </a>
                                <%--previous page--%>
                            <a class="page-link"
                                    <c:if test="${searchKey == ''}">
                                        href="${pageContext.request.contextPath}${URLConst.MANAGE_SKILL_URL}?pageNo=${currentPage}&pageStart=${pageStart-5}&pageEnd=${pgEnd}"
                                    </c:if>
                                    <c:if test="${searchKey != ''}">
                                        href="${pageContext.request.contextPath}${URLConst.SEARCH_SKILL_URL}?searchKey=${searchKey}&pageNo=${currentPage}&pageStart=${pageStart-5}&pageEnd=${pgEnd}"
                                    </c:if>
                               aria-label="Previous">
                                <span class="glyphicon glyphicon-backward" aria-hidden="true"></span>
                            </a>
                        </li>
                        <c:forEach var="page" begin="${pageStart}" end="${pageEnd}">
                            <li class="page-item ${currentPage+1 == page ? 'active' : ''}">
                                <a class="page-link"
                                <c:if test="${searchKey == ''}">
                                    href="${pageContext.request.contextPath}${URLConst.MANAGE_SKILL_URL}?pageNo=${page-1}&pageStart=${pageStart}&pageEnd=${pageEnd}">${page}</a>
                                </c:if>
                                <c:if test="${searchKey != ''}">
                                    href="${pageContext.request.contextPath}${URLConst.SEARCH_SKILL_URL}?searchKey=${searchKey}&pageNo=${page-1}&pageStart=${pageStart}&pageEnd=${pageEnd}">${page}</a>
                                </c:if>
                            </li>
                        </c:forEach>
                        <li class="page-item ${pageEnd >= totalPages ? 'disabled' : ''}">
                                 <%--next page--%>
                            <c:set var="pgEnd"
                                   value="${pageEnd >= (totalPages - 5) ? (4 - (pageEnd - totalPages)) + pageStart : (pageEnd + 5)}"/>
                            <a class="page-link"
                                    <c:if test="${searchKey == ''}">
                                        href="${pageContext.request.contextPath}${URLConst.MANAGE_SKILL_URL}?pageNo=${currentPage}&pageStart=${pageStart+5}&pageEnd=${pgEnd}"
                                    </c:if>
                                    <c:if test="${searchKey != ''}">
                                        href="${pageContext.request.contextPath}${URLConst.SEARCH_SKILL_URL}?searchKey=${searchKey}&pageNo=${currentPage}&pageStart=${pageStart+5}&pageEnd=${pgEnd}"
                                    </c:if>
                               aria-label="Next">
                                <span class="glyphicon glyphicon-forward" aria-hidden="true"></span>
                            </a>
                                  <%--end page--%>
                            <c:set var="pgStart" value="${totalPages % 10 == 1 || totalPages % 10 == 6 ? totalPages :
                                                          (totalPages % 10 == 2 || totalPages % 10 == 7 ? totalPages - 1:
                                                          (totalPages % 10 == 3 || totalPages % 10 == 8 ? totalPages - 2:
                                                          (totalPages % 10 == 4 || totalPages % 10 == 9 ? totalPages - 3:
                                                          totalPages - 4)))}"/>
                            <a class="page-link"
                                    <c:if test="${searchKey == ''}">
                                        href="${pageContext.request.contextPath}${URLConst.MANAGE_SKILL_URL}?pageNo=${currentPage}&pageStart=${pgStart}&pageEnd=${totalPages}"
                                    </c:if>
                                    <c:if test="${searchKey != ''}">
                                        href="${pageContext.request.contextPath}${URLConst.SEARCH_SKILL_URL}?searchKey=${searchKey}&pageNo=${currentPage}&pageStart=${pgStart}&pageEnd=${totalPages}"
                                    </c:if>
                               aria-label="Next">
                                <span class="glyphicon glyphicon-fast-forward" aria-hidden="true"></span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </c:if>
        </div>
    </div>

</div>

<script>
    function deleteSkill(event, id) {
        event.preventDefault();
        Swal.fire({
            title: 'Do you want to delete the skill?',
            showDenyButton: true,
            confirmButtonText: 'Yes',
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = "${pageContext.request.contextPath}${URLConst.DELETE_SKILL_URL}?id=" + id;
            }
        })
    }

    function searchSkillName(event, value) {
        location.href = "${pageContext.request.contextPath}${URLConst.SEARCH_SKILL_URL}?searchKey=" + value + "&pageNo=0&pageStart=1&pageEnd=5";
    }


</script>
</body>

</html>