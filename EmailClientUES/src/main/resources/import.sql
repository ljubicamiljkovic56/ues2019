DROP schema IF EXISTS ues;
CREATE SCHEMA ues DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE ues; 

CREATE TABLE users(user_id BIGINT NOT NULL AUTO_INCREMENT,username VARCHAR(45) NOT NULL,password VARCHAR(45) NOT NULL,firstname VARCHAR(45) NOT NULL,lastname VARCHAR(45) NOT NULL, PRIMARY KEY (user_id));


