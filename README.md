### Database Setup

* _CREATE DATABASE restaurant_management_system;_
* _\c restaurant_management_system;_
* _CREATE TABLE customers (id serial PRIMARY KEY, table_id int, name varchar, total float);_
* _CREATE TABLE meals (id serial PRIMARY KEY, name varchar, price float);_
* _CREATE TABLE tables (id serial PRIMARY KEY, name varchar, guestcount int);_
* _CREATE TABLE receipts (id serial PRIMARY KEY, meal_total float, tableid int, name varchar);_
* _CREATE TABLE customers_meals (id serial PRIMARY KEY, customer_id int, meal_id int);_
* _CREATE TABLE customers_receipts (id serial PRIMARY KEY, customer_id int, receipt_id int);_
* _CREATE DATABASE restaurant_management_system_test WITH TEMPLATE restaurant_management_system;_
