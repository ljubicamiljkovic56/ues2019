--DROP schema IF EXISTS ues;
--CREATE SCHEMA ues DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
--USE ues; 

--CREATE TABLE users(user_id BIGINT NOT NULL AUTO_INCREMENT,username VARCHAR(45) NOT NULL,password VARCHAR(45) NOT NULL,firstname VARCHAR(45) NOT NULL,lastname VARCHAR(45) NOT NULL, PRIMARY KEY (user_id));

--CREATE TABLE accounts (account_id BIGINT NOT NULL AUTO_INCREMENT,smtp_address VARCHAR(45),smtp_port INT NOT NULL,inserver_type INT NOT NULL,inserver_address VARCHAR(45),inserver_port INT NOT NULL,username VARCHAR(45) NOT NULL,password VARCHAR(45) NOT NULL,displayname VARCHAR(45) NOT NULL,user_id BIGINT NOT NULL,PRIMARY KEY (account_id),FOREIGN KEY (user_id) REFERENCES users(user_id));

--CREATE TABLE photos (photo_id BIGINT NOT NULL AUTO_INCREMENT,path VARCHAR(45) NOT NULL,PRIMARY KEY (photo_id));

--CREATE TABLE contacts (contact_id BIGINT NOT NULL AUTO_INCREMENT,firstname VARCHAR(45) NOT NULL,lastname VARCHAR(45) NOT NULL,displayname VARCHAR(45) NOT NULL,email VARCHAR(45) NOT NULL,note VARCHAR(30),user_id BIGINT NOT NULL,photo_id BIGINT,PRIMARY KEY (contact_id),FOREIGN KEY (user_id) REFERENCES users(user_id),FOREIGN KEY (photo_id) REFERENCES photos(photo_id));

---CREATE TABLE folders (folder_id BIGINT NOT NULL AUTO_INCREMENT,name VARCHAR(45) NOT NULL,parent_folder BIGINT,account_id BIGINT NOT NULL,PRIMARY KEY (folder_id),FOREIGN KEY (account_id) REFERENCES accounts(account_id),FOREIGN KEY (parent_folder) REFERENCES folders(folder_id));

--CREATE TABLE tags (tag_id BIGINT NOT NULL AUTO_INCREMENT,name VARCHAR(45) NOT NULL,PRIMARY KEY (tag_id));

--CREATE TABLE attachments (attachment_id BIGINT NOT NULL AUTO_INCREMENT,path VARCHAR(45) NOT NULL,mime_type VARCHAR(45),name VARCHAR(45) NOT NULL,PRIMARY KEY (attachment_id));

--CREATE TABLE messages (message_id BIGINT NOT NULL AUTO_INCREMENT,message_from VARCHAR(45) NOT NULL,message_to VARCHAR(45) NOT NULL,cc VARCHAR(45),bcc VARCHAR(45),message_date TIMESTAMP NOT NULL,message_subject VARCHAR(45),content VARCHAR(45),unread BOOLEAN NOT NULL,message_tags BIGINT,message_attachments BIGINT,PRIMARY KEY (message_id),FOREIGN KEY (message_tags) REFERENCES tags(tag_id),FOREIGN KEY (message_attachments) REFERENCES attachments(attachment_id));

SET FOREIGN_KEY_CHECKS = 0;

--SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO users(username, password, firstname, lastname) VALUES ('miki123', 'miki123', 'Miki', 'Mikic');
INSERT INTO users(username, password, firstname, lastname) VALUES ('pera', 'pera', 'Pera', 'Peric');

INSERT INTO accounts(smtp_address, smtp_port, inserver_type, inserver_address, inserver_port, username, password, displayname, user_id) VALUES ('smtpAddress1', 21, 56, 'inserverAddress1', 80, 'miki@gmail.com', 'miki123', 'miki@gmail.com', 1);
INSERT INTO accounts(smtp_address, smtp_port, inserver_type, inserver_address, inserver_port, username, password, displayname, user_id) VALUES ('smtpAddress2', 11, 77, 'inserverAddress2', 122, 'miki@icloud.com', 'miki23', 'miki@icloud.com', 1);
INSERT INTO accounts(smtp_address, smtp_port, inserver_type, inserver_address, inserver_port, username, password, displayname, user_id) VALUES ('smtpAddress3', 12, 78, 'inserverAddress3', 125, 'pera@gmail.com', 'pera', 'pera@gmail.com', 2);

INSERT INTO photos(photo_id,path) VALUES (1, 'C:/Pictures/oasis.jpg')
INSERT INTO photos(photo_id,path) VALUES (2, 'C:/Pictures/oasis.jpg')
INSERT INTO photos(photo_id,path) VALUES (3, 'C:/Pictures/oasis.jpg')

INSERT INTO contacts(firstname, lastname, displayname, email, note, user_id, photo_id) VALUES ('Ana', 'Antic', 'Ana', 'ana@gmail.com', 'note1', 1, null);
INSERT INTO contacts(firstname, lastname, displayname, email, note, user_id, photo_id) VALUES ('Aca', 'Acic', 'Aca', 'aca@yahoo.com', 'note2', 1, null);
INSERT INTO contacts(firstname, lastname, displayname, email, note, user_id, photo_id) VALUES ('Mile', 'Milic', 'Mile', 'mile@gmail.com', 'note3', 2, null);

INSERT INTO folders (name, parent_folder, account_id) VALUES ('Folder 1', null, 1);
INSERT INTO folders (name, parent_folder, account_id) VALUES ('Folder 2', null, 3);
INSERT INTO folders (name, parent_folder, account_id) VALUES ('Folder 3', 2, 3);

INSERT INTO tags(name, user_id) VALUES ('tag1', 1);

INSERT INTO rules(rule_id, rule_condition, rule_value, rule_operation, destination) VALUES (1, 1, 'value1', 1, 1);

INSERT INTO messages(message_from, message_to, cc, bcc, message_date, message_subject, content, unread, folder_id, account_id) VALUES ('miki@gmail.com', 'pera@gmail.com', null, null, '2020-04-20 09:10:56', 'Zanimljivosti', 'Evo novih zanimljivosti', false, 1, 1);
INSERT INTO messages(message_from, message_to, cc, bcc, message_date, message_subject, content, unread, folder_id, account_id) VALUES ('miki@gmail.com', 'ana@gmail.com', null, null, '2020-04-20 12:10:30', 'Zadatak', 'Domaci zadatak', true, 2, 2);

INSERT INTO attachments(path, mime_type, name, message_id) VALUES ('path1','text/html', 'Zadatak', 2)
