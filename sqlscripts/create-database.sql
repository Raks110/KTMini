
use Project;

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

CREATE TABLE businessLoan(
	BLID INT GENERATED ALWAYS AS IDENTITY,
	BAID INT,
	amount numeric(10,2),
	UIN INT,
	PRIMARY KEY(BLID),
	FOREIGN KEY(UIN) REFERENCES users(UIN),
	FOREIGN KEY(BAID) REFERENCES businessAccount(BAID)
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

CREATE TABLE businessDeposit(
	BDID INT GENERATED ALWAYS AS IDENTITY,
	BAID INT,
	amount numeric(10,2),
	UIN INT,
	PRIMARY KEY(BDID),
	FOREIGN KEY(UIN) REFERENCES users(UIN),
	FOREIGN KEY(BAID) REFERENCES businessAccount(BAID)
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
	FOREIGN KEY(contractorID) REFERENCES businessAccount(BAID),
	FOREIGN KEY(contracteeID) REFERENCES businessAccount(BAID)
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
