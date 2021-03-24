CREATE TABLE IF NOT EXISTS country
(
    code INTEGER PRIMARY KEY NOT NULL,
    name VARCHAR(70)         NOT NULL
);

CREATE TABLE IF NOT EXISTS document_type
(
    code INTEGER PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS organization
(
    id        INTEGER PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(50)           NOT NULL,
    full_name VARCHAR(70)           NOT NULL,
    inn       CHAR(10)              NOT NULL,
    is_active ENUM ('true','false') NOT NULL,
    kpp       CHAR(9)               NOT NULL,
    address   VARCHAR(50)           NOT NULL,
    phone     VARCHAR(20)           NOT NULL
);

CREATE TABLE IF NOT EXISTS office
(
    id              INTEGER PRIMARY KEY AUTO_INCREMENT,
    organization_id INTEGER                NOT NULL,
    name            VARCHAR(50)            NOT NULL,
    phone           VARCHAR(20)            NOT NULL,
    is_active       ENUM ('true', 'false') NOT NULL,
    address         VARCHAR(50)            NOT NULL,
    CONSTRAINT org_id FOREIGN KEY (organization_id) REFERENCES organization (id)
);


CREATE TABLE IF NOT EXISTS user
(
    id               INTEGER PRIMARY KEY AUTO_INCREMENT,
    office_id        INTEGER               NOT NULL,
    first_name       VARCHAR(50)           NOT NULL,
    second_name      VARCHAR(50),
    last_name        VARCHAR(50)           NOT NULL,
    middle_name      VARCHAR(50)           NOT NULL,
    position         VARCHAR(20)           NOT NULL,
    is_identified    ENUM ('true','false') NOT NULL,
    phone            VARCHAR(20)           NOT NULL,
    citizenship_code INTEGER               NOT NULL,
    CONSTRAINT citizenship_code FOREIGN KEY (citizenship_code) REFERENCES country (code),
    CONSTRAINT office_id FOREIGN KEY (office_id) REFERENCES office (id)
);


CREATE TABLE IF NOT EXISTS document
(
    id     INTEGER PRIMARY KEY,
    number VARCHAR(30) NOT NULL,
    date   DATE        NOT NULL,
    code   INT         NOT NULL,
    CONSTRAINT doc_code FOREIGN KEY (code) REFERENCES document_type (code),
    CONSTRAINT user_id FOREIGN KEY (`id`) REFERENCES user (id)
);