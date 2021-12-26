<!DOCTYPE HTML>
<html xmlns:th="http://thymeleaf.org">
   <head>
       <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
       <%@page contentType="text/html" pageEncoding="UTF-8"%>
       <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
       <title>Hẹn Lịch Phỏng Vấn</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
   </head>
   <body>
   <div class ="container">
       <div class="row" align="center">
           <div >
               <h1>Hẹn Lịch Phỏng Vấn</h1>
           </div>
           <form th:action="@{setup_success}" method="post" th:object="${interview}">

               <div>
                   <label>Tên:</label>
                   <input type="text" id="nameInterview" class="form-control" name="nameInterview">
               </div>
               <div>
                   <label>Ngày Phỏng Vấn:</label>
                   <input type="text" id="date" class="form-control" name="date">
               </div>
               <div>
                   <label>Giờ Phỏng Vấn:</label>
                   <input type="text" id="timeInterview" class="form-control" name="timeInterview">
               </div>
               <div>
                   <label>Địa Điểm:</label>
                   <input type="text" id="location" class="form-control" name="location">
               </div>
               <br>
               <div align="center">
                   <button type="submit" class="btn btn-primary">Huỷ</button>
                   <button type="submit" class="btn btn-primary">Liên Hệ</button>
               </div>
           </form>
       </div>
   </div>


   </body>
  
</html>