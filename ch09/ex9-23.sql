-- Listing 9-23. Prepared Statement for Inserting into the CUSTOMER Table

insert into CUSTOMER (firstName, middleInitial, lastName, address, city,
state, zip) values (?, ?, ?, ?, ?, ?, ?)
