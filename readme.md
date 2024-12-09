### GitHub README for Campus Errand Service Platform

# Campus Errand Service Platform

The **Campus Errand Service Platform** is a WeChat mini-program designed to facilitate errand services on the campus. It provides a seamless way for users to request and fulfill errands such as food delivery, parcel delivery, and flash deliveries, while also offering account management, order tracking, and user reviews.

## Features

### For Clients

- **Place Orders**: Users can request various types of errands, including takeout, express, and flash delivery.
- **Order Tracking**: Monitor the status of your errands in real-time.
- **Payment System**: Secure and convenient in-app payment options.
- **Review and Rating**: Evaluate the performance of couriers and share feedback.
- **Personal Management**: Manage personal details and delivery addresses easily.

### For Couriers

- **Accept Orders**: Choose orders based on availability and preferences.
- **Update Delivery Status**: Mark orders as picked up or delivered.
- **Earn Income**: Use free time to complete errands and earn rewards.
- **Performance Ratings**: Build credibility through user ratings.

### For Administrators

- **Manage Platform**: Oversee user and courier activity, and handle disputes.
- **Block/Unblock Accounts**: Manage the reputation system and security.

------

## Technical Overview

### Backend

- **Framework**: Built with Spring Boot for scalable backend development.
- **API**: Well-documented APIs using Swagger for seamless integration and maintenance.
- **Database Design**: A structured relational database supports user profiles, orders, payments, and reviews.
- **Secure Communication**: HTTPS protocol and SHA1 encryption for user authentication and data security.

### Frontend

- **Framework**: Developed with Vue for an interactive and responsive user interface.
- **Platform**: Designed as a WeChat mini-program for accessibility.

------

## System Architecture

The platform follows an incremental development model with a clear separation of concerns between the frontend and backend.

### Data Design

The system integrates data structures for clients, couriers, orders, payments, and reviews. Relationships between entities are established via primary and foreign keys to maintain data integrity and streamline queries.



------

## Deployment and Build

### Front-end

Run the followings in the command line in front-end/miniapp/miniprogram

```
npm init -y
npm i @vant/weapp -S --production
```

Then remove the "style: v2" in app.json and build npm in Build npm in WeChat Developer Tools

### Backend

You can build and run the back-end Spring Boot project built with Maven by following the below steps:

1. Install Java and Maven:

   Ensure that there is a suitable Java Development Kit (JDK>=17) and Maven installed on the computer. 

2. Clone / Download the project:

   You can clone it using Git by 

   `git clone https://github.com/sustech-cs304/team-project-1114.git` 

   or download it directly to the local system from the url 

   https://github.com/sustech-cs304/team-project-1114

3. Navigate to the project directory and build the project:

   Open a terminal or IDE and navigate to the back-end directory of the project by `cd backend/OrderTaker`. This directory will contain the [pom.xml](./backend/OrderTaker/pom.xml) file for the Maven project. 

   Then use Maven to build the project by running the following command in the terminal:

   `mvn clean install`

   The clean command will delete the target directory with all the build data before starting so that it is fresh. The install command will **compile, test, and package** the code in a distributable format as **OrderTaker-v3.0-final.jar** and install the packaged code in the local repository.

4. Run the project in two ways:
   (1) Spring Boot Maven plugin includes a run goal which can be used to quickly compile and run application. Use the following command to start the application:

   `mvn spring-boot:run`

   (2) The final artifact produced by a successful build is an executable JAR file. This JAR file contains all the compiled classes and resources of our application, along with embedded dependencies and an embedded servlet container (like Tomcat).

   To run the built jar, you can use the java -jar command followed by the jar file name. For example:

   `java -jar ./target/OrderTaker-v3.0-final.jar`

   If everything is set up correctly, Spring Boot will start up, and the application will start running. Besides, we can access the developer api documentation at http://localhost:8081:api.html to check if the application is running well.

### Dockerization

1. Create a Docker image:

   ```bash
   docker build -t campus-errand:v1 .
   ```

2. Deploy the container:

   ```bash
   docker run -p 8081:8081 campus-errand:v1
   ```

------

## Documentation

- **User Guide**: Comprehensive instructions for clients and couriers are available here: [User-Readme.md](./User-Readme.md).

- **API Documentation**: Explore the detailed API specifications at `/api-docs` or via [Dev-api.pdf](./Dev-api.pdf).

- **Description**:
  The API documentation generated by Swagger3 is designed for developers, with the primary purpose of helping developers, collaborators, or potential future contributors understand the design, purpose, and implementation of our code entities.

  In the case of our API documentation, it meticulously lists out the HTTP methods (like GET, POST, PATCH, DELETE, etc.) and corresponding parameters for various endpoints, as well as their purposes. These endpoints include Courier/Client/Admin Interface, Order Interface, and so on. Furthermore, the document describes several data models (Schemas) such as Courier, OrderTracking, Payment, Order......

  Developers can understand how to interact with these endpoints to implement related functionalities by reading this documentation. For instance, developers would understand that to register a new courier, they need to send a POST request to "/api/courier/register"; to retrieve payment status, they need to send a GET request to "/api/payment/status", and so forth. This information aids developers in understanding and utilizing the API, thereby implementing the necessary functionalities in their applications.

  Another advantage of the API documentation generated by Swagger is its readability and ease of use. Developers can see detailed information about each endpoint directly in the documentation, without having to inspect the actual code implementation. This greatly improves development efficiency and reduces the learning curve.

------

## Contributors

- **Zeng Xianqing** - Project Manager
- **Chen Wenyan** - Backend Developer
- **Ye Qingyang** - Business Analyst
- **Peng Shubo** - Test Engineer
- **Lin Yuhang** - Frontend Developer

------

## Get Started

Clone the repository to begin:

```bash
git clone https://github.com/tsukii7/Campus-Errand-Service-Platform-Development.git
```

------

## License

This project is licensed under the MIT License. See the LICENSE file for more information.