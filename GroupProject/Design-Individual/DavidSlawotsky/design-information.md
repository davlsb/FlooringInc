Dee Slawotsky
CS 370

1. As a user of the system I want to be able to see each store's offering and the amount of
product in stock by square feet, so there is a class user, which can access the class store, which has a storeName attribute of type String.
2. The application must allow employees to add new products to the system. As well as
delete and edit them, so the employees have access to the store, and the functions addNewProduct(prod), deleteProduct(prod), and editProduct(prod). They also have an ID and pin Integer attributes to differentiate them from Users. They can also login() using the function.
3. The different categories of floors available are tile, stone, wood, laminate and vinyl, so there are five categories that are found in each store.
5. Users must be able to search for products by picking from a hierarchical list, where the
first level is the floor category, and the second level is the floor type, so when the user searches, they have access to the store's 5 categories, and from there the floor types.
6. Users must also be able to specify an item by typing its name (search functionality), so the user also has access from the store to the floor types directly, using the search function and floorTypeName.
7. All floors regardless of their category have an associated color, size, brand, type and
price, so there is a class called floorType that inherits from the category classes and has it's own attributes: a String called color, a Double called size an amountInStock which is a Double, a String called brand, and a Double for the price. It also has setter and getter functions to make it easier to adjust using the employee's edit or the search function.
8. Categories tile and stone have different materials they are made of, e.g. Tile - porcelain,
ceramic, resin; Stone - marble, pebble, slate, so each of those category classes have the String attribute of material.
9. Wood floors have both a type (solid, engineered, bamboo, etc) and species (oak,
hickory, maple, etc.), so the class has an attributes of type String called  type and species.
10. Laminate can be regular laminate or water resistant, whereas vinyl can be water
resistant or waterproof, so the class Laminate has a boolean attribute called waterResistant, and if it's false it's regular laminate and if true it's water resistant. The vinyl class has the boolean attribute WaterResistant as well, but with the addition of the boolean attribute called waterProof, where it's true if waterproof and false if not.
