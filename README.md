# Restful CRUD API using Spring.

### Getting Started
This is an API that supports basic CRUD operations:
* Create a product.
* Retrieve a product.
* Update a product.
* Delete a product.

The API also support:
* Retrieving all products.
* Placing an order.
* Retrieving all orders. 
* Retrieving all orders within a given time period.

### Prerequisites
You are required to have few tools before you start with using the source code.
- JDK 1.8
- Maven
- Git

Install JDK, Maven and Git as first step, before you get the the code base setup.
### Documentation

The API documentation can be found [here](https://documenter.getpostman.com/view/9134047/Tzm2JxzT). It is documented with the help of the tool named [Postman](https://www.postman.com/)  because it provides dynamic examples and machine-readable instructions.

### Steps to Setup

**1. Clone the application.**

```bash
https://github.com/KyriakosZorbas/spring-crude-app.git
```

**2. Build and run the app using maven.**

```bash
mvn package
java -jar target/spring-crude-app-1.0.0-SNAPSHOT.jar
```
The app is listening in port http://localhost:8080

### H2 Persistent Storage

This application is using **H2** database in file-based persistence mode.The default path is:

* src/main/resources/data/

In order to change the path where the data are saved edit the following file:

*  src/main/resources/application.properties

And look for this line 
```bash
spring.datasource.url=jdbc:h2:file:~/src/main/resources/data/products
```

# Docker Setup

### Pre-requisities
- Install [Docker](https://github.com/docker).
- Install [docker-compose](https://docs.docker.com/get-started/).

### Spring Crude App Installation

To install Spring Crude App follow the next steps:
1. Create a folder named `spring-crude-app`.
2. Inside `spring-crude-app` create a folder named `data`. This folder will be used to persist our data.
3. Copy this [docker-compose.yml](https://github.com/KyriakosZorbas/spring-crude-app/blob/main/Docker/docker-compose.yml) to the `spring-crude-app` folder.
3. Execute the following command

```sh
docker-compose -f {docker-compose.yml path} up
```
The app is listening in port http://localhost:8080

The Docker image is in [Dockerhub](https://hub.docker.com/repository/docker/zorbaskyriakos/spring-crude-app).

# Authentication

If we want to add authentication we can use a third-party tool named [Auth0](https://auth0.com/). Auth0 is an easy to implement, adaptable authentication and authorization platform. Auth0 supports multiple protocols, one of the most suitable for this app's case would be **OAuth 2.0** which allows a user to grant limited access to their resources on one site to another site, without having to expose their credentials.

# Considerations

CRUD-based services can be usefull , but there are some things that need to be considered.

1) The CRUD-based services can include a lot of unnecessary methods.The premise is that if you have a data table, you'll require a general set of methods to manipulate the data in it. We could add some simple retrieval techniques to allow sorting, searching, and listing while we're at it.

2) This generic entity management technique omits any important business logic connected with data entities. CRUD-based repositories are considered "useful" because they serve as "building blocks" for system functionality that is wrapped elsewhere. Without proper discipline this can encourage business logic to become fragmented as new systems or modules leverage the CRUD based module to create conflicting rules.

# License

GNU General Public License v3.0, see [LICENSE](https://github.com/KyriakosZorbas/spring-crude-app/blob/main/LICENSE).
