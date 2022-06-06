# Test Plan

**Author**: Team 5: Liza Hashani, Tanvir Sami, Albi Xhuveli, David Slawotsky

## 1 Testing Strategy

### 1.1 Overall strategy

Our overall testing strategy is to check if the system is able to provide the users or employees with their desired data. Our unit and integration testing will mainly be done by our backend developers and the functional testing by our frontend developers. The goal is to have the most unit tests and less integration tests. We will use the method of regression testing after every change is made to a component to make sure everything functions as it should. Some examples include that we need to test conditions where a user/employee searches for a product that is not available or does not exist. We also need to check if a product edited by an employee has been updated for the user in real time, so there isn’t any misinformation.    

### 1.2 Test Selection

In order to test the previous strategies, we will use both black box and white box techniques. We can use black box testing to test the external functions of the app as it is a simple program and any visual error we find on the app can be assumed in the codes. These errors can include errors such as wrong product quantity displayed in a store, or a product that has been edited but not updated in the search. This may also include any distorted screen pages or icons. We can also utilize white box techniques by implementing inputs to check and verify loops and conditions. We will also conduct a form of path testing by executing every possible independent path that source code may take, this way we can pinpoint all faults within the code.

### 1.3 Adequacy Criterion

We will assess the quality of our test cases by quantity. We will repeat 
our tests many times with multiple conditions and make changes to them when necessary in order to have minimum bugs in our code. To ensure that each component is being tested, there will be a test for each activity in the application’s functional process such as the login authentication, viewing of products, editing abilities, and so on. The team developers who have knowledge about the internal structure (code) of this program will conduct the main logical testing. It is important that they communicate their findings with those who conduct external testing as well to better the testing techniques. 
 
### 1.4 Bug Tracking

Our team will need a program that monitors the source code and incorporates a bug tracking database that contains the information of any known bugs found. If the program detects a bug in the app, the user will have the option to report the bug. Any reported bugs will be saved as well as enhancement requests. Enhancement requests can be made by contacting the app developer.

### 1.5 Technology

The program will be tested by executing the Junit technology test cases.


## 2 Test Cases

These test cases were done to test the individual functionality of the activities and functions and their connectivity to each other.

ACTIVITIES:
- ProductDescriptionActivity
- DeleteItemActivity
- EditProductActivity
- MainActivity
- LoginActivity
- ProductListActivity
- CategoryActivity
- SearchProductActivity

We will describe below the activities the app will be used for to ensure that we will test each one for any bugs.

1. Logging into the app.
2. Searching for a product in the database.
3. Editing a product.
2. Deleting a product from the database.
3. Adding a product to the database.
4. Selecting a category.
5. Selecting a product.
6. Selecting a store from the product dropdown.
7. Editing the quantity of a previously added product.
8. Renaming a previously added product.
9. Editing the color of a previously added product.
10. Editing the category of a previously added product.
11. Editing the correct store listing of the employee.
16. Displaying an error at the employees when attempting to enter empty values.


|Test Case Description|Activity|Steps|Expected Result|Actual Result |Pass/Fail|
|---------------------|---------|-----|---------------|-------------|------|
|Start the App|None|Install and open the App|The app would run successfully and would show the Main Activity screen.|The app opened a home page |pass|
|Log into the app|MainActivity|Open the app and click login, then put in the credentials|The app should authenticate and if the credentials are correct show the category screen (CategoryActivity) with the correct buttons|The app displayed the categories after loging in with credentials|pass|
|Editing the product description|EditProductActivity|Once logged in, click on a category. Secondly, click on a product. Then, click on the edit button and edit a detail. Finally, click confirm.|The app should update the product listing and bring you back to the updated product the employee just edited|The app updated the details as per edit|pass|
|Searching for a product by name|SearchProductActivity|From the main screen, click the search bar. Then, type a name in.|The app should display all the product listings with that name.|The app displayed the results with the correct query|pass|
|Selecting a category from the category page|CategoryActivity|From the main screen, click a category.|The app should display all the product listings of that category type.|The app displays the results related to the category selected|pass|
|Selecting a product from the floor product list using categories|ProductDescriptionActivity|From the main screen, click a category. Then, click on a product listing.|The app should display the product details.|The app displays the corresponding product details|pass|
|Selecting a product from the floor product list using categories|ProductListActivity|From the main screen, click a category. Then, click on a product listing.|The app should display the product details.|The app displays the results related to the product selected|pass|
|Selecting a product from the floor product list using search|ProductListActivity|From the main screen, click the search bar. Then, type a name. Finally, click on a product listing.|The app should display the product details.|The app displays the results related to the product selected|pass|
|Selecting a store from the product dropdown.|ProductDescriptionActivity|Click a product list. Then, click on the store dropdown and select a store.|The app should display the product details for the selected store.|The app displayed the products for only in the selected store|pass|
|Renaming a previously added product.|EditProductActivity|Click a product list. Then, click the edit button. Finally, change the name of the product and click confirm.|The app should display the product details for the selected store with the changed name.|The app displays the corresponding product details with the change |pass|
|Editing the color of a previously added product. |EditProductActivity|Click a product list. Then, click the edit button. Finally, change the color of the product and click confirm.|The app should display the product details for the selected store with the changed name.|The app displays the corresponding product details with the change|pass|