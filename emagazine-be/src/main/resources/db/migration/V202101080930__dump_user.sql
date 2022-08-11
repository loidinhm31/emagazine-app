CREATE DATABASE IF NOT EXISTS emagazine;

--
-- Table structure for table users
--
CREATE TABLE IF NOT EXISTS `users`
(id INT NOT NULL AUTO_INCREMENT,
username VARCHAR(50) NOT NULL,
password VARCHAR(80) NOT NULL,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,

PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT charset=latin1;


--
-- Dumping data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
-- Default passwords here are: root & user
INSERT INTO users(username, password, first_name, last_name, email)
VALUES
    ('root','$2a$10$f3f8sQW9hgzCZxiqTU5gF.SpDgcaHKDcR9ZNvnAUL1e4ot95bbGRO', 'David', 'Ben', 'example1@mail.com'),
    ('user', '$2a$10$DaAIzhluaghr48htaQ30LuLGPRaQYe5rX0nEqxpTMLcnJKwCyzRNa', 'Eleven', 'Ease', 'example2@mail.com');


--
-- Table structure for table `roles`
--
CREATE TABLE IF NOT EXISTS `roles`
(id INT(11) NOT NULL AUTO_INCREMENT,
name VARCHAR(50) DEFAULT NULL,

PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT charset=latin1;


--
-- Dumping data for table `roles`
--
INSERT INTO roles(name)
VALUES
('ROLE_ADMIN'), ('ROLE_moderator:read'), ('ROLE_client:read'), ('ROLE_client:write');


