### coin-market-cap

This repository runs UI & API tests on coin-market-cap
It also generate `screen shots` for your tests if you enable it and generates reports at the end of the test

### Installation (pre-requisites)

1. JDK 1.8+ (make sure Java class path is set)
2. Maven (make sure .m2 class path is set)
3. IntelliJ / Eclipse
4. Browser driver (make sure you have your desired browser driver and class path is set)

### Run Tests

Open terminal (MAC OSX) or command prompt / power shell (for windows OS) and navigate to the project directory
type `mvn clean test` command to run features. With this command it will invoke the default Firefox browser and will
execute the tests.

- To run features on specific browser use, `mvn test "-Dbrowser=browser_name"`
  browser_name can be one of following but make sure that browser’s driver file are present and specified in system
  variable. -- firefox -- chrome -- ie -- safari etc.

Please note that browser drivers are not included as part of this framework. The reason for not including is that
selenium browser driver version are varies based on the browser version that you are using and also selenium server
version.

#### To only run all API Tests
``
mvn test -Dcucumber.options="classpath:features/API/"`
``

#### To only run all UI Tests
``
mvn test -Dcucumber.options="classpath:features/UI/"`
``

#### To run specific feature if you have multiple feature files use,
  `mvn test -Dcucumber.options="classpath:features/fileName.feature"`



### Reporters

Once you run your tests you can generate the various types of reports. 

##### Allure Report:

Reports will be auto generated after the suite completion
Reports will be saved tо directory: `reports/cucumber-html-reports/html-report.html/index.html` 

and you can view it locally.
