<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <title>Create New Recruitment</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>

    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.2.0/dist/select2-bootstrap-5-theme.min.css" />
</head>

<body class="bg-light">
<div class="container p-5 my-5 border">
    <div class="row">
        <div class="col-sm-3">
            <jsp:include page="../logoFpt.jsp" />
        </div>

        <div class="col-sm-9">
            <p class="h1 text-warning text-center">CREATE NEW RECRUITMENT</p>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-sm-3">
            <jsp:include page="../menu.jsp" />
        </div>

        <div class="col-sm-9">
            <c:if test="${response != null && response.hasError()}">
                <div class="alert alert-danger" role="alert">
                    ${response.getMessage()}
                </div>
            </c:if>
            <form:form modelAttribute="recruitment" action="${pageContext.request.contextPath}/process-create-recruitment" method="post">
                <table class="table">
                    <tr>
                        <td class="w-50">
                            <div class="mb-3">
                                <label for="career-selection" class="form-label fw-bold">Career</label>
                                <form:select id="career-selection" class="form-select" aria-label="Default select example" path="careerId">
                                </form:select>
                                <form:errors path="careerId" class="form-text text-danger fst-italic"></form:errors>
                            </div>
                        </td>
                        <td class="w-50">
                            <div class="mb-3">
                                <label for="job-selection" class="form-label fw-bold">Job</label>
                                <form:select id="job-selection" class="form-select" aria-label="Default select example" path="jobId">
                                </form:select>
                                <form:errors path="jobId" class="form-text text-danger fst-italic"></form:errors>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="w-50">
                            <div class="mb-3">
                                <label for="quantity" class="form-label fw-bold">Quantity</label>
                                <form:input path="quantity" type="number" class="form-control" id="quantity" placeholder="0"/>
                                <form:errors path="quantity" class="form-text text-danger fst-italic"></form:errors>
                            </div>
                        </td>
                        <td class="w-50">
                            <div class="mb-3">
                                <label class="form-label fw-bold">Salary range</label>
                                <div class="d-flex">
                                    <div class="d-flex flex-column w-50">
                                        <form:input path="minSalary" type="number" class="form-control" id="minSalary" min="100" step="100" placeholder="Min..."/>
                                        <form:errors path="minSalary" class="form-text text-danger fst-italic"></form:errors>
                                    </div>
                                    <div class="d-flex flex-column w-50">
                                        <form:input path="maxSalary" type="number" class="form-control" id="maxSalary" min="100" step="100" placeholder="Max..."/>
                                        <form:errors path="maxSalary" class="form-text text-danger fst-italic"></form:errors>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="w-50">
                            <div class="mb-3">
                                <label for="skill-selection" class="form-label fw-bold">Needed skills</label>
                                <form:select id="skill-selection" class="form-select" multiple="multiple" aria-label="multiple select" path="skillIds">
                                </form:select>
                                <form:errors path="skillIds" class="form-text text-danger fst-italic"></form:errors>
                            </div>
                        </td>
                        <td class="w-50">
                            <div class="mb-3">
                                <label class="form-label fw-bold">Date range</label>
                                <div class="d-flex flex-row">
                                <form:input path="startDate" type="date" class="form-control" />
                                <form:input path="endDate" type="date" class="form-control" />
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form:form>

        </div>
    </div>
</div>
<script>
    $('#career-selection').select2({
        theme: "bootstrap-5",
        placeholder: "Please choose career",
        ajax: {
            url: "${pageContext.request.contextPath}/api/get-career-selection",
            dataType: 'json',
            processResults: function (data) {
                let processData = data.map(item => ({id: item.id, text: item.career}));
                return {
                    results: processData
                };
            }
        },
        minimumResultsForSearch: -1
    });

    $('#job-selection').select2({
        theme: "bootstrap-5",
        placeholder: "Please choose job",
        ajax: {
            url: function () {
                let careerId = $('#career-selection').val();
                return "${pageContext.request.contextPath}/api/get-job-selection?careerId=" + careerId;
            },
            dataType: 'json',
            processResults: function (data) {
                let processData = data.map(item => ({id: item.id, text: item.job}));
                return {
                    results: processData
                };
            }
        },
        minimumResultsForSearch: -1
    });

    $('#skill-selection').select2({
        theme: "bootstrap-5",
        placeholder: "Please choose skills",
        ajax: {
            url: function () {
                let jobId = $('#job-selection').val();
                return "${pageContext.request.contextPath}/api/get-skill-selection?jobId=" + jobId;
            },
            dataType: 'json',
            processResults: function (data) {
                let processData = data.map(item => ({id: item.id, text: item.skill}));
                return {
                    results: processData
                };
            }
        },
        minimumResultsForSearch: -1
    });

    $('#career-selection').on('change', function () {
        $("#job-selection").val('').trigger('change');
    });

    $('#job-selection').on('change', function () {
        $("#skill-selection").val('').trigger('change');
    });
</script>
</body>

</html>