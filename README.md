# Introduction
This project contains two parts: a website which is written in php and a desktop application written in Java. To run the application correctly, make sure you have a webserver running.

## Installation and Testing:
### Webpage component
To deploy the web page component of this application, follow the steps below:

   Start a webserver on *localhost*
   Import the database *database/prestige_villa_db.sql* into MySQL
   Copy the *prestige-website/theprestigevilla* directory to your websever
   Goto 'localhost/theprestigevilla' from a web browser to access the website

### Desktop component   
To run the desktop application, compile the *prestige-desktop* gradle project into a jar file, and run the jar file. The first window of the application would as for your user ID and password. Used the user ID **0001** and a password of **admin1234** to gain access to the system as admin, which allow you to make changes such as adding a new user. Alternatively, you can make changes directly from the *users* table of the database.
