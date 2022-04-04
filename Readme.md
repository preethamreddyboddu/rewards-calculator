# Assignment

A retailer offers a rewards program to its customers, awarding points based on each recorded
purchase.

A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point
for every dollar spent over $50 in each transaction
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

Given a record of every transaction during a three month period, calculate the reward points
earned for each customer per month and total.


## Steps to Setup

**1. Clone the application**

   - 


**2. Run the app using maven from command line  **
   - mvn spring-boot:run 


## Explore Rest APIs

The app defines following CRUD APIs.
 - Get all Purchases    
    - GET /purchase   

 - Get a Purchase by id;
   - GET /purchase/{id}

 - Create a new Purchase
   - POST /purchase

 - Get all Customers Points from the last three months
   - GET /rewards/{customerId}

You can test them using postman or any other rest client.

