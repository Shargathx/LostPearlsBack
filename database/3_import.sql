INSERT INTO pearls.role (id, name)
VALUES (default, 'admin');
INSERT INTO pearls.role (id, name)
VALUES (default, 'customer');


INSERT INTO pearls."user" (id, role_id, username, password, email, status)
VALUES (default, 1, 'admin', '123', 'admin@admin.com', 'A');
INSERT INTO pearls."user" (id, role_id, username, password, email, status)
VALUES (default, 2, 'martin', '123', 'martin@martin.com', 'A');
INSERT INTO pearls."user" (id, role_id, username, password, email, status)
VALUES (default, 2, 'mikk', '123', 'mikk@mikk.com', 'D');
INSERT INTO pearls."user" (id, role_id, username, password, email, status)
VALUES (default, 2, '', '', 'mikk@mikk.com', 'D');


INSERT INTO pearls.county (id, name, longField, latField, zoom_level)
VALUES (default, 'Harjumaa', 24.742995401694774, 59.413590862727105, 9);
INSERT INTO pearls.county (id, name, longField, latField, zoom_level)
VALUES (default, 'Hiiumaa', 22.6215092879, 58.934989263, 10);
INSERT INTO pearls.county (id, name, longField, latField, zoom_level)
VALUES (default, 'Tartu', 26.725891046226, 58.370620775, 9);
INSERT INTO pearls.county (id, name, longField, latField, zoom_level)
VALUES (default, 'Rapla', 24.800537042319, 59.009625071, 10);
INSERT INTO pearls.county (id, name, longField, latField, zoom_level)
VALUES (default, 'Viljandi', 25.581115689, 58.381804461, 10);
INSERT INTO pearls.county (id, name, longField, latField, zoom_level)
VALUES (default, 'Võrumaa', 27.01167295686901, 57.83890342754204, 11);



INSERT INTO pearls.location (id, user_id, county_id, name, longfield, latfield, zoom_level, status, teaser,
                             extended_info, question, answer, date_added)
VALUES (1, 1, 1, 'SomeName', 24.807961900000000, 59.4424832000000000, 0, 'A', 'teaser here', 'põhjalik info siia',
        'mitu jalga on toolil', '4', '2000-01-01');
INSERT INTO pearls.location (id, user_id, county_id, name, longfield, latfield, zoom_level, status, teaser,
                             extended_info, question, answer, date_added)
VALUES (2, 2, 3, 'SomeOtherName', 47.570100000000000, 48.048400000000000, 0, 'B', 'teaser here',
        'põhjalik info siia_kaks', 'kanamuna või kana', 'kana', '2015-05-05');
INSERT INTO pearls.location (id, user_id, county_id, name, longfield, latfield, zoom_level, status, teaser,
                             extended_info, question, answer, date_added)
VALUES (3, 3, 5, 'SomeThirdName', 32.843100000000000, 31.987100000000000, 0, 'C', 'teaser here',
        'põhjalik info siia_kolm', 'mis on väiksem kui aatom', 'ma ei tea', '2020-07-11');
INSERT INTO pearls.location (id, user_id, county_id, name, longfield, latfield, zoom_level, status, teaser,
                             extended_info, question, answer, date_added)
VALUES (4, 1, 3, 'SomeThirdName', 32.843100000000000, 31.987100000000000, 0, 'A', 'teaser here',
        'põhjalik info siia_kolm', 'mis on väiksem kui aatom', 'ma ei tea', '2020-07-11');
INSERT INTO pearls.location (id, user_id, county_id, name, longfield, latfield, zoom_level, status, teaser,
                             extended_info, question, answer, date_added)
VALUES (5, 1, 4, 'SomeThirdName', 32.843100000000000, 31.987100000000000, 0, 'A', 'teaser here',
        'põhjalik info siia_kolm', 'mis on väiksem kui aatom', 'ma ei tea', '2020-07-11');
INSERT INTO pearls.location (id, user_id, county_id, name, longfield, latfield, zoom_level, status, teaser,
                             extended_info, question, answer, date_added)
VALUES (6, 1, 6, 'SomeThirdName', 32.843100000000000, 31.987100000000000, 0, 'A', 'teaser here',
        'põhjalik info siia_kolm', 'mis on väiksem kui aatom', 'ma ei tea', '2020-07-11');
INSERT INTO pearls.location (id, user_id, county_id, name, longfield, latfield, zoom_level, status, teaser,
                             extended_info, question, answer, date_added)
VALUES (7, 1, 2, 'SomeThirdName', 32.843100000000000, 31.987100000000000, 0, 'A', 'teaser here',
        'põhjalik info siia_kolm', 'mis on väiksem kui aatom', 'ma ei tea', '2020-07-11');
INSERT INTO pearls.location (id, user_id, county_id, name, longfield, latfield, zoom_level, status, teaser,
                             extended_info, question, answer, date_added)
VALUES (8, 1, 1, 'SomeThirdName', 32.843100000000000, 31.987100000000000, 0, 'A', 'teaser here',
        'põhjalik info siia_kolm', 'mis on väiksem kui aatom', 'ma ei tea', '2020-07-11');
INSERT INTO pearls.location (id, user_id, county_id, name, longfield, latfield, zoom_level, status, teaser,
                             extended_info, question, answer, date_added)
VALUES (9, 1, 1, 'SomeThirdName', 32.843100000000000, 31.987100000000000, 0, 'A', 'teaser here',
        'põhjalik info siia_kolm', 'mis on väiksem kui aatom', 'ma ei tea', '2020-07-11');



INSERT INTO pearls.game (id, location_id, user_id, start_time, end_time, status, complete_date, points)
VALUES (default, 1, 1, null, null, 'GA', null, 0);
INSERT INTO pearls.game (id, location_id, user_id, start_time, end_time, status, complete_date, points)
VALUES (default, 1, 1, '2005-05-07 12:15:10.000000', null, 'GS', null, 0);
INSERT INTO pearls.game (id, location_id, user_id, start_time, end_time, status, complete_date, points)
VALUES (default, 1, 1, '2015-09-12 01:01:12.000000', '2015-09-12 04:08:12.000000', 'GC', '2001-05-05', 5);
INSERT INTO pearls.game (id, location_id, user_id, start_time, end_time, status, complete_date, points)
VALUES (default, 1, 1, '2015-09-12 01:01:12.000000', '2015-09-12 04:08:12.000000', 'GC', '2001-05-05', 5);
INSERT INTO pearls.game (id, location_id, user_id, start_time, end_time, status, complete_date, points)
VALUES (default, 1, 1, '2015-09-12 01:01:12.000000', '2015-09-12 04:08:12.000000', 'GC', '2001-05-05', 5);
INSERT INTO pearls.game (id, location_id, user_id, start_time, end_time, status, complete_date, points)
VALUES (default, 1, 1, '2015-09-12 01:01:12.000000', '2015-09-12 04:08:12.000000', 'GC', '2001-05-05', 5);
INSERT INTO pearls.game (id, location_id, user_id, start_time, end_time, status, complete_date, points)
VALUES (default, 1, 1, '2015-09-12 01:01:12.000000', '2015-09-12 04:08:12.000000', 'GC', '2001-05-05', 5);
INSERT INTO pearls.game (id, location_id, user_id, start_time, end_time, status, complete_date, points)
VALUES (default, 1, 1, '2015-09-12 01:01:12.000000', '2015-09-12 04:08:12.000000', 'GC', '2001-05-05', 5);
INSERT INTO pearls.game (id, location_id, user_id, start_time, end_time, status, complete_date, points)
VALUES (default, 1, 1, '2015-09-12 01:01:12.000000', '2015-09-12 04:08:12.000000', 'GC', '2001-05-05', 5);
INSERT INTO pearls.game (id, location_id, user_id, start_time, end_time, status, complete_date, points)
VALUES (default, 1, 1, '2015-09-12 01:01:12.000000', '2015-09-12 04:08:12.000000', 'GC', '2001-05-05', 5);
INSERT INTO pearls.game (id, location_id, user_id, start_time, end_time, status, complete_date, points)
VALUES (default, 1, 1, '2015-09-12 01:01:12.000000', '2015-09-12 04:08:12.000000', 'GC', '2001-05-05', 5);

INSERT INTO pearls.hint (id, location_id, hint)
VALUES (default, 1, 'Tekst et anda hint');
INSERT INTO pearls.hint (id, location_id, hint)
VALUES (default, 2, 'Tekst et anda hint');
INSERT INTO pearls.hint (id, location_id, hint)
VALUES (default, 3, 'Tekst et anda hint');
