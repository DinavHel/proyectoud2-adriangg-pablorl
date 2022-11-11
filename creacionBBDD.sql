DROP DATABASE IF EXISTS monsterhunterworld;
CREATE DATABASE IF NOT EXISTS monsterhunterworld;
USE monsterhunterworld;
CREATE TABLE headgear (
                          id VARCHAR(100)NOT NULL ,
                          name VARCHAR(100) NOT NULL,
                          fire_res VARCHAR(10) NULL,
                          water_res VARCHAR(10) NULL,
                          ice_res VARCHAR(10) NULL,
                          thunder_res VARCHAR(10) NULL,
                          dragon_res VARCHAR(10) NULL,
                          PRIMARY KEY(id)
) ENGINE=InnoDB;
CREATE TABLE headgear_skills (
                                 headgear VARCHAR(100)NOT NULL,
                                 skill VARCHAR(100) NOT NULL,
                                 skill_level VARCHAR(100) not null,
                                 FOREIGN KEY(headgear)
                                     REFERENCES headgear(id)
                                     ON UPDATE CASCADE
                                     ON DELETE CASCADE,
                                 PRIMARY KEY(headgear, skill)
) ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS Chest (
    id VARCHAR(100)NOT NULL ,
    name VARCHAR(100) NOT NULL,
    fire_res VARCHAR(10) NULL,
    water_res VARCHAR(10) NULL,
    ice_res VARCHAR(10) NULL,
    thunder_res VARCHAR(10) NULL,
    dragon_res VARCHAR(10) NULL,
    PRIMARY KEY(id)
    )ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS Chest_skills (
    Chest VARCHAR(100) NOT NULL,
    skill VARCHAR(100) NOT NULL,
    skill_level VARCHAR(10) null,
    FOREIGN KEY(Chest) REFERENCES Chest(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    PRIMARY KEY(Chest,skill)

    ) ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS gloves (
    id VARCHAR(100) NOT NULL ,
    name VARCHAR(100) NOT NULL,
    fire_res VARCHAR(10) NULL,
    water_res VARCHAR(10) NULL,
    ice_res VARCHAR(10) NULL,
    thunder_res VARCHAR(10) NULL,
    dragon_res VARCHAR(10) NULL,
    PRIMARY KEY(id)
    )ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS gloves_skills (
    gloves VARCHAR(100) NOT NULL,
    skill VARCHAR(100) NOT NULL,
    skill_level VARCHAR(10) null,
    FOREIGN KEY(gloves) REFERENCES gloves(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    PRIMARY KEY(gloves,skill)
    )ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS waist (
    id VARCHAR(100) NOT NULL ,
    name VARCHAR(100) NOT NULL,
    fire_res VARCHAR(10) NULL,
    water_res VARCHAR(10) NULL,
    ice_res VARCHAR(10) NULL,
    thunder_res VARCHAR(10) NULL,
    dragon_res VARCHAR(10) NULL,
    PRIMARY KEY(id)
    )ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS waist_skills (
    waist VARCHAR(100) NOT NULL,
    skill VARCHAR(100) NOT NULL,
    skill_level VARCHAR(10) null,
    FOREIGN KEY(waist) REFERENCES waist(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    PRIMARY KEY(waist,skill)
    ) ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS legs (
    id VARCHAR(100) NOT NULL ,
    name VARCHAR(100) NOT NULL,
    fire_res VARCHAR(10) NULL,
    water_res VARCHAR(10) NULL,
    ice_res VARCHAR(10) NULL,
    thunder_res VARCHAR(10) NULL,
    dragon_res VARCHAR(10) NULL,
    PRIMARY KEY(id)

    )ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS legs_skills (
    legs VARCHAR(100) NOT NULL,
    skill VARCHAR(100) NOT NULL,
    skill_level VARCHAR(10) null,
    FOREIGN KEY(legs) REFERENCES legs(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    PRIMARY KEY (legs,skill)
    )ENGINE=Innodb;