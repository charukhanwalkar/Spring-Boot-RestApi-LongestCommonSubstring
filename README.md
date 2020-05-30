# Spring-Boot-RestApi-LongestCommonSubstring
This is a Spring Boot REST API used to demo how REST API work. The REST API will run on port#8080.



A POST request can be sent with a list of all String in JSON format for which we need to get the longest comman substring




The LCS(longest common substring ) is returned in the body of the request



If the strings are not unique a 422(UNPROCESSABLE_ENTITY) error is thrown



If the string list is empty 422(UNPROCESSABLE_ENTITY) error is thrown


Junit test cases are included to test the functionality of the API



Clone of download the repository in you local.



To build the project use this command in your command prompt--  mvn clean install



To run the project use this command in your command prompt ./mvnw spring-boot:run 

