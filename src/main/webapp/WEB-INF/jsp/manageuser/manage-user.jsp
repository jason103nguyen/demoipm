<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page import="com.demoipm.consts.URLConst" %>
<t:template>
    <jsp:attribute name="title">
        Manage User
    </jsp:attribute>
    <jsp:attribute name="header">
        <h1 class="text-warning text-center m-3">MANAGE USER</h1>
    </jsp:attribute>
    <jsp:attribute name="content">
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
    </jsp:attribute>
    <jsp:attribute name="customScript">
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
                    {"targets": "_all", "orderable": false, "className": "text-center"},
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
                            let editBtn = `<a class="btn btn-sm btn-warning mx-2" href="${pageContext.request.contextPath}${URLConst.UPDATE_USER_PAGE_URL}?username=` + row.username + `">Edit</a>`;
                            let deleteBtn = `<a class="btn btn-sm btn-danger mx-2" href="#" onclick="deleteUser(event, '` + row.username + `')">Delete</a>`;
                            return editBtn + deleteBtn;
                        },
                        "className": "text-center"
                    }
                ],
            });

            $('#user-datatable_length').append(`<a class="btn btn-sm btn-success mx-2" href="${pageContext.request.contextPath}${URLConst.CREATE_USER_PAGE_URL}">Add new</a>`);

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
    </jsp:attribute>
</t:template>