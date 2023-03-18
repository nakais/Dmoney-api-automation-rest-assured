# Dmoney-api-automation-rest-assured
This is a REST API for managing customers and agents and performing financial transactions between them using the dmoney API system. This API is implemented using the Java programming language and the REST-assured framework.

## Getting Started
To get started with using this API, follow the steps below:

1. Clone this repository to your local machine.
2. Build and run the application using your favorite Java IDE.
3. Use REST-assured or any other HTTP client tool to interact with the API endpoints.

## Scenarios 
1. Call login API
2. Create  a new customer and an agent
3. Search by the customer phone number
4. Deposit 5000 tk to the Agent from system
5. Deposit 2000 tk by agent to customer 
6. Check balance of customer
7. Check statement by trnxId 
8. Withdraw 1000 tk by customer and assert expected balance
9. Send 500 tk to another customer and assert expected balance
10. Check customer statement

The scenarios are implemented in the UserTestRunner.java and TransactionTestRunner file.

## Test Cases
https://docs.google.com/spreadsheets/d/1Em3G00ggRjbCUaKwb-l7fj1RxTYTBF2Q/edit?usp=sharing&ouid=109830629185705881813&rtpof=true&sd=true

## Test Reports:
### HTML Report:
The HTML report provides a summary of the test results and includes a list of passed and failed test cases.

For generating HTML report hit this commmand: ```gradle clean test```

![html_restassured](https://user-images.githubusercontent.com/52671754/226095420-b0269d77-d083-47fa-9743-ce2bff57d59a.png)

### Allure Report: 
The Allure report provides detailed information about each test case and includes screenshots and logs for each step. 

For generating Allure report hit the command:
 ```
allure generate allure-results --clean -o allure-report
allure serve allure-results
 ```

![allu-rest-assured1](https://user-images.githubusercontent.com/52671754/226095438-fe9cfeca-66e9-4f93-898d-1e2e512bcc83.png)
![allu-rest-assured2](https://user-images.githubusercontent.com/52671754/226095459-3e5a6b3e-4327-4dc4-9d01-c6a191903eef.png)

