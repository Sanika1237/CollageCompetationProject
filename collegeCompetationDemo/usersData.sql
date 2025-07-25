CREATE DATABASE collegeCdb;
USE collegeCdb;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    role ENUM('ADMIN', 'USER') NOT NULL
);

CREATE TABLE competitions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    competition_name VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    college_name VARCHAR(255),
    user_id BIGINT,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

INSERT INTO users (username, password, email, role) 
VALUES ('sanika kadam', 'Sanika@1899', 'sanika@college.com', 'ADMIN');

select * from users;

select * from competitions;


