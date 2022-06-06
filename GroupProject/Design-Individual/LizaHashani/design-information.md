# 370Spring22Liza-Hashani

## Assignment 5 Design Information

1. As a user of the system I want to be able to see each store's offering and the amount of
product in stock by square feet.

For this requirement, I created a separate class diagram for Store representing stores of the chain with attributes StoreID, ProductName, and ProductCount which is the amount of product in stock by square feet. Each store contains information for all the products that can be searched. The class storeOffering with the same attributes. I designed a class Products that has attributes productName, productCount, and productStoreID. StoreOffering utilizes the Products method getProductName and getProductCount. 

2. The application must allow employees to add new products to the system. As well as
delete and edit them.

For this requirement, I designed a class Employee with attribute EmployeeName. This class indicates that an employee can add, delete, and edit a product based on its name on the application.


3. The different categories of floors available are tile, stone, wood, laminate and vinyl

For this requirement, I designed a class FloorCategory that includes tile, stone, wood, laminate, and vinyl as the attributes. 


4. The application must contain a database (DB) of floors.

This requirement was not considered because it does not affect the design directly.


5. Users must be able to search for products by picking from a hierarchical list, where the
first level is the floor category, and the second level is the floor type.

For this requirement, I designed a class User that can utilize the searchProduct and searchFloor from the Product class to search the product based on those two levels on the application.


6. Users must also be able to specify an item by typing its name (search functionality).

This requirement is realized in my design because the User utilizes the searchProduct method in Product class that specifies the product name when a search is conducted.


7. All floors regardless of their category have an associated color, size, brand, type and
price.

For this requirement, I added a class Floor with the associated color, size, brand, type, and price as attributes. 


8. Categories tile and stone have different materials they are made of, e.g. Tile - porcelain,
ceramic, resin; Stone - marble, pebble, slate.

This has been realized by creating separate class designs that shows FloorCategory Types such as: TileType with its attributes such as porcelain, ceramic and resin and StoneType class design with its attributes such as marble, pebble, slate. Each of these classes can provide types (attributes) via getTileType() or getStoneType() methods.

9. Wood floors have both a type (solid, engineered, bamboo, etc) and species (oak,
hickory, maple, etc.)

For this requirement, I added two classes WoodSpecies and WoodType. Both classes represent types and species of Wood FloorCategory. Each class could implement method to access respective class attributes


10. Laminate can be regular laminate or water resistant, whereas vinyl can be water
resistant or waterproof.

For this requirement, I added two separate class designs, LaminateType and VinylType. Each represents Laminate and Vinyl FloorCategory types respectively. Each class implements getLaminateType() and/or getVinylType() methods to access respective class attributes.


11. The User Interface (UI) must be intuitive and responsive.

This requirement was not considered because it does not affect the design directly.


