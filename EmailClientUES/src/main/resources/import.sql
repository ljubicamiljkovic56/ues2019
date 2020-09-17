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
INSERT INTO users(username, password, firstname, lastname) VALUES ('ana', 'ana', 'Ana', 'Antić');

INSERT INTO accounts(smtp_address, smtp_port, inserver_type, inserver_address, inserver_port, username, password, displayname, user_id) VALUES ('smtpAddress1', 21, 56, 'inserverAddress1', 80, 'miki@gmail.com', 'miki123', 'miki@gmail.com', 1);
INSERT INTO accounts(smtp_address, smtp_port, inserver_type, inserver_address, inserver_port, username, password, displayname, user_id) VALUES ('smtpAddress2', 11, 77, 'inserverAddress2', 122, 'miki@icloud.com', 'miki23', 'miki@icloud.com', 1);
INSERT INTO accounts(smtp_address, smtp_port, inserver_type, inserver_address, inserver_port, username, password, displayname, user_id) VALUES ('smtpAddress3', 12, 78, 'inserverAddress3', 125, 'pera@gmail.com', 'pera', 'pera@gmail.com', 2);
INSERT INTO accounts(smtp_address, smtp_port, inserver_type, inserver_address, inserver_port, username, password, displayname, user_id) VALUES ('smtpAddress4', 33, 44, 'inserverAddress4', 127, 'ana@gmail.com', 'ana', 'ana@gmail.com', 3);


INSERT INTO photos(photo_id,path,contact_id) VALUES (1, 'C:/Pictures/oasis.jpg', 4);
INSERT INTO photos(photo_id,path,contact_id) VALUES (2, 'C:/Pictures/cat.jpg', 5);
INSERT INTO photos(photo_id,path,contact_id) VALUES (3, 'C:/Pictures/sea.jpg', 6);
INSERT INTO photos(photo_id,path,contact_id) VALUES (4, 'C:/Pictures/blue wave.jpg', 1);


INSERT INTO contacts(firstname, lastname, displayname, email, note, user_id, photo_id) VALUES ('Ana', 'Antić', 'Ana', 'ana@gmail.com', 'anticeva', 1, 1);
INSERT INTO contacts(firstname, lastname, displayname, email, note, user_id, photo_id) VALUES ('Aca', 'Acić', 'Aca', 'aca@yahoo.com', 'aca', 1, null);
INSERT INTO contacts(firstname, lastname, displayname, email, note, user_id, photo_id) VALUES ('Mile', 'Milić', 'Mile', 'mile@gmail.com', 'mile skolski', 2, null);
INSERT INTO contacts(firstname, lastname, displayname, email, note, user_id, photo_id) VALUES ('Ana', 'Mitić', 'AMitić', 'mitic.ana@gmail.com', 'miticeva', 2, 1);
INSERT INTO contacts(firstname, lastname, displayname, email, note, user_id, photo_id) VALUES ('Верица','Крстић', 'В.Крстић', 'verica@ptt.rs', 'verica 2.mejl', 3, 2);
INSERT INTO contacts(firstname, lastname, displayname, email, note, user_id, photo_id) VALUES ('Милош','Жикић', 'Милош Ж.', 'milos.zikic@yahoo.com', 'милош миша жикић', 3, 3);

INSERT INTO folders (name, parent_folder, account_id) VALUES ('Folder 1', null, 1);
INSERT INTO folders (name, parent_folder, account_id) VALUES ('Folder 2', 1, 3);
INSERT INTO folders (name, parent_folder, account_id) VALUES ('Folder 3', 2, 3);
INSERT INTO folders (name, parent_folder, account_id) VALUES ('Drafts', null, 3)
INSERT INTO folders (name, parent_folder, account_id) VALUES ('Sent', null, 2)
INSERT INTO folders (name, parent_folder, account_id) VALUES ('Archieved', 5, 2);

INSERT INTO tags(name, user_id) VALUES ('important', 1);
INSERT INTO tags(name, user_id) VALUES ('sent', 1);
INSERT INTO tags(name, user_id) VALUES ('promotions', 2);
INSERT INTO tags(name, user_id) VALUES ('archieved', 3);
INSERT INTO tags(name, user_id) VALUES ('snoozed', 4);

INSERT INTO rules(rule_id, rule_condition, rule_value, rule_operation, destination) VALUES (1, 1, 'rule value1', 1, 1);
INSERT INTO rules(rule_id, rule_condition, rule_value, rule_operation, destination) VALUES (2, 2, 'sent', 2, 2);
INSERT INTO rules(rule_id, rule_condition, rule_value, rule_operation, destination) VALUES (3, 3, 'folder 3', 3, 3);
INSERT INTO rules(rule_id, rule_condition, rule_value, rule_operation, destination) VALUES (4, 4, 'draft', 2, 4);
INSERT INTO rules(rule_id, rule_condition, rule_value, rule_operation, destination) VALUES (5, 1, 'sent', 3, 5);
INSERT INTO rules(rule_id, rule_condition, rule_value, rule_operation, destination) VALUES (6, 3, 'archive', 1, 6);

INSERT INTO messages(message_from, message_to, cc, bcc, message_date, message_subject, content, unread, folder_id, account_id) VALUES ('miki@gmail.com', 'pera@gmail.com', ' ', 'pera@gmail.com', '2020-04-20 09:10:56', 'Zanimljivosti', 'Evo novih zanimljivosti', false, 1, 1);
INSERT INTO messages(message_from, message_to, cc, bcc, message_date, message_subject, content, unread, folder_id, account_id) VALUES ('miki@gmail.com', 'ana@gmail.com', 'miki@gmail.com', 'ana@gmail.com', '2020-04-20 12:10:30', 'Zadatak', 'Domaci zadatak', true, 2, 2);
INSERT INTO messages(message_from, message_to, cc, bcc, message_date, message_subject, content, unread, folder_id, account_id) VALUES ('pera@gmail.com', 'mile@gmail.com', ' ', 'miki@gmail.com', '2020-08-29 18:30:10', 'Slika', 'Slika', true, 5, 3);
INSERT INTO messages(message_from, message_to, cc, bcc, message_date, message_subject, content, unread, folder_id, account_id) VALUES ('pera@gmail.com', 'mitic.ana@gmail.com', 'mile@gmail.com', ' ', '2020-09-10 14:20:22', 'Raspored', 'Raspored casova', true, 5, 3);
INSERT INTO messages(message_from, message_to, cc, bcc, message_date, message_subject, content, unread, folder_id, account_id) VALUES ('ana@gmail.com', 'verica@ptt.rs', ' ', ' ', '2020-09-14 11:30:00', 'text fajl', 'importsql.txt', true, 4, 3);
INSERT INTO messages(message_from, message_to, cc, bcc, message_date, message_subject, content, unread, folder_id, account_id) VALUES ('ana@gmail.com', 'milos.zikic@yahoo.com', 'pera@gmail.com', ' ', '2020-09-15 16:50:12', 'cat', 'cat.jpg', false, 6, 3);

INSERT INTO attachments(path, mime_type, name, message_id) VALUES ('C:/Users/Ljubica/Downloads/attachs','text', 'Zadatak', 2);
INSERT INTO attachments(path, mime_type, name, message_id) VALUES ('C:/Users/Ljubica/Downloads/attachs/oasis.jpg','jpg', 'Slika', 3);
INSERT INTO attachments(path, mime_type, name, message_id) VALUES ('C:/Users/Ljubica/Downloads/attachs/OSSSIT Raspored casova','pdf', 'Raspored', 4);
INSERT INTO attachments(path, mime_type, name, message_id) VALUES ('C:/Users/Ljubica/Downloads/attachs/importsql.txt','text', 'importsql text file', 5);
INSERT INTO attachments(path, mime_type, name, message_id) VALUES ('C:/Users/Ljubica/Downloads/attachs/cat.jpg','jpg', 'Cat', 6);
INSERT INTO attachments(path, mime_type, name, message_id) VALUES ('C:/Users/Ljubica/Downloads/attachs/blue wave.jpg','jpg', 'Wave', 3);
