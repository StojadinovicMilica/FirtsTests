# badin-selenium-simple-UI-framework 



## Name
badin-selenium-simple-UI-framework 

## Description
Simple selenium framework without unnecessary dependencies that can be used out of the box and customized according to project needs. It is a maven project, that is using Junit5 test runner and selenium v3, it supports java 8.

## Installation
Clone the project to your local repo, make sure you have jdk and maven installed, maven will handle the rest of the dependencies.

## Usage
Test suites from the project can be run by using maven commands "mvn test" and also "mvn surefire-report:report" if we want surefire to generate the report files in target folder. If we only want to generate the html reports for previously ran tests we can do that by executing "mvn surefire-report:report-only" which will generate "site" folder inside of the target folder, which contains html report and resources.

Custom system properties that are used by the framework currently are "env", "browser" and "execType":
env - sets the environment (dev,prod)
browser - sets the browser that will be used for test run (Firefox, Chrome)
execType - determines if tests will be run on local machine or on selenium grid setup on badin servers. (local, grid)

Inbuilt System property for determining which group of tests should be run is "groups".

The system properties can be set by appending the command ie -Denv=dev -Dbrowser=Chrome etc to maven command used to run the tests.

For example if we only want to run the tests tagged with @Tag("smoke") we can do that with the following command:
mvn test -Dgroups=smoke


# FirtsTests
