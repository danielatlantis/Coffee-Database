# Coffee-Database

This database was created for an imaginary coffee shop. An ER-model was created to outline the key entities and relatioships within the database structure. The coffee shop had to have multiple stores, customers, coffees, sales, and promotions. 

A driver program ws created in Java that when run, allows the user/client to perform operations such as adding a new customer, or making a new sale, and many more advanced operations as well. A benchmark program was also created that tests the capabilities of the database and assures that it survives copious amounts of data operations while maintaining it's structure.

The SQL schema is very strightforward and basically mimics the ER-model that was created, a few triggers were made so that when specific opertions are performed the database acts accordingly, such as a promotion's end date reaaching the current clock's date would prevent the promotion from being activated upon a sale. Other situations such as a customer using their redeem points on a coffee also assures that the customer has enough points and if they do then those points are subtracted from their current total.
