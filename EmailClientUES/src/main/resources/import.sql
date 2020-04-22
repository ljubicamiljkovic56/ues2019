INSERT INTO users(username, password, firstname, lastname) VALUES ('miki123', 'miki123', 'Miki', 'Mikic');
INSERT INTO users(username, password, firstname, lastname) VALUES ('pera', 'pera', 'Pera', 'Peric');

INSERT INTO accounts(smtp_address, smtp_port, inserver_type, inserver_address, inserver_port, username, password, displayname, user_id) VALUES ('smtpAddress1', 21, 56, 'inserverAddress1', 80, 'miki@gmail.com', 'miki123', 'miki@gmail.com', 1);
INSERT INTO accounts(smtp_address, smtp_port, inserver_type, inserver_address, inserver_port, username, password, displayname, user_id) VALUES ('smtpAddress2', 11, 77, 'inserverAddress2', 122, 'miki@icloud.com', 'miki23', 'miki@icloud.com', 1);
INSERT INTO accounts(smtp_address, smtp_port, inserver_type, inserver_address, inserver_port, username, password, displayname, user_id) VALUES ('smtpAddress3', 12, 78, 'inserverAddress3', 125, 'pera@gmail.com', 'pera', 'pera@gmail.com', 2);

INSERT INTO contacts(firstname, lastname, displayname, email, note, user_id, photo_id) VALUES ('Ana', 'Antic', 'Ana', 'ana@gmail.com', 'note1', 1, null);
INSERT INTO contacts(firstname, lastname, displayname, email, note, user_id, photo_id) VALUES ('Aca', 'Acic', 'Aca', 'aca@yahoo.com', 'note2', 1, null);
INSERT INTO contacts(firstname, lastname, displayname, email, note, user_id, photo_id) VALUES ('Mile', 'Milic', 'Mile', 'mile@gmail.com', 'note3', 2, null);

INSERT INTO folders(name, parent_folder, account_id) VALUES ('Folder1', null, 1);
INSERT INTO folders(name, parent_folder, account_id) VALUES ('Folder2', null, 2);
INSERT INTO folders(name, parent_folder, account_id) VALUES ('Folder3', null, 3);

INSERT INTO messages(message_from, message_to, cc, bcc, message_date, message_subject, content, unread, message_tags, message_attach) VALUES ('miki@gmail.com', 'pera@gmail.com', null, null, '2020-04-20 09:10:56', 'Zanimljivosti', 'Evo novih zanimljivosti', false, null, null);
INSERT INTO messages(message_from, message_to, cc, bcc, message_date, message_subject, content, unread, message_tags, message_attach) VALUES ('miki@gmail.com', 'ana@gmail.com', null, null, '2020-04-20 12:10:30', 'Zadatak', 'Domaci zadatak', true, null, null);

