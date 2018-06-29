DROP TABLE Mitgliederverwaltung.Spiel;

DROP TABLE Mitgliederverwaltung.Mitglied;


CREATE TABLE Mitgliederverwaltung.Mitglied (

    id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
    Name VARCHAR(40) NOT NULL,
    Vorname VARCHAR(40) NOT NULL,
    Anrede VARCHAR(4) NOT NULL,
    Titel VARCHAR(10) ,
    Geschlecht VARCHAR(1) NOT NULL,
    Strasse VARCHAR(50) NOT NULL,
    Plz VARCHAR(5) NOT NULL,
    Ort VARCHAR(40) NOT NULL,
    Staat VARCHAR(40) NOT NULL,
    Geburtsdatum DATE NOT NULL,
    Eintrittsdatum DATE NOT NULL,
    Austrittsdatum DATE NOT NULL,
    Email VARCHAR(40) NOT NULL UNIQUE,
    Foto BLOB,
    Beitrag DOUBLE );


CREATE TABLE Mitgliederverwaltung.Spiel (
   id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
   Spieltitel VARCHAR(50) NOT NULL,
   Spieldatum DATE NOT NULL,
   MitgliederId INTEGER NOT NULL,
   CONSTRAINT fk_mitglieder 
   FOREIGN KEY (mitgliederId) REFERENCES Mitgliederverwaltung.Mitglied(id) );