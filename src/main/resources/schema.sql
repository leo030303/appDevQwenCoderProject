CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(255) NOT NULL,
                       unlocked BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE households (
                            eircode VARCHAR(10) PRIMARY KEY,
                            number_occupants INT NOT NULL,
                            max_occupants INT NOT NULL,
                            owner_occupied BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE pets (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      age INT NOT NULL,
                      animal_type VARCHAR(255) NOT NULL,
                      breed VARCHAR(255) NOT NULL,
                      eircode VARCHAR(10),
                      FOREIGN KEY (eircode) REFERENCES households(eircode)
);
