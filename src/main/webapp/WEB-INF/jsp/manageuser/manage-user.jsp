<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.demoipm.consts.URLConst" %>
<!DOCTYPE HTML>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
    <title>Manage User</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
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
            <p class="h1 text-warning text-center">MANAGE USER</p>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-sm-3">
            <jsp:include page="../menu.jsp"/>
        </div>

        <div class="col-sm-9">
            <table id="user-datatable" class="table table-bordered table-striped">
                <thead>
                <tr class="text-center">
                    <th>No</th>
                    <th>Fullname</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Username</th>
                    <th>Role</th>
                    <th>Action</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>

</div>
<script>

    let datatable = $('#user-datatable').DataTable({
        serverSide: true,
        ajax: {
            url: '${pageContext.request.contextPath}${URLConst.API_GET_USER_BY_CONDITION_URL}',
            method: 'POST',
            dataSrc: 'data',
            contentType: "application/json",
            data: function (request) {
                return JSON.stringify(request);
            }
        },
        order: [],
        columnDefs: [
            {"targets": "_all", "orderable": false},
            {
                "targets": 0,
                "render": (data, type, row, meta) => {
                    return meta.row + 1;
                }
            },
            {"data": "fullName", "targets": 1},
            {"data": "phone", "targets": 2},
            {"data": "email", "targets": 3},
            {"data": "username", "targets": 4},
            {
                "data": "roles",
                "targets": 5,
                "render": data => {
                    let content = "";
                    data.forEach(element => {
                        content += element + '<br>';
                    });
                    return content;
                }
            },
            {
                "targets": 6,
                "render": function (data, type, row) {
                    let editBtn = `<a class="btn btn-sm btn-primary mx-2" href="${pageContext.request.contextPath}/update-user-page?username=` + row.username + `">Edit</a>`;
                    let deleteBtn = `<a class="btn btn-sm btn-primary mx-2" href="#" onclick="deleteUser(event, '` + row.username + `')">Delete</a>`;
                    return editBtn + deleteBtn;
                },
                "className": "text-center"
            }
        ],
    });

    $('#user-datatable_length').append(`<a class="btn btn-sm btn-primary mx-2" href="${pageContext.request.contextPath}${URLConst.CREATE_USER_PAGE_URL}">Add new</a>`);

    function deleteUser(event, username) {
        event.preventDefault();
        Swal.fire({
            title: 'Do you want to delete the user?',
            showDenyButton: true,
            confirmButtonText: 'Yes',
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    type: "DELETE",
                    url: "${pageContext.request.contextPath}${URLConst.API_DELETE_USER_URL}?username=" + username,
                    dataType: "json",
                    complete: function(response){
                        switch (response.status) {
                            case 200:
                                Swal.fire({
                                    title: 'User deleted successfully',
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
                                    title: 'User deleted failed',
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

</script>
</body>

</html>