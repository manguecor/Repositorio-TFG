INSERT INTO users(username,enabled,password) VALUES ('admin',TRUE,'admin');
INSERT INTO authorities(id,authority,username) VALUES (1,'admin','admin');
INSERT INTO users(username,enabled,password) VALUES ('client1',TRUE,'client');
INSERT INTO authorities(id,authority,username) VALUES (2,'client','client1');

INSERT INTO clients(username,name,surname,DNI,birthday,email,phone,fav_team) VALUES ('client1','Pedro','Pérez López','23214565R','1999-10-20','pedropl@gmail.com','645123321','Real Betis'); 