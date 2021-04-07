INSERT INTO country (code, name) VALUES (643,'Российская федерация');
INSERT INTO country (code, name) VALUES (392,'Япония');

INSERT INTO document_type (code, name) VALUES (21,'Паспорт гражданина РФ');
INSERT INTO document_type (code, name) VALUES (10,'Паспорт иностранного гражданина');

INSERT INTO organization (name, full_name,inn,is_active,kpp,address,phone) VALUES ('Успех','ОАО Успех','0123456789','true','999999999','Первомайская,50','89999999999');
INSERT INTO organization (name, full_name,inn,is_active,kpp,address,phone) VALUES ('Баланс','ЗАО Баланс','9876543210','true','777777777','Владимирская,12','87777777777');

INSERT INTO office(organization_id,name,phone,is_active,address) VALUES (1,'Главный оффис','71234567890','true','Костромская, 19');
INSERT INTO office(organization_id,name,phone,is_active,address) VALUES (2,'Главный оффис','79876543210','false','Советская, 11');

INSERT INTO user(office_id,first_name,second_name,last_name,middle_name,position,is_identified,phone,country_id) VALUES (2,'Иванов',null,'Иван', 'Иванович','Директор','true','83333333333',1);

INSERT INTO document(id,doc_number,doc_date,doc_type_id) VALUES (1,'4500123456','2005-02-02',1);