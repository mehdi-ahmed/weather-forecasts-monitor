"# weather-forecasts-monitor" 

Possible use of :
spring retry ?
actuator and monitoring spring boot admin
spring shell?

Elastic search? ELK?
MongoDB how to?
Mongo 4.0.5 as windows service


Implement a “Health-Check” Endpoint


spring-shell-starter ??? 
why no jar is generated 
what is shell>





We recommend making calls to the API no more than one time every 10 minutes 
for one location (city / coordinates / zip-code). 
This is due to the fact that weather data in our system is 
updated no more than one time every 10 minutes.


 The server name is api.openweathermap.org. 
 Please, never use the IP address of the server.
 
 
 Better call API by city ID instead of a city name, city coordinates or zip code to get a precise result. 
 The list of cities' IDs is here.
 
 
 Please, mind that Free and Startup accounts have limited data availability. 
 If you do not receive a response from the API, please, wait at least for 10 min and then repeat your request. 
 We also recommend you to store your previous request.
 
 
 scheduling
 The method should have a void return type.
 The method should not accept any arguments.
 
 