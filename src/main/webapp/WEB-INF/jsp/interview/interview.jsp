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
       <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
       <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
       <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
       <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   </head>
   <body>
   <div class ="container">
       <div class="row" align="center">
           <div >
               <h1>Hẹn Lịch Phỏng Vấn</h1>
           </div>
           <form th:action="@{/interview/create}" method="post" th:object="${interviewRequest}">
               <table border = "0">
                   <tbody>

                   <tr>
                       <td>
                           <label>Tên:</label>
                       </td>
                          <td>
                              <input type="text" id="nameInterviewer" class="form-control" name="nameInterviewer">
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
                               <option>Liên Hệ Qua Zalo</option>
                               <option>Liên Hệ Qua Facebook</option>
                           </select>
                       </td>
                   </tr>

                   <tr>
                       <td>
                           <input type="reset" value="Huỷ" class="btn btn-primary">
                       </td>
                       <td>
                           <button type="submit" id="submit" class="btn btn-primary">Liên Hệ</button>
                       </td>
                   </tr>
                   </tbody>
               </table>
           </form>
       </div>
   </div>
<script>
    $("#submit").click(function(){
        var location = $("#location").val();
        var timeInterview = $("#timeInterview").val();
        var date = $("#date").val();
        var nameInterview = $("#nameInterview").val();
        if(!location == '' || !timeInterview == '' || !date == '' || !nameInterview == '') {
            swal({
                title: "Setup Successfully!",
                icon: "success",
                button: "Ok",
            });
        }
    });
</script>


   </body>
  
</html>