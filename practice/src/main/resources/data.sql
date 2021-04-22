INSERT INTO country (version, code, name) VALUES (0, 643, 'Российская федерация');
INSERT INTO country (version, code, name) VALUES (0, 392, 'Япония');

INSERT INTO document_type (version, code, name) VALUES (0, 21, 'Паспорт гражданина РФ');
INSERT INTO document_type (version, code, name) VALUES (0, 10, 'Паспорт иностранного гражданина');

INSERT INTO organization (version, name, full_name, inn, is_active, kpp, address, phone) VALUES (0, 'Успех', 'ОАО Успех', '0123456789', 'true', '999999999', 'Первомайская,50', '89999999999');
INSERT INTO organization (version, name, full_name, inn, is_active, kpp, address, phone) VALUES (0, 'Баланс', 'ЗАО Баланс', '9876543210', 'true', '777777777', 'Владимирская,12', '87777777777');

INSERT INTO office(version, organization_id, name, phone, is_active, address) VALUES (0, 1, 'Главный оффис', '71234567890', 'true', 'Костромская, 19');
INSERT INTO office(version, organization_id, name, phone, is_active, address) VALUES (0, 2, 'Главный оффис', '79876543210', 'false', 'Советская, 11');

INSERT INTO user(version, office_id, first_name, second_name, last_name, middle_name, position, is_identified, phone, country_id) VALUES (0, 2, 'Иванов', null, 'Иван', 'Иванович', 'Директор', 'true', '83333333333', 1);

INSERT INTO document(version, id, doc_number, doc_date, doc_type_id) VALUES (0, 1, '4500123456', '2005-02-02', 1);