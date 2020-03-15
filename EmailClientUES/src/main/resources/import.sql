
INSERT INTO users (username, password, first_name, last_name) VALUES ('miki','miki123', 'Miladin', 'Zivkovic');

INSERT INTO users (username, password, first_name, last_name) VALUES ('kiki','kiki123', 'Kristijan', 'Golubovic');

INSERT INTO accounts (smtp_address, smtp_port, in_server_type, in_server_address, in_server_port, username, pasword, display_name, user_id) VALUES ('smtpAddress1', 21, 56, 'inServerAddress1', 80, 'miki@gmail.com', 'miki123', 'miki@gmail.com', 1);

INSERT INTO accounts (smtp_address, smtp_port, in_server_type, in_server_address, in_server_port, username, pasword, display_name, user_id) VALUES ('smtpAddress2', 11, 77, 'inServerAddress2', 122, 'miki@icloud.com', 'miki123', 'miki@icloud.com', 1);

INSERT INTO accounts (smtp_address, smtp_port, in_server_type, in_server_address, in_server_port, username, pasword, display_name, user_id) VALUES ('smtpAddress3', 12, 78, 'inServerAddress3', 125, 'kiki@gmail.com', 'kiki123', 'kiki@gmail.com', 2);
    
INSERT INTO contacts (first_name, last_name, display_name, email, note, user_id, photo_id) VALUES ('Miladin', 'Zivkovic','miki', 'miki@gmail.com', 'note', 1,null);
			
INSERT INTO contacts (first_name, last_name, display_name, email, note, user_id, photo_id) VALUES ('Kristijan', 'Golubovic','kiki', 'kiki@icloud.com', 'note', 1,null);
			
INSERT INTO contacts (first_name, last_name, display_name, email, note, user_id, photo_id) VALUES ('Miladin', 'Zivkovic','miki', 'miki@gmail.com', 'note', 2,null);
			
INSERT INTO contacts (first_name, last_name, display_name, email, note, user_id, photo_id) VALUES ('Kristijan', 'Golubovic','kiki', 'kiki@icloud.com', 'note', 2,null);

INSERT INTO contacts (first_name, last_name, display_name, email, note, user_id, photo_id) VALUES ('Marko', 'Markovic','marko', 'markom@yahoo.com', 'note', 1,null);
			
INSERT INTO contacts (first_name, last_name, display_name, email, note, user_id, photo_id) VALUES ('Jasar', 'Ahmedovski','jasar', 'jasara@uns.ac.rs', '', 2,null);
			
INSERT INTO contacts (first_name, last_name, display_name, email, note, user_id, photo_id) VALUES ('Mitar', 'Miric','mitar', 'mitarm@gmail.com','', 2, null);
			
INSERT INTO contacts (first_name, last_name, display_name, email, note, user_id, photo_id) VALUES ('Ana', 'Antic', 'ana', 'antic@gmail.com', '', 1, null);
			
INSERT INTO contacts (first_name, last_name, display_name, email, note, user_id, photo_id) VALUES ('Jovana', 'Jovanovic', 'jovana', 'jovana@yahoo.com', '', 2, null);
			
INSERT INTO contacts (first_name, last_name, display_name, email, note, user_id, photo_id) VALUES ('Avram', 'Gojic', 'avram', 'gavra@hotmail.com', '', 1, null);

INSERT INTO folders (name, parent_folder_id, account_id) VALUES ('Folder 1', null, 1);
INSERT INTO folders (name, parent_folder_id, account_id) VALUES ('Folder 2', null, 3);
INSERT INTO folders (name, parent_folder_id, account_id) VALUES ('Folder 3', 2, 3);
INSERT INTO folders (name, parent_folder_id, account_id) VALUES ('Folder 4', 2, 3);
INSERT INTO folders (name, parent_folder_id, account_id) VALUES ('Folder 5', 1, 1);
INSERT INTO folders (name, parent_folder_id, account_id) VALUES ('Folder 6', 1, 1);
INSERT INTO folders (name, parent_folder_id, account_id) VALUES ('Folder 7', null, 2);
INSERT INTO folders (name, parent_folder_id, account_id) VALUES ('Drafts', null, 1);
INSERT INTO folders (name, parent_folder_id, account_id) VALUES ('Drafts', null, 2);
INSERT INTO folders (name, parent_folder_id, account_id) VALUES ('Drafts', null, 3);
			
INSERT INTO messages (message_from, message_to, cc, bcc, datem, subject, content, unread, folder_id, account_id) VALUES ('miki@gmail.com', 'mitarm@gmail.com', null, null, '2019,05,26,11,06', 'Veliki pozdrav', 'Sta ima novo kod tebe?', false, 5, 1);
			
			
INSERT INTO messages (message_from, message_to, cc, bcc, datem, subject, content, unread, folder_id, account_id) VALUES ('kiki@gmail.com', 'miki@gmail.com', null, null, '2019,05,26,11,06', 'Veliki pozdrav', 'Sta ima novo kod tebe?', false, 6, 1);
			
			
INSERT INTO messages (message_from, message_to, cc, bcc, datem, subject, content, unread, folder_id, account_id) VALUES ('miki@gmail.com', 'kiki@gmail.com', null, null, '2019,04,17,12,21', 'Filomovi', 'Evo nekih filmskih predloga', false, 3, 3);
			
			
INSERT INTO messages (message_from, message_to, cc, bcc, datem, subject, content, unread, folder_id, account_id) VALUES ('mitarm@gmail.com', 'miki@gmail.com', null, null, '2019,05,23,23,12', 'Srecan rodjendan', 'Zelim ti sve najlepse', false, 5, 1);
			
			
INSERT INTO messages (message_from, message_to, cc, bcc, datem, subject, content, unread, folder_id, account_id) VALUES ('kiki@icloud.com', 'mitarm@gmail.com', null, null, '2019,05,24,12,13', 'Promena rasporeda', 'Novi raspored nastave je postavljen na sajtu skole', false, 4, 3);
			
			
INSERT INTO messages (message_from, message_to, cc, bcc, datem, subject, content, unread, folder_id, account_id) VALUES ('markom@yahoo.com', 'kiki@gmail.com', null, null, '2019,05,25,11,00', 'Zanimljivosti', 'Evo par zanimljivosti', false, 3, 3);