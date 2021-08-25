# InsulinGlucagonPump

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


6. Add the dependencies and libraries if the IDE cannot detect them automatically.

7. Open the Terminal and create the database called InsulinPump
  ```sh
    $mysql -u mysql -p mysql
  ```
  
  ```sh
    mysql> CREATE DATABASE InsulinPump
  ```
  
8. Connect to the Database InsulinPump 

  ```sh
    mysql> connect InsulinPump
  ```
  
9. Create new table called Persons
  
  ```sh
    mysql> CREATE TABLE Persons
            (
            username varchar(255),
            email varchar(255),
            age varchar(255),
            weight varchar(255),
            height varchar(255)
            );
  ```
  
10. To view Table content, connect to the Database InsulinPump and simply query

  ```sh
    mysql> select * from Persons
  ```
  
