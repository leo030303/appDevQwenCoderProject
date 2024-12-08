INSERT INTO users (username, password, role, unlocked) VALUES
                                                           ('admin', '$2a$10$dF3IwDZjg9LjU4HkxYtQGeKJWzjM5iXV8TqfG7bN6pRvPzZsQzZsQ', 'ADMIN', TRUE),
                                                           ('user', '$2a$10$dF3IwDZjg9LjU4HkxYtQGeKJWzjM5iXV8TqfG7bN6pRvPzZsQzZsQ', 'USER', TRUE);

INSERT INTO households (eircode, number_occupants, max_occupants, owner_occupied) VALUES
                                                                                      ('A1234567B', 2, 4, TRUE),
                                                                                      ('C7654321D', 3, 5, FALSE);

INSERT INTO pets (name, age, animal_type, breed, eircode) VALUES
                                                              ('Buddy', 3, 'Dog', 'Golden Retriever', 'A1234567B'),
                                                              ('Whiskers', 2, 'Cat', 'Siamese', 'C7654321D');
