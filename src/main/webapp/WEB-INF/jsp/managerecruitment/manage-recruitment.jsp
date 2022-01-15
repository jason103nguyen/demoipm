<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.demoipm.consts.URLConst" %>
<!DOCTYPE HTML>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
    <title>Manage Recruitment</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>--%>
<%--    <link--%>
<%--            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"--%>
<%--            rel="stylesheet">--%>

<%--    <script--%>
<%--            src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>--%>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs5/jq-3.6.0/dt-1.11.3/datatables.min.css"/>

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/v/bs5/jq-3.6.0/dt-1.11.3/datatables.min.js"></script>
</head>

<body class="bg-light">
<div class="container p-5 my-5 border">
    <div class="row">
        <div class="col-sm-3">
            <jsp:include page="../logoFpt.jsp"/>
        </div>

        <div class="col-sm-9">
            <p class="h1 text-warning text-center">MANAGE RECRUITMENT</p>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-sm-3">
            <jsp:include page="../menu.jsp"/>
        </div>

        <div class="col-sm-9">
            <table id="recruitment-datatable" class="table table-bordered table-striped">
                <thead>
                    <tr class="text-center">
                        <th>ID</th>
                        <th>Job</th>
                        <th>Career</th>
                        <th>Quantity</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                </thead>
            </table>
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

    let datatable = $('#recruitment-datatable').DataTable({
        serverSide: true,
        ajax: {
            url: '${pageContext.request.contextPath}${URLConst.API_GET_RECRUITMENT_BY_CONDITION_URL}',
            method: 'POST',
            dataSrc: 'data',
            contentType: "application/json",
            data: function (request) {
                return JSON.stringify(request);
            }
        },
        order: [],
        searching: false,
        columnDefs: [
            {"targets": "_all", "orderable": false},
            {"data": "id", "targets": 0},
            {"data": "job", "targets": 1},
            {"data": "career", "targets": 2},
            {"data": "quantity", "targets": 3},
            {
                "targets": 4,
                "render": function (data, type, row) {
                    return `<a class="btn btn-sm btn-primary mx-2" href="#" data-bs-toggle="modal" data-bs-target="#recruitment-detail" onclick="showDetail(event, ` + row.id + `)">Detail</a>`;
                },
                "className": "text-center"
            },
            {
                "targets": 5,
                "render": function (data, type, row) {
                    let editBtn = `<a class="btn btn-sm btn-primary mx-2" href="${pageContext.request.contextPath}/update-recruitment-page?id=` + row.id + `">Edit</a>`;
                    let deleteBtn = `<a class="btn btn-sm btn-primary mx-2" href="#" onclick="deleteUser(event, ` + row.id + `)">Delete</a>`;
                    return editBtn + deleteBtn;
                },
                "className": "text-center"
            }
        ],
    });

    $('#recruitment-datatable_length').append(`<a class="btn btn-sm btn-primary mx-2" href="${pageContext.request.contextPath}${URLConst.CREATE_RECRUITMENT_PAGE_URL}">Add new</a>`);

    function deleteUser(event, id) {
        event.preventDefault();
        Swal.fire({
            title: 'Do you want to delete the recruitment?',
            showDenyButton: true,
            confirmButtonText: 'Yes',
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    type: "DELETE",
                    url: "${pageContext.request.contextPath}${URLConst.API_DELETE_RECRUITMENT_URL}?id=" + id,
                    dataType: "json",
                    complete: function(response){
                        switch (response.status) {
                            case 200:
                                Swal.fire({
                                    title: 'Recruitment deleted successfully',
                                    icon: 'success',
                                    confirmButtonText: 'OK',
                                    allowOutsideClick: false,
                                    allowEscapeKey: false,
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        datatable.ajax.reload();
                                    }
                                });
                                break;
                            default:
                                Swal.fire({
                                    title: 'Recruitment deleted failed',
                                    icon: 'error',
                                    confirmButtonText: 'OK',
                                    allowOutsideClick: false,
                                    allowEscapeKey: false,
                                });
                                break;
                        }
                    },
                });
            }

        })
    }

    function showDetail(event, id) {
        event.preventDefault();
        console.log($("#recruitment-detail-id").text(id));
        $.ajax({
            url: "${pageContext.request.contextPath}${URLConst.API_GET_RECRUITMENT_DETAIL_URL}?id=" + id,
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