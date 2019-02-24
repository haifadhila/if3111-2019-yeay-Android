DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS cats;

CREATE TABLE IF NOT EXISTS task (
  id int(11) NOT NULL AUTO_INCREMENT,
  title varchar(50) NOT NULL,
  date_time DATETIME NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cat (
  id int(11) NOT NULL AUTO_INCREMENT,
  longitude float(4) NOT NULL,
  latitude float(4) NOT NULL,
  img varchar(300) NOT NULL,
  date_time DATETIME NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(20) NOT NULL,
  password varchar(50) NOT NULL,
  email varchar(100) NOT NULL,
  PRIMARY KEY (id)
);