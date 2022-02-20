<!DOCTYPE HTML>
<html xmlns:th="http://thymeleaf.org">
   <head>
       <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
       <%@page contentType="text/html" pageEncoding="UTF-8"%>
       <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
       <link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
       <link rel="icon" type="image/png" href="../assets/img/favicon.png">
       <meta name="viewport" content="width=device-width, initial-scale=1">
       <title>
           Thêm Câu Hỏi Bài Test
       </title>
       <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
       <!--     Fonts and icons     -->
       <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
       <link href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
       <!-- CSS Files -->
       <link href="../assets/css/bootstrap.min.css" rel="stylesheet" />
       <link href="../assets/css/paper-dashboard.css?v=2.0.0" rel="stylesheet" />
       <!-- CSS Just for demo purpose, don't include it in your project -->
       <link href="../assets/demo/demo.css" rel="stylesheet" />

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
       <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
       <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
       <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
       <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
       <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
       <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   </head>
   <body class="">
   <div class="wrapper ">
       <!--silebarr chèn tại đây-->
<%--
       <jsp:include page="SideMenuQLCauHoi.jsp"></jsp:include>
--%>
       <div class="main-panel">
           <!-- Navbar -->
           <!---Navbar chèn tại đây-->
<%--
           <jsp:include page="NavBarQLCauHoi.jsp"></jsp:include>
--%>
           <!-- End Navbar -->
           <!-- <div class="panel-header panel-header-sm">


     </div> -->
           <div class="content">
               <div class="row">
                   <div class="col-md-12">
                       <div class="card">
                           <div class="">

                               <div class="row">
                                   <div class="col-md-6">
                                       <h1 class="card-title"><b> Thêm câu hỏi mới </b> </h1>
                                   </div>

                               </div>

                           </div>
                       </div>
                       <div class="row">
                           <div class="col-md-2">
                               <h1 class="card-title"><b></b> </h1>
                           </div>
                       </div>
                       <div class="row">
                           <div class="justify-content-center">
                               <div class="card card-chart">
                                   <div class="card-header">
                                       <h5 class="card-title">Danh sách câu trả lời</h5>
                                   </div>
                                   <div class="card-body">
                                       <form th:action="@{/question/create}" method="post" th:object="${questionRequest}">
                                           <div class="card-body ">
                                               <div class="input-group mb-4">
                                                   <div class="form-control" style="border:none">
                                                       <p><b>Lĩnh vực</b></p>
                                                       <select type="text" id="skill" class="form-control" name="skill">
                                                           <option> Skill</option>
                                                           <option>Java</option>
                                                           <option>C</option>
                                                           <option>Python</option>
                                                       </select>
                                                   </div>
                                               </div>

                                           </div>
                                           <table><p> <b> Nội dung câu hỏi </b> </p>
                                               <textarea id="content" name="content" class="form-control p-2 my-2" rows="5" ></textarea></table>
                                           <ul class="nav flex-column" name="">
                                               <li class="nav-item">
                                                   <div class="input-group-text">
                                                       <input type="radio" id="answer1" name="answer1" class="mr-2" aria-label="Radio button for following text input">
                                                       <input type="text" id="option1" class="form-control" name="option1" aria-label="Text input with radio button">
                                                   </div>
                                               </li>
                                               <li class="nav-item">
                                                   <div class="input-group-text">
                                                       <input type="radio" id="answer2" name="answer2" class="mr-2" aria-label="Radio button for following text input">
                                                       <input type="text" id="option2" class="form-control" name="option2" aria-label="Text input with radio button">
                                                   </div>
                                               </li>
                                               <li class="nav-item">
                                                   <div class="input-group-text">
                                                       <input type="radio" id="answer3" name="answer3" class="mr-2" aria-label="Radio button for following text input">
                                                       <input type="text" id="option3" class="form-control" name="option3" aria-label="Text input with radio button">
                                                   </div>
                                               </li>
                                               <li class="nav-item">
                                                   <div class="input-group-text">
                                                       <input type="radio" id="answer4"  name="answer4" class="mr-2" aria-label="Radio button for following text input">
                                                       <input type="text" id="option4" class="form-control" name="option4" aria-label="Text input with radio button">
                                                   </div>
                                               </li>
                                           </ul>
                                           <div class="card-footer">
                                               <hr/>
                                               <div class="card-stats">
                                                   <button type="submit" class="col-md-12 btn btn-success"> <i class="fa fa-plus"></i> Thêm câu hỏi</button>
                                               </div>
                                           </div>
                                       </form>
                                   </div>
                               </div>
                           </div>
                       </div>
                       <div class="row justify-content-center">
                           <button class="btn btn-info col-3"> <i class="fa fa-save"></i> Entry Test </button>
                       </div>
                   </div>
               </div>
<%--
               <jsp:include page="FooterQLCauHoi.jsp"></jsp:include>
--%>
           </div>


           <!--   Core JS Files   -->
           <script src="../assets/js/core/jquery.min.js"></script>
           <script src="../assets/js/core/popper.min.js"></script>
           <script src="../assets/js/core/bootstrap.min.js"></script>
           <script src="../assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
           <!--  Google Maps Plugin    -->
           <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
           <!-- Chart JS -->
           <script src="../assets/js/plugins/chartjs.min.js"></script>
           <!--  Notifications Plugin    -->
           <script src="../assets/js/plugins/bootstrap-notify.js"></script>
           <!-- Control Center for Now Ui Dashboard: parallax effects, scripts for the example pages etc -->
           <script src="../assets/js/paper-dashboard.min.js?v=2.0.0" type="text/javascript"></script>
           <!-- Paper Dashboard DEMO methods, don't include it in your project! -->
           <script src="../assets/demo/demo.js"></script>
   </body>


</html>