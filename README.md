# AccessingDataMySQL

## Introduction

It's a spring-boot project that has been written for the purpose of learning to develop CRUD applications. It features basic mappings, that you can access via specific endpoints. Program can be tested using Postman.

## Requirements

- Java version 15
- Latest version of maven (3.8.5)
- Relational database management system, in this project latest version of MySQL (8.0) has been used
- Create database named users
- Postman application

## Setup guide
1. Login to mysql and create database and user
```
create database users; -- Creates the new database
create user 'testuser'@'%' identified by 'testuser'; -- Create the user
grant all on users.* to 'testuser'@'%'; -- Give all privileges to the newly created user
```
2. (Optional) Edit the application.properties file if you are using different credentials to log in to your database.
```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/users -- edit your database <mysql> port that connects you to database <3306> and the name of the schema/database <users> 
spring.datasource.username=<your username> -- edit username
spring.datasource.password=<your password> -- edit password
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```
3. Go to root directory where you have installed project and run the following command
```
/mvnw spring-boot:run
```

## Project architecture

```
-- accessing-data-mysql
  |-- AccessingDataMySqlApplication.java
  |-- controllers
  |   |-- BookController.java
  |   |-- UserController.java
  |-- entities
  |   |-- Book.java
  |   |-- User.java
  |-- exceptions
  |   |-- ControllerExceptionHandler.java
  |   |-- ErrorMessage.java
  |   |-- ResourceNotFoundException.java
  |-- repositories
  |   |-- BookRepository.java
  |   |-- UserRepository.java
```

## Postman collection
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/2cdd5c955c11b958dc85?action=collection%2Fimport)

## Future plans
1. Add Postman collection :heavy_check_mark:
2. Separate logic from controllers to service layer :x:
3. Create frontend :x:
4. Learn about Spring Security and implement it :x:
