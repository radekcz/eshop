### E-Shop feature - example/skeleton of REST API

This java project consists of 2 modules:

- ***application*** - proper REST API based on Spring Boot framework built by Maven
    - API accepts JSON or XML input data.
    - Another input data converter can be easily added to class `EShopConverter` (there is an example of a converter) and application is ready to use this this new data format.
    - Application uses H2 in-memory database engine for storing data.
    - Entity used in project contains some required and some optional properties therefore there are custom validations in project too.
- ***acceptance-test*** - module contains independent acceptance tests for this REST API based on `RestTemplate` framework.


#### Running (application module)
- For running the application from an IDE, you can run application as a simple Java application - just run the main method of class `EshopApp`.
- Or you can run this `application` over maven command: `mvn spring-boot:run`


#### Testing

##### JUnit testing
JUnit tests are part of `application`. You can use `mvn test` to run JUnit tests over Maven.

##### Acceptance tests
As above, after running `application`, you can use `mvn test` on module **`acceptance-test`** to make run of acceptance tests independently of application.
