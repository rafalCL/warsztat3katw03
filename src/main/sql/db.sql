CREATE DATABASE warsztat3katw03
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;


CREATE TABLE users (
  id int AUTO_INCREMENT,
  username varchar(255) UNIQUE NOT NULL,
  email varchar(255) UNIQUE NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY(id)
);