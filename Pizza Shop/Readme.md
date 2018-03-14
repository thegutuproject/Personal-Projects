Pizza Shop Application

This program will simply sort a list of pizza orders lexicographically. If two orders are the same, then the order date and time will be used.

You can run the program using the jar by running this command

    java 

One of the assumptions made is all data provided via file input is in a specific format:

    Food        Order

    pizza       1506176687

    etc         etc

where each item under the "Order" column is represented by epoch time.

There are a few assumptions made about the data that comes in from the user.
For starters, all data is to be represented in epoch time format. Furthermore, any spelling mistakes by the user (Pizza VS pizza) will be considered as their OWN separate orders.
Timezone information is not being tracked, the program runs off of UTC timezone, so please be mindful when inputting data. You can use https://www.epochconverter.com/ to see the difference between your timezone and UTC for each epoch time value you enter.

The program will run on localhost:8080, and has a REST API implemented, although very very basic. This ties in to a MySQL database, which will be used to store the data (in the future) from each user/shop owner. Not all database access functions are implemented, since that would be out of the scope of this application, but some are.

Swagger is implemented, and the API can be explored at http://localhost:8080/swagger-ui.html#/order-entry-resource or http://localhost:8080/swagger-ui.html#/order-log-resource.

There are some unit tests implemented, but not a terribly large amount due to the simplicity of this application. Also note, error handling at the DAO level is very spotty at best, there are better opportunities to handle exceptions, but that would also be out of the scope of this project. 

The one thing missing from this is running tests via commandline. It seems like in order to do so, you'd need to create a larger JAR file with test files loaded, and then have a condition in the main application to run the tests if a certain param is passed (?) 