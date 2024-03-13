# Test Automation Framework - UI & API
Sample end 2 end test automation framework for UI and API testing.

This sample Software Test Automation Framework is built as a Maven project using Behavior Driven Development principles with Cucumber and Junit in Java. And also utilizing Page Object Module structure to maximize framework re-usability and maintainability. 
The scenario is written in Gherkin language which is a plain English, that could benefit connecting the team.

And the framework is also structured to support API testing using RestAssured with TestNG, and Database integration capabilities with JDBC.


## Project structure
The project structure is mainly divided into 2 sections, `src/test/java` and `src/test/resource`

src/test/java
 - api_tests - (API tests written using RestAssured library in TestNG tests structure).
 - pages - (Page objects are created within this folder)
 - runners - (One or more Cucumber runner classes are within this folder)
 - step_definitions - (Test steps implementation for the scenario steps defined in feature files)
 - utils - (Any utility and support classes created within this folder)
 
src/test/resources
 - features - (Cucumber feature files are created within this folder to define scenarios for the feature)
 - testdata - (Test data / properties files are stored within this folder)
 - testfiles - (Test Json or any dummy files are stored here)

reports - (cucumber generated reports are stored in this location)

test-output - (testng generated reports are stored in this location)

pom.xml - (Project configurations, and content/library/dependencies management and build/run configuration)

testng.xml files - (TestNG test suite management and executions control)

## Tools used

UI - tools used for UI test automation:
 
 - Maven - (Project configurations, and content/library/dependencies management and build/run configuration)
 - Cucumber - (Used to define feature scenarios in gherkin, and also to create test suites and execution flow with tagging, and reporting)
 - Selenium - (Used to automate the web application by implementing the step definitions, managing the page objects)
 - Junit - (Used to run the cucumber scenarios with cucumber options, and assertions)
 - JDBC - (Used to connect to database for testing activities)
 - MySql driver - (Used to allow connection to MySql database that application uses)
 
 API - tools used to API test automation:
 
 - TestNG - (Used to manage API test suites, test execution flow and assertion, reporting)
 - RestAssured - (Used to define API tests)
 - JDBC - (Used to connect to database for testing activities)
 - MySql driver - (Used to allow connection to MySql database that application uses)
 
 Other tools used for end 2 end testing activities
 
 - Git - (Source code management / version control)
 - Github - (Remote source code management / version control platform)
 - Jenkins - (CI/CD tool which we have used to run test suites) 
 - Eclipse - (IDE for project development)

## Creating tests

  Creating UI tests: [Cucumber docs](https://cucumber.io/docs/cucumber/api/?lang=java#running-cucumber)
  
  1. Create a feature file in `features` folder under `scr/test/resource` folder with extention `.feature`
  2. Define scenario with cucumber key words Given, When and Then structure
  3. Generate step definition snippets (use `dryrun`)
  4. Create a steps class under `step_definitions` folder and put those generated steps snippets
  5. Create page classes under `pages` folder and define the page objects `(elements)`
  6. Implement the step definitions based on the scenario behaviours

### Running UI tests

  1. Create a runner class under `runners` folder
  2. Define the RunWith cucumber options with necessary options
  3. Pass a scenario tag to execute tests locally using cucumber tags, and rus as Junit test
  4. To run the tests remotely via a CI/CD tool like Jenkins, create a build in pom.xml 
  and point to the runner class in build configuration. (refer to existing pom.xml for examples)
  5. On Jenkins job under build workflow, select top level maven target and pass the following command:
  
  ```bash
  clean test -Dcucumber.filter.tags="@smoketest"
  ```
  
### Creating API tests: [RestAssured Docs](https://rest-assured.io/)
  
  1. Create a class in `api_tests` folder under `src/test/java` folder
  2. Create a TestNG test and define the API test flow with RestAssured

### Running API tests

