# sprintboot-classloader-poc
Proof-of-concept Spring Boot REST API demonstrating Java ClassLoader mechanics. Dynamically loads external classes at runtime, executes methods, and unloads them after processing. Includes multithreading support to showcase concurrent class loading and isolation.
# ClassLoader

A Spring Boot application exploring ClassLoader concepts and functionality.

## Description

This project is part of the DSA (Data Structures and Algorithms) revision series and focuses on understanding and demonstrating ClassLoader behavior in Java.

## Technology Stack

- **Java Version**: 17
- **Spring Boot**: 4.0.5
- **Build Tool**: Maven
- **Framework**: Spring Boot Starter Web

## Project Structure

```
ClassLoader/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/zeeshan/
│   │   │       ├── classloader/
│   │   │       │   ├── ClassLoaderApplication.java
│   │   │       │   └── controller/
│   │   │       │       └── ClassLoaderController.java
│   │   │       └── external/
│   │   │           └── ExternalDataProvider.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/zeeshan/classloader/
│               └── ClassLoaderApplicationTests.java
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## Dependencies

- spring-boot-starter
- spring-boot-starter-web
- spring-boot-starter-test

## Building the Project

### Using Maven

```bash
mvn clean install
```

## Running the Application

### Using Maven

```bash
mvn spring-boot:run
```

### Using Java

```bash
java -jar target/ClassLoader-0.0.1-SNAPSHOT.jar
```

## Default Port

The application runs on `http://localhost:8080` by default.

## API Endpoints

### Process External Class
Dynamically loads and executes methods from external classes using ClassLoader.

**Endpoint:**
```
GET http://localhost:8080/api/process?filePath=/tmp/external/&className=ExternalDataProvider
```

**Parameters:**
- `filePath` (required): The directory path containing the compiled class files
- `className` (required): The fully qualified class name to load and execute

**Example Request:**
```bash
curl "http://localhost:8080/api/process?filePath=/tmp/external/&className=ExternalDataProvider"
```

**Example Response:**
```
Data fetched: Hello from external class!
```

**How it Works:**
1. Creates a URLClassLoader pointing to the specified directory
2. Dynamically loads the class by name
3. Creates an instance of the class
4. Invokes the `fetchData()` method using reflection
5. Returns the result

## Features

- Spring Boot application setup
- ClassLoader demonstration
- REST Controller implementation
- External data provider integration
- Dynamic class loading via URL ClassLoader
- Reflection-based method invocation

## Contributing

Feel free to contribute to this project by submitting issues or pull requests.

## Author

Zeeshan

---

For more information about ClassLoaders in Java, refer to the [Java ClassLoader Documentation](https://docs.oracle.com/javase/17/docs/api/java.base/java/lang/ClassLoader.html).
