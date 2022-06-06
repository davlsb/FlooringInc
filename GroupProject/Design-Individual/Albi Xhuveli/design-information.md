Class Diagram:
A class diagram is a structural type of UML diagrams. It depicts the structural view of a system. It shows classes of objects and their relationship with each other.

Design Information:
1.	In user class a method search is added so user can search a product(floor).
2.	An employee class is associated with floor class so employee can add new products (floor) in the system. As well as delete and edit them.
3.	There are subclasses added in the class diagram which represents the categories of floor.
4.	There is unique ID which is attached with every class so when a floor is added in the system it is separately recorded in the database.
5.	In user class a method search is added so user can search a product(floor). Also they can search by name or by selecting category and then type.
6.	In user class a method searchByName is added so user can search a product(floor) directly by its name.
7.	All common attributes of floor are already added in super class Floor. (color, size, brand, type, price).
8.	In Tile and Stone subclasses additional attributes material is added. To record the type of material of both Strone and Tile.
9.	In wood subclass additional attributes type and species is added. To specify the type and species of Wood floor.
10.	In Laminate subclass laminateType is added to specify the type of laminate floor. In Vinyl subclass vinylType is added to specify the type of vinyl floor.
11.	The User Interface part is not realized in the design because it doesn’t affect the class diagram directly.
