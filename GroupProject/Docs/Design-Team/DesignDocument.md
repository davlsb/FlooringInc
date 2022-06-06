# Design Document

**Author**: Team 5: Liza Hashani, Tanvir Sami, Albi Xhuveli, David Slawotsky

## 1 Design Considerations

### 1.1 Assumptions

- This app will be used by both employees and guest users
- Users will look at various stores for quantity
- Each floor only has one corresponding floor category
- Product availability is dependent on updated information given by employees
- Employees need to sign in/sign up to the app

### 1.2 Constraints

- Time: This application must be completed before the end of the semester if not before.
- Layout: The application must be adaptable to a standard smartphone screen size. The fonts of icons and search words, as well as the language must be consistent. 
- Latency: The application will have to instantly respond and load page information and images to make it convenient for the users.
- Testing: Application must successfully pass all test cases after design and implementation is complete.

### 1.3 System Environment

Android operating system with minimum API of 21.

## 2 Architectural Design 


### 2.1 Component Diagram

The main components that will be working together are:
The User component, The Employee component, UI component, and Product Database component.
The main interaction is happening between the user/employee and the store UI where the user/employee can search for an existing item in  the flooring list.
Adding and deleting a product can also be completed by the employee. 

![](https://i.imgur.com/Skq14gA.png)


### 2.2 Deployment Diagram

The application’s deployment consists of the android UI as the main device in connection with the store as the main component. This is in direct connection with the product database as our server. In terms of clients, our users access only the UI and employees have access to product management in the database through authentication. Our application is a simple system because it relays data to only one database, and in terms of hardware the users and employees have direct access to the physical means of the application.

![](https://i.imgur.com/lZY7fbD.png)

## 3 Low-Level Design

### 3.1 Class Diagram

![](https://lh4.googleusercontent.com/DG3Sm7EqB7fr-kqMD0iK2aaAWRzK4kp8TH7TeQFVypZxEuL3xdG_xFoIIaaTFVJbPKX4JPZEFc_FjDmX4b4BSeTiHLycmgcB-tyXmwBKv0BYupLnKfSoRYVHQogISOs74LY8D-Oo)

### 3.2 Other Diagrams

![](https://lh5.googleusercontent.com/4ITUstVdDN4Jud1vvj8eWcch702YNEY_K_yGpe6plB9UJEm8hdmmk97I27ADG4eSQj1By7nbMBFOZ9yFwXQhawXDen7BZMpx6bUstDsZqQ98zTWVQPyJITQ1T03u5OGq26cGcUkp_W2wj0tOdA)


## 4 User Interface Design

Homepage (User)
![](https://lh5.googleusercontent.com/n-Ll-BfwE5RgPWSHpYF4zGogYeqFFTbo_-Isk0SCeD7Z7D3mhGFVevfZ_R6y6Wp9SqngWcCPZkIX3z0SuzMAEYCqLq_oildUSmAmQD0R2jrAJv83WUhTvA_6uF3BiuStTX1TY2Bb)

Select category (User)
![](https://lh5.googleusercontent.com/dzxvJu-zglHX7uTR0Tjf-Tz6acKlwVMMsxojcGTXae1INorKvn97vDcgDOT1ioc3__wsAi_0a9UNLOjhOww269xShW1LKO4iIS-SRApOzjvxI-N8tW8AloGJU_AhtapBK6vh0A8A)

All Products (User)
![](https://lh5.googleusercontent.com/QF8reOCkUxNofWDSMFmjJWbk2btIP9kw8tNhTqv2rquWlxKYjDvCIJq0cRF0FIOgajbfNHMJsvUIc0hC6mUPRmGtZS7Cc5IqA7UlvcPQdY2T4Hul-9YxipUt90aqSxsWfm0LgIyL)

Employee Login
![](https://lh5.googleusercontent.com/pb5mXHdTA-S_MO4qJsg1zYty2S0pIntNLwEr1S-v9KygFP62ySX5CB2ruDiLOIkNStYNawe4_KwDScAikY9kFBGT4yzyH4yvkyvRYCzeVns45LtNZD5sBg5CqqkxlE1fJ2oPajf2)

Select category (Employee)
![](https://lh4.googleusercontent.com/xo4VJf_HpPdadciBs9LEdvqQ48vRfhq0CoaI5fikk5ShEcH9lwTjzNs4yKTofldWFZ-lvSTt-TF9MXnGQ6f11BTl9RegY8HY62zb3jW34spDj7dG0PJ99CRJPm4nH1viOoaR9mjS)

All Products (Employee)
![](https://lh3.googleusercontent.com/T86Qa6yR8k2mTmAdvYp_r8zC71FLvqw2SImMSV1ztHKoccyOQ0XMxXFY0agHtfkKgOxsn88ckOOjmeIw0j58uN0mNuKnsCz294BkDWlMrBEhKkEJh8V14QJjSls3u5I6i8e3gln-)

Product Details (Employee)
![](https://lh6.googleusercontent.com/-AR6Vd4U3KAJgU7bgFkPQKUoKFAgXFJpHveI_7P3G9tvc6B8OPt63P0CcqBQLYmpRZFO-KMvER1yKu3S3IGFQWXeW9qa5BePgFPQiTrnHWPtCHkgjQXH3CecXTigGpS2c9eayW4O)

Edit Products (Employee)
![](https://lh6.googleusercontent.com/W0uVBZG0aU25AT_0sm6f_NxPV09BQjjc99a8QV4vXPSR63Rw2nV68AUHFkBvL6Q7Efb979B_vEoyL4O_f_Q_BFBra_9rhit6UJc5nGTa0PsQjnKj9RVkZ_kxrZ2jEwdQOu2LwmH1)

Add Products (Employee)
![](https://lh3.googleusercontent.com/3TMBUwohpB8UAn-m8khCBpcsnPx5_8YbAQ65m-yPC_gs16QDD-WvERefixcqcnyi9M2dO5Wj47zW1CIU5xCBWBBzNPD_Bbljfy0Y8jCpYM1FDRp0uPCrdhum-5FQsIVGPf82HO2s)




























