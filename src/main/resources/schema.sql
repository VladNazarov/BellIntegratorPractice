CREATE TABLE IF NOT EXISTS country
(
    id      INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    version INTEGER     NOT NULL COMMENT 'Служебное поле Hibernate',
    code    INTEGER     NOT NULL COMMENT 'Код страны',
    name    VARCHAR(70) NOT NULL COMMENT 'Название страны'
);
CREATE UNIQUE INDEX country_id ON country(id);
COMMENT ON TABLE country IS 'Страна';

CREATE TABLE IF NOT EXISTS document_type
(
    id      INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version INTEGER     NOT NULL COMMENT 'Служебное поле Hibernate',
    code    INTEGER     NOT NULL COMMENT 'Код документа',
    name    VARCHAR(50) NOT NULL COMMENT 'Название документа'
);
CREATE UNIQUE INDEX document_type_id ON document_Type(id);
COMMENT ON TABLE document_type IS 'Тип документа';

CREATE TABLE IF NOT EXISTS organization
(
    id        INTEGER               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version   INTEGER      NOT NULL COMMENT 'Служебное поле Hibernate',
    name      VARCHAR(50)  NOT NULL COMMENT 'Название организации',
    full_name VARCHAR(100) NOT NULL COMMENT 'Полное название организации',
    inn       VARCHAR(12)  NOT NULL COMMENT 'ИНН организации',
    is_active BOOLEAN               COMMENT 'Флаг активности организации',
    kpp       CHAR(9)      NOT NULL COMMENT 'КПП организации',
    address   VARCHAR(50)  NOT NULL COMMENT 'Адрес организации',
    phone     VARCHAR(20)           COMMENT 'Телефонный номер организации'
);
CREATE UNIQUE INDEX organization_id   ON organization(id);
CREATE        INDEX organization_name ON organization(name);

COMMENT ON TABLE organization IS 'Организация';

CREATE TABLE IF NOT EXISTS office
(
    id              INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER     NOT NULL COMMENT 'Служебное поле Hibernate',
    organization_id INTEGER     NOT NULL COMMENT 'ID организации, к которой относится офис',
    name            VARCHAR(50) NOT NULL COMMENT 'Название офиса',
    phone           VARCHAR(20)          COMMENT 'Телефонный номер офиса',
    is_active       BOOLEAN              COMMENT 'Флаг активности офиса',
    address         VARCHAR(50) NOT NULL COMMENT 'Адрес офиса',
    CONSTRAINT org_id FOREIGN KEY (organization_id) REFERENCES organization (id)
);
CREATE UNIQUE INDEX office_id     ON office(id);
CREATE INDEX        office_org_id ON office(organization_id);
CREATE INDEX        office_name   ON office(name);

COMMENT ON TABLE office IS 'Офис';

CREATE TABLE IF NOT EXISTS user
(
    id            INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version       INTEGER     NOT NULL COMMENT 'Служебное поле Hibernate',
    office_id     INTEGER     NOT NULL COMMENT 'ID офиса, к которому прикреплён пользователь',
    first_name    VARCHAR(50) NOT NULL COMMENT 'Имя пользователя',
    second_name   VARCHAR(50)          COMMENT 'Второе имя пользователя',
    last_name     VARCHAR(50)          COMMENT 'Фамилия пользователя',
    middle_name   VARCHAR(50)          COMMENT 'Отчество пользователя',
    position      VARCHAR(20) NOT NULL COMMENT 'Должность пользователя',
    is_identified BOOLEAN              COMMENT 'Флаг идентификации пользователя',
    phone         VARCHAR(20)          COMMENT 'Телефонный номер пользователя',
    country_id    INTEGER              COMMENT 'ID страны - гражданства пользователя',
    CONSTRAINT citizenship_code FOREIGN KEY (country_id) REFERENCES country (id),
    CONSTRAINT office_id FOREIGN KEY (office_id) REFERENCES office (id)
);
CREATE UNIQUE INDEX user_id         ON user(id);
CREATE INDEX        user_office_id  ON user(office_id);
CREATE INDEX        user_first_name ON user(first_name);

COMMENT ON TABLE user IS 'Пользователь';

CREATE TABLE IF NOT EXISTS document
(
    id          INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY,
    version     INTEGER     NOT NULL COMMENT 'Служебное поле Hibernate',
    doc_number  VARCHAR(30)          COMMENT 'Номер документа',
    doc_date    DATE                 COMMENT 'Дата получения документа',
    doc_type_id INTEGER              COMMENT 'ID типа документа',
    CONSTRAINT doc_code FOREIGN KEY (doc_type_id) REFERENCES document_type (id),
    CONSTRAINT user_id FOREIGN KEY (id) REFERENCES user (id)
);
CREATE INDEX document_doc_type_id ON document(doc_type_id);
COMMENT ON TABLE document IS 'Документ';