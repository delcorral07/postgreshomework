# SpringBoot, Postgres & JPA Homework

In this project we're covering some of the basics usages of these three technologies.

In order to add some complexity to the excercise, I divided the main entity **(Kings)** into three different tables: 
- Kings
- Country
- Houses

These three tables are related by Kings and Houses, having the following Schema:
1. Kings has both Country and House (Unidirectional many to one relationship)
2. Houses has a Country (Unidirectional many to one relationship)
3. Country goes on its own, without relationships

## SpringBoot

Initialize a micro-service with some endpoints, having a total of 3
1. Country end-point with CRUD functionalities
2. Kings end-point with CRUD functionalities
3. Houses end-point with CRUD functionalities

## Postgres

Creation of a relational database using foreign keys to stablish a relationship within the tables

## JPA

1. Usage of JPA repositories, using both In-method-name queries (JPA Queries) and @Query annotation (Also with native queries)
  - Native Query = true can be found on CountryRepository
  - Native Query = false (default) can be found on HouseRepository
  - JPA Queries can be foun on KingRepository


## DTO

DTO are used to convert the objects from the database into a client-side objet. In this particular case I'm not changing any parameter, but in another 
case I can use this conversion for example, to hide a password from User object when it's retrieved from the client
