INSERT INTO users(username,enabled,password) VALUES ('admin',TRUE,'admin');
INSERT INTO authorities(id,authority) VALUES (1,'admin');
INSERT INTO users_authorities(user_username,authorities_id) VALUES ('admin',1);
INSERT INTO users(username,enabled,password) VALUES ('client1',TRUE,'client');
INSERT INTO authorities(id,authority) VALUES (2,'client');
INSERT INTO users_authorities(user_username,authorities_id) VALUES ('client1',2);
INSERT INTO users(username,enabled,password) VALUES ('client2',TRUE,'client');
INSERT INTO authorities(id,authority) VALUES (3,'client');
INSERT INTO users_authorities(user_username,authorities_id) VALUES ('client2',3);
INSERT INTO users(username,enabled,password) VALUES ('client3',TRUE,'client');
INSERT INTO authorities(id,authority) VALUES (4,'client');
INSERT INTO users_authorities(user_username,authorities_id) VALUES ('client3',4);

INSERT INTO clients(username,name,surname,email) VALUES ('client1','Pedro','Pérez López','pedropl@gmail.com');
INSERT INTO clients(username,name,surname,email) VALUES ('client2','Jesus','García Fekir','jesusgf@gmail.com');
INSERT INTO clients(username,name,surname,email) VALUES ('client3','Sergio','Canales Ruiz','sergiocr@gmail.com');
INSERT INTO leagues(name,code) VALUES ('Prueba','crvtv');
INSERT INTO players(points,client_id) VALUES (500,1);
INSERT INTO leagues_players(league_id,players_id) VALUES (1,1);
INSERT INTO players(points,client_id) VALUES (700,2);
INSERT INTO leagues_players(league_id,players_id) VALUES (1,2);
INSERT INTO players(points,client_id) VALUES (550,3);
INSERT INTO leagues_players(league_id,players_id) VALUES (1,3);

INSERT INTO bets(bet_type,description,estado,league_id,player_id) VALUES (0,'Apuesta 2 partidos','PENDIENTE',1,1);
INSERT INTO matches(api_id,away_team,competition,home_team,match_date,result,status,bet_id) VALUES (325784,'SC Heerenveen','Eredivisie','Go Ahead Eagles',null,'AWAY_TEAM','FINISHED',1);
INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (325784,'DRAW',1,1);
INSERT INTO matches(api_id,away_team,competition,home_team,match_date,result,status,bet_id) VALUES (325783,'AZ','Eredivisie','RKC Waalwijk',null,'HOME_TEAM','FINISHED',1);
INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (325783,'HOME_TEAM',1,1);
INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (325784,'AWAY_TEAM',1,2);
INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (325783,'HOME_TEAM',1,2);
INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (325784,'DRAW',1,3);
INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (325783,'DRAW',1,3);

INSERT INTO bets(bet_type,description,estado,league_id,player_id) VALUES (1,'Eredivisie','PENDIENTE',1,1);
INSERT INTO matches(api_id,away_team,competition,home_team,match_date,result,status,bet_id) VALUES (325780,'AFC Ajax','Eredivisie','NEC',null,'0-1','FINISHED',2);
INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (325780,'0-1',2,1);
INSERT INTO matches(api_id,away_team,competition,home_team,match_date,result,status,bet_id) VALUES (325779,'SC Cambuur-Leeuwarden','Eredivisie','FC Groningen',null,'1-0','FINISHED',2);
INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (325779,'1-1',2,1);
INSERT INTO matches(api_id,away_team,competition,home_team,match_date,result,status,bet_id) VALUES (325782,'PSV','Eredivisie','Heracles Almelo',null,'0-2','FINISHED',2);
INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (325782,'3-3',2,1);

INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (325780,'2-1',2,2);
INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (325779,'1-4',2,2);
INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (325782,'0-3',2,2);
INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (325780,'0-0',2,3);
INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (325779,'1-0',2,3);
INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (325782,'2-2',2,3);

INSERT INTO matches(api_id,away_team,competition,home_team,match_date,result,status,bet_id) VALUES (325781,'Fortuna Sittard','Eredivisie','FC Twente',null,'1-3','FINISHED',2);
INSERT INTO player_bet(match_id,player_result,bet_id,player_id) VALUES (325781,'0-2',2,1);
