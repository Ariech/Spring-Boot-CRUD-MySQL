# AccessingDataMySQL

## Requirments

- Java version 15
- Latest version of maven (3.8.5)
- Relational database managment system, in this project latest version of MySQL (8.0) has been used
- Create database named users

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
