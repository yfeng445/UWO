USE assign2db;

-- -------------
-- Part 1 SQL Updates
SELECT * FROM course;
UPDATE course SET coursename = "Multimedia and Communications" WHERE coursename = "Multimedia";
UPDATE hasworkedon SET hours = 200 WHERE tauserid IN (SELECT tauserid FROM ta WHERE firstname LIKE 'R%');
SELECT * FROM course;
SELECT * FROM hasworkedon;


-- -------------
-- Part 2 SQL Inserts
SELECT * FROM course;
SELECT * FROM courseoffer;
SELECT * FROM ta;
SELECT * FROM loves;
INSERT INTO course VALUES ('CS3342','Programming Languages', 3, 2001);
INSERT INTO courseoffer VALUES ('RD11',19, 'Fall',2020, 'CS3342');
INSERT INTO courseoffer VALUES ('RD45',198, 'Fall',2020, 'CS3342');
INSERT INTO courseoffer VALUES ('RD14',10, 'Fall',2020, 'CS3342');
INSERT INTO ta VALUES ('myuki','Minoto','Yukina','251919810','PhD');
INSERT INTO loves VALUES ('staki','CS3342');
SELECT * FROM course;
SELECT * FROM courseoffer;
SELECT * FROM ta;
SELECT * FROM loves;


-- -------------
-- Part 3 SQL Queries;
-- Query 1;
SELECT lastname FROM ta;
-- Query 2
SELECT DISTINCT lastname FROM ta;
-- Query 3
SELECT * FROM ta ORDER BY firstname;
-- Query 4
SELECT firstname, lastname, tauserid FROM ta WHERE degreetype = "Masters";
-- Query 5
SELECT courseoffer.coid, courseoffer.term, courseoffer.year, course.coursenum FROM (courseoffer JOIN course ON course.coursenum = courseoffer.whichcourse) WHERE course.coursename LIKE '%Database%';
-- Query 6
SELECT * FROM (course JOIN courseoffer ON course.coursenum = courseoffer.whichcourse) WHERE (courseoffer.year < course.year);
-- Query 7
SELECT course.coursename, course.coursenum FROM ((course JOIN loves ON course.coursenum = loves.lcoursenum) JOIN ta ON loves.ltauserid = ta.tauserid) WHERE (ta.lastname = "Geller");
-- Query 8
SELECT SUM(courseoffer.numstudent), course.coursename, course.coursenum FROM (course JOIN courseoffer ON course.coursenum = courseoffer.whichcourse) WHERE (course.coursenum = "CS1033");
-- Query 9
SELECT DISTINCT ta.firstname, ta.lastname, courseoffer.whichcourse FROM ((ta JOIN hasworkedon ON ta.tauserid = hasworkedon.tauserid) JOIN courseoffer ON hasworkedon.coid = courseoffer.coid) WHERE (courseoffer.whichcourse LIKE "CS1%");
-- Query 10
SELECT ta.firstname, ta.lastname, hasworkedon.hours, courseoffer.whichcourse FROM ((ta JOIN hasworkedon ON ta.tauserid = hasworkedon.tauserid) JOIN courseoffer ON courseoffer.coid = hasworkedon.coid) WHERE (hasworkedon.hours = (SELECT MAX(hours) FROM hasworkedon));
-- Query 11
SELECT course.coursename, course.coursenum FROM ((course JOIN loves ON course.coursenum = loves.lcoursenum) JOIN hates ON course.coursenum = hates.hcoursenum) WHERE ((course.coursenum NOT IN (SELECT lcoursenum FROM loves)) AND (course.coursenum NOT IN (SELECT hcoursenum FROM hates))); 
-- Query 12
SELECT ta.lastname, ta.firstname, COUNT(hasworkedon.coid) FROM ta JOIN hasworkedon ON ta.tauserid = hasworkedon.tauserid GROUP BY ta.tauserid, ta.lastname, ta.firstname HAVING COUNT(hasworkedon.coid) > 1;
-- Query 13
SELECT DISTINCT ta.firstname, ta.lastname, course.coursenum, course.coursename FROM (ta JOIN loves ON ta.tauserid = loves.ltauserid JOIN course ON course.coursenum = loves.lcoursenum);
-- Query 14
CREATE VIEW FallCourseCounts AS SELECT co.whichcourse AS course_number, COUNT(co.coid) AS offering_count FROM courseoffer AS co WHERE co.term = 'Fall' GROUP BY co.whichcourse;
SELECT c.coursenum, c.coursename, f.offering_count FROM course AS c JOIN FallCourseCounts AS f ON c.coursenum = f.course_number WHERE f.offering_count = (SELECT MAX(offering_count) FROM FallCourseCounts);
-- Query 15 - My Query 
-- Display the time consumption for each ta for each course offering by descending order
SELECT DISTINCT ta.firstname, ta.lastname, ta.tauserid, hasworkedon.coid, hasworkedon.hours from ta JOIN hasworkedon ON ta.tauserid = hasworkedon.tauserid ORDER BY hasworkedon.hours DESC;



-- -------------
-- Part 4 SQL Views/Deletes
CREATE VIEW  tahate AS (SELECT ta.firstname, ta.lastname, ta.tauserid, course.coursenum, course.coursename FROM (ta JOIN hates ON ta.tauserid = hates.htauserid JOIN course ON coursenum = hates.hcoursenum));
SELECT * FROM tahate;
SELECT DISTINCT tahate.firstname, tahate.lastname, tahate.tauserid FROM (tahate JOIN hasworkedon ON tahate.tauserid = hasworkedon.tauserid JOIN courseoffer ON hasworkedon.coid = courseoffer.coid) ORDER BY tahate.firstname;
SELECT * FROM ta;
SELECT * FROM hates;
DELETE FROM hasworkedon WHERE tauserid = 'pbing';
DELETE FROM loves WHERE ltauserid = 'pbing';
DELETE FROM hates WHERE htauserid = 'pbing';
DELETE FROM ta WHERE tauserid = 'pbing';
SELECT * FROM ta;
SELECT * FROM hates;
SELECT * FROM loves;
SELECT * FROM hasworkedon;
-- DELETE FROM ta WHERE tauserid = 'mgeller';
-- 'mgeller' could not be deleted since it is used as a primary key in hasworkenon table
SELECT * FROM ta;
ALTER TABLE ta ADD image VARCHAR(200);
SELECT * FROM ta;
UPDATE ta SET image = "https://i.pinimg.com/originals/bf/85/8d/bf858d262ce992754e2b78042c9e0fe8.gif" WHERE ta.tauserid = "mgeller";
SELECT * FROM ta;
