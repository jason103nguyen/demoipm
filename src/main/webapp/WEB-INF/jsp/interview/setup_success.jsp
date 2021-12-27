<!DOCTYPE HTML>
<html>
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
       <p th:text="${interviewRequest.nameInterviewer}"></p>
       <p th:text="${interviewRequest.date}"></p>
       <p th:text="${interviewRequest.timeInterview}"></p>
       <p th:text="${interviewRequest.location}"></p>
       <p th:text="${interviewRequest.contactForm}"></p>
       </div>
   </div>

   </body>
  
</html>