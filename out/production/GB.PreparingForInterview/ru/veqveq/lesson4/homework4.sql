CREATE SCHEMA `cinema_db`;

CREATE TABLE `cinema_db`.`films_tbl` (
  `id_fld` INT NOT NULL AUTO_INCREMENT,
  `name_fld` VARCHAR(255) NOT NULL,
  `duration_fld` INT NOT NULL,
  PRIMARY KEY (`id_fld`));

CREATE TABLE `cinema_db`.`sessions_tbl` (
  `id_fld` INT NOT NULL AUTO_INCREMENT,
  `film_id_fld` INT NOT NULL,
  `beginning_fld` DATETIME NOT NULL,
  `price_fld` INT NOT NULL,
  PRIMARY KEY (`id_fld`),
  INDEX `fk_films_idx` (`film_id_fld` ASC) VISIBLE,
  CONSTRAINT `fk_films`
    FOREIGN KEY (`film_id_fld`)
    REFERENCES `cinema_db`.`films_tbl` (`id_fld`));

CREATE TABLE `cinema_db`.`tickets_tbl` (
  `id_fld` INT NOT NULL AUTO_INCREMENT,
  `session_id_fld` INT NOT NULL,
  PRIMARY KEY (`id_fld`),
  INDEX `fk_session_idx` (`session_id_fld` ASC) VISIBLE,
  CONSTRAINT `fk_session`
    FOREIGN KEY (`session_id_fld`)
    REFERENCES `cinema_db`.`sessions_tbl` (`id_fld`));

INSERT INTO `cinema_db`.`films_tbl` (`name_fld`, `duration_fld`) VALUES 
	('A Quiet Place Part II', '97'), 
	('Cruella', '134'), 
	('F9', '145'), 
	('Wrath of Man', '119'), 
	('Awake', '92');

INSERT INTO `cinema_db`.`sessions_tbl` (`film_id_fld`, `beginning_fld`, `price_fld`) VALUES 
	('1', '2021-06-08 12:00', '220'),
	('2', '2021-06-08 13:00', '240'),
	('3', '2021-06-08 16:00', '240'),
	('1', '2021-06-08 18:00', '300'),
	('5', '2021-06-08 22:00', '150');

INSERT INTO `cinema_db`.`tickets_tbl` (`session_id_fld`) VALUES 
    ('1'),('1'),('1'),('1'),
    ('2'),('1'),('3'),('5'),
    ('5'),('1'),('4'),('5'),
    ('2'),('4'),('1'),('5'),
    ('4'),('2'),('3'),('1'),
    ('2'),('3'),('4'),('1'),
    ('2'),('3'),('4'),('5'),
    ('1'),('2'),('3'),('4'),
    ('5'),('2'),('2'),('5'),
    ('5'),('5'),('5'),('1'),
    ('2'),('4'),('4'),('2'),
    ('2'),('1'),('2'),('4'),
    ('2'),('1'),('3'),('4'),
    ('1'),('2'),('2'),('3');

//Запрос на наложение времени фильмов
SELECT t4.name_fld, t3.beginning_fld, t4.duration_fld, 
       t2.name_fld, t1.beginning_fld, t2.duration_fld 
FROM cinema_db.sessions_tbl AS t1 
	LEFT JOIN cinema_db.films_tbl AS t2 ON t1.film_id_fld=t2.id_fld
INNER JOIN cinema_db.sessions_tbl AS t3 ON t1.id_fld <> t3.id_fld
	LEFT JOIN cinema_db.films_tbl AS t4 ON t3.film_id_fld=t4.id_fld
WHERE t1.beginning_fld < addtime(t3.beginning_fld, SEC_TO_TIME(t4.duration_fld*60))
	AND t1.beginning_fld>t3.beginning_fld
ORDER BY (addtime(t3.beginning_fld, SEC_TO_TIME(t4.duration_fld*60))-t1.beginning_fld);

//Запрос на перерывы
SELECT t2.name_fld, t1.beginning_fld, t2.duration_fld, 
       t3.beginning_fld, 
       TIMEDIFF(t3.beginning_fld, addtime(t1.beginning_fld, SEC_TO_TIME(t2.duration_fld*60)))  AS pause
FROM cinema_db.sessions_tbl AS t1
	LEFT JOIN cinema_db.films_tbl AS t2 ON t1.film_id_fld = t2.id_fld
INNER JOIN cinema_db.sessions_tbl AS t3 ON t1.id_fld<>t3.id_fld AND t1.beginning_fld < t3.beginning_fld
GROUP BY t1.id_fld
HAVING pause>sec_to_time(30*60)
ORDER BY (pause) DESC;

//Запрос по статистике
SELECT 
	films.name_fld,
    COUNT(tickets.id_fld) AS total_viewers,
    COUNT(tickets.id_fld)/COUNT(DISTINCT sessions.id_fld) AS average_viewers,
    SUM(sessions.price_fld) AS total_profit
FROM cinema_db.films_tbl AS films
	LEFT JOIN cinema_db.sessions_tbl AS sessions ON films.id_fld = sessions.film_id_fld
    LEFT JOIN cinema_db.tickets_tbl AS tickets ON sessions.id_fld = tickets.session_id_fld
GROUP BY(films.id_fld)
ORDER BY(total_profit) DESC;

