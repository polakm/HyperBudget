# Hyper Budget

Project is a home budget management system. It's also my sandbox for learning and testing technologies, patterns and ideas.

### Modules

- ui
- api-gateway
- transaction-service
- transaction-summary-service
- account-service
- category-service
- service-discovery
- tracing-service

### Technologies

- Spring Framework
- Spring Boot
- Spring HATEOAS
- Spring Cloud
- Netflix Zuul
- Netflix Eureka
- Zipkin
- Swagger
- React
- JUnit
- Gradle

### Running environment

- JRE 8
- node.js
- Docker
- Linux

\* Only if you want to run on docker and build docker image. 
It could be different machine or virtual machine.

### Develop environment

- Git
- JDK 8
- InteliJ IDEA
- Docker Integration Plugin

### Prepare project

Download project form git 

```
git clone https://github.com/polakm/HyperBudget.git
```

Open root project directory in InteliJ IDEA

### Build

All services besides ui is compiling by gradle.
You cen run gradlew.bat files or tools in your IDE to build java modules
For ui you can use node.js command npm install

### Tests

All tests is writing in JUnit. Search a class with suffix Test.
In dictionary {MODULE}/src/test/java are test for that module

### Deployment & Run

If services have built you can use start_all.bat or {MODULE_NAME}/start.bat to run all or one module on localhost

Obviously you can start services on docker. Use the Docker.file to build docker images.
I recommend Docker Integration plugin
Remember to create common network for all services and publish ports for ui and api-gateway;

Examples:
```
docker build --build-arg JAR_FILE=libs/api-gateway-0.0.1-SNAPSHOT.jar -t api-gateway . && docker run --name api-gateway api-gateway
```

## Author

**Michał Polak**

See my other projects on my [GitHub](https://github.com/polakm)

If you can read polish, you can also visit my website [http://michalpolak.com.pl](https://michalpolak.com.pl/)

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE.md](LICENSE.md) file for details
