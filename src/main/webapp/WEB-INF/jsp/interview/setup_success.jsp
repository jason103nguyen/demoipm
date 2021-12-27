<!DOCTYPE HTML>
<html xmlns:th="http://thymeleaf.org">
   <head>
       <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
       <%@page contentType="text/html" pageEncoding="UTF-8"%>
       <title>Setup Successfully</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
   </head>
   <body>
   <div align="center">
       <h1>Setup Successfully!</h1>
       <p th:text="${interview.nameInterview}"></p>
       <p th:text="${interview.date}"></p>
       <p th:text="${interview.timeInterview}"></p>
       <p th:text="${interview.location}"></p>
       <p th:text="${interview.contactForm}"></p>
       </div>
   </div>

   </body>
  
</html>