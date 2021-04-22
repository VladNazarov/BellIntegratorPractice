CREATE TABLE IF NOT EXISTS country
(
    id      INTEGER PRIMARY KEY AUTO_INCREMENT,
    version INTEGER     NOT NULL,
    code    INTEGER     NOT NULL,
    name    VARCHAR(70) NOT NULL
);

CREATE TABLE IF NOT EXISTS document_type
(
    id      INTEGER PRIMARY KEY AUTO_INCREMENT,
    version INTEGER     NOT NULL,
    code    INTEGER     NOT NULL,
    name    VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS organization
(
    id        INTEGER PRIMARY KEY AUTO_INCREMENT,
    version   INTEGER      NOT NULL,
    name      VARCHAR(50)  NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    inn       VARCHAR(12)  NOT NULL,
    is_active BOOLEAN,
    kpp       CHAR(9)      NOT NULL,
    address   VARCHAR(50)  NOT NULL,
    phone     VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS office
(
    id              INTEGER PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER     NOT NULL,
    organization_id INTEGER     NOT NULL,
    name            VARCHAR(50) NOT NULL,
    phone           VARCHAR(20),
    is_active       BOOLEAN,
    address         VARCHAR(50) NOT NULL,
    CONSTRAINT org_id FOREIGN KEY (organization_id) REFERENCES organization (id)
);


CREATE TABLE IF NOT EXISTS user
(
    id            INTEGER PRIMARY KEY AUTO_INCREMENT,
    version       INTEGER     NOT NULL,
    office_id     INTEGER     NOT NULL,
    first_name    VARCHAR(50) NOT NULL,
    second_name   VARCHAR(50),
    last_name     VARCHAR(50),
    middle_name   VARCHAR(50),
    position      VARCHAR(20) NOT NULL,
    is_identified BOOLEAN,
    phone         VARCHAR(20),
    country_id    INTEGER     NOT NULL,
    CONSTRAINT citizenship_code FOREIGN KEY (country_id) REFERENCES country (id),
    CONSTRAINT office_id FOREIGN KEY (office_id) REFERENCES office (id)
);


CREATE TABLE IF NOT EXISTS document
(
    id          INTEGER PRIMARY KEY,
    version     INTEGER     NOT NULL,
    doc_number  VARCHAR(30) NOT NULL,
    doc_date    DATE        NOT NULL,
    doc_type_id INTEGER     NOT NULL,
    CONSTRAINT doc_code FOREIGN KEY (doc_type_id) REFERENCES document_type (id),
    CONSTRAINT user_id FOREIGN KEY (id) REFERENCES user (id)
);