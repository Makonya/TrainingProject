-- MySQL Script generated by MySQL Workbench
-- Sun Jan 13 22:43:57 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema faculty
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `faculty` DEFAULT CHARACTER SET utf8 ;
USE `faculty` ;

-- -----------------------------------------------------
-- Table `faculty`.`ROLE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `faculty`.`ROLE` (
  `ID_ROLE` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_ROLE`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `faculty`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `faculty`.`USER` (
  `ID_USER` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `LOGIN` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(128) NOT NULL,
  `NAME` VARCHAR(45) NOT NULL,
  `SURNAME` VARCHAR(45) NULL,
  `EMAIL` VARCHAR(45) NULL,
  `ID_ROLE` BIGINT(20) NOT NULL,
  PRIMARY KEY (`ID_USER`),
  INDEX `fk_USER_ROLE_idx` (`ID_ROLE` ASC) VISIBLE,
  CONSTRAINT `fk_USER_ROLE`
    FOREIGN KEY (`ID_ROLE`)
    REFERENCES `faculty`.`ROLE` (`ID_ROLE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `faculty`.`LOCALE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `faculty`.`LOCALE` (
  `ID_LOCALE` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `LOCALE_NAME` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_LOCALE`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `faculty`.`CATEGORY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `faculty`.`CATEGORY` (
  `ID_CATEGORY` BIGINT(20) NOT NULL,
  `ID_LOCALE` BIGINT(20) NOT NULL,
  `CATEGORY_NAME` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_CATEGORY`, `ID_LOCALE`),
  INDEX `fk_CATEGORY_LOCALE1_idx` (`ID_LOCALE` ASC) VISIBLE,
  CONSTRAINT `fk_CATEGORY_LOCALE1`
    FOREIGN KEY (`ID_LOCALE`)
    REFERENCES `faculty`.`LOCALE` (`ID_LOCALE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `faculty`.`COURSE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `faculty`.`COURSE` (
  `ID_COURSE` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `COURSE_NAME` VARCHAR(140) NULL,
  `DESCRIPTION` TEXT NULL,
  `START_DATE` DATE NULL,
  `END_DATE` DATE NULL,
  `ID_USER` BIGINT(20) NOT NULL,
  `ID_CATEGORY` BIGINT(20) NOT NULL,
  PRIMARY KEY (`ID_COURSE`),
  INDEX `fk_COURSE_USER1_idx` (`ID_USER` ASC) VISIBLE,
  INDEX `fk_COURSE_CATEGORY1_idx` (`ID_CATEGORY` ASC) VISIBLE,
  CONSTRAINT `fk_COURSE_USER1`
    FOREIGN KEY (`ID_USER`)
    REFERENCES `faculty`.`USER` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_COURSE_CATEGORY1`
    FOREIGN KEY (`ID_CATEGORY`)
    REFERENCES `faculty`.`CATEGORY` (`ID_CATEGORY`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `faculty`.`FEEDBACK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `faculty`.`FEEDBACK` (
  `ID_FEEDBACK` BIGINT(20) NOT NULL,
  `INFO` TEXT NOT NULL,
  `FEEDBACK_DATE` DATE NOT NULL,
  `ID_COURSE` BIGINT(20) NOT NULL,
  `ID_USER` BIGINT(20) NOT NULL,
  PRIMARY KEY (`ID_FEEDBACK`),
  INDEX `fk_FEEDBACK_COURSE1_idx` (`ID_COURSE` ASC) VISIBLE,
  INDEX `fk_FEEDBACK_USER1_idx` (`ID_USER` ASC) VISIBLE,
  CONSTRAINT `fk_FEEDBACK_COURSE1`
    FOREIGN KEY (`ID_COURSE`)
    REFERENCES `faculty`.`COURSE` (`ID_COURSE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FEEDBACK_USER1`
    FOREIGN KEY (`ID_USER`)
    REFERENCES `faculty`.`USER` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `faculty`.`MARK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `faculty`.`MARK` (
  `ID_MARK` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `MARK` INT NOT NULL,
  `ID_COURSE` BIGINT(20) NOT NULL,
  `ID_USER` BIGINT(20) NOT NULL,
  PRIMARY KEY (`ID_MARK`),
  INDEX `fk_MARK_COURSE1_idx` (`ID_COURSE` ASC) VISIBLE,
  INDEX `fk_MARK_USER1_idx` (`ID_USER` ASC) VISIBLE,
  CONSTRAINT `fk_MARK_COURSE1`
    FOREIGN KEY (`ID_COURSE`)
    REFERENCES `faculty`.`COURSE` (`ID_COURSE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MARK_USER1`
    FOREIGN KEY (`ID_USER`)
    REFERENCES `faculty`.`USER` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `faculty`.`COURSE_USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `faculty`.`COURSE_USER` (
  `ID_COURSE` BIGINT(20) NOT NULL,
  `ID_USER` BIGINT(20) NOT NULL,
  INDEX `fk_COURSE_has_USER_USER1_idx` (`ID_USER` ASC) VISIBLE,
  INDEX `fk_COURSE_has_USER_COURSE1_idx` (`ID_COURSE` ASC) VISIBLE,
  CONSTRAINT `fk_COURSE_has_USER_COURSE1`
    FOREIGN KEY (`ID_COURSE`)
    REFERENCES `faculty`.`COURSE` (`ID_COURSE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_COURSE_has_USER_USER1`
    FOREIGN KEY (`ID_USER`)
    REFERENCES `faculty`.`USER` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- View `faculty`.`COURSE_STUDENTS`
-- -----------------------------------------------------
CREATE VIEW COURSE_STUDENTS AS SELECT c_u.ID_COURSE, c_u.ID_USER, u.NAME, u.SURNAME, m.MARK FROM COURSE_USER c_u
			LEFT JOIN MARK m ON c_u.ID_COURSE=m.ID_COURSE AND c_u.ID_USER=m.ID_USER
            LEFT JOIN USER u ON c_u.ID_USER=u.ID_USER;

-- -----------------------------------------------------

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------

USE faculty;

insert into role(NAME) values('admin');
insert into role(NAME) values('teacher');
insert into role(NAME) values('student');

insert into user(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ID_ROLE)
			values('admin', '0192023a7bbd73250516f069df18b500', 'Makhabbat', 'Kuzhaniyazova', 'makonya2302@gmail.com', 1);
insert into user(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ID_ROLE)
			values('teacher', 'a426dcf72ba25d046591f81a5495eab7', 'Irina', 'Ivanova', 'ivanova_ir@gmail.com', 2);
insert into user(LOGIN, PASSWORD, NAME, SURNAME, EMAIL, ID_ROLE)
			values('student', 'ad6a280417a0f533d8b670c61667e1a0', 'Victoriya', 'Ivanova', 'ivanova_v@gmail.com', 3);

insert into locale(LOCALE_NAME) values('ru');
insert into locale(LOCALE_NAME) values('en');

insert into category(ID_CATEGORY, ID_LOCALE, CATEGORY_NAME) values(1, 1, 'Математика');
insert into category(ID_CATEGORY, ID_LOCALE, CATEGORY_NAME) values(1, 2, 'Mathematics');
insert into category(ID_CATEGORY, ID_LOCALE, CATEGORY_NAME) values(2, 1, 'Английский');
insert into category(ID_CATEGORY, ID_LOCALE, CATEGORY_NAME) values(2, 2, 'English');
insert into category(ID_CATEGORY, ID_LOCALE, CATEGORY_NAME) values(3, 1, 'Информатика');
insert into category(ID_CATEGORY, ID_LOCALE, CATEGORY_NAME) values(3, 2, 'Informatics');
insert into category(ID_CATEGORY, ID_LOCALE, CATEGORY_NAME) values(4, 1, 'География');
insert into category(ID_CATEGORY, ID_LOCALE, CATEGORY_NAME) values(4, 2, 'Geography');

insert into course(COURSE_NAME, DESCRIPTION, START_DATE, END_DATE, ID_USER, ID_CATEGORY)
			values('Школьный курс математики', 'Курс формирует у школьников математическое и естественно-научное мышление.', '2019-01-01', '2019-05-25', 2, 1);
insert into course(COURSE_NAME, DESCRIPTION, START_DATE, END_DATE, ID_USER, ID_CATEGORY)
			values('Грамматика и активный словарь', 'Курс по изучению английского языка позволяет не только освоить основы языка, но и стать его активным пользователем, вплоть до мышления на английском.', '2018-09-01', '2018-12-31', 2, 2);
insert into course(COURSE_NAME, DESCRIPTION, START_DATE, END_DATE, ID_USER, ID_CATEGORY)
			values('В ногу со временем', 'В мире современных технологий каждый человек должен хорошо владеть компьютером.', '2019-02-01', '2019-04-01', 2, 3);
insert into course(COURSE_NAME, DESCRIPTION, START_DATE, END_DATE, ID_USER, ID_CATEGORY)
			values('Изучаем географию с интересом', 'Благодаря курсу вы узнаете множество интересного о разных странах, материках, океанах и всем, что связано с географией. Курс изложен в формате интерактивных уроков с презентациями и тестами для закрепления пройденного материала. ', '2019-01-15', '2019-02-16', 2, 4);

insert into course_user values(2, 3);