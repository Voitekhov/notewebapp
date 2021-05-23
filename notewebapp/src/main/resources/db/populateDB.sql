DELETE FROM notes;
DELETE FROM categories;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (first_name, email, password)
VALUES ('Anton','Anton@gmail.ru','anton'),
       ('Max','Max@gmail.com','456'),
       ('John','John@gmail.com','789');
INSERT INTO categories (user_id, name)
VALUES (100001,'sport'),
       (100002,'study');
INSERT INTO notes (category_id, title, text,link)
VALUES ('100003','Running','Smth','somelink'),
       ('100004','English course','Smth2','somelink2')

