<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page import="com.demoipm.consts.URLConst" %>
<t:template>
    <jsp:attribute name="title">
        Manage Recruitment
    </jsp:attribute>
    <jsp:attribute name="header">
        <h1 class="text-warning text-center m-3">MANAGE RECRUITMENT</h1>
    </jsp:attribute>
    <jsp:attribute name="content">
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
    </jsp:attribute>
    <jsp:attribute name="customScript">
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
                    {"targets": "_all", "orderable": false, "className": "text-center"},
                    {"data": "id", "targets": 0},
                    {"data": "job", "targets": 1},
                    {"data": "career", "targets": 2},
                    {"data": "quantity", "targets": 3},
                    {
                        "targets": 4,
                        "render": function (data, type, row) {
                            return `<a class="btn btn-sm btn-info mx-2" href="#" data-bs-toggle="modal" data-bs-target="#recruitment-detail" onclick="showDetail(event, ` + row.id + `)">Detail</a>`;
                        },
                        "className": "text-center"
                    },
                    {
                        "targets": 5,
                        "render": function (data, type, row) {
                            let editBtn = `<a class="btn btn-sm btn-warning mx-2" href="${pageContext.request.contextPath}/update-recruitment-page?id=` + row.id + `">Edit</a>`;
                            let deleteBtn = `<a class="btn btn-sm btn-danger mx-2" href="#" onclick="deleteUser(event, ` + row.id + `)">Delete</a>`;
                            return editBtn + deleteBtn;
                        },
                        "className": "text-center"
                    }
                ],
            });

            $('#recruitment-datatable_length').append(`<a class="btn btn-sm btn-success mx-2" href="${pageContext.request.contextPath}${URLConst.CREATE_RECRUITMENT_PAGE_URL}">Add new</a>`);

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
    </jsp:attribute>
</t:template>