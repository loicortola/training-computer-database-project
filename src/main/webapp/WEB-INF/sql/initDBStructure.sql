DROP TABLE IF EXISTS `computerDatabaseProject_DB`.`computer`;
DROP TABLE IF EXISTS `computerDatabaseProject_DB`.`company`;
DROP TABLE IF EXISTS `computerDatabaseProject_DB`.`stats`;

CREATE  TABLE `computerDatabaseProject_DB`.`company` (
  `id_company` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id_company`) );


CREATE  TABLE `computerDatabaseProject_DB`.`stats` (
  `id_stats` INT NOT NULL AUTO_INCREMENT ,
  `id_computer` INT NULL ,
  `stat_type` VARCHAR(255) NOT NULL ,
  `logged` TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`id_stats`) );

  
CREATE  TABLE `computerDatabaseProject_DB`.`computer` (
  `id_computer` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `introduced` TIMESTAMP NOT NULL DEFAULT NOW(),
  `discontinued` TIMESTAMP NULL ,
  `id_company` INT NULL ,
  `is_visible` INT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_computer`) ,
  INDEX `fk_computer_1` (`id_company` ASC) ,
  CONSTRAINT `fk_computer_1`
    FOREIGN KEY (`id_company` )
    REFERENCES `computerDatabaseProject_DB`.`company` (`id_company` )
    ON DELETE SET NULL
    ON UPDATE SET NULL);