# Unit Conversion Backend Application

## Problem Statement

Our users are science teachers who are as comfortable using the command line as they are using a
browser. In their “Unit Conversion” science unit, they want to assign students unit-conversion problems
on paper worksheets. After students turn in their completed worksheets, the teachers want to be able to
enter the questions and student responses into a computer to be graded. 

Students will convert:
* temperatures between Kelvin, Celsius, Fahrenheit, and Rankine
* volumes between liters, tablespoons, cubic-inches, cups, cubic-feet, and gallons

This repository contains the code for a unit conversion backend application containing a service to assist teachers with conversion validation.

## Installation and Usage

### Prerequisites
- Java 17 or later
- Maven

### Installation
1. Clone this repository to your local machine.
   ```
   git clone git@github.com:Kevin1289/unit-conversion-backend.git
   ```
2. Navigate to the project directory.
   ```
   cd unit-conversion-backend
   ```
3. Copy git hook files into your .git/hooks folder
   ```
   cp git-hooks/* .git/hooks/
   ```

### Running the Application Locally
1. Build the project using Maven:
   ```
   mvn clean install
   ```
2. Run the application:
    ```
    java -jar target/unit-conversion.jar
    ```
3. The application will be accessible at `http://localhost:8080`.
 
### Running the Tests
1. Run the tests using Maven:
   ```
   mvn test
   ```

## API Documentation
1. This application provides a REST API to validate unit conversions.
2. The API documentation can be found at `http://localhost:8080/swagger-ui.html`.

## Assumptions
1. The application only supports the conversion of temperatures between Kelvin, Celsius, Fahrenheit, and Rankine.
2. The application only supports the conversion of volumes between liters, tablespoons, cubic-inches, cups, cubic-feet, and gallons.

## CI/CD Pipeline
1. The CI/CD pipeline is set up using GitHub Actions.
2. The pipeline runs the tests and builds the application on every push to the main branch.


