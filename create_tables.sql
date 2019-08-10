CREATE TABLE PartyQueues (
  code varchar(5) NOT NULL,
  createPlaylist BOOLEAN,
  PRIMARY KEY ( code )
);

CREATE TABLE QueueItems (
  id INT NOT NULL AUTO_INCREMENT,
  queue varchar(5),
  trackid varchar(30),
  votes INT,
  created DATE,
  PRIMARY KEY ( id ),
  FOREIGN KEY (queue) REFERENCES PartyQueues (code) ON UPDATE CASCADE ON DELETE CASCADE
);
