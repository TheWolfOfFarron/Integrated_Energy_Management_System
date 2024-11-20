
# 1Conceptual Architecture

In this UML deployment diagram:

![A diagram of a software company Description automatically
generated](media/image1.png){width="6.5in" height="4.461111111111111in"}

User: Represents users interacting with the system through a web
browser.

Frontend Server: This component represents the web server that serves
the frontend application. It hosts the React application and serves web
pages to users.

Backend Servers:

Person Microservice: Represents the server running the Person
microservice. It handles CRUD operations on person-related data and
enforces user authentication and authorization.

Device Microservice: Represents the server running the Device
microservice. It handles CRUD operations on device-related data and
enforces user authentication and authorization.

Databases:

Person Database: This component represents the database for storing
person-related data.

Device Database: This component represents the database for storing
device-related data.

User Roles:

User Role: Shows the role of standard users, who can interact with the
system to view device-related information.

Admin Role: Represents admin users with extended privileges to perform
CRUD operations on both persons and devices.

Interactions:

Lines: These lines indicate the interactions between the user and the
frontend server, between the frontend server and the backend servers,
and between the backend servers and their respective databases. These
interactions show the flow of requests and responses.

Deployment Nodes:

User\'s Device: Represents the user\'s device (e.g., a computer or
mobile device) from which users access the web application.

Web Server Node: Represents the hardware or server running the frontend
application.

Microservice Nodes: Represent the hardware or servers where the Person
and Device microservices are deployed.

Database Nodes: Represent the servers or hardware where the Person and
Device databases are hosted.

# 2.Introduction

This section provides a high-level overview of the conceptual
architecture of the distributed system for our website project. The
system is composed of multiple components, including microservices,
databases, a backend, and a frontend, each with distinct functionalities
and interactions.

# 3.Components

1\. Frontend

\- The frontend is responsible for the user interface and user
interaction.

\- It provides a login functionality and different functionalities based
on user roles.

\- Users interact with the system through the frontend, which
communicates with the backend.

2\. Backend

\- The backend consists of two microservices: Person and Device and
Management.

\- It handles the business logic, data processing, and database
interactions.

\- The backend microservices expose APIs for the frontend to access and
perform CRUD (Create, Read, Update, Delete) operations on persons and
devices.

\- The backend authenticates users, enforces authorization rules based
on user roles, and ensures data security.

-The backend stores and analyze the data consumption.

3\. Databases

\- There are two databases, one for persons and another for devices.

\- Each database stores relevant data, including user information,
device details, and associated attributes.

\- The databases are managed by the respective microservices and are
used to store and retrieve data.

1.1. Chat Microservice

The Chat Microservice is designed to facilitate real-time communication
between users and administrators. Below are the functional requirements:

Chat Interface:

Chat Interface: Users can utilize a chat interface to send and receive
messages.

Asynchronous Messaging: Messages are sent asynchronously to the
administrator, accompanied by user identifiers.

Chat Session: Both users and administrators can exchange messages during
a chat session.

Multi-User Capability: Administrators can manage multiple chat sessions
concurrently with various users.

Notifications: Notifications are triggered when a user or administrator
reads a message.

Typing Indicators: Users and administrators receive typing
notifications, indicating when the other party is composing a message.

1.2. Authorization Component

The Authorization Component is responsible for managing user
authentication and generating access tokens for secure interactions
between microservices. The following are the functional requirements:

Authorization Server:

Service Selection: Choose an appropriate service to act as the
authorization server, which can be the User Microservice or a dedicated
Authorization Microservice.

Token Generation: The selected service generates JWT (JSON Web Tokens)
as access tokens for client applications.

Microservice Access: These tokens will serve as credentials for
accessing other microservices within the system.

1.3.1. Chat Component

The Chat Component leverages Web Sockets technology to provide real-time
communication features.

1.3.2. Authorization Component

For the Authorization Component, the system employs JWT-based
authorization mechanisms. Based on architectural considerations, you
have three design options:

Design Architectures:

Shared Secret Key: Implement a single authorization service responsible
for token generation. All other microservices verify tokens using a
shared secret key.

Reverse Proxy Configuration: Configure an authorization service as a
reverse proxy for all services. Behind this proxy, services do not
require individual authorization since they are shielded by the local
area network (LAN).

Spring OAuth2: Adopt Spring OAuth2 to implement an OAuth 2.0
authorization service, granting access to all interconnected
microservices.

# 4. User Roles

The system defines two user roles:

1\. User

\- Users have limited access to the system.

\- They can view the list of devices but cannot perform CRUD operations
on persons or devices.

\- Users can log in to access their functionalities.

\- Get notifications if the data consumption its bigger then maxHour

2\. Admin

\- Admins have full control over the system.

\- They can perform any CRUD operation on persons and devices.

\- Admins have access to advanced functionalities and management
features.

# 5. Data Flow

1\. User or Admin logs in through the frontend.

2\. The frontend sends authentication requests to the backend.

3\. The backend validates the user\'s credentials and role.

4\. Based on the user\'s role, the backend provides appropriate
functionalities and data.

5\. Users can interact with the frontend, which communicates with the
respective microservices.

6\. The microservices interact with their associated databases to
retrieve or update data.

7\. The frontend displays data and responds to user actions.

# 6.Security

\- Authentication and authorization mechanisms are implemented to ensure
data security and access control.

\- Communication between components is secured to protect sensitive
information.

\- User data and credentials are encrypted and stored securely in the
databases.

# 7. Database

# 8. Conclusion

The conceptual architecture of our distributed system outlines the key
components, their functionalities, and the data flow. This architectural
overview serves as the foundation for our website project, enabling
users and admins to interact with persons and devices securely and
efficiently.
