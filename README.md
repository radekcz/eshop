### E-Shop feature - example of REST API

This java project consists of 2 modules:

- **application** - proper REST API based on Spring Boot built by Maven
    - API accepts JSON or XML input data.
    - Another input data converter can be added to class `EShopConverter` where is an example of a converter.
- **acceptance-test** - module contains independent acceptance tests for this REST API


#### Running (application)
- For running the application from an IDE, you can run application as a simple Java application - just run the main method of class `EshopApp`.
- Or you can run this application over maven command: `mvn spring-boot:run`


#### Testing

##### JUnit testing
JUnit tests are part of `application`. You can use `mvn test` to run unit test in Maven.

##### Acceptance tests
As above, after running `application`, you can use `mvn test` on module **`acceptance-test`** to make run of acceptance tests independently of application.
