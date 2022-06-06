# Assignment 5: Design Information

1. The customer class has a function to search for a product. They can specify any of the parameters, which will access the product database and access the product class to provide the customer with the desired search resuslts. 
2. The employee class has three functions, where each function atleast requires the storeID and productID in order to add, delete, or edit a specified product in a specified store. 
3. The product class uses the FloorDatabase to relate each productID to a given category. These categories include tile, stone, wood, laminate and vinyl.
4. This has been implemented in the system in order for employees, stores and customers to be able to specify the floor informations. 
5. The searchProduct class has a floorType function, which requires the floorCategory function to return a value first. Therefore, the search prioritizes a category before a type.
6. The searchProduct class has a floorName functions, which will return the desired product by searching its name and match it with the corelating productID. 
7. The Product class has all the specifications for the floor. 
8. Product class will match the given category of a product to the material they are made of using the productCategory function and productMaterial functions.
9. Floor type and species are specified in product class corelated to the productID. 
10. Product class utilizes productMaterial class in order to determine which product is regular, water resistant, or water proof. 
11. The UI is not implemented in this UML. 