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

CREATE TABLE exercises (
  id int AUTO_INCREMENT,
  title varchar(255),
  description text,
  PRIMARY KEY(id)
);

CREATE TABLE solutions (
  id int AUTO_INCREMENT,
  created datetime NOT NULL,
  updated datetime NOT NULL,
  description text NOT NULL,
  users_id int,
  exercises_id int,
  PRIMARY KEY(id),
  FOREIGN KEY(users_id) REFERENCES users(id),
  FOREIGN KEY(exercises_id) REFERENCES exercises(id)
);