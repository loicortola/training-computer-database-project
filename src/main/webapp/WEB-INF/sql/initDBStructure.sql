DROP TABLE IF EXISTS `computerDatabaseProject_DB`.`computer`;
DROP TABLE IF EXISTS `computerDatabaseProject_DB`.`company`;
DROP TABLE IF EXISTS `computerDatabaseProject_DB`.`stats`;

CREATE  TABLE `computerDatabaseProject_DB`.`company` (
  `id_company` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id_company`) );


CREATE  TABLE `computerDatabaseProject_DB`.`stats` (
  `id_stats` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_computer` BIGINT NULL ,
  `stat_type` VARCHAR(255) NOT NULL ,
  `logged` TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`id_stats`) );

  
CREATE  TABLE `computerDatabaseProject_DB`.`computer` (
  `id_computer` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `introduced` TIMESTAMP NOT NULL DEFAULT NOW(),
  `discontinued` TIMESTAMP NULL ,
  `id_company` BIGINT NULL ,
  `is_visible` INT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_computer`) ,
  INDEX `fk_computer_1` (`id_company` ASC) ,
  CONSTRAINT `fk_computer_1`
    FOREIGN KEY (`id_company` )
    REFERENCES `computerDatabaseProject_DB`.`company` (`id_company` )
    ON DELETE SET NULL
    ON UPDATE SET NULL);