# Design Discussion

## Individual Designs (Section A)

### Design 1

![](https://i.imgur.com/xHZP4uI.png)
Cons:
* Unnecessary function under employee.
* Login info for user is not needed as they are just users.
* Overlap of search functions
* Missing material type
* Water resistance missing from Vinyl and Laminate.
* The search goes directly to Floor, rather than to Category

Pros:
* Incorporated some multiplicities.
* the dual tone design of the UML allows for easy grasp of information

### Design 2

![](https://i.imgur.com/jroKIeW.png)
Cons:
* Both employees and users get access to the same functions.
* The examples are specific, and shouldn’t be
*  Laminate and Vinyl can be resistant in boolean vs string.
*  Size of floor is in String rather than a number.
*  Store offering is repetitive

Pros:
*  Has various relevant association types
*  Has clear hierarchy 
*  Very detailed

### Design 3

![](https://i.imgur.com/xdgu8Io.png)
Cons:
* Store names are identical in a chain, so an ID is needed

Pros:
* Neat, simple, and organized
* clearly shows how the search works
* Shows good hierarchy 

### Design 4

![](https://i.imgur.com/WiHrXAc.png)
Cons:
* Unnecessary database adds to UML complexion
* Not the correct hierarchy 
* No customer needed, nor payment info as there is no purchasing in this store.
* Quantity size is not of the store, but rather of the individual floors

Pros:
* Gave thought to the need of a database

## Team Design (Section B)

![](https://lh4.googleusercontent.com/DG3Sm7EqB7fr-kqMD0iK2aaAWRzK4kp8TH7TeQFVypZxEuL3xdG_xFoIIaaTFVJbPKX4JPZEFc_FjDmX4b4BSeTiHLycmgcB-tyXmwBKv0BYupLnKfSoRYVHQogISOs74LY8D-Oo)

Our main UML design uses a combination of both Design 2 and Design 3. We found this to be best as Design 3 was oversimplified, it had the least cons, while Design 2 made up for it with the thorough details. All designs shared similarities in many aspects, such as making sure we have a user, employee, and floor types. Yet, the functions were different and some attributes.

We decided to share a direct relationship from user to store and employee to store, as to have access to the offerings they need access to the store. This is in contrast to some of our other individual UMLs that didn’t account for a chain of stores. From there, we decided to tackle the hierarchy problem, making sure that after store we have the various products, and from there we have the categories (first level) and floor types (second level). So to search, we made sure to include the search function in user as well as empoloyee, as both can search the same way. 

In order to include employees, we have a login function that uses an employees ID and PIN number. We also decided to assign StoreID to employees so we can make sure their edited products are at their own stores.


## Summary (Section C)

We elaborated on how to make each other’s design better. We compared and implemented each of our designs to one and the pros and cons helped us determine what to exclude and include in the design. We considered flexibility in our design while making it, since there are many interpretations of a request for a design. Having a team that criticizes each one’s interpretation, we can create an optimum design to satisfy the request. 


