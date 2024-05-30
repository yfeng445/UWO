-- ---------------------------------
-- SCRIPT 1

-- Set up the database

SHOW DATABASES;
DROP DATABASE IF EXISTS assign2db;
CREATE DATABASE assign2db;
USE assign2db; 


-- Create the tables for the database

SHOW TABLES;

CREATE TABLE ta(tauserid VARCHAR(8) NOT NULL, firstname VARCHAR(30) NOT NULL, lastname VARCHAR(30) NOT NULL, studentnum CHAR(9) NOT NULL, degreetype VARCHAR(7),  PRIMARY KEY(tauserid));

CREATE TABLE course (coursenum CHAR(6) NOT NULL, coursename VARCHAR(30) NOT NULL, level SMALLINT, year SMALLINT, PRIMARY KEY(coursenum));

CREATE TABLE courseoffer (coid CHAR(4) NOT NULL, numstudent SMALLINT CHECK (numstudent > 0), term CHAR (6) NOT NULL, year SMALLINT NOT NULL, whichcourse CHAR(6) NOT NULL, PRIMARY KEY (coid),   FOREIGN KEY (whichcourse) REFERENCES course(coursenum) ON DELETE CASCADE);

-- add many to many tables

CREATE TABLE hasworkedon(tauserid VARCHAR(8) NOT NULL, coid CHAR(4) NOT NULL, hours SMALLINT, PRIMARY KEY (tauserid, coid), FOREIGN KEY(tauserid) REFERENCES ta(tauserid) ON DELETE RESTRICT, FOREIGN KEY(coid) REFERENCES courseoffer(coid) ON DELETE RESTRICT);

CREATE TABLE hates(htauserid VARCHAR(8) NOT NULL, hcoursenum CHAR(6) NOT NULL,  PRIMARY KEY (htauserid, hcoursenum), FOREIGN KEY(htauserid) REFERENCES ta(tauserid) ON DELETE CASCADE, FOREIGN KEY (hcoursenum) REFERENCES course(coursenum) ON DELETE CASCADE);

CREATE TABLE loves(ltauserid VARCHAR(8) NOT NULL, lcoursenum CHAR(6) NOT NULL,  PRIMARY KEY (ltauserid, lcoursenum), FOREIGN KEY(ltauserid) REFERENCES ta(tauserid) ON DELETE CASCADE, FOREIGN KEY (lcoursenum) REFERENCES course(coursenum) ON DELETE CASCADE);

SHOW TABLES;

-- ------------------------------------
-- insert some data

-- insert into the ta table
SELECT * FROM ta;
INSERT INTO ta VALUES ('mgeller','Monica','Geller','251000123','PhD');
INSERT INTO ta VALUES ('rgeller','Ross','Geller','251000222','Masters');
INSERT INTO ta VALUES ('rgreen','Rachel','Green','251000333','PhD');
INSERT INTO ta VALUES ('cgeller','Chandler','Geller','251000444','PhD');
INSERT INTO ta VALUES ('jbing','Joey','Bing','251000444','PhD');
INSERT INTO ta VALUES ('pbing','Phoebe','Bing','251000555','Masters');
INSERT INTO ta VALUES ('pbing2','Patty','Bing','251000666','Masters');
SELECT * FROM ta;

-- insert into the Course Table
SELECT * FROM course;
INSERT INTO course VALUES ('CS1033','Multimedia', 1, 2003);
INSERT INTO course VALUES ('CS3319','Intro to Databases', 3, 1973);
INSERT INTO course VALUES ('CS4411','Advanced Databases', 4, 1987);
INSERT INTO course VALUES ('CS1026','Intro to Programming', 1, 1995);
SELECT * FROM course;

-- insert into the Course Offering Table
SELECT * FROM courseoffer;
INSERT INTO courseoffer VALUES ('RD34',820, 'Fall',2021, 'CS1033');
INSERT INTO courseoffer VALUES ('RD44',200, 'Fall',2023, 'CS3319');
INSERT INTO courseoffer VALUES ('RD55',250, 'Fall',2020, 'CS3319');
INSERT INTO courseoffer VALUES ('RD66',170, 'Fall',2021, 'CS3319');
INSERT INTO courseoffer VALUES ('RD37',720, 'Spring',2021, 'CS1033');
INSERT INTO courseoffer VALUES ('RD99',600, 'Fall',2001, 'CS1033');
INSERT INTO courseoffer VALUES ('RD12',120, 'Spring',2022, 'CS4411');
INSERT INTO courseoffer VALUES ('RD13',130, 'Spring',1990, 'CS1026');
SELECT * FROM courseoffer;

-- insert into the Table showing which t.a.s worked on which courses.
SELECT * FROM hasworkedon;
INSERT INTO hasworkedon VALUES ('mgeller','RD34',100);
INSERT INTO hasworkedon VALUES ('mgeller','RD99',100);
INSERT INTO hasworkedon VALUES ('mgeller','RD44',20);
INSERT INTO hasworkedon VALUES ('mgeller','RD37',50);
INSERT INTO hasworkedon VALUES ('mgeller','RD12',100);
INSERT INTO hasworkedon VALUES ('rgeller','RD34',80);
INSERT INTO hasworkedon VALUES ('rgreen','RD34',100);
SELECT * FROM hasworkedon;

SELECT * FROM loves;
INSERT INTO loves VALUES ('mgeller','CS1033');
INSERT INTO loves VALUES ('mgeller','CS4411');
INSERT INTO loves VALUES ('rgreen','CS1033');
INSERT INTO loves VALUES ('rgreen','CS1026');
SELECT * FROM loves;

SELECT * FROM hates;
INSERT INTO hates VALUES ('mgeller','CS3319');
INSERT INTO hates VALUES ('pbing','CS3319');
INSERT INTO hates VALUES ('pbing','CS1033');
SELECT * FROM hates;