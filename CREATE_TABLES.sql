CREATE TABLE Spieler (
  Spieler_ID NUMBER GENERATED ALWAYS AS IDENTITY primary key,
  Charaktername VARCHAR2(45) NOT NULL,
  EMAIL VARCHAR2(45) NOT NULL,
  Passwort VARCHAR(45) NOT NULL
);

CREATE TABLE Rohstoffe (
  Rohstoff_ID NUMBER GENERATED ALWAYS AS IDENTITY primary key,
  Rohstoffname VARCHAR2(45) NOT NULL,
  Rohstoffbezeichnung VARCHAR2(90) NOT NULL,
  Bot_Verkaufspreis number(10) NOT NULL,
  Bot_Einkaufspreis number(10) NOT NULL,
  Startwert number(10) NOT NULL
);

CREATE TABLE Gebaeude (
  gebaeude_id NUMBER GENERATED ALWAYS AS IDENTITY primary key,
  gebaeudename varchar2(45) NOT NULL,
  gebaeudebeschreibung varchar2(255),
  typ varchar2(45) NOT NULL,
  baudauer number(10),
  produktionsdauer number(10),
  rohstoff_id_prod_lager REFERENCES Rohstoffe(ROHSTOFF_ID) NOT NULL
);

CREATE TABLE Rezept (
  Rezept_ID NUMBER GENERATED ALWAYS AS IDENTITY primary key,
  rohstoff_id_ziel REFERENCES Rohstoffe(ROHSTOFF_ID) NOT NULL,
  rohstoff_id_benoetigt REFERENCES Rohstoffe(ROHSTOFF_ID) NOT NULL
);

CREATE TABLE Lager (
  Lager_ID NUMBER GENERATED ALWAYS AS IDENTITY primary key,
  Lager_Spieler_ID REFERENCES Spieler(Spieler_ID) NOT NULL,
  Lager_Rohstoff_ID REFERENCES Rohstoffe(Rohstoff_ID) NOT NULL,
  Menge number(25)
);

CREATE TABLE ZUO_SPIELER_GEBAEUDE (
  Spieler_GEBAEUDE_ID NUMBER GENERATED ALWAYS AS IDENTITY primary key,
  SPIELER_ID REFERENCES SPieler(Spieler_ID) NOT NULL,
  GEBAEUDE_ID REFERENCES GEBAEUDE(GEBAEUDE_ID) NOT NULL,
  ausgebautes_Level number(10) NOT NULL
);

CREATE TABLE Gebaeude_Rohstoff_Kosten (
  gebaeude_rohstoff_kosten_id NUMBER GENERATED ALWAYS AS IDENTITY primary key,
  gebaeude_id REFERENCES gebaeude(gebaeude_id) NOT NULL,
  rohstoff_id REFERENCES rohstoffe(rohstoff_id) NOT NULL
);

CREATE TABLE Markt (
  Transaktion_ID NUMBER GENERATED ALWAYS AS IDENTITY primary key,
  erstellt_am date NOT NULL,
  spieler_ID_verkaeufer REFERENCES Spieler(Spieler_ID) NOT NULL,
  rohstoff_ID_angebot REFERENCES Rohstoffe(Rohstoff_ID) NOT NULL,
  angebot_menge number(25) NOT NULL,
  rohstoff_ID_gefordert REFERENCES ROhstoffe(Rohstoff_ID) NOT NULL,
  gefordert_menge number(25) NOT NULL,
  spieler_ID_kaeufer references SPIELER(SPIELER_ID),
  angenommen_am date
);























