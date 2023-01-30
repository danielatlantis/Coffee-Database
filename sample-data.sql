-- For IDs: I use 1 digit for stores, 2 digits for coffees, 3 digits for customers,
-- 4 digits for sales, 5 digits for promos

INSERT INTO STORE VALUES ('BoutiqueCoffeeOakland', 1, 'sitting', 40.43, 79.95);
INSERT INTO STORE VALUES ('BoutiqueCoffeeShadyside', 2, 'sitting', 40.45, 79.93);
INSERT INTO STORE VALUES ('BoutiqueCoffeeStripDistrict', 3, 'kiosk', 40.45, 79.98);

-- Also I know nothing about coffee, so most of these are ripped straight from https://foundfamiliar.com/
-- If that's going to be a problem, I can revise them for a future step.
INSERT INTO COFFEE VALUES (10, 'Fey Step', 'Sweet, fruity, nutty, and super smooth!', 'Guatemala', 4, 18.50, 18.50, 18.50);
INSERT INTO COFFEE VALUES (11, 'Goodberry', 'Berry-forward, with hints of lavender and sangria.', 'Ethiopia', 10, 21.00, 21.00, 21.00);
INSERT INTO COFFEE VALUES (12, 'Initiative', 'Caramel, toffee, and plum; rich and decadent!', 'Colombia', 8, 17.00, 17.00, 17.00);
INSERT INTO COFFEE VALUES (13, 'Inspiration', 'Like dark chocolate and pecan brownies in a cup.', 'Brazil', 7, 17.00, 17.00, 17.00);
INSERT INTO COFFEE VALUES (14, 'Regular Gnoll', 'Hints of brown sugar and cherry; dark and potent.', 'Italy', 11, 17.00, 17.00, 17.00);
INSERT INTO COFFEE VALUES (15, 'Thieves Cant', 'Best had with food, this heart attack in a cup has hints of black cherry, almonds, and toffee.', 'Secret Blend', 12, 17.50, 17.50, 17.50);
INSERT INTO COFFEE VALUES (16, 'Hex', 'Chocolatey and acidic, with a hint of toasted almonds.', 'Honduras', 4, 18.50, 18.50, 18.50);
INSERT INTO COFFEE VALUES (17, 'Metamagic', 'Fruity, floral, and earthy; notes of citrus, jasmine, and spices.', 'Sumatra', 7, 17.00, 17.00, 17.00);
INSERT INTO COFFEE VALUES (18, 'Panache', 'A light and floral blend! Great iced to bring out oolong tea and dates.', 'Peru', 3, 19.50, 19.50, 19.50);
INSERT INTO COFFEE VALUES (19, 'Spare the Dying', 'Fruity, sweet, and rich, with hints of mango, raspberry, and spices.', 'Thailand', 10, 22.00, 22.00, 22.00);
INSERT INTO COFFEE VALUES (20, 'False Life', 'Mild and sweet, with notes of cocoa, graham cracker, and vanilla.', 'Brazil', 1, 16.00, 16.00, 16.00);
INSERT INTO COFFEE VALUES (21, 'Seeming', 'Decadent taste of butterscotch and molasses.', 'Colombia', 1, 17.50, 17.50, 17.50);

INSERT INTO CUSTOMER VALUES (100, 'Sophia', 'Mato', 'A', 18, 11, '412-200-9750', 'home', 'basic', 37.50);
INSERT INTO CUSTOMER VALUES (101, 'Saeed', 'Roshan', 'B', 3, 4, '412-202-0728', 'mobile', 'bronze', 40.50);
INSERT INTO CUSTOMER VALUES (102, 'Josh', 'Maksim', 'C', 12, 1, '412-205-6639', 'work', 'silver', 49.00);
INSERT INTO CUSTOMER VALUES (103, 'Zion', 'Ayaan', 'D', 11, 12, '412-208-1005', 'other', 'gold', 38.00);
INSERT INTO CUSTOMER VALUES (104, 'Fran', 'Ligeia', 'E', 25, 1, '412-213-2034', 'home', 'platinum', 91.75);
INSERT INTO CUSTOMER VALUES (105, 'Martin', 'Omran', 'F', 21, 16, '412-216-5980', 'mobile', 'diamond', 96.00);
INSERT INTO CUSTOMER VALUES (106, 'Courtney', 'Giannis', 'G', 4, 1, '412-223-5562', 'home', 'basic', 65.50);
INSERT INTO CUSTOMER VALUES (107, 'Theresa', 'Rohit', 'H', 6, 7, '412-225-6060', 'mobile', 'bronze', 45.50);
INSERT INTO CUSTOMER VALUES (108, 'Donnel', 'Nadire', 'I', 15, 12, '412-227-1711', 'work', 'silver', 36.00);
INSERT INTO CUSTOMER VALUES (109, 'Moira', 'Yakov', 'J', 14, 3, '412-229-9909', 'other', 'gold', 24.50);
INSERT INTO CUSTOMER VALUES (110, 'Marcella', 'Kaiser', 'A', 22, 7, '412-231-9358', 'home', 'platinum', 34.50);
INSERT INTO CUSTOMER VALUES (111, 'Ruben', 'Sasho', 'B', 24, 4, '412-234-5460', 'mobile', 'diamond', 82.00);
INSERT INTO CUSTOMER VALUES (112, 'Adrian', 'Safa', 'C', 20, 7, '412-236-6991', 'home', 'basic', 42.50);
INSERT INTO CUSTOMER VALUES (113, 'Pippa', 'Bell', 'D', 21, 1, '412-238-9277', 'mobile', 'bronze', 31.50);
INSERT INTO CUSTOMER VALUES (114, 'Parker', 'Benedict', 'E', 5, 5, '412-242-8537', 'work', 'silver', 36.00);
INSERT INTO CUSTOMER VALUES (115, 'Niles', 'Benedict', 'F', 19, 11, '412-244-7963', 'other', 'gold', 29.50);
INSERT INTO CUSTOMER VALUES (116, 'Cian', 'Bianche', 'G', 18, 3, '412-247-4083', 'home', 'platinum', 64.00);
INSERT INTO CUSTOMER VALUES (117, 'Isadora', 'Moore', 'H', 2, 7, '412-250-2979', 'mobile', 'diamond', 73.50);
INSERT INTO CUSTOMER VALUES (118, 'Fionn', 'Gale', 'I', 28, 10, '412-252-2892', 'home', 'basic', 22.50);
INSERT INTO CUSTOMER VALUES (119, 'Rhiannon', 'Holder', 'J', 12, 1, '412-254-1310', 'mobile', 'bronze', 27.50);

INSERT INTO SALE VALUES (1110, 100, 1, '2022-12-01 09:03:00', 10, 0.5, 0.5);
INSERT INTO SALE VALUES (1111, 101, 2, '2022-12-01 09:05:03', 11, 0.4, 0.6);
INSERT INTO SALE VALUES (1112, 102, 3, '2022-12-01 09:08:05', 12, 0.5, 0.5);
INSERT INTO SALE VALUES (1113, 103, 1, '2022-12-01 09:09:08', 13, 0.4, 0.6);
INSERT INTO SALE VALUES (1114, 104, 2, '2022-12-01 09:26:09', 14, 1.0, 0.0);
INSERT INTO SALE VALUES (1115, 105, 3, '2022-12-01 09:27:26', 15, 0.7, 0.3);
INSERT INTO SALE VALUES (1116, 106, 1, '2022-12-01 09:32:27', 16, 1.0, 0.0);
INSERT INTO SALE VALUES (1117, 107, 2, '2022-12-01 09:49:32', 17, 0.4, 0.6);
INSERT INTO SALE VALUES (1118, 108, 3, '2022-12-01 09:59:49', 18, 0.6, 0.4);
INSERT INTO SALE VALUES (1119, 109, 1, '2022-12-01 10:13:59', 19, 0.8, 0.2);
INSERT INTO SALE VALUES (1120, 110, 2, '2022-12-01 10:18:13', 20, 0.9, 0.1);
INSERT INTO SALE VALUES (1121, 111, 3, '2022-12-01 10:25:18', 21, 0.1, 0.9);
INSERT INTO SALE VALUES (1122, 112, 1, '2022-12-01 10:26:25', 10, 1.0, 0.0);
INSERT INTO SALE VALUES (1123, 113, 2, '2022-12-01 10:28:26', 11, 0.6, 0.4);
INSERT INTO SALE VALUES (1124, 114, 3, '2022-12-01 10:39:28', 12, 0.2, 0.8);
INSERT INTO SALE VALUES (1125, 115, 1, '2022-12-01 10:43:39', 13, 0.8, 0.2);
INSERT INTO SALE VALUES (1126, 116, 2, '2022-12-01 10:44:43', 14, 1.0, 0.0);
INSERT INTO SALE VALUES (1127, 117, 3, '2022-12-01 10:46:44', 15, 0.5, 0.5);
INSERT INTO SALE VALUES (1128, 118, 1, '2022-12-01 10:54:46', 16, 0.1, 0.9);
INSERT INTO SALE VALUES (1129, 119, 2, '2022-12-01 10:57:54', 17, 0.2, 0.8);
INSERT INTO SALE VALUES (1130, 100, 3, '2022-12-01 11:07:57', 18, 0.8, 0.2);
INSERT INTO SALE VALUES (1131, 101, 1, '2022-12-01 11:11:07', 19, 0.5, 0.5);
INSERT INTO SALE VALUES (1132, 102, 2, '2022-12-01 11:23:11', 20, 0.8, 0.2);
INSERT INTO SALE VALUES (1133, 103, 3, '2022-12-01 11:34:23', 21, 0.4, 0.6);
INSERT INTO SALE VALUES (1134, 104, 1, '2022-12-01 11:45:34', 10, 0.5, 0.5);
INSERT INTO SALE VALUES (1135, 105, 2, '2022-12-01 12:15:45', 11, 0.9, 0.1);
INSERT INTO SALE VALUES (1136, 106, 3, '2022-12-01 12:22:15', 12, 0.7, 0.3);
INSERT INTO SALE VALUES (1137, 107, 1, '2022-12-01 12:31:22', 13, 0.3, 0.7);
INSERT INTO SALE VALUES (1138, 108, 2, '2022-04-05 12:52:31', 14, 0.5, 0.5);
INSERT INTO SALE VALUES (1139, 109, 3, '2022-12-01 12:58:52', 15, 0.2, 0.8);
INSERT INTO SALE VALUES (1140, 110, 1, '2022-12-01 13:01:58', 16, 0.8, 0.2);
INSERT INTO SALE VALUES (1141, 111, 2, '2022-12-01 13:13:01', 17, 0.3, 0.7);
INSERT INTO SALE VALUES (1142, 112, 3, '2022-12-01 13:33:13', 18, 0.2, 0.8);
INSERT INTO SALE VALUES (1143, 113, 1, '2022-12-01 13:37:33', 19, 0.5, 0.5);
INSERT INTO SALE VALUES (1144, 114, 2, '2022-12-01 13:55:37', 20, 0.3, 0.7);
INSERT INTO SALE VALUES (1145, 115, 3, '2022-12-01 14:00:55', 21, 0.7, 0.3);
INSERT INTO SALE VALUES (1146, 116, 1, '2022-03-23 14:15:00', 10, 0.4, 0.6);
INSERT INTO SALE VALUES (1147, 117, 2, '2022-12-16 14:18:15', 11, 0.5, 0.5);
INSERT INTO SALE VALUES (1148, 118, 3, '2022-12-01 14:30:18', 12, 0.6, 0.4);
INSERT INTO SALE VALUES (1149, 119, 1, '2022-09-11 14:44:30', 13, 0.5, 0.5);
INSERT INTO SALE VALUES (1150, 100, 2, '2022-12-01 14:48:44', 14, 0.5, 0.5);
INSERT INTO SALE VALUES (1151, 101, 3, '2022-12-01 15:14:48', 15, 0.8, 0.2);
INSERT INTO SALE VALUES (1152, 102, 1, '2022-02-01 15:27:14', 16, 0.7, 0.3);
INSERT INTO SALE VALUES (1153, 103, 2, '2022-12-01 15:40:27', 17, 0.2, 0.8);
INSERT INTO SALE VALUES (1154, 104, 3, '2022-12-01 15:58:40', 18, 0.4, 0.6);
INSERT INTO SALE VALUES (1155, 105, 1, '2022-12-11 15:59:58', 19, 1.0, 0.0);
INSERT INTO SALE VALUES (1156, 106, 2, '2022-12-21 16:00:19', 20, 1.0, 0.0);
INSERT INTO SALE VALUES (1157, 107, 3, '2022-12-31 16:13:00', 21, 1.0, 0.0);
INSERT INTO SALE VALUES (1158, 108, 1, '2022-11-01 16:31:13', 10, 1.0, 0.0);
INSERT INTO SALE VALUES (1159, 109, 2, '2022-06-01 17:39:13', 11, 1.0, 0.0);
INSERT INTO SALE VALUES (1160, 100, 1, '2022-12-11 15:59:58', 11, 0.0, 1.0);
INSERT INTO SALE VALUES (1161, 100, 1, '2022-12-11 15:59:58', 11, 0.5, 0.5);

INSERT INTO PROMO VALUES (11110, 'Oakland Anniversary', '2023-09-01', '2023-09-02');
INSERT INTO PROMO VALUES (11111, 'Shadyside Anniversary Week', '2023-12-06', '2023-12-13');
INSERT INTO PROMO VALUES (11112, 'National Coffee Day', '2023-10-01', '2023-10-02');

INSERT INTO OFFERS VALUES (11111, 3);
INSERT INTO OFFERS VALUES (11110, 1);

INSERT INTO PROMOTES VALUES (11110, 20);