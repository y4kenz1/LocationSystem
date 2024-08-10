Location system 
 
Description 
It’s required to implement a web-server that will provide functionality for users and his locations. 
Person can have multiple locations (zero or one are possible), for example home, parent's home, office, etc. Owner of the location can share location with friends (users from the system). Owner can share location to friends either with read only access or with admin access. Admin access allows friend user to add other friends to the owner's location. One user can have his own locations and be added to different shared locations. 
 
Web-server should provide the following possibilities: 
1.	Register new  user account by email; 
2.	Create location; 
3.	Share location with another user from the system; 
4.	Get all friend users on the location; 
5.	Manage access for friend user on owner’s location; 
6.	Get all locations available for user including shared locations. 
 
Additional requirements 
Person information should have at least name and email. Email must be valid. Location should have at least location name and address in US format with blank validation for required fields. 
The project must be run with one command. For data storage in-memory database should be used.  
Server APIs should accept JSON objects, Spring MVC is not allowed to use. 
Application should be tested using Spock Framework and code coverage must be at least 85%. Also 1 integration test must be created. 
The project should be built using Gradle wrapper and stored in GitHub. Project should have readable and logical commits history. 
 
Technical requirements 
Versions: 
•	Java 8 (OpenJDK) 
•	Spring Boot 2.6.4 
•	Gradle 6.8.3 
•	Spock Framework 1.3-groovy-2.4 
•	MapStruct 1.4.2.Final 
