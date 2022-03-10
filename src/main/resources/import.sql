INSERT INTO users(username,enabled,password) VALUES ('admin',TRUE,'admin');
INSERT INTO authorities(id,authority,username) VALUES (1,'admin','admin');

INSERT INTO clients(name,surname,DNI,birthday,email,phone,fav_team) VALUES ('Pedro','Pérez López','23214565R','1999-10-20','pedropl@gmail.com','645123321','Real Betis'); 