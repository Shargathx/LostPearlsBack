INSERT INTO pearls.role (id, name) VALUES (default, 'admin');
INSERT INTO pearls.role (id, name) VALUES (default, 'customer');


INSERT INTO pearls."user" (id, role_id, username, password, email, status) VALUES (default, 1, 'admin', '123', 'admin@admin.com', 'A');
INSERT INTO pearls."user" (id, role_id, username, password, email, status) VALUES (default, 2, 'martin', '123', 'martin@martin.com', 'A');
INSERT INTO pearls."user" (id, role_id, username, password, email, status) VALUES (default, 2, 'mikk', '123', 'mikk@mikk.com', 'D');
INSERT INTO pearls."user" (id, role_id, username, password, email, status) VALUES (default, 2, '', '', 'mikk@mikk.com', 'D');


INSERT INTO pearls.county (id, name)
VALUES (default, 'Harjumaa');
INSERT INTO pearls.county (id, name)
VALUES (default, 'Hiiumaa');
INSERT INTO pearls.county (id, name)
VALUES (default, 'Põlva');
INSERT INTO pearls.county (id, name)
VALUES (default, 'Rapla');
INSERT INTO pearls.county (id, name)
VALUES (default, 'Viljandi');
INSERT INTO pearls.county (id, name)
VALUES (default, 'Võrumaa');



INSERT INTO pearls.location (id, user_id, county_id, name, long, lat, status, teaser, extended_info, question, answer, date_added)
VALUES (default, 1, 1, 'SomeName', 50.1623, 50.7977, 'A', 'teaser here', 'põhjalik info siia', 'mitu jalga on toolil', '4', '2000-01-01');
INSERT INTO pearls.location (id, user_id, county_id, name, long, lat, status, teaser, extended_info, question, answer, date_added)
VALUES (default, 2, 3, 'SomeOtherName', 47.5701, 48.0484, 'B', 'teaser here', 'põhjalik info siia_kaks', 'kanamuna või kana', 'kana', '2015-05-05');
INSERT INTO pearls.location (id, user_id, county_id, name, long, lat, status, teaser, extended_info, question, answer, date_added)
VALUES (default, 3, 5, 'SomeThirdName', 32.8431, 31.9871, 'C', 'teaser here', 'põhjalik info siia_kolm', 'mis on väiksem kui aatom', 'ma ei tea', '2020-07-11');


INSERT INTO pearls.game (id, location_id, user_id, start_time, end_time, status, complete_date, points)
VALUES (default, 1, 1, '2005-05-07 12:15:10', null, 'N', null, 0);
INSERT INTO pearls.game (id, location_id, user_id, start_time, end_time, status, complete_date, points)
VALUES (default, 2, 2, '2009-02-02 15:37:11', '2009-02-02 18:05:10', 'B', '2020-12-12', 5);
INSERT INTO pearls.game (id, location_id, user_id, start_time, end_time, status, complete_date, points)
VALUES (default, 3, 3, '2015-09-12 01:01:12', '2015-09-12 04:08:12', 'C', '2001-05-05', 5);


INSERT INTO pearls.hint (id, location_id, hint) VALUES (default, 1, 'Tekst et anda hint');
INSERT INTO pearls.hint (id, location_id, hint) VALUES (default, 2, 'Tekst et anda hint');
INSERT INTO pearls.hint (id, location_id, hint) VALUES (default, 3, 'Tekst et anda hint');
