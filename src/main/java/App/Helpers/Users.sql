CREATE TABLE USERS(
    id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
    username VARCHAR(140) NOT NULL,
    password VARCHAR(140) NOT NULL,
    name VARCHAR (240) NOT NULL,
    role INT NOT NULL DEFAULT 0
);