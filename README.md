# Events

## Idea

This project is a backend written in Java that simulates a database where an event (for example: a concert, a theater event etc...) can be inserted deleted and so on. Each event can be assigned a participant.

# How to use

## Introduction
There are two ways to run the project:

1. **For developers**: You can clone the repository and follow the guide below to set up the project manually.
2. **For users**: If you only want to use the backend, you can download the Docker image available at `pepppercoc/giornale`. The images are versioned, so you can choose the one you need. To run the project, you must have Docker installed and also download the MongoDB and Mongo Express images.

## For Users
### Prerequisites

Before running the project, make sure you have Docker installed on your machine.

### Docker Compose Configuration

Below is the `docker-compose.yml` file to set up the necessary services:


To start the Docker containers for MongoDB and Mongo Express, navigate to the project root and run:

```
docker-compose up --build
```

## For Developers
### Start Docker Containers

To start the Docker containers and run:

```
docker-compose up --build
```

Ensure that Docker is running for a successful setup.

### Install Java Dependencies

To install the required Java dependencies, move to the root of the project and execute:

```
mvn clean install
```

If you encounter errors related to the test suite or test cases, use:

```
mvn install -DskipTests
```

### Start the Project

To start the project, open `Basi2Application` in your IDE and run it.

## Check the Database

To inspect the database, go to [Mongo Express](http://localhost:8081/unimolab/) (localhost on port 8081).

## Try the API

To test the API, visit [Swagger UI](http://localhost:8080/swagger-ui/index.html) (localhost on port 8080 at `/swagger-ui`).
