<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="com.demoipm.consts.URLConst" %>
<t:template>
    <jsp:attribute name="title">
        Create New Recruitment
    </jsp:attribute>
    <jsp:attribute name="header">
        <h1 class="text-warning text-center m-3">CREATE NEW RECRUITMENT</h1>
    </jsp:attribute>
    <jsp:attribute name="content">
        <c:if test="${response != null && response.hasError()}">
            <div class="alert alert-danger" role="alert">
                    ${response.getMessage()}
            </div>
        </c:if>
        <form:form id="recruitment-create-form" modelAttribute="recruitment" action="${pageContext.request.contextPath}${URLConst.API_PROCESS_CREATE_RECRUITMENT_URL}" method="post">
            <table class="table">
                <tr>
                    <td class="w-50">
                        <div class="mb-3">
                            <label for="career-selection" class="form-label fw-bold">Career</label>
                            <form:select id="career-selection" class="form-select" aria-label="Default select example" path="careerId">
                            </form:select>
                            <span id="careerId-error" class="error form-text text-danger fst-italic"></span>
                        </div>
                    </td>
                    <td class="w-50">
                        <div class="mb-3">
                            <label for="job-selection" class="form-label fw-bold">Job</label>
                            <form:select id="job-selection" class="form-select" aria-label="Default select example" path="jobId">
                            </form:select>
                            <span id="jobId-error" class="error form-text text-danger fst-italic"></span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="w-50">
                        <div class="mb-3">
                            <label for="quantity" class="form-label fw-bold">Quantity</label>
                            <form:input path="quantity" type="number" class="form-control" id="quantity" placeholder="0"/>
                            <span id="quantity-error" class="error form-text text-danger fst-italic"></span>
                        </div>
                    </td>
                    <td class="w-50">
                        <div class="mb-3">
                            <label class="form-label fw-bold">Salary range</label>
                            <div class="d-flex">
                                <div class="d-flex flex-column w-50">
                                    <form:input path="minSalary" type="number" class="form-control" id="minSalary" min="100" step="100" placeholder="Min..."/>
                                    <span id="minSalary-error" class="error form-text text-danger fst-italic"></span>
                                </div>
                                <div class="d-flex flex-column w-50">
                                    <form:input path="maxSalary" type="number" class="form-control" id="maxSalary" min="100" step="100" placeholder="Max..."/>
                                    <span id="maxSalary-error" class="error form-text text-danger fst-italic"></span>
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
                            <span id="skillIds-error" class="error form-text text-danger fst-italic"></span>
                        </div>
                    </td>
                    <td class="w-50">
                        <div class="mb-3">
                            <label class="form-label fw-bold">Date range</label>
                            <div class="d-flex flex-row">
                                <div class="d-flex flex-column w-50">
                                    <form:input path="startDate" type="date" class="form-control" placeholder="dd-mm-yyyy" />
                                    <span id="startDate-error" class="error form-text text-danger fst-italic"></span>
                                </div>
                                <div class="d-flex flex-column w-50">
                                    <form:input path="endDate" type="date" class="form-control" placeholder="dd-mm-yyyy" />
                                    <span id="endDate-error" class="error form-text text-danger fst-italic"></span>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
            <button type="submit" class="btn btn-primary">Create</button>
        </form:form>
    </jsp:attribute>
    <jsp:attribute name="customScript">
        <script>
            $('#career-selection').select2({
                theme: "bootstrap-5",
                placeholder: "Please choose career",
                ajax: {
                    url: "${pageContext.request.contextPath}${URLConst.API_GET_CAREER_SELECTION_URL}",
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
                        return "${pageContext.request.contextPath}${URLConst.API_GET_JOB_SELECTION_URL}?careerId=" + careerId;
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
                        return "${pageContext.request.contextPath}${URLConst.API_GET_SKILL_SELECTION_URL}?jobId=" + jobId;
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

            $(".error").hide();
            $("#recruitment-create-form").submit(function (event) {
                event.preventDefault();
                let formData = $("#recruitment-create-form").serializeArray();
                console.log(formData);
                $(".error").hide();
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}${URLConst.API_PROCESS_CREATE_RECRUITMENT_URL}",
                    data: formData,
                    dataType: "json",
                    encode: true,
                    complete: function(response){
                        const responseBody = response.responseJSON;
                        switch (response.status) {
                            case 200:
                                Swal.fire({
                                    title: 'Recruitment saved successfully',
                                    icon: 'success',
                                    confirmButtonText: 'OK',
                                    allowOutsideClick: false,
                                    allowEscapeKey: false,
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        window.location.href = "${pageContext.request.contextPath}${URLConst.MANAGE_RECRUITMENT_URL}";
                                    }
                                });
                                break;
                            case 400:
                                console.log(response);
                                const data = responseBody.fieldErrors;
                                data.forEach(error => {
                                    let errorFieldName = "#" + error.field + "-error";
                                    let errorFieldMessage = error.defaultMessage;
                                    $(errorFieldName).text(errorFieldMessage);
                                    $(errorFieldName).show();
                                });
                                break;
                            default:
                                Swal.fire({
                                    title: responseBody.message,
                                    icon: 'error',
                                    confirmButtonText: 'OK',
                                    allowOutsideClick: false,
                                    allowEscapeKey: false,
                                });
                                break;
                        }
                    },
                });
            });
        </script>
    </jsp:attribute>
</t:template>