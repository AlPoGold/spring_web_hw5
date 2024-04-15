CREATE TABLE tasks(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(100) NOT NULL,
    status VARCHAR(30),
    creating_time DATE
);