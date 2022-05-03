INSERT INTO users(username,enabled,password) VALUES ('admin',TRUE,'admin');
INSERT INTO authorities(id,authority) VALUES (1,'admin');
INSERT INTO users_authorities(user_username,authorities_id) VALUES ('admin',1);
INSERT INTO users(username,enabled,password) VALUES ('client1',TRUE,'client');
INSERT INTO authorities(id,authority) VALUES (2,'client');
INSERT INTO users_authorities(user_username,authorities_id) VALUES ('client1',2);

INSERT INTO clients(username,name,surname,email,fav_team) VALUES ('client1','Pedro','Pérez López','pedropl@gmail.com','Real Betis');
INSERT INTO leagues(name,code) VALUES ('Prueba','crvtv');
INSERT INTO players(points,client_id) VALUES (500,1);
INSERT INTO leagues_players(league_id,players_id) VALUES (1,1);