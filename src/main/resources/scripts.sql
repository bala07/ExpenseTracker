CREATE TABLE expense_sheet
(
	id INT NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id)
);

CREATE TABLE transaction
(
	id INT NOT NULL AUTO_INCREMENT,
	expense_sheet_id INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (expense_sheet_id) REFERENCES expense_sheet(id) ON DELETE CASCADE
);

CREATE TABLE user
(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	balance DOUBLE NOT NULL,
	expense_sheet_id INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (expense_sheet_id) REFERENCES expense_sheet(id) ON DELETE CASCADE
);
