<!DOCTYPE HTML>
<html xmlns:th="http://thymeleaf.org">
   <head>
       <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
       <%@page contentType="text/html" pageEncoding="UTF-8"%>
       <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
       <title>Hẹn Lịch Phỏng Vấn</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
       <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
       <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
       <script>
           flatpickr("input[type=date]", {});
       </script>
   </head>
   <body>
   <div class ="container">
       <div class="row" align="center">
           <div >
               <h1>Hẹn Lịch Phỏng Vấn</h1>
           </div>
           <form th:action="@{setup_success}" method="post" th:object="${interview}">
               <table border = "0">
                   <tbody>

                   <tr>
                       <td>
                           <label>Tên:</label>
                       </td>
                          <td>
                              <input type="text" id="nameInterview" class="form-control" name="nameInterview">
                          </td>
                   </tr>

                   <tr>
                       <td>
                           <label>Ngày Phỏng Vấn:</label>
                       </td>
                       <td>
                           <input type="date" placeholder="Select DateTime" id="date" class="form-control" name="date">
                       </td>
                   </tr>

                   <tr>
                       <td>
                           <label>Giờ Phỏng Vấn:</label>
                       </td>
                       <td>
                           <input type="time" id="timeInterview" class="form-control" name="timeInterview">
                       </td>
                   </tr>

                   <tr>
                       <td>
                           <label>Địa Điểm:</label>
                       </td>
                       <td>
                           <input type="text" id="location" class="form-control" name="location">
                       </td>
                   </tr>

                   <tr>
                       <td>
                           <label>Hình Thức Liên Hệ:</label>
                       </td>
                       <td>
                           <select type="text" id="contactForm" class="form-control" name="contactForm">
                               <option>Gửi Email</option>
                               <option>Liên Hệ Qua Điện Thoại</option>
                           </select>
                       </td>
                   </tr>

                   <tr>
                       <td>
                           <input type="reset" value="Huỷ" class="btn btn-primary">
                       </td>
                       <td>
                           <input type="submit" value="Liên Hệ" class="btn btn-primary">
                       </td>
                   </tr>

                   </tbody>
               </table>
           </form>
       </div>
   </div>


   </body>
  
</html>