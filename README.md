# tuumBankDemoProject

Pre-requesites:

    Maven
    Docker
    Docker-compose

To Start this project follow these steps: 
  1. Download the code base
  2. open command prompt/terminal and cd into the project folder
  3. type: "mvn clean install -DskipTests"
  4. Once the repositories and dependencies are downloaded, type "docker-compose up" (NOTE: Make sure to be in the folder where docker-compose.yml file is 			 present).
  5. Wait for services to spin-up and start running
  6. you can check if its up and running using the command "docker ps"

        
  This project Includes Integrations tests on all the Rest API's (Make sure all the services are up and running while running the tests).
        
    This Project Uses MyBatis for data persistence to DB Used MyBatis Generator to generate the sources according to the DB.
    Uses Rabbit MQ to queue Events on Create, Update and Delete of Accounts and Transactions.
  
  Please use the Gateway service url as the base URL for all the endpoints
    
    Gateway: http://localhost:4000
    Base URL: http://localhost:4000/api/v1

This application has 4 services
  1. Account Service
  2. Transaction Service
  3. Eureka Registry Service
  4. Gateway Service

Account Service running on port 8080, Connects to account-serviceDb and has the following Endpoints:
  1. Find All Accounts Registered: http://localhost:4000/api/v1/accounts/mybatis/getAllAccounts
  2. Create an Account: http://localhost:4000/api/v1/accounts/mybatis/createAccount
      Json Request Body: 
          
            {
              "userId":"Johane",
              "balance":4382,
              "country":"Germany",
              "currency": "EUR"   // Only Selected Currencies are allowed
            }
     
     
  Every input has it's own validation which throws an exception with a wrong input. 
  3. Find Account by account Id: http://localhost:4000/api/v1/accounts/getAccount?accountId=<Replace the account Id you want the details of> Note: Get account Id using Find All Accounts.
  4. Find Account by User Id: http://localhost:4000/api/v1/accounts/getAccountByUser?userId=Johane
  5. Find all transactions using your Account Id: http://localhost:8080/api/v1/accounts/getAllTransactions?accountId=<REPLACE ACCOUNTID HERE>
  
Transaction Service running on port 8081, Connects to transaction-serviceDb and has the following Endpoints:
  1. Create a Transaction: http://localhost:4000/api/v1/transaction/mybatis/createTransaction
    JSON Request Body:
    
          
          {
              "accountId":"3837f207-7cd2-4cd2-bbb0-10223b72e273",
              "amount":999,
              "currency":"GBP",  // Validation Currencies should Match with the account also has invalid currency exception if given a bad input
              "transactionDirection":"OUT",   // IN(Deposit) and OUT(Withdrawal)
              "description":"I Got a PS5"
           }
     
  Every input has it's own validation which throws an exception with a wrong input 
  
  Find All Transaction By Account Id: http://localhost:4000/api/v1/transaction/getTransactionsByAccountId?accountId=<REPLACE ACCOUNT ID HERE>
  Delete All Transaction By Account Id: http://localhost:4000/api/v1/transaction/deleteTransactionByAccountId?accountId=<REPLACE ACCOUNT ID HERE>
  
  
  

I have optionally tried using reactive programming on certain api's which may be incorrect cause reactive should be in a nonblocking way need sometime to work on this. 
  1. Find All Accounts: http://localhost:4000/api/v1/accounts/webclient/getAllAccounts
  2. Find Account By Account Id: http://localhost:4000/api/v1/accounts/webclient/getAccount?accountId=<REPLACE ACCOUNT ID HERE>
  3. Find Transactions by Account Id: http://localhost:4000/api/v1/transaction/getTransactionsByAccountId?accountId=<REPLACE ACCOUNT ID HERE>


Features: 
  All services are registered to eureka server and Gateway handles routing of services between services.
  Loadbalancing is implemented on services.
  Every Endpoints have validation and appropriate Exceptions thrown on bad inputs.

Scaling Horizontly could be done by the following:

  1. Replicating instances of our application and loading balancing them.
  2. If this exceeds the system resources(CPU, RAM etc), scale by adding new systems and routing the requests distributively
  3. Dockerising the application helps in portability and scaling up the application 
  
For the Purpose of testing I have included a User with the UserId: DemoUser
        ![Screenshot 2022-10-14 at 8 23 34 PM](https://user-images.githubusercontent.com/52725211/195877299-c81ff246-c917-4fb6-9bed-e9721315c902.png)
  Account Id will vary for different Machines;
  
        Copy the Account Id to perform transactions
        
 Creating Account with wrong currency throws the following exception with error message
        ![Screenshot 2022-10-14 at 8 26 19 PM](https://user-images.githubusercontent.com/52725211/195878032-f3906aea-93ff-454c-b33f-be352acc7ef2.png)



Successful Account Creation:
    ![Screenshot 2022-10-14 at 8 28 28 PM](https://user-images.githubusercontent.com/52725211/195878472-cf4c1684-5693-41bf-9f45-20c800c733c1.png)




Transactions with different Currency: Throws Exception with error message to users
![Screenshot 2022-10-14 at 8 30 28 PM](https://user-images.githubusercontent.com/52725211/195878844-102b8fbc-cadd-4093-9deb-0a0fb3366335.png)


Successfull Transaction:
    ![Screenshot 2022-10-14 at 8 31 36 PM](https://user-images.githubusercontent.com/52725211/195879107-a08b9164-8206-486f-81fc-8e3e6b12b67f.png)


Insufficient Funds Transaction:

    ![Screenshot 2022-10-14 at 8 33 04 PM](https://user-images.githubusercontent.com/52725211/195879413-43fd1c18-e407-478c-a6dc-fd51e475adaf.png)


Finding All Transactions for an account:
![Screenshot 2022-10-14 at 8 34 27 PM](https://user-images.githubusercontent.com/52725211/195879688-f13beda0-9c50-4226-ac47-8989dcba9770.png)

