--
-- Table structure for table `users_roles`
--

-- CREATE TABLE IF NOT EXISTS `users_roles`
-- (user_id INT NOT NULL,
-- role_id INT(11) NOT NULL,

-- PRIMARY KEY (user_id, role_id),
--
-- KEY `fk_role_idx` (role_id),
--
-- CONSTRAINT `fl_user_01` FOREIGN KEY (user_id) REFERENCES users(id)
-- ON DELETE NO ACTION ON UPDATE NO ACTION,
--
-- CONSTRAINT `fk_role` FOREIGN KEY (role_id) REFERENCES roles(id)
-- ON DELETE NO ACTION ON UPDATE NO ACTION
-- ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users_roles`
--
INSERT INTO users_roles(user_id, role_id)
VALUES
(1, 1),
(2, 3),
(2, 4);