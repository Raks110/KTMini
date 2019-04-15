
use Project;

CREATE TABLE globalLedger(

	total_cash_flow NUMERIC(15, 2),
	savings_interest NUMERIC(4, 2),
	savings_amount NUMERIC(15, 2),
	loan_interest NUMERIC(4,2),
	loan_amount NUMERIC(16, 2),
	credit_amount NUMERIC(15, 2),
	debit_amount NUMERIC(15,2)

);

CREATE TABLE users(
	UIN INT GENERATED ALWAYS AS IDENTITY,
	firstName VARCHAR(30),
	middleName VARCHAR(30),
	lastName VARCHAR(30),
	contact INT,
	password VARCHAR(30),
	PRIMARY KEY(UIN)
);

CREATE TABLE business(
	BID INT GENERATED ALWAYS AS IDENTITY,
	managerID INT,
	name VARCHAR(20),
	password VARCHAR(30),
	CONSTRAINT business_id PRIMARY KEY(BID),
	FOREIGN KEY(managerID) REFERENCES users(UIN)
);

CREATE TABLE bank(
	bankID INT GENERATED ALWAYS AS IDENTITY,
	name VARCHAR(10),
	managerID INT,
	password VARCHAR(30),
	CONSTRAINT bank_id PRIMARY KEY(bankID)
);

CREATE TABLE user_account(
	accountID INT,
	UIN INT,
	bankID INT,
	balance INT,
	PRIMARY KEY(accountID),
	FOREIGN KEY(UIN) REFERENCES users(UIN),
	FOREIGN KEY(bankID) REFERENCES bank(bankID)
);

CREATE TABLE loan(
	LID INT GENERATED ALWAYS AS IDENTITY,
	accountID INT,
	amount numeric(10,2),
	UIN INT,
	PRIMARY KEY(LID),
	FOREIGN KEY(UIN) REFERENCES users(UIN),
	FOREIGN KEY(accountID) REFERENCES user_account(accountID)
);

CREATE TABLE deposit(
	DID INT GENERATED ALWAYS AS IDENTITY,
	accountID INT,
	UIN INT,
	amount numeric(15,2),
	PRIMARY KEY(DID),
	FOREIGN KEY(UIN) REFERENCES users(UIN),
	FOREIGN KEY(accountID) REFERENCES user_account(accountID)
);

CREATE TABLE businessAccount(
	BAID INT GENERATED ALWAYS AS IDENTITY,
	BID INT,
	bankID INT,
	amount numeric(15,2),
	PRIMARY KEY(BAID),
	FOREIGN KEY(BID) REFERENCES business(BID),
	FOREIGN KEY(bankID) REFERENCES bank(bankID)
);

CREATE TABLE contract(
	contractID INT GENERATED ALWAYS AS IDENTITY,
	contractorID INT,
	contracteeID INT,
	title VARCHAR(50),
	terms VARCHAR(500),
	amount numeric(15,2),
	PRIMARY KEY(contractID),
	FOREIGN KEY(contractorID) REFERENCES business(BID),
	FOREIGN KEY(contracteeID) REFERENCES business(BID)
);

CREATE TABLE payments(
	payID INT GENERATED ALWAYS AS IDENTITY,
	recieverUIN INT,
	senderUIN INT,
	amount INT,
	PRIMARY KEY(payID),
	FOREIGN KEY(recieverUIN) REFERENCES users(UIN),
	FOREIGN KEY(senderUIN) REFERENCES users(UIN)
)

======================== LETS SEE =====================================

CREATE TABLE appointments(
	APPNID INT,
	UIN INT,
	BID INT,
	timestamp Date,
	status VARCHAR(8),
	PRIMARY KEY(APPNID),
	FOREIGN KEY(UIN) REFERENCES users(UIN),
	FOREIGN KEY(BID) REFERENCES business(BID)
);