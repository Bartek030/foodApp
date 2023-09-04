# Food App

> This is a web application where you can order or sell foods.

## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Usage](#usage)
* [Project Status](#project-status)

## General Information
Food App is an application that allows you to find Restaurant in your location and order food
in restaurants that deliver foods to the given address. As restaurant owner you can also sell
foods by adding your restaurant and specify menus, foods and delivery addresses.

Application has been divided into 3 layers: Web, Service and Model. Each layer has its own
data model. 

Web layer has been prepared using Interface driven Controller pattern. Application end point has been
prepared in 2 ways: basic Controllers for Thymeleaf usage and RestControllers for REST API usage.

Model layer has been prepared using Spring Data JPA framework. Each entity has its own JPA 
repository. Data access can be achieved through repositories that implement dao interface.

Service layer is responsible for application business logic. Each calculation, data preparation
for database transaction or for application view, data validation has been prepared in services.
Additionally each entity has its counterpart in service. Access to the model layer from other services
is performed through dedicated service.

Application and database structure has been drawn using Draw.io tool. Files can be found under
following links:
https://github.com/Bartek030/foodApp/blob/master/src/main/resources/app_structure.drawio  
https://github.com/Bartek030/foodApp/blob/master/src/main/resources/db_schema.drawio


## Technologies Used
- Spring Boot v. 3.1.1 (including web, data jpa, security, test)
- Thymeleaf v. 3.1.2.RELEASE
- Flyway
- Lombok
- JUnit
- Mockito
- Test Containers
- Rest Assured v. 5.3.1
- WireMock v. 2.27.2
- Mapstruct v. 1.5.5.Final
- Swagger v. 2.2.0
- Jacoco v. 0.8.10

Others:
- PostgreSQL


## Features
As application user you can use the following features:
- searching for restaurants that deliver foods to the specified location
- viewing restaurant menus and offered meals
- ordering foods
- reviewing order details
- cancelling orders before 20 min from submitting order
- displaying the current weather for the given city

As restaurant owner you can use additionally the following features:
- registering restaurants in application database
- adding menus and meals, including photo for each position
- adding delivery address for restaurant
- viewing restaurants orders
- marking orders as delivered

## Usage
To run this application you need to perform the following steps:
- install JDK - version 17+
- install Docker on your computer
- download repository
- from the main folder run command: docker compose up

You can skip Docker installation and run application using java -jar or IDE like Intellij,
but you will need to prepare Postgre database on your computer. Additional configuration
might be required.

## Project Status
Project status: _in progress_.  
Next steps:
- JWT implementation
- User profile info implementation
- Separate frontend application
- Separate application for Weather forecast
