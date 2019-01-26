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
 
 
Java 7’s Try-With-Resource Statement
Another option is the try-with-resource statement which I explained in more detail in my introduction to Java exception handling.

You can use it if your resource implements the AutoCloseable interface.
 That’s what most Java standard resources do. When you open the resource in the try clause, 
it will get automatically closed after the try block got executed, or an exception handled.


cron
Let’s understand the cron expression; it consists of six fields:

1
<second> <minute> <hour> <day-of-month> <month> <day-of-week> <year> <command>
0         0/11

every 10 sec  = "*/10 * * * * *"



* * */3 * *  that says, every minute of every hour on every three days. 

0 0 */3 * *  says at 00:00 (midnight) every three days.