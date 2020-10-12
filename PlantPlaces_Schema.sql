-- MySQL Script generated by MySQL Workbench
-- Fri Jul 14 12:32:46 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema PlantPlaces
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `PlantPlaces` ;

-- -----------------------------------------------------
-- Schema PlantPlaces
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `PlantPlaces` DEFAULT CHARACTER SET utf8 ;
USE `PlantPlaces` ;

-- -----------------------------------------------------
-- Table `PlantPlaces`.`Plant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PlantPlaces`.`Plant` ;

CREATE TABLE IF NOT EXISTS `PlantPlaces`.`Plant` (
  `plant_id` INT NOT NULL AUTO_INCREMENT,
  `genus` VARCHAR(45) NULL,
  `species` VARCHAR(45) NULL,
  `cultivar` VARCHAR(45) NULL,
  `common` VARCHAR(45) NULL,
  PRIMARY KEY (`plant_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PlantPlaces`.`Specimen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PlantPlaces`.`Specimen` ;

CREATE TABLE IF NOT EXISTS `PlantPlaces`.`Specimen` (
  `specimen_id` INT NOT NULL AUTO_INCREMENT,
  `latitude` VARCHAR(60) NULL,
  `longitude` VARCHAR(60) NULL,
  `plant_id` INT NOT NULL,
  `planted_by` VARCHAR(255) NULL,
  `planted_date` VARCHAR(60) NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`specimen_id`),
  INDEX `fk_Specimen_Plant_idx` (`plant_id` ASC),
  CONSTRAINT `fk_Specimen_Plant`
    FOREIGN KEY (`plant_id`)
    REFERENCES `PlantPlaces`.`Plant` (`plant_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PlantPlaces`.`collection`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PlantPlaces`.`collection` ;

CREATE TABLE IF NOT EXISTS `PlantPlaces`.`collection` (
  `collection_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `address` VARCHAR(150) NULL,
  PRIMARY KEY (`collection_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PlantPlaces`.`Photo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PlantPlaces`.`Photo` ;

CREATE TABLE IF NOT EXISTS `PlantPlaces`.`Photo` (
  `photo_id` INT NOT NULL AUTO_INCREMENT,
  `specimen_id` INT NOT NULL,
  `uri` VARCHAR(512) NULL,
  `date_taken` VARCHAR(60) NULL,
  `contributor` VARCHAR(255) NULL,
  PRIMARY KEY (`photo_id`),
  UNIQUE INDEX `photo_id_UNIQUE` (`photo_id` ASC),
  INDEX `fk_Photo_Specimen1_idx` (`specimen_id` ASC),
  CONSTRAINT `fk_Photo_Specimen1`
    FOREIGN KEY (`specimen_id`)
    REFERENCES `PlantPlaces`.`Specimen` (`specimen_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PlantPlaces`.`Contributor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PlantPlaces`.`Contributor` ;

CREATE TABLE IF NOT EXISTS `PlantPlaces`.`Contributor` (
  `contributor_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  PRIMARY KEY (`contributor_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;