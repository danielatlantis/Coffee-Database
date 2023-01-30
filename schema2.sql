DROP TABLE IF  EXISTS CLOCK CASCADE;
DROP TABLE IF EXISTS STORE CASCADE;
DROP TABLE IF EXISTS COFFEE CASCADE;
DROP TABLE IF EXISTS LOYALTY CASCADE;
DROP TABLE IF EXISTS CUSTOMER CASCADE;
DROP TABLE IF EXISTS SALE CASCADE;
DROP TABLE IF EXISTS PROMO CASCADE;
DROP TABLE IF EXISTS PROMOTES CASCADE;
DROP TABLE IF EXISTS OFFERS CASCADE;
DROP DOMAIN IF EXISTS store_enum CASCADE;
DROP DOMAIN IF EXISTS phone_enum CASCADE;
DROP DOMAIN IF EXISTS loyalty_enum CASCADE;

CREATE DOMAIN store_enum varchar(7)
    CONSTRAINT store_enum
    CHECK (VALUE IN ('sitting', 'kiosk'));
CREATE DOMAIN phone_enum varchar(6)
    CONSTRAINT phone_enum
    CHECK (VALUE IN ('home', 'mobile', 'work', 'other'));
CREATE DOMAIN loyalty_enum varchar(10)
    CONSTRAINT loyalty_enum
    CHECK( VALUE IN ('basic', 'bronze', 'silver', 'gold', 'platinum', 'diamond'));

CREATE TABLE IF NOT EXISTS CLOCK(
    p_date  date,
    CONSTRAINT PK_CLOCK PRIMARY KEY (p_date)
);

CREATE TABLE IF NOT EXISTS STORE(
    name varchar(50) NOT NULL UNIQUE,
    est_num int,
    store_type store_enum,
    gps_lat float NOT NULL,
    gps_lon float NOT NULL,
    CONSTRAINT PK_STORE PRIMARY KEY (est_num)
);
CREATE TABLE IF NOT EXISTS COFFEE(
    coffee_id int,
    name varchar(50) NOT NULL,
    description varchar(250),
    country varchar(60),
    intensity int,
    price float NOT NULL,
    reward_pts float DEFAULT 0,
    redeem_pts float,
    CONSTRAINT PK_COFFEE PRIMARY KEY (coffee_id),
    CONSTRAINT CHECK_INTENSITY CHECK (intensity >= 0 AND intensity <= 12) INITIALLY IMMEDIATE,
    CONSTRAINT NON_NEG CHECK (price >= 0 AND reward_pts >= 0 AND redeem_pts >= 0) INITIALLY IMMEDIATE
);

CREATE TABLE IF NOT EXISTS LOYALTY(
    loyalty_level loyalty_enum,
    booster float,
    CONSTRAINT PK_LOYALTY PRIMARY KEY (loyalty_level)
);

CREATE TABLE IF NOT EXISTS CUSTOMER(
    customer_id int,
    first varchar(50) NOT NULL,
    last varchar(50) NOT NULL,
    middle char(1),
    birth_day char(2),
    birth_month char(3),
    phone_number varchar(16) NOT NULL,
    phone_type phone_enum,
    loyalty_level loyalty_enum,
    points_earned float DEFAULT 0,
    CONSTRAINT PK_CUSTOMER PRIMARY KEY (customer_id),
    CONSTRAINT FK_LOYALTY FOREIGN KEY (loyalty_level) REFERENCES LOYALTY(loyalty_level) DEFERRABLE
);
CREATE TABLE IF NOT EXISTS SALE(
    purchase_id int,
    customer_id int,
    est_num int,
    purchase_time timestamp NOT NULL,
    coffee_id int,
    purchase_portion float DEFAULT 1,
    redeem_portion float DEFAULT 0,
    CONSTRAINT PK_SALE PRIMARY KEY (purchase_id),
    CONSTRAINT FK_SALE_CUSTOMER FOREIGN KEY (customer_id) REFERENCES CUSTOMER(customer_id) DEFERRABLE,
    CONSTRAINT FK_SALE_STORE FOREIGN KEY (est_num) REFERENCES STORE(est_num) DEFERRABLE,
    CONSTRAINT FK_SALE_COFFEE FOREIGN KEY (coffee_id) REFERENCES COFFEE(coffee_id) DEFERRABLE
);

CREATE TABLE IF NOT EXISTS PROMO(
    promo_id int,
    name varchar(50) NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    CONSTRAINT PK_PROMO PRIMARY KEY (promo_id),
    CONSTRAINT DATE_CHECK CHECK (start_date < end_date)
);

CREATE TABLE IF NOT EXISTS PROMOTES(
    promo_id int,
    coffee_id int,
    CONSTRAINT PK_PROMOTES PRIMARY KEY (promo_id, coffee_id),
    CONSTRAINT FK_PROMOTES_PROMO FOREIGN KEY (promo_id) REFERENCES PROMO(promo_id) DEFERRABLE,
    CONSTRAINT FK_PROMOTES_COFFEE FOREIGN KEY (coffee_id) REFERENCES COFFEE(coffee_id) DEFERRABLE
);

CREATE TABLE IF NOT EXISTS OFFERS(
    promo_id int,
    est_num int,
    CONSTRAINT PK_OFFER PRIMARY KEY (promo_id, est_num),
    CONSTRAINT FK_OFFER_PROMO FOREIGN KEY (promo_id) REFERENCES PROMO(promo_id) DEFERRABLE,
    CONSTRAINT FK_OFFER_STORE FOREIGN KEY (est_num) REFERENCES STORE(est_num) DEFERRABLE
);
INSERT INTO LOYALTY VALUES ('basic', 0.25);
INSERT INTO LOYALTY VALUES ('bronze', 0.5);
INSERT INTO LOYALTY VALUES ('silver', 0.75);
INSERT INTO LOYALTY VALUES ('gold', 1.0);
INSERT INTO LOYALTY VALUES ('platinum', 1.25);
INSERT INTO LOYALTY VALUES ('diamond', 1.5);
INSERT INTO CLOCK VALUES ('2022-01-01');