CREATE OR REPLACE FUNCTION add_points()
RETURNS trigger AS
    $$ DECLARE
        new_points float = 0.0;
    BEGIN
        SELECT COFFEE.reward_pts FROM COFFEE WHERE COFFEE.coffee_id = NEW.coffee_id into new_points;

        UPDATE customer
        SET points_earned = points_earned + new_points
        WHERE CUSTOMER.customer_id = NEW.customer_id;

        RETURN NEW;
    end;
    $$ language plpgsql;

CREATE OR REPLACE TRIGGER sale_points_earned_trig
    AFTER INSERT
    ON sale
    FOR EACH ROW
    WHEN (new.redeem_portion = 0)
    EXECUTE FUNCTION add_points();

CREATE OR REPLACE FUNCTION check_customer_points()
RETURNS TRIGGER AS
    $$ DECLARE
        redeem_amount float;
        total_price float;
    BEGIN
        SELECT redeem_pts FROM COFFEE WHERE coffee_id = NEW.coffee_id into total_price;
        redeem_amount = total_price * NEW.redeem_portion;
        IF ((SELECT points_earned FROM CUSTOMER WHERE customer_id = NEW.customer_id) < redeem_amount) THEN
            RAISE NOTICE 'customer does not have enough points';
            RETURN NULL;
        ELSE
            RAISE NOTICE 'Customer has enough points';
            UPDATE customer
            SET points_earned = points_earned - redeem_amount
            WHERE customer_id = NEW.customer_id;

            RETURN NEW;
        end if;
    end;
    $$ language plpgsql;

CREATE OR REPLACE TRIGGER sale_check_trig
    BEFORE INSERT
    ON sale
    FOR EACH ROW
    when ( new.redeem_portion = 1 )
    EXECUTE FUNCTION check_customer_points();


CREATE OR REPLACE FUNCTION check_promo_end()
RETURNS TRIGGER AS
    $$ DECLARE
        promotion RECORD;
    BEGIN
        FOR promotion IN SELECT promo_id FROM PROMO WHERE end_date < NEW.p_date
        LOOP
            DELETE FROM PROMO WHERE promo_id = promotion.promo_id;
        end loop;

        RETURN NEW;
    end;
    $$ language plpgsql;

CREATE OR REPLACE TRIGGER promo_end_trigger
    BEFORE UPDATE
    ON clock
    FOR EACH ROW
    EXECUTE FUNCTION check_promo_end();

DROP FUNCTION IF EXISTS new_store_id();
DROP FUNCTION IF EXISTS new_store();
DROP FUNCTION IF EXISTS new_coffee_id();
DROP FUNCTION IF EXISTS new_coffee();
DROP FUNCTION IF EXISTS new_promo_id();
DROP FUNCTION IF EXISTS new_promo();
DROP FUNCTION IF EXISTS new_offer();
DROP FUNCTION IF EXISTS check_offers();
DROP FUNCTION IF EXISTS new_loyalty();
DROP FUNCTION IF EXISTS new_customer_id();
DROP FUNCTION IF EXISTS new_customer();
DROP FUNCTION IF EXISTS customer_points();
DROP FUNCTION IF EXISTS list_coffees();
DROP FUNCTION IF EXISTS list_stores();
DROP FUNCTION IF EXISTS list_stores(id int);
DROP FUNCTION IF EXISTS find_store(gps_lat float, gps_lon float);
DROP FUNCTION IF EXISTS find_store(gps_lat float, gps_lon float, id int);
DROP FUNCTION IF EXISTS new_purchase();

-- TASK 1
CREATE OR REPLACE FUNCTION new_store_id()
RETURNS INTEGER
AS $$
DECLARE
    original_id int = 1000;
    empty  decimal(10,0);
    new_id decimal(10,0);
BEGIN
    select Count(*) from store into empty;
    if empty > 0 then
        select est_num FROM store WHERE est_num=(SELECT max(est_num) FROM store) into new_id;
        return new_id + 1;
    else
        return original_id;
    end if;
end;
$$ language plpgsql;
-- TASK 1
CREATE OR REPLACE FUNCTION new_store(name varchar(50), store_type store_enum, gps_lat float, gps_lon float)
RETURNS INTEGER
AS $$
DECLARE
    new_id decimal(10,0);
BEGIN
    select new_store_id() into new_id;
    insert into STORE
    values(name, new_id, store_type, gps_lat, gps_lon);
    return new_id;
end;
$$ language plpgsql;
-- TASK 2
CREATE OR REPLACE FUNCTION new_coffee_id()
RETURNS INTEGER
AS $$
DECLARE
    original_id int = 100;
    empty  decimal(10,0);
    new_id decimal(10,0);
BEGIN
    select Count(*) from coffee into empty;
    if empty > 0 then
        select coffee_id FROM coffee WHERE coffee_id=(SELECT max(coffee_id) FROM coffee) into new_id;
        return new_id + 1;
    else
        return original_id;
    end if;
end;
$$ language plpgsql;
-- TASK 2
CREATE OR REPLACE FUNCTION new_coffee(name varchar(50), description varchar(250), country varchar(60), intensity int,
price float, reward_pts float, redeem_pts float)
RETURNS INTEGER
AS $$
DECLARE
    new_id decimal(5,0);
BEGIN
    select new_coffee_id() into new_id;
    insert into coffee
    values(new_id, name, description, country, intensity, price, reward_pts, redeem_pts);
    return new_id;
end;
$$ language plpgsql;
-- TASK 3
CREATE OR REPLACE FUNCTION new_promo_id()
RETURNS INTEGER
AS $$
DECLARE
    original_id int = 100;
    empty  decimal(10,0);
    new_id decimal(10,0);
BEGIN
    select Count(*) from promo into empty;
    if empty > 0 then
        select promo_id FROM promo WHERE promo_id=(SELECT max(promo_id) FROM promo) into new_id;
        return new_id + 1;
    else
        return original_id;
    end if;
end;
$$ language plpgsql;
-- TASK 3
CREATE OR REPLACE FUNCTION new_promo(name varchar(50), start_date date, end_date date, coffee_id int)
RETURNS INTEGER
AS $$
DECLARE
    new_id decimal(10,0);
BEGIN
    select new_promo_id() into new_id;
    insert into promo
    values(new_id, name, start_date, end_date);

    insert into promotes
    values(new_id, coffee_id);

    return new_id;
end;
$$ language plpgsql;
-- TASK 4
CREATE OR REPLACE FUNCTION new_offer(promo_id int, store_id int)
RETURNS INTEGER
AS $$

BEGIN
    SET CONSTRAINTS ALL DEFERRED;
    insert into offers
    values(promo_id, store_id);

    return store_id;
end;
$$ language plpgsql;
-- TASK 5
-- List all stores that offer a promotion
CREATE OR REPLACE FUNCTION list_stores()
RETURNS TABLE(name varchar(50))
AS $$
BEGIN
    RETURN QUERY select distinct STORE.name from STORE NATURAL JOIN OFFERS;
END;
$$ language plpgsql;

-- TASK 5
-- List all the stores that offer a specific promotion
CREATE OR REPLACE FUNCTION list_stores(id int)
RETURNS TABLE(name varchar(50))
AS $$
BEGIN
    RETURN QUERY select distinct STORE.name
                 from STORE NATURAL JOIN OFFERS NATURAL JOIN PROMOTES as info
                 where info.coffee_id = id;
END;
$$ language plpgsql;

-- TASK 6
CREATE OR REPLACE FUNCTION check_offers(store_id int)
RETURNS TABLE(
    promo_id int,
    name varchar(50),
    start_date date,
    end_date date
    )
AS $$
BEGIN
    return QUERY (select promo.promo_id, promo.name, promo.start_date, promo.end_date promo
                  from offers NATURAL JOIN promo where est_num = store_id);
end;
$$ language plpgsql;
-- TASK 6
CREATE OR REPLACE FUNCTION check_offers(store_id int, store_coffee int)
RETURNS TABLE(
    promo_id int,
    name varchar(50),
    start_date date,
    end_date date
    )
AS $$
BEGIN

    return QUERY (SELECT s.promo_id, s.name, s.start_date, s.end_date FROM
(select offers.est_num, promo.promo_id, promo.name, promo.start_date, promo.end_date
                  from offers NATURAL JOIN promo where est_num = 1000) s NATURAL JOIN
(select promotes.promo_id, coffee.coffee_id from promotes NATURAL JOIN coffee where coffee_id = 100) c);
end;
$$ language plpgsql;

-- Compute the distance between two points
CREATE OR REPLACE FUNCTION euclidean_dist(p_lat float, p_lon float, q_lat float, q_lon float)
RETURNS float
AS $$
DECLARE
    result float;
BEGIN
    SELECT sqrt(power(q_lat - p_lat, 2)+power(q_lon - p_lon, 2)) into result;
    return result;
end;
$$ language plpgsql;
-- TASK 7
-- List the closest store(s)
CREATE OR REPLACE FUNCTION find_store(gps_lat float, gps_lon float)
RETURNS TABLE(name varchar(50), est_num int, distance float)
AS $$
DECLARE
        cur_dist float = 0.0;
        store_dist RECORD;
BEGIN
    DROP TABLE IF EXISTS dists;
    CREATE TEMPORARY TABLE dists(
        name varchar(50),
        est_num int,
        distance float
    );
    FOR store_dist IN SELECT * FROM STORE
    LOOP
        cur_dist = euclidean_dist(gps_lat, gps_lon, store_dist.gps_lat, store_dist.gps_lon);
        insert into dists values (store_dist.name, store_dist.est_num, cur_dist);
    end loop;
    return query select * from dists where dists.distance in (select min(dists.distance) from dists);
end;
$$ language plpgsql;
-- TASK 7
-- List the closest store(s) that offers promotion with id
CREATE OR REPLACE FUNCTION find_store(gps_lat float, gps_lon float, id int)
RETURNS TABLE(name varchar(50), est_num int, distance float)
AS $$
DECLARE
        cur_dist float = 0.0;
        store_dist RECORD;
BEGIN
    DROP TABLE IF EXISTS dists;
    CREATE TEMPORARY TABLE dists(
        name varchar(50),
        est_num int,
        distance float
    );
    FOR store_dist IN SELECT * FROM STORE NATURAL JOIN OFFERS WHERE OFFERS.promo_id = id
    LOOP
        cur_dist = euclidean_dist(gps_lat, gps_lon, store_dist.gps_lat, store_dist.gps_lon);
        insert into dists values (store_dist.name, store_dist.est_num, cur_dist);
    end loop;
    return query select * from dists where dists.distance in (select min(dists.distance) from dists);
end;
$$ language plpgsql;
-- TASK 8
CREATE OR REPLACE FUNCTION new_loyalty(new_level loyalty_enum, new_booster float)
RETURNS loyalty_enum
AS $$
DECLARE
    empty decimal(10,0);
BEGIN
    select COUNT(*) from (select loyalty_level from loyalty where loyalty_level = 'bronze') l1 into empty;
    if empty = 0 then
        insert into loyalty values(new_level, new_booster);
    else
        update loyalty set booster = new_booster where loyalty_level = new_level;
    end if;
    return new_level;
end;
$$ language plpgsql;
-- TASK 9
CREATE OR REPLACE FUNCTION new_customer_id()
RETURNS INTEGER
AS $$
DECLARE
    original_id int = 1000;
    empty  decimal(10,0);
    new_id decimal(10,0);
BEGIN
    select Count(*) from customer into empty;
    if empty > 0 then
        select customer_id FROM customer WHERE customer_id=(SELECT max(customer_id) FROM customer) into new_id;
        return new_id + 1;
    else
        return original_id;
    end if;
end;
$$ language plpgsql;
-- TASK 9
CREATE OR REPLACE FUNCTION new_customer(first varchar(50), last varchar(50), middle char(1), dob char(2), mob char(3), phone_num varchar(16), type phone_enum)
RETURNS FLOAT
AS $$
DECLARE
    new_id decimal(10,0);
BEGIN
    select new_customer_id() into new_id;
    insert into customer
    values(new_id, first, last, middle, dob, mob, phone_num, type, 'basic', 0);

    return new_id;
end;
$$ language plpgsql;
-- TASK 10
CREATE OR REPLACE FUNCTION customer_points(cust_id int)
RETURNS FLOAT
AS $$
BEGIN
    return (select points_earned from customer where customer_id = cust_id);
end;
$$ language plpgsql;

-- TASK 11
CREATE OR REPLACE FUNCTION list_customers()
RETURNS TABLE(customer int, loyalty_level loyalty_enum, points_earned float)
AS $$
BEGIN
    return QUERY SELECT customer_id, CUSTOMER.loyalty_level, CUSTOMER.points_earned
                 FROM customer NATURAL JOIN LOYALTY
                 ORDER BY LOYALTY.booster DESC, points_earned DESC;
end;
$$ language plpgsql;

-- TASK 12
CREATE OR REPLACE FUNCTION new_purchase_id()
RETURNS INTEGER
AS $$
DECLARE
    original_id int = 100;
    empty  decimal(10,0);
    new_id decimal(10,0);
BEGIN
    select Count(*) from sale into empty;
    if empty > 0 then
        select purchase_id FROM sale WHERE purchase_id=(SELECT max(purchase_id) FROM sale) into new_id;
        return new_id+1;
    else
        return original_id;
    end if;
end;
$$ language plpgsql;
-- TASK 12
CREATE OR REPLACE FUNCTION new_purchase(customer int, store_id int, time_of_sale timestamp, coffee_list int[], quantity int[], redeem_quantity int[])
RETURNS INTEGER
AS $$
DECLARE
    new_id decimal(10,0);
BEGIN
    SET CONSTRAINTS ALL DEFERRED;
    -- For every different coffee
    FOR i IN 1..array_upper(quantity, 1)
    LOOP
        -- Create a new purchase ID and add that coffee quantity[i] times
        FOR j IN 1..quantity[i]
        LOOP
            select new_purchase_id() into new_id;
            insert into SALE values (new_id, customer, store_id, time_of_sale, coffee_list[i], 1-redeem_quantity[i], redeem_quantity[i]);
        end loop;
    end loop;
    IF new_id IN (SELECT purchase_id FROM sale) THEN
        return new_id;
    END IF;
    return -1;
end;
$$ language plpgsql;
-- TASK 13
CREATE OR REPLACE FUNCTION list_coffees()
RETURNS TABLE(
    coffee_id int,
    name varchar(50),
    description varchar(250),
    country varchar(60),
    intensity int,
    price float,
    reward_pts float,
    redeem_pts float
)
AS $$
BEGIN
    return QUERY select * from coffee;
end;
$$ language plpgsql;
-- TASK 14
CREATE OR REPLACE FUNCTION show_coffee(strength int, keyword_1 varchar(50), keyword_2 varchar(50))
RETURNS TABLE(id int, name varchar(50))
AS $$BEGIN
    RETURN QUERY SELECT coffee_id, COFFEE.name
                 FROM coffee
                 WHERE COFFEE.intensity = strength and COFFEE.name LIKE '%' || keyword_1 || '%'
                                                    and COFFEE.name LIKE '%' || keyword_2 || '%';
end;
$$ language plpgsql;

-- TASK 15
CREATE OR REPLACE FUNCTION top_stores(num_stores int, months int)
RETURNS TABLE(est_num int, revenue float)
AS $$
DECLARE
    past_date date;
    store_revenue float;
    purchase RECORD;
BEGIN
    -- Calculate the date to look back through
    select p_date from CLOCK into past_date;
    past_date = past_date - months * 30;

    DROP TABLE IF EXISTS revenue;
    CREATE TABLE revenue(est_num int, tot_revenue float);

    -- Calculate the total revenue for each store
    FOR purchase IN SELECT SALE.est_num, price, purchase_portion, purchase_time
                    FROM SALE NATURAL JOIN COFFEE
                    WHERE purchase_time > past_date
    LOOP
        select tot_revenue from revenue where revenue.est_num = purchase.est_num into store_revenue;
        IF store_revenue IS NULL THEN
            insert into revenue values(purchase.est_num, purchase.price * purchase.purchase_portion);
        ELSE
            store_revenue = store_revenue + (purchase.price * purchase.purchase_portion);

            UPDATE revenue
            SET tot_revenue = store_revenue
            WHERE revenue.est_num = purchase.est_num;
        END IF;
    end loop;

    RETURN QUERY SELECT revenue.est_num, tot_revenue FROM revenue ORDER BY tot_revenue DESC LIMIT num_stores;
end;
$$ language plpgsql;
-- TASK 16
CREATE OR REPLACE FUNCTION top_spenders(num_spenders int, months int)
RETURNS TABLE(est_num int, money float)
AS $$
DECLARE
    past_date date;
    money_spent float;
    purchase RECORD;
BEGIN
    -- Calculate the date to look back through
    select p_date from CLOCK into past_date;
    past_date = past_date - months * 30;

    DROP TABLE IF EXISTS spending;
    CREATE TABLE spending(customer_id int, tot_spent float);

    -- Calculate the total revenue for each store
    FOR purchase IN SELECT SALE.customer_id, price, purchase_portion, purchase_time
                    FROM SALE NATURAL JOIN COFFEE
                    WHERE purchase_time > past_date
    LOOP
        select tot_spent from spending where spending.customer_id = purchase.customer_id into money_spent;
        IF money_spent IS NULL THEN
            insert into spending values(purchase.customer_id, purchase.price * purchase.purchase_portion);
        ELSE
            money_spent = money_spent + (purchase.price * purchase.purchase_portion);

            UPDATE spending
            SET tot_spent = money_spent
            WHERE spending.customer_id = purchase.customer_id;
        END IF;
    end loop;

    RETURN QUERY SELECT spending.customer_id, tot_spent FROM spending ORDER BY tot_spent DESC LIMIT num_spenders;
end;
$$ language plpgsql;
