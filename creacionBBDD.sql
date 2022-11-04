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
CREATE TABLE IF NOT EXISTS Chest (
    id INT UNSIGNED NOT NULL auto_increment,
    name VARCHAR(100) NOT NULL,
    fire_res INT NOT NULL,
    water_res INT NOT NULL,
    ice_res INT NOT NULL,
    thunder_res INT NOT NULL,
    dragon_res INT NOT NULL,
    PRIMARY KEY(id)
)ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS Chest_skills (
    Chest INT UNSIGNED NOT NULL,
    skill VARCHAR(100) NOT NULL,
    skill_level int not null,
    FOREIGN KEY(Chest) REFERENCES Chest(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    PRIMARY KEY(Chest,skill)

) ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS gloves (
    id INT UNSIGNED NOT NULL auto_increment,
    name VARCHAR(100) NOT NULL,
    fire_res INT NOT NULL,
    water_res INT NOT NULL,
    ice_res INT NOT NULL,
    thunder_res INT NOT NULL,
    dragon_res INT NOT NULL,
    PRIMARY KEY(id)
)ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS gloves_skills (
    gloves INT UNSIGNED NOT NULL,
    skill VARCHAR(100) NOT NULL,
    skill_level int not null,
    FOREIGN KEY(gloves) REFERENCES gloves(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    PRIMARY KEY(gloves,skill)
)ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS waist (
    id INT UNSIGNED NOT NULL auto_increment,
    name VARCHAR(100) NOT NULL,
    fire_res INT NOT NULL,
    water_res INT NOT NULL,
    ice_res INT NOT NULL,
    thunder_res INT NOT NULL,
    dragon_res INT NOT NULL,
    PRIMARY KEY(id)
)ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS waist_skills (
    waist INT UNSIGNED NOT NULL,
    skill VARCHAR(100) NOT NULL,
    skill_level int not null,
    FOREIGN KEY(waist) REFERENCES waist(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    PRIMARY KEY(waist,skill)
) ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS legs (
    id INT UNSIGNED NOT NULL auto_increment,
    name VARCHAR(100) NOT NULL,
    fire_res INT NOT NULL,
    water_res INT NOT NULL,
    ice_res INT NOT NULL,
    thunder_res INT NOT NULL,
    dragon_res INT NOT NULL,
    PRIMARY KEY(id)

)ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS legs_skills (
    legs INT UNSIGNED NOT NULL,
    skill VARCHAR(100) NOT NULL,
    skill_level int not null,
    FOREIGN KEY(legs) REFERENCES legs(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    PRIMARY KEY (legs,skill)
)ENGINE=Innodb;