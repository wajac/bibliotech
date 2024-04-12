CREATE TABLE bibliotech.category (
	uid INTEGER AUTO_INCREMENT NOT NULL,
	code VARCHAR(10) NOT NULL,
	label VARCHAR(64) NOT NULL,
	
	PRIMARY KEY (uid)
);


CREATE TABLE bibliotech.book (
	uid INTEGER AUTO_INCREMENT NOT NULL,
	title VARCHAR(255) NOT NULL,
	description VARCHAR(2000),
	isbn VARCHAR(255),
	nb_pages INTEGER,
	published DATE,
	illustrations BOOLEAN DEFAULT FALSE,
	cover BLOB,
	maj_date TIMESTAMP NOT NULL DEFAULT current_timestamp,
	
	PRIMARY KEY (uid)
);


CREATE TABLE bibliotech.author (
	uid INTEGER AUTO_INCREMENT NOT NULL,
	civility VARCHAR(15),
	gender CHAR(1),
	first_name VARCHAR(64) NOT NULL,
	last_name VARCHAR(64) NOT NULL,
	address1 VARCHAR(64),
	address2 VARCHAR(64),
	zip_code VARCHAR(15),
	city VARCHAR(64),
	country VARCHAR(64),
	maj_date TIMESTAMP NOT NULL DEFAULT current_timestamp,
	
	PRIMARY KEY (uid),
	
	CONSTRAINT check_gender CHECK (gender IN ('M', 'F'))
);


CREATE TABLE bibliotech.book_category (
	uid_book INTEGER NOT NULL,
	uid_category INTEGER NOT NULL,
	
	PRIMARY KEY (uid_book, uid_category),
	
	CONSTRAINT fk_book_book_category FOREIGN KEY (uid_book) REFERENCES book (uid)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	
	CONSTRAINT fk_category_book_category FOREIGN KEY (uid_category) REFERENCES category (uid)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
);


CREATE TABLE bibliotech.author_book (
	uid_author INTEGER NOT NULL,
	uid_book INTEGER NOT NULL,
	
	PRIMARY KEY (uid_author, uid_book),
	
	CONSTRAINT fk_author_author_book FOREIGN KEY (uid_author) REFERENCES author (uid)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	
	CONSTRAINT fk_book_author_book FOREIGN KEY (uid_book) REFERENCES book (uid)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
);


INSERT INTO bibliotech.category (code, label) values
('NOVEL', 'category.label.novel'),
('ESSAY', 'category.label.essay'),
('SHORTSTORY', 'category.label.shortstory'),
('FANTASY', 'category.label.fantasy'),
('SCIFI', 'category.label.scifi'),
('HISTORY', 'category.label.history'),
('BIOGRAPHY', 'category.label.biography'),
('HUMANITIES', 'category.label.humanities'),
('ST', 'category.label.st'),
('EDUCATION', 'category.label.education'),
('DICTIONARY', 'category.label.dictionary'),
('ART', 'category.label.art'),
('COOKING', 'category.label.cooking'),
('COMICS', 'category.label.comics'),
('CHILDREN', 'category.label.children');


INSERT INTO bibliotech.book (title, description, nb_pages, published, illustrations) values
('Design Patterns: Elements of Reusable Object-Oriented Software',
'Capturing a wealth of experience about the design of object-oriented software, four top-notch designers present a catalog of simple and succinct solutions to commonly occurring design problems. Previously undocumented, these 23 patterns allow designers to create more flexible, elegant, and ultimately reusable designs without having to rediscover the design solutions themselves.
The authors begin by describing what patterns are and how they can help you design object-oriented software. They then go on to systematically name, explain, evaluate, and catalog recurring designs in object-oriented systems. With Design Patterns as your guide, you will learn how these important patterns fit into the software development process, and how you can leverage them to solve your own design problems most efficiently.
Each pattern describes the circumstances in which it is applicable, when it can be applied in view of other design constraints, and the consequences and trade-offs of using the pattern within a larger design. All patterns are compiled from real systems and are based on real-world examples. Each pattern also includes code that demonstrates how it may be implemented in object-oriented programming languages like C++ or Smalltalk.',
416, '1994-01-01', TRUE),
('Introduction to Algorithms',
'A comprehensive update of the leading algorithms text, with new material on matchings in bipartite graphs, online algorithms, machine learning, and other topics.
Some books on algorithms are rigorous but incomplete; others cover masses of material but lack rigor. Introduction to Algorithms uniquely combines rigor and comprehensiveness. It covers a broad range of algorithms in depth, yet makes their design and analysis accessible to all levels of readers, with self-contained chapters and algorithms in pseudocode. Since the publication of the first edition, Introduction to Algorithms has become the leading algorithms text in universities worldwide as well as the standard reference for professionals. This fourth edition has been updated throughout.
New for the fourth edition
New chapters on matchings in bipartite graphs, online algorithms, and machine learningNew material on topics including solving recurrence equations, hash tables, potential functions, and suffix arrays140 new exercises and 22 new problemsReader feedback-informed improvements to old problemsClearer, more personal, and gender-neutral writing styleColor added to improve visual presentationNotes, bibliography, and index updated to reflect developments in the fieldWebsite with new supplementary material
Warning: Avoid counterfeit copies of Introduction to Algorithms by buying only from reputable retailers. Counterfeit and pirated copies are incomplete and contain errors.',
1184, '1989-01-01', TRUE),
('The Fellowship of the Ring',
'One Ring to rule them all, One Ring to find them, One Ring to bring them all and in the darkness bind them.
In ancient times the Rings of Power were crafted by the Elven-smiths, and Sauron, the Dark Lord, forged the One Ring, filling it with his own power so that he could rule all others. But the One Ring was taken from him, and though he sought it throughout Middle-earth, it remained lost to him. After many ages it fell into the hands of Bilbo Baggins, as told in The Hobbit.
In a sleepy village in the Shire, young Frodo Baggins finds himself faced with an immense task, as his elderly cousin Bilbo entrusts the Ring to his care. Frodo must leave his home and make a perilous journey across Middle-earth to the Cracks of Doom, there to destroy the Ring and foil the Dark Lord in his evil purpose.',
432, '1954-07-29', FALSE),
('The Two Towers',
'Begin your journey into Middle-earth.
The inspiration for the upcoming original series on Prime Video, The Lord of the Rings: The Rings of Power.
The Two Towers is the second part of J.R.R. Tolkien’s epic adventure The Lord of the Rings.
One Ring to rule them all, One Ring to find them, One Ring to bring them all and in the darkness bind them.
Frodo and his Companions of the Ring have been beset by danger during their quest to prevent the Ruling Ring from falling into the hands of the Dark Lord by destroying it in the Cracks of Doom. They have lost the wizard, Gandalf, in a battle in the Mines of Moria. And Boromir, seduced by the power of the Ring, tried to seize it by force. While Frodo and Sam made their escape, the rest of the company was attacked by Orcs. Now they continue the journey alone down the great River Anduin—alone, that is, save for the mysterious creeping figure that follows wherever they go.',
448, '1954-11-11', FALSE),
('The Return of the King',
'Begin your journey into Middle-earth.
The inspiration for the upcoming original series on Prime Video, The Lord of the Rings: The Rings of Power.
The Return of the King is the third part of J.R.R. Tolkien’s epic adventure The Lord of the Rings.
One Ring to rule them all, One Ring to find them, One Ring to bring them all and in the darkness bind them.
The Dark Lord has risen, and as he unleashes hordes of Orcs to conquer all Middle-earth, Frodo and Sam struggle deep into his realm in Mordor.
To defeat Sauron, the One Ring must be destroyed in the fires of Mount Doom. But the way is impossibly hard, and Frodo is weakening. The Ring corrupts all who bear it and Frodo’s time is running out.
Will Sam and Frodo succeed, or will the Dark Lord rule Middle-earth once more?',
432, '1955-10-20', FALSE);


INSERT INTO bibliotech.author (first_name, last_name) values
('Erich', 'Gamma'),
('Richard', 'Helm'),
('Ralph', 'Johnson'),
('John', 'Vlissides'),
('Thomas H.', 'Cormen'),
('Charles E.', 'Leiserson'),
('Ronald L.', 'Rivest'),
('Clifford', 'Stein'),
('J.R.R.', 'Tolkien');


INSERT INTO bibliotech.book_category (uid_book, uid_category) values
(1, 9), (1, 10),
(2, 9), (2, 10),
(3, 4), (4, 4), (5, 4);


INSERT INTO bibliotech.author_book (uid_author, uid_book) values
(1, 1), (2, 1), (3, 1), (4, 1),
(5, 2), (6, 2), (7, 2), (8, 2),
(9, 3), (9, 4), (9, 5);


