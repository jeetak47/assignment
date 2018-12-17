# README

### Project setup and configuration
* For running this project requires JDK 8 and Maven 3+ installed  on system
* use `mvn spring-boot:run` to run this project it start http server here [http://localhost:9090/assignment/](http://localhost:9090/assignment/) 
* use `mvn test` for unit test

### Features 

#### This  app exposes following rest APIs 
* GET /assignment/user  get all users in db 
* GET /assignment/user/{id} return single user with id
* POST /assignment/user  with payload user,  user will be creative in db 
* DELETE /assignment/user with payload user, delete user with user.id
* PUT /assignment/user with payload user,  upate user with user.id
* Sample user playload
` {
        "username": "jon",
        "password": "P1245678",
        "status": "Activated"
    }`


### Notes
* This app doesn't provide ui so you can use any rest client/curl to test api
* This app is secured with basic authentication, credential are user:`admin` password: `Admin123`


 
 
