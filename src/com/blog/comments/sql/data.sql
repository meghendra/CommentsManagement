-- create database perfectsense;
-- use perfectsense;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Replies;

CREATE TABLE Users
(
    user_ID INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR (255) NOT NULL,
    email VARCHAR (255) NOT NULL,
    password VARCHAR (255) NOT NULL
);

INSERT INTO Users (user_name, email, password) VALUES ('Alice','alice@gmail.com','unsafe');
INSERT INTO Users (user_name, email, password) VALUES ('Bob','bob@gmail.com','unsafe');
INSERT INTO Users (user_name, email, password) VALUES ('Charlie','charlie@gmail.com','unsafe');
INSERT INTO Users (user_name, email, password) VALUES ('Dave','dave@gmail.com','unsafe');
INSERT INTO Users (user_name, email, password) VALUES ('Eve','eve@gmail.com','unsafe');


CREATE TABLE Comments
(
    comment_ID INT PRIMARY KEY AUTO_INCREMENT,
    article_ID INT NOT NULL,
    user_name VARCHAR (255) NOT NULL,
    time_stamp DATETIME NOT NULL,
    content VARCHAR (10000) NOT NULL,
    parent_ID INT DEFAULT -1, 
    number_of_likes INT DEFAULT 0,
    is_deleted INT DEFAULT 0,
    times_reported INT DEFAULT 0
);

-- top level comments
INSERT INTO Comments (comment_ID, article_ID, user_name, time_stamp, content) VALUES (1, 1, 'Alice', '2013-08-05 18:19:03', 'Alice made a comment...');
INSERT INTO Comments (comment_ID, article_ID, user_name, time_stamp, content) VALUES (2, 1, 'Bob', '2013-08-06 18:19:15', 'Bob made a comment...');
INSERT INTO Comments (comment_ID, article_ID, user_name, time_stamp, content) VALUES (3, 1, 'Charlie', '2013-08-07 18:19:03', 'Charlie made a comment...');
INSERT INTO Comments (comment_ID, article_ID, user_name, time_stamp, content) VALUES (4, 1, 'Dave', '2013-08-10 18:19:03', 'Charlie made a comment...');
-- nested comments 
INSERT INTO Comments (comment_ID, article_ID, user_name, time_stamp, content, parent_ID) VALUES (5, 1, 'Alice', '2013-08-05 18:20:00', 'Alice made a second comment...', 1);
INSERT INTO Comments (comment_ID, article_ID, user_name, time_stamp, content, parent_ID) VALUES (6, 1, 'Alice', '2013-08-05 18:20:00', 'Alice made a third comment...', 2);
INSERT INTO Comments (comment_ID, article_ID, user_name, time_stamp, content, parent_ID) VALUES (7, 1, 'Bob', '2013-08-05 18:20:00', 'Alice has so many things to say...', 5);


CREATE TABLE Replies
(
    reply_ID INT PRIMARY KEY AUTO_INCREMENT,
    parent_comment_ID INT NOT NULL, 
    child_comment_ID INT NOT NULL
);

-- replies to comments
INSERT INTO Replies (parent_comment_ID, child_comment_ID) VALUES (1, 5);
INSERT INTO Replies (parent_comment_ID, child_comment_ID) VALUES (2, 6);
INSERT INTO Replies (parent_comment_ID, child_comment_ID) VALUES (5, 7);

select * from Comments;
select * from Users;
select * from Replies;

