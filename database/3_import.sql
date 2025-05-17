INSERT INTO pearls.role (id, name) VALUES (default, 'admin');
INSERT INTO pearls.role (id, name) VALUES (default, 'customer');

INSERT INTO pearls."user" (id, role_id, username, password, status) VALUES (default, 1, 'admin', '123', 'A');
INSERT INTO pearls."user" (id, role_id, username, password, status) VALUES (default, 2, 'rain', '123', 'A');
INSERT INTO pearls."user" (id, role_id, username, password, status) VALUES (default, 2, 'mitteaktiivne', '123', 'D');


INSERT INTO pearls.location (id, name) VALUES (default, 'Tartu');
INSERT INTO pearls.location (id, name) VALUES (default, 'Tallinn');
INSERT INTO pearls.location (id, name) VALUES (default, 'Pärnu');


INSERT INTO pearls.location (id, city_id, name, number_of_atms, status) VALUES (default, 2, 'Sikupilli Prisma', 5, 'A');
INSERT INTO pearls.location (id, city_id, name, number_of_atms, status) VALUES (default, 2, 'Tondi Selver', 3, 'A');


INSERT INTO pearls.transaction_type (id, name) VALUES (default, 'cash in');
INSERT INTO pearls.transaction_type (id, name) VALUES (default, 'cash out');
INSERT INTO pearls.transaction_type (id, name) VALUES (default, 'payments');
INSERT INTO pearls.transaction_type (id, name) VALUES (default, 'Bitcoin');


INSERT INTO pearls.location_transaction_type (id, location_id, transaction_type_id) VALUES (default, 1, 1);
INSERT INTO pearls.location_transaction_type (id, location_id, transaction_type_id) VALUES (default, 1, 2);
INSERT INTO pearls.location_transaction_type (id, location_id, transaction_type_id) VALUES (default, 1, 3);
INSERT INTO pearls.location_transaction_type (id, location_id, transaction_type_id) VALUES (default, 2, 2);

