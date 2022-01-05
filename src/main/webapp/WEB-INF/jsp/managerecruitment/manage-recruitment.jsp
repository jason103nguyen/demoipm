<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
    <title>Manage Recruitment</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
            rel="stylesheet">

    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body class="bg-light">
<div class="container p-5 my-5 border">
    <div class="row">
        <div class="col-sm-3">
            <jsp:include page="../logoFpt.jsp"/>
        </div>

        <div class="col-sm-9">
            <p class="h1 text-warning text-center">MANAGE RECRUITMENT</p>
            <div class="d-flex flex-row justify-content-between align-items-end">
                <a href="${pageContext.request.contextPath}/create-recruitment-page" class="btn btn-primary">Add new</a>
            </div>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-sm-3">
            <jsp:include page="../menu.jsp"/>
        </div>

        <div class="col-sm-9">
            <c:if test="${response.recruitmentList != null && response.recruitmentList.size() > 0}">
                <nav aria-label="...">
                    <ul class="pagination">
                        <li class="page-item disabled">
                            <a class="page-link" tabindex="-1" aria-disabled="true">Entries</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/manage-recruitment?entriesNo=1">1</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/manage-recruitment?entriesNo=2">2</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/manage-recruitment?entriesNo=3">3</a>
                        </li>
                    </ul>
                </nav>
            </c:if>

            <table class="table table-bordered table-striped">
                <tr class="text-center">
                    <th>No.</th>
                    <th>Job</th>
                    <th>Career</th>
                    <th>Quantity</th>
                    <th>Description</th>
                    <th colspan="2">Action</th>
                </tr>
                <c:forEach items="${response.recruitmentList}" var="recruit" varStatus="theCount">
                    <tr>
                        <td class="align-middle">${theCount.count}</td>
                        <td class="align-middle">${recruit.job}</td>
                        <td class="align-middle">${recruit.career}</td>
                        <td class="align-middle">${recruit.quantity}</td>
                        <td class="align-middle"><a href="#" data-bs-toggle="modal" data-bs-target="#recruitment-detail" onclick="showDetail(event, '${recruit.id}')">Detail</a></td>
                        <td class="align-middle">
                            <a href="${pageContext.request.contextPath}/update-recruitment-page?id=${recruit.id}">Edit</a></td>
                        <td class="align-middle">
                            <a href="#" onclick="deleteUser(event, '${recruit.id}')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${response.recruitmentList == null || response.recruitmentList.size() == 0}">
                    <tr>
                        <td class="text-center" colspan="8">No data available</td>
                    </tr>
                </c:if>
            </table>

            <c:if test="${response.recruitmentList != null && response.recruitmentList.size() > 0}">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <li class="page-item ${response.getCurrentPage() == 1 ? 'disabled' : ''}">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/manage-recruitment?entriesNo=${response.getEntriesNo()}&pageNo=${response.getCurrentPage()-1}"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <c:forEach begin="1" end="${response.getTotalPage()}" varStatus="pageNo">
                            <li class="page-item ${response.getCurrentPage() == pageNo.count ? 'active' : ''}">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/manage-recruitment?entriesNo=${response.getEntriesNo()}&pageNo=${pageNo.count}">${pageNo.count}</a>
                            </li>
                        </c:forEach>
                        <li class="page-item ${response.getCurrentPage() == response.getTotalPage() ? 'disabled' : ''}">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/manage-recruitment?entriesNo=${response.getEntriesNo()}&pageNo=${response.getCurrentPage()+1}"
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
<div id="recruitment-detail" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Recruitment detail</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <table class="table table-bordered">
                    <tr>
                        <th>Career</th>
                        <td id="recruitment-detail-career"></td>
                    </tr>
                    <tr>
                        <th>Job</th>
                        <td id="recruitment-detail-job"></td>
                    </tr>
                    <tr>
                        <th>Quantity</th>
                        <td id="recruitment-detail-quantity"></td>
                    </tr>
                    <tr>
                        <th>Min Salary</th>
                        <td id="recruitment-detail-min"></td>
                    </tr>
                    <tr>
                        <th>Max Salary</th>
                        <td id="recruitment-detail-max"></td>
                    </tr>
                    <tr>
                        <th>Start Date</th>
                        <td id="recruitment-detail-start"></td>
                    </tr>
                    <tr>
                        <th>End Date</th>
                        <td id="recruitment-detail-end"></td>
                    </tr>
                    <tr>
                        <th>Skills</th>
                        <td id="recruitment-detail-skills"></td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script>
    function deleteUser(event, id) {
        event.preventDefault();
        Swal.fire({
            title: 'Do you want to delete the recruitment?',
            showDenyButton: true,
            confirmButtonText: 'Yes',
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = "${pageContext.request.contextPath}/delete-recruiment?id=" + id;
            }
        })
    }

    function showDetail(event, id) {
        event.preventDefault();
        console.log($("#recruitment-detail-id").text(id));
        $.ajax({
            url: "${pageContext.request.contextPath}/api/get-recruitment-detail?id=" + id,
            data: {
                format: 'json'
            },
            error: function() {
                $('#info').html('<p>An error has occurred</p>');
            },
            success: function(data) {
                $("#recruitment-detail-career").text(data.career);
                $("#recruitment-detail-job").text(data.job);
                $("#recruitment-detail-quantity").text(data.quantity);
                $("#recruitment-detail-min").text(data.minSalary);
                $("#recruitment-detail-max").text(data.maxSalary);
                $("#recruitment-detail-start").text(data.startDate);
                $("#recruitment-detail-end").text(data.endDate);
                $("#recruitment-detail-skills").text(data.skills);
            },
            type: 'GET'
        });
    }
</script>
</body>

</html>