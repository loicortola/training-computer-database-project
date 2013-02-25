DROP TABLE IF EXISTS `computerDatabaseProject_DB`.`computer`;
DROP TABLE IF EXISTS `computerDatabaseProject_DB`.`company`;

CREATE  TABLE `computerDatabaseProject_DB`.`company` (
  `id_company` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id_company`) );

CREATE  TABLE `computerDatabaseProject_DB`.`computer` (
  `id_computer` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `introduced` TIMESTAMP NOT NULL DEFAULT NOW(),
  `discontinued` TIMESTAMP NULL ,
  `id_company` INT NULL ,
  PRIMARY KEY (`id_computer`) ,
  INDEX `fk_computer_1` (`id_company` ASC) ,
  CONSTRAINT `fk_computer_1`
    FOREIGN KEY (`id_company` )
    REFERENCES `computerDatabaseProject_DB`.`company` (`id_company` )
    ON DELETE SET NULL
    ON UPDATE SET NULL);