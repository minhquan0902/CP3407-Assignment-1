# InsulinGlucagonPump


## CP3407- Project Description

Insulin pump is a medical system that simulates the movement of the pancreas. The software that controls this system is a built-in system that collects information from sensors and controls the pump that transmits a controlled amount of insulin to users. 
People with diabetes use this system. Diabetes is a common condition in which the pancreas cannot produce a hormone called insulin. Insulin metabolizes glucose in the blood. The conventional treatment for diabetes is regular injections of genetically modified insulin. Diabetics use an external meter to measure blood sugar and calculate the amount of insulin they should inject.
The problem with this treatment is that the insulin level required depends not only on the blood glucose level, but also on the last insulin injection time. This can significantly lower blood glucose levels or increase blood glucose levels (when insulin is too high). Hypoglycemia is more serious in the short run because it can lead to temporary brain malfunctions and eventually unconsciousness and death. However, if blood glucose levels continue to rise in the long run, eye damage, kidney damage, and heart disease may occur.

## UML Diagram

![InsulinGlucagonPump_UML](https://user-images.githubusercontent.com/58071533/130734918-f90673d7-c2e3-4355-a114-3759d34b75f9.png)


## Technology Used
1. Java Programming
2. JFreeChart Java
3. Java AWT as UI
4. Java DB MYSQL Connector
5. MySQL Server as backend

## Running the Project Locally guide
1. Install MySQL: https://dev.mysql.com/downloads/mysql/
    - Create a user "mysql" with password "mysql", This is vital because the main connector config for MYSQL within the App using this credentials. You can change this within the codes if you insist
    - Set up PATH variable in your operating system if the shell couldn't find "mysql" command


2. To inspect the database, I would recommend you using this software call Tableplus: https://tableplus.com/. From here you can inspect the data of the user that take the Insulin Simulator test.


3. Clone this Repo at:
  ```sh
    git clone https://github.com/minhquan0902/CP3407-Assignment-1.git
  ```
4. Open the Project with any IDE (VScode, IntelliJ or Eclipse,...)


5. Add the dependencies and libraries if the IDE cannot detect them automatically.

6. Open the Terminal and create the database called InsulinPump
  ```sh
    $mysql -u mysql -p mysql
  ```
  
  ```sh
    mysql> CREATE DATABASE InsulinPump
  ```
  
7. Connect to the Database InsulinPump 

  ```sh
    mysql> connect InsulinPump
  ```
  
8. Create new table called users for storing users
  
  ```sh
    mysql> CREATE TABLE users
            (
            id int NOT NULL AUTO_INCREMENT,
            username varchar(255) NOT NULL UNIQUE,
            email varchar(255) NOT NULL UNIQUE,
            password varchar(255),
            PRIMARY KEY (id)
            );
  ```
9. Create another table called users_detail for user detail

 ```sh
    mysql> CREATE TABLE users_detail
            (
            id int NOT NULL AUTO_INCREMENT,
            username varchar(255) NOT NULL UNIQUE,
            email varchar(255) NOT NULL UNIQUE,
            age   varchar(255),
            weight varchar(255),
            height varchar(255)
            PRIMARY KEY (id)
            );
  ```
  
10. To view Table content, connect to the Database InsulinPump and simply query

  ```sh
    mysql> select * from Persons
  ```
  
