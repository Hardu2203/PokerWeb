CREATE TABLE user (
username VARCHAR(12) NOT NULL,
password VARCHAR(255) NOT NULL,
PRIMARY KEY(username));

CREATE TABLE game(
gameid INTEGER AUTO_INCREMENT,
gamename VARCHAR(30) NOT NULL,
gamedate DATE NOT NULL,
Status VARCHAR(100),
HostName VARCHAR(100),
PRIMARY KEY(gameid));

CREATE TABLE usergame(
usergameid INTEGER AUTO_INCREMENT,
username VARCHAR(12) NOT NULL,
gameid INTEGER NOT NULL,
CONSTRAINT Username_Fk FOREIGN KEY (username) REFERENCES user(username),
CONSTRAINT gameid_Fk FOREIGN KEY (gameid) REFERENCES game(gameid),
winner BIT,
hand VARCHAR(200),
PRIMARY KEY(usergameid));



