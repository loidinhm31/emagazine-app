--
-- Table structure for table `articles`
--
-- CREATE TABLE `articles` (
-- id BIGINT NOT NULL AUTO_INCREMENT,
-- is_root boolean default false,
-- name VARCHAR(255) NOT NULL,
-- parent_id BIGINT NULL,

-- PRIMARY KEY (id)
-- ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT charset=latin1;


--
-- Dumping data for table `articles`
--
INSERT INTO articles(name, is_root)
VALUES
    ('Programming', true),
    ('Technology', false),
    ('Economics', true),
    ('Sport', false);

INSERT INTO articles(name, is_root, parent_id)
VALUES
('Programming Language', false, 1), ('Mobile Dev', false, 1), ('Database', false, 1), ('Tools', false, 1),
('AI', false, 2), ('5G Network', false, 2), ('Big Data', false, 2),
('Finance', false, 3), ('Wall Street', false, 3),
('Football', false, 4), ('Badminton', false, 4),
('Java', false, 5), ('C/C++', false, 5), ('Python', false, 5),
('iOS', false, 6), ('Android', false, 6),
('SQLite', false, 7), ('MySQL', false, 7), ('Oracle', false, 7), ('MongoDB', false, 7),
('Java Core', false, 16), ('JDBC', false, 16), ('Java 8', false, 16), ('Spring Framework', false, 16),
('C Basic', false, 17), ('C++ Basic', false, 17), ('Functions in C/C++', false, 17), ('Master C++', false, 17),
('Python Basic', false, 18), ('Matplotlib', false, 18), ('Tensorflow', false, 18), ('Numpy', false, 18), ('Pandas', false, 18), ('VAEX', false, 18), ('Django', false, 18),
('Swift', false, 19), ('Objective-C', false, 19),
('Kotlin', false, 20), ('SDK tools', false, 20);


Update articles set is_root = true where id = 5 Or id = 6 Or id = 7 Or id = 8 Or id = 16 Or id = 17 Or id = 18 Or id = 19 Or id = 20;
--
-- Table structure for table `posts`
--
-- CREATE TABLE IF NOT EXISTS `posts`
-- (id BIGINT NOT NULL AUTO_INCREMENT,
-- title VARCHAR(255) NULL,
-- thumbnail VARCHAR(255) NULL,
-- short_description TEXT NULL,
-- content TEXT NULL,
-- date_create date NOT NULL,
-- article_id BIGINT NOT NULL,

-- PRIMARY KEY (id),
-- CONSTRAINT `fk_posts_articles` FOREIGN KEY (article_id) REFERENCES articles(id)
-- ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT charset=latin1;


-- CREATE TABLE IF NOT EXISTS `comments`
-- (id BIGINT NOT NULL AUTO_INCREMENT,
-- content TEXT NOT NULL,
-- username VARCHAR(255) NOT NULL,
-- user_email VARCHAR(255) NOT NULL,
-- post_id BIGINT NOT NULL,

-- PRIMARY KEY (id),
-- FOREIGN KEY (post_id) REFERENCES posts(id)
-- );


-- SELECT a.id, a.name
-- FROM articles a
--     INNER JOIN article_relationship ar ON a.id = ar.child_article_id
-- WHERE ar.parent_article_id = 1
-- ORDER BY id;

-- SELECT a.id, a.name
-- FROM articles a
--     INNER JOIN article_relationship ar ON a.id = ar.parent_article_id
-- WHERE ar.child_article_id = 5 /* ? is the articleID that the user clicked */
-- ORDER BY id;
