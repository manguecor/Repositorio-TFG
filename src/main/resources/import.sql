INSERT INTO users(username,enabled,password) VALUES ('admin',TRUE,'admin');
INSERT INTO authorities(id,authority) VALUES (1,'admin');
INSERT INTO users_authorities(user_username,authorities_id) VALUES ('admin',1);
INSERT INTO users(username,enabled,password) VALUES ('client1',TRUE,'client');
INSERT INTO authorities(id,authority) VALUES (2,'client');
INSERT INTO users_authorities(user_username,authorities_id) VALUES ('client1',2);
INSERT INTO users(username,enabled,password) VALUES ('client2',TRUE,'client');
INSERT INTO authorities(id,authority) VALUES (3,'client');
INSERT INTO users_authorities(user_username,authorities_id) VALUES ('client2',3);

INSERT INTO clients(username,name,surname,email,fav_team) VALUES ('client1','Pedro','Pérez López','pedropl@gmail.com','Real Betis');
INSERT INTO clients(username,name,surname,email,fav_team) VALUES ('client2','Daniel','López Gómez','daniellg@gmail.com','Villarreal CF');
INSERT INTO leagues(name,code) VALUES ('Prueba','crvtv');
INSERT INTO players(points,client_id) VALUES (500,1);
INSERT INTO leagues_players(league_id,players_id) VALUES (1,1);
INSERT INTO players(points,client_id) VALUES (500,2);
INSERT INTO leagues_players(league_id,players_id) VALUES (1,2);

INSERT INTO bets(league_id,player_id) VALUES (1,1);
INSERT INTO matches(api_id,away_team,competition,home_team,match_date,result,status,bet_id) VALUES (391973,'CA Vélez Sarsfield','Copa Libertadores','RB Bragantino',null,'DRAW','FINISHED',1);
INSERT INTO matches(api_id,away_team,competition,home_team,match_date,result,status,bet_id) VALUES (333587,'Venezia FC','Serie A','US Salernitana 1919',null,'HOME_TEAM','FINISHED',1);
INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (391973,'HOME_TEAM',1,1);
INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (333587,'HOME_TEAM',1,1);
INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (391973,'DRAW',1,2);
INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (333587,'HOME_TEAM',1,2);