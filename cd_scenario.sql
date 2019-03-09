use test;
CREATE TABLE Leaders (
	Id INT UNSIGNED NOT NULL,
	Company VARCHAR(100),
	firstName VARCHAR(50),
	Interes VARCHAR(50),
	lastName VARCHAR(50),
	jobTitleId TINYINT UNSIGNED,
	functionId TINYINT UNSIGNED,
	departmentId TINYINT UNSIGNED,
	locationId INT UNSIGNED,
	qualificationLevelId TINYINT UNSIGNED,
	approvalStatusId TINYINT UNSIGNED,
	statusId TINYINT UNSIGNED,
	ownerId MEDIUMINT UNSIGNED,
	sourceId TINYINT UNSIGNED,
	campaignId MEDIUMINT UNSIGNED,
	BCDS BOOLEAN,
	startDate DATE,
	endDate DATE,
	ownerSaleId MEDIUMINT UNSIGNED,
	makertingUnitId MEDIUMINT UNSIGNED, 
	createdOn DATETIME DEFAULT NOW(),
	orderTypeId TINYINT UNSIGNED,
	typeOfDeploymentId TINYINT UNSIGNED,
	Telemarketing BOOLEAN
);

DROP TABLE test.Leaders;
CREATE TABLE JobTitles (
	Id TINYINT UNSIGNED,
	Title VARCHAR(100),
	PRIMARY KEY(Id)
);

CREATE TABLE Functions (
	Id TINYINT UNSIGNED,
	Function VARCHAR(100),
	PRIMARY KEY(Id)
);

CREATE TABLE Departments (
	Id TINYINT UNSIGNED,
	Department VARCHAR(100),
	PRIMARY KEY(Id)
);

CREATE TABLE Location (
	Id INT UNSIGNED,
	Note VARCHAR(100),
	cityId INT UNSIGNED, 
	PRIMARY KEY(Id)
);

CREATE TABLE Cities (
	Id INT UNSIGNED,
	Note VARCHAR(100),
	stateId INT UNSIGNED, 
	PRIMARY KEY(Id)
);

CREATE TABLE States (
	Id INT UNSIGNED,
	Note VARCHAR(100),
	stateId INT UNSIGNED, 
	PRIMARY KEY(Id)
);

CREATE TABLE QualificationLevels (
	Id TINYINT UNSIGNED,
	Note VARCHAR(100),
	PRIMARY KEY(Id)
);

CREATE TABLE ApprovalStatus (
	Id TINYINT UNSIGNED,
	Note VARCHAR(100),
	PRIMARY KEY(Id)
);

CREATE TABLE Status (
	Id TINYINT UNSIGNED,
	Note VARCHAR(100),
	PRIMARY KEY(Id)
);

CREATE TABLE Owners (
	Id MEDIUMINT UNSIGNED,
	Note VARCHAR(100),
	PRIMARY KEY(Id)
);

CREATE TABLE Sources (
	Id TINYINT UNSIGNED,
	Note VARCHAR(100),
	PRIMARY KEY(Id)
);

CREATE TABLE Campaigns (
	Id MEDIUMINT UNSIGNED,
	Note VARCHAR(100),
	PRIMARY KEY(Id)
);

CREATE TABLE OwnerSale (
	Id MEDIUMINT UNSIGNED,
	Note VARCHAR(100),
	PRIMARY KEY(Id)
);

CREATE TABLE MakertingUnits (
	Id MEDIUMINT UNSIGNED,
	Note VARCHAR(100),
	PRIMARY KEY(Id)
);

CREATE TABLE OrderType (
	Id TINYINT UNSIGNED,
	Note VARCHAR(100),
	PRIMARY KEY(Id)
);

CREATE TABLE TypeOfDeployments (
	Id TINYINT UNSIGNED,
	Note VARCHAR(100),
	PRIMARY KEY(Id)
);

ALTER TABLE Leaders ADD CONSTRAINT FK_Leaders_jobTitleId FOREIGN KEY (jobTitleId) REFERENCES JobTitles(Id);

ALTER TABLE Leaders ADD CONSTRAINT FK_Leaders_functionId FOREIGN KEY (functionId) REFERENCES Functions(Id);

ALTER TABLE Leaders ADD CONSTRAINT FK_Leaders_departmentId FOREIGN KEY (departmentId) REFERENCES Departments(Id);

ALTER TABLE Leaders ADD CONSTRAINT FK_Leaders_locationId FOREIGN KEY (locationId) REFERENCES Location(Id);

ALTER TABLE Leaders ADD CONSTRAINT FK_Leaders_qualificationLevelId FOREIGN KEY (qualificationLevelId) REFERENCES QualificationLevels(Id);

ALTER TABLE Leaders ADD CONSTRAINT FK_Leaders_approvalStatusId FOREIGN KEY (approvalStatusId) REFERENCES ApprovalStatus(Id);

ALTER TABLE Leaders ADD CONSTRAINT FK_Leaders_statusId FOREIGN KEY (statusId) REFERENCES Status(Id);

ALTER TABLE Leaders ADD CONSTRAINT FK_Leaders_ownerId FOREIGN KEY (ownerId) REFERENCES Owners(Id);

ALTER TABLE Leaders ADD CONSTRAINT FK_Leaders_sourceId FOREIGN KEY (sourceId) REFERENCES Sources(Id);

ALTER TABLE Leaders ADD CONSTRAINT FK_Leaders_campaignId FOREIGN KEY (campaignId) REFERENCES Campaigns(Id);

ALTER TABLE Leaders ADD CONSTRAINT FK_Leaders_ownerSaleId FOREIGN KEY (ownerSaleId) REFERENCES OwnerSale(Id);

ALTER TABLE Leaders ADD CONSTRAINT FK_Leaders_makertingUnitId FOREIGN KEY (makertingUnitId) REFERENCES MakertingUnits(Id);

ALTER TABLE Leaders ADD CONSTRAINT FK_Leaders_orderTypeId FOREIGN KEY (orderTypeId) REFERENCES OrderType(Id);

ALTER TABLE Leaders ADD CONSTRAINT FK_Leaders_typeOfDeploymetId FOREIGN KEY (typeOfDeploymentId) REFERENCES TypeOfDeployments(Id);