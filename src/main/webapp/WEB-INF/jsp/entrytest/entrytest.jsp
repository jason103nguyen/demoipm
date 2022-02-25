<!DOCTYPE HTML>
<html xmlns:th="http://thymeleaf.org">
   <head>
       <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
       <%@page contentType="text/html" pageEncoding="UTF-8"%>
       <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
       <link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
       <link rel="icon" type="image/png" href="../assets/img/favicon.png">
       <meta name="viewport" content="width=device-width, initial-scale=1">
       <title>
           Create Entry Test
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
   <div class="header">
       <div class="row">
           <div class="col-md-4">
               <div class="logo"><img src="../img/LogoFPT.jpg" alt="logo fpt" width="20%"></div>
           </div>
           <div class="col-md-8">
               <div><h1 class="Title" style="color: #00DD00">CREATE ENTRY TEST</h1></div>
           </div>
       </div>
   <div class="wrapper ">
       <div class="main-panel">
           <div class="content ">
               <div class="row ">
               <div class="col-md-4">
                   <div class="mb-3">
                       <div class="card card-user">
                           <form action="${pageContext.request.contextPath}/entrytest/question" method="get" >
                               <div class="row align-items-center justify-content-center">
                                   <div class="col-md-8">
                                       <div class="form-control" style="border:none">
                                           <p><b>Programming language</b></p>
                                           <tbody>
                                           <select type="text" class="form-control" name="skill">
                                               <option> Skill</option>
                                               <option>Java</option>
                                               <option>C</option>
                                               <option>C#</option>
                                               <option>Python</option>
                                               <option>JavaScript</option>
                                               <option>PHP</option>
                                           </select>
                                           </tbody>
                                       </div>
                                   </div>
                               </div>
                               <div class="row justify-content-center">
                                   <div class="col-md-9">
                                       <div class="card card-user border">
                                           <div class="card-header">
                                               <h4 class="card-title">Number of questions</h4>
                                           </div>
                                           <div class="col-md-12">
                                               <div class="row justify-content-center ">
                                                   <div class="col-md-6">
                                                       <div class="form-group justify-content-center text-center">
                                                           <p>Number of questions</p>
                                                           <input type="text" id="numberofQuestion" class="form-control" name="numberofQuestion">
                                                       </div>
                                                   </div>
                                                   </p>
                                               </div>
                                           </div>
                                       </div>
                                   </div>
                               </div>
                               <p>
                               <div class="row justify-content-center">
                                   <button type="submit" class="btn btn-primary btn-round col-4">Random</button>
                               </div>
                               </p>
                           </form>
                       </div>
                   </div>
                   <div class="mb-3">
                       <form action="${pageContext.request.contextPath}/entrytest/create" method="post" modelAttribute="entryTestRequest">
                           <div class="card">
                               <div class="card-header">
                                   <h4 class="card-title">Option</h4>
                               </div>
                               <div class="card-body">
                                   <ul class="list-unstyled ">
                                       <li>
                                           <div>
                                               <label>Start day</label>
                                           </div>
                                           <div class="mb-1">
                                               <input id="beginTest" type="date" class="form-control" name="beginTest"  placeholder="Select DateTime">
                                           </div>
                                       </li>
                                       <li>
                                           <div>
                                               <label>End day</label>
                                           </div>
                                           <div class="mb-1">
                                               <input id="finishTest" type="date" class="form-control" name="finishTest" placeholder="Select DateTime">
                                           </div>
                                       </li>
                                       <li>
                                           <div>
                                               <label>Start time</label>
                                           </div>
                                           <div class="mb-1">
                                               <input type="time" class="form-control" name="timeEntryTest" placeholder="Thời Lượng">
                                           </div>
                                       </li>

                                       <li>
                                           <div>
                                               <label>End time</label>
                                           </div>
                                           <div class="mb-1">
                                               <input type="time" class="form-control" name="timeEntryTest2" placeholder="Thời Lượng">
                                           </div>
                                       </li>

                                   </ul>
                               </div>
                           </div>

                   </div>
                   <div class="mb-3">

                       <div style="width:700px; margin:auto;padding:15px 32px; border:10px; ">
                           <button type="submit" class="btn btn-primary btn-round col-3 float-right ">Create Entry Test</button>
                               <a href="http://localhost:8081/question/create"><input type="button" class="btn btn-primary btn-round col-2 float-right" value="Back" /></a>
                       </div>

                   </div>
               </div>
                   <div class="col-md-8">
                       <div class="card card-user">
                           <div class="card-header">
                               <h5 class="card-title">List of question</h5>
                           </div>
                           <div class="card-body">
                               <table class="table">
                                   <thead>
                                   <tr>
                                       <th>No.</th>
                                       <th>Content</th>
                                       <th>Answer</th>
                                   </tr>
                                   </thead>
                                   <c:forEach var="questionAdd" items="${question}">
                                       <tbody>
                                       <tr>
                                           <input type="number" name="questionIds" value="${questionAdd.id }" hidden></input>
                                           <td id="questions">${questionAdd.id }</td>
                                           <td>${questionAdd.content }</td>
                                           <td>
                                               <p>A: ${questionAdd.option1 }</p>
                                               <p>B: ${questionAdd.option2 }</p>
                                               <p>C: ${questionAdd.option3 }</p>
                                               <p>D: ${questionAdd.option4 }</p>
                                           </td>
                                       </tr>
                                       </tbody>
                                   </c:forEach>
                               </table>
                           </div>
                       </div>
                   </div>
       </form>
                   </div>
           </div>
       </div>
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