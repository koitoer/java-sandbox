
INSERT INTO USERS(id, userId, name, password) VALUES (1, 'Koitoer', 'Mauricio Mena', '1234');
INSERT INTO PASSWORD(id, alias, site, userName, password) VALUES (1, 'walmart', 'www.walmart.com', 'koitoer', 'walpass');
INSERT INTO PASSWORD(id, alias, site, userName, password) VALUES (2, 'amazon', 'www.amazon.com', 'koitoer', 'amazon');

INSERT INTO USERS_PASSWORD(userId, passwdId)  VALUES (1,1);
INSERT INTO USERS_PASSWORD(userId, passwdId)  VALUES (1,2);
