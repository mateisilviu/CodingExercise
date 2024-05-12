--- BIRDS 
CREATE TABLE birds (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    weight DOUBLE PRECISION NOT NULL,
    height DOUBLE PRECISION NOT NULL
);

--- 
CREATE TABLE sightings (
    id VARCHAR(36) PRIMARY KEY,
    bird_id VARCHAR(36),
    location VARCHAR(255) NOT NULL,
    dateTime TIMESTAMP NOT NULL,
    CONSTRAINT fk_bird
        FOREIGN KEY (bird_id)
        REFERENCES birds (id)
);
