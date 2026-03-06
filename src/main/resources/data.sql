-- USERS

INSERT INTO users (id, email, name, phone, password, tax_id, created_at)
VALUES 
('11111111-1111-1111-1111-111111111111','user1@mail.com','user1','5551234567','encryptedpass1','AARR990101XXX','2026-01-01 10:00:00'),

('22222222-2222-2222-2222-222222222222','dev@mail.com','developerUser','5559876543','encryptedpass2','BBBB990101YYY','2026-01-01 11:00:00'),

('33333333-3333-3333-3333-333333333333','admin@test.com','admin','5215512345678','encryptedpass3','CCCC990101ZZZ','2026-01-01 12:00:00');

-- ADDRESSES

INSERT INTO address (id, name, street, country_code, user_id)
VALUES 
(1,'workaddress','street No. 1','UK','11111111-1111-1111-1111-111111111111'),

(2,'homeaddress','street No. 2','AU','11111111-1111-1111-1111-111111111111'),

(3,'office','street No. 3','US','22222222-2222-2222-2222-222222222222'),

(4,'main','street No. 4','CA','33333333-3333-3333-3333-333333333333'),

(5,'vacation','street No. 5','ES','33333333-3333-3333-3333-333333333333');