# HRS Coding Challenge

Scenario Receptionists have to accomplish various tasks throughout the day when operating a hotel. One of those tasks is
to accept parcels for guests.

* It happens, that receptionists accept parcels for guests, that was already checked out of the hotel.
* It happens from time to time, that guests forget to pick up their parcels before check-out and even forget it then.

Requirements

1. The receptionist needs a tracking tool that knows to any given time which guests are checked into the hotel and have
   not checked out, to allow the receptionist to decide whether to accept the parcel or not.
2. The tracking tool should further allow the receptionist to check for parcels available for pick-up when the guest is
   checking out.
3. Use JAVA 8 & SpringBoot
4. Don’t forget logging ☺
5. Unit tests are a must, isn’t it?

Tasks

* Document the usability and the design of your solution in a PDF file.
* Build a RESTful JSON-API that supports the following use cases. Your implementation should be based on the
  Microservices architecture approach.
* Document your solution with a Swagger file served by the API.

Deliverable Please provide your solution on GitHub repo or send a zip file named to us that contains:
firstname-lastname.zip to us that contains:

* The source code (buildable and runnable without changes) with Maven or Gradle build script
* An executable Jar
* Instructions to run the solution are documented in a readme.md When shipping your solution, please let us know how
  long you took and where you did it. Don't artificially decrease the development time you tell us - we have good
  statistical measures and taking longer is not a bad thing at all.

In Scope We're interested to see how you produce production-ready code. By that, we focus on naming conventions, coding
style, sensible design, and meaningful commenting. Perfect code ships at a shape, that enables colleagues to quickly
understand the domain and codebase.

We understand writing even a trivial application and having it production-ready is a lot of work. Please limit writing a
lot of boilerplate - showing the intent and leaving "to-do" comments in your code is okay. However, make sure your
solution works.

Out of Scope

* We don't expect you to use every design pattern you've ever heard of - only apply patterns when it makes sense to do
  so.
* We don't expect you to build a user interface.
* We're not expecting you to have optimized the solution for performance or memory size - readability is more important
  than performance.

<!-- TABLE OF CONTENTS -->

## Table of Contents

<details open="open">
   <ul>
      <li>
         <a href="#technology-stack---other-open-source-libraries">Technology stack &amp; other Open-source libraries</a>
         <ul>
            <li><a href="#data">Data</a></li>
            <li><a href="#server---backend">Server - Backend</a></li>
            <li><a href="#libraries-and-plugins">Libraries and Plugins</a></li>
            <li><a href="#others">Others</a></li>
         </ul>
      </li>
      <li>
         <a href="#installing">Installing</a>
         <ul>
            <li><a href="#running-the-application-with-ide">Running the application with IDE</a></li>
            <li><a href="#running-the-application-with-maven">Running the application with Maven</a></li>
            <li>
               <a href="#running-the-application-with-executable-jar">Running the application with Executable JAR</a>
               <ul>
                  <li>
                     <a href="#accessing-data-in-h2-database">Accessing Data in H2 Database</a>
                     <ul>
                        <li><a href="#h2-console">H2 Console</a></li>
                     </ul>
                  </li>
               </ul>
            </li>
         </ul>
      </li>
      <li>
         <a href="#code-coverage">Code Coverage</a>
         <ul>
            <li><a href="#jacoco">Jacoco</a></li>
         </ul>
      </li>
      <li>
         <a href="#testing-api">Testing API</a>
         <ul>
            <li><a href="#testing-with-postman-runner">Testing with Postman Runner</a></li>
            <li><a href="#testing-with-maven">Testing with Maven</a></li>
         </ul>
      </li>
      <li><a href="#contact">Contact</a></li>
   </ul>
</details>

## Technology stack & other Open-source libraries

### Data

<details open="open">
   <ul>
      <li><a href="https://www.h2database.com/html/main.html">H2 Database Engine</a> - Java SQL database. Embedded and server modes; in-memory databases</li>
   </ul>
</details>

### Server - Backend

<details open="open">
   <ul>
      <li><a href="http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html">JDK</a> - Java™ Platform, Standard Edition Development Kit</li>
      <li><a href="https://spring.io/projects/spring-boot">Spring Boot</a> - Framework to ease the bootstrapping and development of new Spring Applications</li>
      <li><a href="https://maven.apache.org/">Maven</a> - Dependency Management</li>
   </ul>
</details>

### Libraries and Plugins

<details open="open">
   <ul>
      <li><a href="https://swagger.io/">Swagger</a> - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.</li>
   </ul>
</details>

### Others

<details open="open">
   <ul>
      <li><a href="https://git-scm.com/">git</a> - Free and Open-Source distributed version control system</li>
   </ul>
</details>

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing
purposes.

#### Running the application with IDE

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method
in the `com.arc.sbtest.SBtemplateApplication` class from your IDE.

* Download the zip or clone the Git repository.
* Unzip the zip file (if you downloaded one)
* Open Command Prompt and Change directory (cd) to folder containing pom.xml
* Open Eclipse
    * File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
    * Select the project
* Choose the Spring Boot Application file (search for @SpringBootApplication)
* Right Click on the file and Run as Java Application

#### Running the application with Maven

Alternatively you can use
the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html)
like so:

```shell
$ git clone https://github.com/ppagote/hrs-task.git
$ cd hrs-task
$ mvn spring-boot:run
```
#### Running the application with Executable JAR

Run the jar by double-clicking on it
or by using the command

```shell
$ git clone https://github.com/ppagote/hrs-task.git
$ cd hrs-task/jar
$ java -jar target/hrs-task-0.0.1-SNAPSHOT.jar
```

To shutdown the jar, follow the below mentioned steps on a Windows machine.

* In command prompt execute the **jcmd** command to print a list of all running Java processes
* **Taskkill /PID PROCESS_ID_OF_RUNNING_APP /F** execute this command by replacing the **PROCESS_ID_OF_RUNNING_APP**
  with the actual process id of the running jar found out from executing the previous command
* Press Ctrl+C

#### Creating  Executable JAR and then running the application

The code can also be built into a jar and then executed/run. Once the jar is built, run the jar by double-clicking on it
or by using the command

```shell
$ git clone https://github.com/ppagote/hrs-task.git
$ cd hrs-task
$ mvn package -DskipTests
$ java -jar target/hrs-task-0.0.1-SNAPSHOT.jar
```

To shutdown the jar, follow the below mentioned steps on a Windows machine.

* In command prompt execute the **jcmd** command to print a list of all running Java processes
* **Taskkill /PID PROCESS_ID_OF_RUNNING_APP /F** execute this command by replacing the **PROCESS_ID_OF_RUNNING_APP**
  with the actual process id of the running jar found out from executing the previous command
* Press Ctrl+C
##### Accessing Data in H2 Database

###### H2 Console

URL to access H2 console: **http://localhost:8080/h2-console/login.jsp**
or **https://192.168.99.102:8080/h2-console/login.jsp** if **SSL** is enabled.

Fill the login form as follows and click on Connect:

* Saved Settings: **Generic H2 (Embedded)**
* Setting Name: **Generic H2 (Embedded)**
* Driver class: **org.h2.Driver**
* JDBC URL: **jdbc:h2:mem:testdb;DB_CLOSE_ON_EXIT=FALSE**
* User Name: **sa**
* Password:

## Code Coverage

### Jacoco

Generating code coverage reports

```shell
$ mvn test
```

This will create a detailed HTML style report showing code coverage statistics gathered via code instrumentation.

**hrs-task\target\site\jacoco**

## Testing API

### Testing with Postman Runner

Import the **HRS.postman_collection.json** file into postman and run the API tests.

### Testing with Maven

* Run only unit tests:

```shell
$ mvn clean test
```

## Documentation

* [Swagger](http://localhost:8080/swagger-ui/) - `http://localhost:8080/swagger-ui/`- Documentation & Testing
* [Swagger](http://localhost:8080/v2/api-docs)
    - `http://localhost:8080/v2/api-docs`- Documentation & Testing

<!-- CONTACT -->

## Contact

Pranav Pagote - pranav1990.pagote@gmail.com

Project Link: [https://github.com/ppagote/hrs-task](https://github.com/ppagote/hrs-task.git)