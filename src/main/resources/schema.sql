CREATE TABLE USER_INFO (
    cin BIGINT NOT NULL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    passport_number VARCHAR(255) NOT NULL,
    passport_issue_date DATE NOT NULL,
    passport_expiry_date DATE NOT NULL,
    phone_number VARCHAR(255),
    address VARCHAR(255),
    birth_date DATE NOT NULL,
    nationality VARCHAR(255),
    gender VARCHAR(50),
    role VARCHAR(50)
);