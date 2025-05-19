INSERT INTO pearls.role (id, name)
VALUES (default, 'admin');
INSERT INTO pearls.role (id, name)
VALUES (default, 'customer');
INSERT INTO pearls.role (id, name)
VALUES (default, 'not active');
INSERT INTO pearls.role (id, name)
VALUES (default, 'banned');


INSERT INTO pearls."user" (id, role_id, username, password, email, status)
VALUES (default, 1, 'admin', '111', 'admin@gmail.com', 'A');
INSERT INTO pearls."user" (id, role_id, username, password, email, status)
VALUES (default, 2, 'testuser', '222', 'testuser@gmail.com', 'B');
INSERT INTO pearls."user" (id, role_id, username, password, email, status)
VALUES (default, 3, 'mitteaktiivne', '333', 'notactive@gmail.com', 'C');
INSERT INTO pearls."user" (id, role_id, username, password, email, status)
VALUES (default, 4, 'banned', '444', 'banneduser@gmail.com', 'D');


INSERT INTO pearls."county" (id, name)
VALUES (default, 'Harjumaa');
INSERT INTO pearls."county" (id, name)
VALUES (default, 'Hiiumaa');
INSERT INTO pearls."county" (id, name)
VALUES (default, 'Põlva');
INSERT INTO pearls."county" (id, name)
VALUES (default, 'Rapla');
INSERT INTO pearls."county" (id, name)
VALUES (default, 'Viljandi');
INSERT INTO pearls."county" (id, name)
VALUES (default, 'Võrumaa');


INSERT INTO pearls."game" (id, location_id, user_id, start_time, end_time, status, complete_date, points)
VALUES (default, 2, 1, '2005-05-07 12:15:10', '2005-05-07 14:15:10', 'A', '2025-05-17', 10);
INSERT INTO pearls."game" (id, location_id, user_id, start_time, end_time, status, complete_date, points)
VALUES (default, 3, 2, '2009-02-02 15:37:11', '2009-02-02 18:05:10', 'B', '2020-12-12', 5);
INSERT INTO pearls."game" (id, location_id, user_id, start_time, end_time, status, complete_date, points)
VALUES (default, 5, 3, '2015-09-12 01:01:12', '2015-09-12 04:08:12', 'C', '2001-05-05', 5);


INSERT INTO pearls.location (id, user_id, county_id, name, long, lat, status, teaser, extended_info, question, date_added)
VALUES (default, 1, 1, 'SomeName', 50.1623, 50.7977, 'A', 'teaser here', 'põhjalik info siia', 'mitu jalga on toolil', '2000-01-01');
INSERT INTO pearls.location (id, user_id, county_id, name, long, lat, status, teaser, extended_info, question, date_added)
VALUES (default, 2, 3, 'SomeOtherName', 47.5701, 48.0484, 'B', 'teaser here', 'põhjalik info siia_kaks', 'kanamuna või kana', '2015-05-05');
INSERT INTO pearls.location (id, user_id, county_id, name, long, lat, status, teaser, extended_info, question, date_added)
VALUES (default, 3, 5, 'SomeThirdName', 32.8431, 31.9871, 'C', 'teaser here', 'põhjalik info siia_kolm', 'mis on väiksem kui aatom', '2020-07-11');



