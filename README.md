# Interview Process Management

Interview Process Management (IPM) is a program that helps automatically manage interview process in FPT Software from potential candidate management to the end of the process when candidates become official staff.

## Getting Started

### Dependencies

* Web server: Tomcat 9.x
* Java version: JDK - 8
* Maven: 3.x
* MySQL: 8.x

### Installing

* Modify information of your local MySQL database in application.properties file
```
spring.datasource.url=jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME?createDatabaseIfNotExist=true
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```
* Build jar file (if you use IDE to run SpringBoot project, please skip the step)
```
mvn clean install
```
* demoipm-0.0.1-SNAPSHOT.jar will appear in /target folder
### Executing program

* From command line run
```
java -jar demoipm-0.0.1-SNAPSHOT.jar
```
* After application running, access local web page to login
```
http://localhost:8080/login
```
* Accounts below according to the roles
```
| Username       | Password    | Role        |
| -------------- | ----------- | ----------- |
| admin          | admin       | ADMIN       |
| hr             | hr          | HR          |
| interviewer    | interviewer | INTERVIEWER |
| superadmin     | superadmin  | (3 above)   |
```