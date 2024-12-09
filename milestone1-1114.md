# [CS304] Requirement Analysis Report
Project Name: Campus Errand Platform
Project members: Zeng Xianqing, Chen Wenyan, Ye Qingyang, Peng Shubo, Lin Yuhang
### Version History
| Date | Version Number | Description | Author |
| ---- | ------ | --------| ------------ |
| March 10, 2023 | v0.1 | New Project Document | All members |
| ... | ... | ... | ... |
## 1. Outline
### 1.1 Motivation and Background 
There are many students and faculty on the campus of Southern University of Science and Technology who need to use running errands to complete some daily or urgent tasks, such as delivering take-out, couriers, urgent items, etc. At the same time, there are some students or faculty who want to become riders, use their free time and transportation to provide running errands, and get some income from it. However, there are some problems with the existing leg service platforms, such as:
- It only supports take-out distribution, has a narrow business scope, can not meet the diverse needs of users for running legs, and can not provide more order opportunities for riders;
- Using group chat to place orders results in inefficient communication and confused information, making it difficult to find the right rider or customer in time;
- Lack of professional order management and payment system, resulting in unclear order status, inconvenient payment method, and opaque fee settlement;
- Lack of effective reputation evaluation and security mechanisms leads to a lack of trust and responsibility between riders and customers, which can lead to disputes and losses.
In order to solve these problems and meet the dual needs of running service and income sources of users on the campus of Nanke University, we propose a design scheme of the campus running service platform. The purpose of this project is to provide a convenient, fast and safe running legs service platform for students and faculty on the campus of USTC, and to provide a flexible, reliable and beneficial income channel for users who wish to become riders.
### 1.2 Target users and user characteristics
There are two types of target users for the Campus Running Services Platform: one is for customers who need running services, the other is for riders who provide running services. These two types of users are mainly students and staff on the campus of UST.

For customers who need running errands, they have a certain level of spending power, are willing to pay for services that can improve their efficiency, and want to reduce the cost of communication for help and use a relatively reliable trading platform. For riders who provide running services, they usually have some free time or convenient transportation, and are willing to use their free time to earn income through labor.
### 1.3 Project Scope
The service platform has the following characteristics:
- Support various types of running errands (e.g. take-out, express delivery, urgent items, etc.) and expand business scope
- Provides real-time display of riders and order status as well as smart push capabilities to enable customers and riders to make reasonable choices according to their needs and abilities, and to improve service quality and efficiency;
- Use online ordering and payment methods to improve communication efficiency, simplify the order process, and make it easy for users to use;
- Establish interaction and trust mechanisms between riders and customers by using credit rating and evaluation systems, and provide security measures such as appropriate compensation terms.
### 1.4 Ultimate Target
The motivation of this system is to provide a convenient, fast and safe running legs service platform for students and faculty on the campus of SUSTech, so that they can assist them in some daily or urgent running tasks by ordering and paying online under busy or special circumstances, while providing a flexible, reliable and beneficial income channel for users who wish to become riders.
The ultimate goals expected by the system are as follows:
- To meet the diverse needs of different user groups for running services, improve the quality and efficiency of communication of running services
- Create a win-win platform for riders to increase revenue and customers to save time, and optimize the allocation of resources
## 2. Development process
### 2.1 Process Model
This project uses an incremental model.
This project has the following features:
- Project requirements are clear (for waterfall models)
- Project functionality is easy to modularize (waterfall model - > incremental model)
- User requirements will not change significantly in a short period of time (not for prototype design)
- Project content needs to be presented in stages according to course requirements
-Developers lack experience in risk assessment (and therefore do not apply to spiral models)

### 2.2 Project Time Planning

Project Manager: Zeng Xianqing

Business Manager: Ye Qingyang

Technical Architect: Forest Rainfall (Front End), Chen Wenyan (Back End)

Test Engineer: Bloomberg

![Timeline v1 1](https://user-images.githubusercontent.com/82144027/224532577-bb6f0ebc-b813-4cb1-8331-56ca32d97053.png)

## 3. Expected Delivery
### 3.1 Milestone 2
For Milestone 2, deliveries include:
- User registration and login: The platform should allow users to register and login using an e-mail address or mobile phone number and select their identity (rider or user).
- User Order Placement Function: Users should be able to create order placing requests on the platform and specify the details of the running legs service they need, i.e. delivery location, delivery time, and any other specific instructions.
- Rider order taking function: Riders can filter order information and take orders on the platform.
- Communications: The platform should allow users and riders to communicate with each other through in-app messaging systems.
- Service status update: The platform should allow riders to update service status (orders, deliveries, deliveries) and allow users to confirm service completion.
### 3.2 Milestone 3
For Milestone 3, deliveries include:
- The full functionality, fully tested version of the Campus Running Legs Service Platform, including all the features listed in Milestone 2, as well as the following advanced features:
- Promotion service type: Platform provides take-out, express and flash functions
- Rating and feedback: The platform should allow users and riders to rate each other and leave feedback on the quality of service provided.
- Payment: The platform should allow users to pay for services through the application.
- User and technical documentation: The platform should be accompanied by user and technical documentation, including usage guidelines, system instructions, user manuals, and so on.
- Test cases and test reports: The platform should include test cases and test reports to ensure system stability and reliability
- Project Summary Report: This report should summarize the entire project development process, key technologies and lessons learned
- Program source code: The platform should be accompanied by complete program source code so that customers can maintain and upgrade independently.
## 4. Project Requirements
### 4.1 Functional Requirements
### 4.1.1 Diversified service type support
- Input: Type of service required by the user
- Processing: Platform provides appropriate service options based on the type of service required by users
- Output: User can choose the appropriate service type to place an order
- Description: The platform should support users'diverse service needs, such as delivery, delivery, courier, file, shopping, booking, etc. to meet users' various running needs.
### 4.1.2 Smart Matching Order Mechanism
- Input: User's order information, including service type, service address, service time, service fee, etc.
- Processing: Platform matches qualified riders through algorithms to provide users with the best service
- Output: The platform recommends the right rider to the user, who can choose whether or not to accept the recommendation
- Description: Platform should use intelligent algorithms to automatically match orders, improve order matching success and service quality, and provide more order opportunities for riders
### 4.1.3 Professional Order Management and Payment System
- Input: User's order information, including service type, service address, service time, service fee, etc.
- Process: The platform tracks the status of the order through a professional order management system to ensure the normal operation of the order; Through the payment system, provide a variety of payment methods and preferential activities to facilitate user payment.
- Output: Order status is clearly visible, payment is convenient and fast.
- Description: The platform should set up professional order management and payment system to let users know clearly the status of the order, facilitate payment and refund, and improve the transparency and reliability of the service.
### 4.1.4 Effective Reputation Evaluation and Security Mechanisms
- Input: User order information, rider order information, order completion
- Process: The platform provides a mechanism for users and riders to evaluate each other, and establishes a transaction protection mechanism to safeguard the interests of both parties.
- Output: Users and riders can choose the right opponent based on the evaluation information, and the platform can evaluate the rider's reputation level and provide transaction guarantee services according to the evaluation situation.
- Description: The platform should establish an effective reputation evaluation and security mechanism to ensure the fairness, integrity and security of transactions, and to improve the trust and satisfaction of users and riders.
### 4.2 Non-functional Requirements
#### 4.2.1 Quality Requirements
#### 4.2.1.1 execution speed
The platform should have a faster execution speed to ensure that users can get satisfactory service in time. In addition, the platform should be scalable, able to cope with user growth and traffic changes, and ensure the stability and reliability of the platform.
#### 4.2.1.2 Usability
The platform should be easy to use, that is, the interface is simple and clear, the operation is easy to understand, and meet the user's habits and needs. At the same time, the platform should provide adequate documentation and technical support to ensure users can use the platform correctly and provide timely services and solutions.
### 4.2.2 Security Requirements
#### 4.2.2.1 Reliability
The platform should be reliable, that is, in the face of emergencies, the platform can still operate normally, ensure the normal processing and safe transmission of order information, and successfully complete users'running needs. In addition, the platform should have a reliable data backup and recovery mechanism to ensure that data is not lost due to hardware failures or other unexpected events.

#### 4.2.2 exception handling
The platform should have a good exception handling mechanism, and be able to detect and handle exceptions quickly when they occur, so as to avoid affecting the normal use of users. In addition, the platform should provide detailed logging and error hints to facilitate troubleshooting and repair by technicians.

## 5. limit
### 5.1 Environment
In order to ensure the normal operation of the campus running legs service platform, the following environmental constraints need to be met:
- Requires smartphones that run on mobile devices that support WeChat applets, such as iOS and Android systems.
- The WeChat client needs to be installed so that users can sign in, use applets and complete payment functions.
- A network connection is required so that users and riders can obtain order information and communicate in real time.
- It needs to be run on the campus of the Southern University of Science and Technology, because the platform mainly serves students and faculty in the University.
### 5.2 Design and Implementation
In order to achieve the functions and features of the campus running legs service platform, the following design and implementation constraints need to be met:
- The platform will be in the form of WeChat applet and web-side, which users can use directly in the WeChat applet or access through the browser on the web-side.
-The front-end is developed with Vue framework, and the back-end is developed with SpringBoot framework, separating the front-end from the back-end, improving development efficiency and code maintainability.
- The development tools mainly use WebStorm and IntelliJ IDEA, which are rich in development plug-ins and functions to greatly improve development efficiency.
- To ensure the security and stability of the platform, HTTPS protocol will be used for communication.
- In order to improve the efficiency and accuracy of communication between users and riders, real-time communication technologies, such as Websocket, will be used to ensure the real-time and reliability of information.
- In order to improve trust and security between users and riders, the platform will provide a variety of evaluation and security mechanisms, such as scoring systems, insurance mechanisms, etc. to ensure that cooperation between users and riders is fully guaranteed.
