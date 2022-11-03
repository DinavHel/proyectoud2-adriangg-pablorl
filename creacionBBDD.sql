DROP DATABASE IF EXISTS monsterhunterworld;
CREATE DATABASE IF NOT EXISTS monsterhunterworld;
USE monsterhunterworld;
CREATE TABLE headgear (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    fire_res INT NOT NULL,
    water_res INT NOT NULL,
    ice_res INT NOT NULL,
    thunder_res INT NOT NULL,
    dragon_res INT NOT NULL,
    PRIMARY KEY(id)
) ENGINE=InnoDB;
CREATE TABLE headgear_skills (
    headgear INT NOT NULL,
    skill VARCHAR(100) NOT NULL,
    FOREIGN KEY(headgear)
        REFERENCES headgear(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    PRIMARY KEY(headgear, skill)
) ENGINE=InnoDB;
