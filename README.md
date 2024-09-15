# DPV Vocabulary Crafter

DPV Vocabulary Crafter is a Java-based web application using Spring Boot that was designed to assist with the creation and management of personal sub-vocabularies created from the Base/Core Data Privacy Vocabulary(DPV) which was created based mostly on the General Data Protection Regulation (GDPR). This application is structured using two main components: the **Client** and the **Server**.

## Project Structure

### 1. Client
The **Client** folder contains a Java-based terminal user interface (UI) that allows users to interact with the vocabulary system. The client communicates directly with the server to send and receive data, and all operations are handled via command-line inputs.

### 2. Server
The **Server** folder manages the backend logic of the application. It is built with:
- **Java** as the core programming language (17 or higher).
- **Spring Boot** for setting up the server.
- **REST APIs** to handle data requests from the client.

## Features
- **Vocabulary Management**: Create and update vocabularies.
- **Java-based Terminal UI**: Command-line based interface for interacting with the server.
- **Access to Core DPV Vocabulary**: Access the base DPV vocabulary to facilitate easier creation and updates of personal DPV sub-vocabularies.
- **RESTful API**: Facilitates smooth communication between the client and server.

## How to Run

1. Clone the repository:

```bash
    git clone https://github.com/dbouros/DPV-Vocabulary-Crafter.git
```

2. Navigate to the `Server` directory and run the server application:

```bash
    cd Server
    mvn spring-boot:run

    # or
    
    java -cp target/classes com.DPV_Vocabulary_Crafter.Server.DpvVocabularyCrafterApplication

```

3. Navigate to the `Client` directory and run the client application:

```bash
    cd Client
    java -cp target/classes com.DPV_Vocabulary_Crafter.Client.Main

```
