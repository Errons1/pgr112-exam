/* CREATE SCHEMA `quizdb` ; */

CREATE TABLE `quizdb`.`multichoicequiz` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(200) NULL,
  `correctAnswer` VARCHAR(1) NULL,
  `answerA` VARCHAR(100) NULL,
  `answerB` VARCHAR(100) NULL,
  `answerC` VARCHAR(100) NULL,
  `answerD` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

CREATE TABLE `quizdb`.`binaryquiz` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(200) NULL,
  `correctAnswer` VARCHAR(1) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
  
  CREATE TABLE `quizdb`.`score` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `score` VARCHAR(45) NULL,
  `topic` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
  
  INSERT INTO quizdb.multichoicequiz (question, correctAnswer, answerA, answerB, answerC, answerD)
  VALUE ('When was starcraft 2 Wings of Liberty released?',
	     '1',
         '27. July, 2010',
         '15. August, 2010',
         '25. July, 2011',
         '5. Januar. 2010'),
        ('What game engine does Starcraft 2 use?',
		 '3',
         'Unity',
         'Unreal engine 4',
         'Havok',
         'Creation Engine'),
        ('What is Elder Scolls 4 called?',
         '2',
         'Old man Jack',
         'Oblivion',
         'Havok',
         'Skyrim'),
        ('Why is the Genre Rougelike named Rougelike?',
         '4',
         'Its name was randomy given Rougelike',
         'Early youtuber Rougelike made the genre popular',
         'Cause it plays like a rouge',
         'The first game in this genre was named Rouge'),
        ('What is probly Bogdas favoritt game?',
         '1',
         'World of warship',
         'Warthunder',
         'Thunder strike 2',
         'Elderscolls 5 - Skyrim');

  INSERT INTO quizdb.binaryquiz (question, correctAnswer)
  VALUE ('Was Bjarne Stroustrup the designer behind C++?',
         '1'),
        ('Was the first appearance of C in 1974?',
         '2'),
        ('Is Java a OOP language?',
         '1'),
        ('Does programmer still use C?',
         '1'),
        ('Is String mutable in Java?',
         '2');

  INSERT INTO quizdb.score (user, score, topic)
  VALUE ('Testerson', '0', 'Binary choice'),
        ('Testerdottir', '1', 'Multiple choice'),
        ('Testerson', '2', 'Binary choice'),
        ('Testerdottir', '3', 'Binary choice'),
        ('Testerson', '4', 'Binary choice'),
        ('Testerdottir', '5', 'Binary choice'),
        ('Testerson', '6', 'Binary choice'),
        ('Testerdottir', '7', 'Binary choice'),
        ('Testerson', '8', 'Multiple choice'),
        ('Testerdottir', '9', 'Binary choice'),
        ('Testerson', '10', 'Binary choice');
         
         

  