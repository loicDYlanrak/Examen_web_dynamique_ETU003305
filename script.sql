DROP DATABASE IF EXISTS gestion_financiere;
CREATE DATABASE IF NOT EXISTS gestion_financiere;
USE gestion_financiere;


DROP TABLE IF EXISTS utilisateurs;
DROP TABLE IF EXISTS lignes_credit;
DROP TABLE IF EXISTS lignes_depense;


CREATE TABLE utilisateurs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL UNIQUE,
    pwd VARCHAR(255) NOT NULL
);

CREATE TABLE lignes_credit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL,
    montant DECIMAL(10,2) NOT NULL
);

CREATE TABLE lignes_depense (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_credit INT NOT NULL,
    montant DECIMAL(10,2) NOT NULL,
    date_depense DATE NOT NULL,
    FOREIGN KEY (id_credit) REFERENCES lignes_credit(id)
);

INSERT INTO utilisateurs (nom, pwd) 
VALUES ('admin', 'admin123');

INSERT INTO lignes_credit (libelle, montant)
VALUES ('Budget mensuel', 1500.00);

INSERT INTO lignes_depense (id_credit, montant, date_depense)
VALUES (1, 120.50, CURDATE());