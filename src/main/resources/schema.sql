DROP
    TABLE
        IF EXISTS users;

CREATE
    TABLE
        IF NOT EXISTS users(
            id BIGINT AUTO_INCREMENT PRIMARY KEY,
            name VARCHAR(50) NOT NULL,
            email VARCHAR(100) NOT NULL,
            password VARCHAR(100) NOT NULL,
            phone_number VARCHAR(20) NOT NULL
        );

ALTER TABLE users ADD CONSTRAINT u_name UNIQUE(name);
ALTER TABLE users ADD CONSTRAINT u_email UNIQUE(email);