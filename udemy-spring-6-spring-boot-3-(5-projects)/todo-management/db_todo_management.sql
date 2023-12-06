DROP SCHEMA IF EXISTS db_todo_management;
CREATE SCHEMA IF NOT EXISTS db_todo_management;
USE db_todo_management;

SELECT * FROM users;
SELECT * FROM user_roles;
SELECT * FROM roles;
SELECT * FROM todo;

INSERT INTO users (name, username, email, password)
VALUES ("Vinícius", 'viniciusdsandrade2', 'viniciusdsandrade0662@gmail.com', 'GhostSthong567890@'),
       ("Andrade", 'viniciusdsandrade', 'vinicius_andrade2010@hotmail.com', 'GhostSthong567890@');

INSERT INTO roles (id, name)
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 2),
       (2, 1);