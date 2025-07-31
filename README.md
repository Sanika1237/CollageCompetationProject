# CollageCompetationProject

Step 1: create a spring boot project using spring initializer with dependencies

Spring Web

Spring Data JPA

Spring Security

MySQL Driver

Step 2: add configuration in application.properties

Step 3: create a usersData.sql file 

               -Database name: collegeCdb
            
               -Table names: 
            
            1.users
            
            2.competitions
                          
Step 4:create a Entity package

class Files:

 1.User.java
                    -declare table name first
                    
                   -declare column names with annotation
                   
                    -create a constructor for payload data and response data 
                    
                    -declare getter() and setter() methods
                    
 2.Competation.java
 
                   -declare table name first
                   
                   -declare column names with annotation
                   
                    -create a constructor for payload data and response data 
                    
                    -declare getter() and setter() methods

Step 5: create a repository package

Interface files: 

1.	UserRepository

                -extends JpaRepository to provide CRUD operations for User entities

               -It includes derived and custom queries using both JPQL and native SQL to fetch users

               -Methods like findUsersByRole and countUsersByRole help in filtering users by their roles.

2.CompetationRepository

            -extends JpaRepository to manage Competition entities with standard CRUD and custom query methods

            -defines both JPQL and native SQL queries to fetch competitions by user ID, competition name, date, or a combination of name and date

Step 6: create a service package

Class files: 

1.UserService

            -it handles logic related to User entities

            -It uses UserRepository for database interactions and PasswordEncoder to securely hash user passwords during registration

 2. CompetitionService
    
                              - create private variable first
    
                              -create constructor for this variables
    
                               -create registerCompetition function
    
                               -create list for competation

Step 7: create a config package 

Class files:

 1. SecurityConfig
    
                    -create private variable first
    
                    -create constructor for this variable
    
 2. JwtUtil
    
                    -create private variables first
    
                     -create constructor for this variable
    
 3. JwtAuthenticationFilter

Step 8: create controller package

Class files: 

1. AdminController
   
                 -It uses UserRepository to access user data and is mapped under the “/admin” URL path.

                 -The “/users” endpoint returns all users, while “/users/byDate” and “/users/byname” fetch users based on competition date and competition name, respectively.
 
2. AuthController:
               
3. UserController:
   
Step 9: create exception package

Class file:

1.GlobalExceptionHandle

Exception file:

1.ResourceNotFoundException

2.UnauthorizedException

Step 10: create apis using postman

Admin apis

  GET /admin/users
  
  GET /admin/users/by-date
  
  GET /admin/users/name/{name}
  
  GET /admin/users/by-competition

Auth apis

  POST /auth/admin/signup
  
  POST /auth/admin/login
  
  POST/auth/user/signup
  
  POST/auth/user/login
  
User apis

  POST /user/competitions/register
  
  GET/user/competitions/{userid}
  
  GET /user/competitions

step 11: create frontend using html and css 

first create : index.html

Admin folder:

1.dashboard.html

2.login.html

3.signup.html

Css folder:

style.css

User folder:

1. competations.html

2. login.html

3.register.html

4.signup.html
