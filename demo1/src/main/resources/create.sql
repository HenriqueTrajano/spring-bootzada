DROP TABLE books IF EXISTS;
CREATE TABLE books (
    identifier long,
    title VARCHAR(255),
    author VARCHAR(255),
    yearAux int,
    PRIMARY KEY(identifier)
);


