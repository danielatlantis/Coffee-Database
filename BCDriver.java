import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class BCDriver {
    public static void main(String[] args) throws
            ClassNotFoundException, SQLException {
        //check if jdbc driver is properly linked
        Class.forName("org.postgresql.Driver");

        //connection
        String url = "jdbc:postgresql://localhost:5432/";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "hailtopitt23");
        props.setProperty("escapeSyntaxCallMode", "callIfNoReturn");
        Connection conn = DriverManager.getConnection(url, props);

        System.out.println("Inserting store 1000");
        try(CallableStatement properCase1 = conn.prepareCall("{ ? = call new_store( ? , ?, ?, ?) }"))
        {
            conn.setAutoCommit(false);
            Integer Return1;
            properCase1.registerOutParameter(1, Types.INTEGER);
            properCase1.setString(2, "Oakland");
            properCase1.setString(3, "sitting");
            properCase1.setFloat(4, (float)40.45);
            properCase1.setFloat(5, (float)50.55);
            properCase1.execute();
            Return1 = properCase1.getInt(1);
            System.out.println(Return1);
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }
        System.out.println("Inserting store 1001");
        try(CallableStatement properCase1 = conn.prepareCall("{ ? = call new_store( ? , ?, ?, ?) }"))
        {
            conn.setAutoCommit(false);
            Integer Return1;
            properCase1.registerOutParameter(1, Types.INTEGER);
            properCase1.setString(2, "Shadyside");
            properCase1.setString(3, "sitting");
            properCase1.setFloat(4, (float)45.66);
            properCase1.setFloat(5, (float)54.34);
            properCase1.execute();
            Return1 = properCase1.getInt(1);
            System.out.println(Return1);
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }
        System.out.println("Inserting store 1002");
        try(CallableStatement properCase1 = conn.prepareCall("{ ? = call new_store( ? , ?, ?, ?) }"))
        {
            conn.setAutoCommit(false);
            Integer Return1;
            properCase1.registerOutParameter(1, Types.INTEGER);
            properCase1.setString(2, "Southside");
            properCase1.setString(3, "sitting");
            properCase1.setFloat(4, (float)54.88);
            properCase1.setFloat(5, (float)53.99);
            properCase1.execute();
            Return1 = properCase1.getInt(1);
            System.out.println(Return1);
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("Listing all coffees in database (none expected)");
        try(PreparedStatement properCase13 = conn.prepareStatement("select * from list_coffees()")){
            conn.setAutoCommit(false);
            ResultSet rs13 = properCase13.executeQuery();
            if(rs13.next() == false)
            {
                System.out.println("No items are currently offered by BoutiqueCoffee.");
            }
            else
            {
                do
                {
                    System.out.print(rs13.getInt(1) + " ");
                    System.out.println(rs13.getString(2));
                } while(rs13.next());
            }
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("Inserting coffee 100");
        try(CallableStatement properCase2 = conn.prepareCall("{ ? = call new_coffee( ? , ?, ?, ?, ?, ?, ?) }")) {
            conn.setAutoCommit(false);
            Integer Return2;
            properCase2.registerOutParameter(1, Types.INTEGER);
            properCase2.setString(2, "mocha");
            properCase2.setString(3, "dark and rich");
            properCase2.setString(4, "Brazil");
            properCase2.setInt(5, 3);
            properCase2.setFloat(6, (float)8.99);
            properCase2.setFloat(7, (float) 10);
            properCase2.setFloat(8, (float) 100);
            properCase2.execute();
            Return2 = properCase2.getInt(1);
            System.out.println(Return2);
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }
        System.out.println("Inserting coffee 101");
        try(CallableStatement properCase2 = conn.prepareCall("{ ? = call new_coffee( ? , ?, ?, ?, ?, ?, ?) }")) {
            conn.setAutoCommit(false);
            Integer Return2;
            properCase2.registerOutParameter(1, Types.INTEGER);
            properCase2.setString(2, "caramel");
            properCase2.setString(3, "magically delicious");
            properCase2.setString(4, "Germany");
            properCase2.setInt(5, 5);
            properCase2.setFloat(6, (float)9.99);
            properCase2.setFloat(7, (float) 12);
            properCase2.setFloat(8, (float) 120);
            properCase2.execute();
            Return2 = properCase2.getInt(1);
            System.out.println(Return2);
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("Inserting coffee 102");
        try(CallableStatement properCase2 = conn.prepareCall("{ ? = call new_coffee( ? , ?, ?, ?, ?, ?, ?) }")) {
            conn.setAutoCommit(false);
            Integer Return2;
            properCase2.registerOutParameter(1, Types.INTEGER);
            properCase2.setString(2, "Pumpkin Spice");
            properCase2.setString(3, "spooky taste");
            properCase2.setString(4, "France");
            properCase2.setInt(5, 6);
            properCase2.setFloat(6, (float)11.99);
            properCase2.setFloat(7, (float) 15);
            properCase2.setFloat(8, (float) 120);
            properCase2.execute();
            Return2 = properCase2.getInt(1);
            System.out.println(Return2);
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("Listing all coffees in database");
        try(PreparedStatement properCase13 = conn.prepareStatement("select * from list_coffees()")){
            conn.setAutoCommit(false);
            ResultSet rs13 = properCase13.executeQuery();
            if(rs13.next() == false)
            {
                System.out.println("No items are currently offered by BoutiqueCoffee.");
            }
            else
            {
                do
                {
                    System.out.print(rs13.getInt(1) + " ");
                    System.out.println(rs13.getString(2));
                } while(rs13.next());
            }
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("Search for coffee with intensity 6 and keywords 'Pumpkin' and 'Spice'");
        try(PreparedStatement properCase14 = conn.prepareStatement("select * from show_coffee(?, ?, ?)")){
            conn.setAutoCommit(false);
            properCase14.setInt(1, 6);
            properCase14.setString(2, "Pumpkin");
            properCase14.setString(3, "Spice");
            ResultSet rs14 = properCase14.executeQuery();
            if(rs14.next() == false)
            {
                System.out.println("No coffees satisfied these conditions.");
            }
            else
            {
                do
                {
                    System.out.print(rs14.getInt(1) + " ");
                    System.out.println(rs14.getString(2));
                } while(rs14.next());
            }
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("Inserting promotion for coffee 100");
        try(CallableStatement properCase3 = conn.prepareCall("{ ? = call new_promo( ? , ?, ?, ?) }")) {
            conn.setAutoCommit(false);
            Integer Return3;
            properCase3.registerOutParameter(1, Types.INTEGER);
            properCase3.setString(2, "BOGO mocha");
            properCase3.setDate(3, Date.valueOf("2022-01-01"));
            properCase3.setDate(4, Date.valueOf("2022-02-01"));
            properCase3.setInt(5, 100);
            properCase3.execute();
            Return3 = properCase3.getInt(1);
            System.out.println(Return3);
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }
        System.out.println("Inserting offer for store 1000 for coffee 100");
        try(CallableStatement properCase4 = conn.prepareCall("{ ? = call new_offer( ? , ?) }")) {
            conn.setAutoCommit(false);
            Integer Return4;
            properCase4.registerOutParameter(1, Types.INTEGER);
            properCase4.setInt(2, 100);
            properCase4.setInt(3, 1000);
            properCase4.execute();
            Return4 = properCase4.getInt(1);
            System.out.println(Return4);
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("List stores promoting coffee 100");
        try(PreparedStatement properCase5 = conn.prepareStatement("select * from list_stores(?)")){
            conn.setAutoCommit(false);
            properCase5.setInt(1, 100);
            ResultSet rs = properCase5.executeQuery();
            if(rs.next() == false)
            {
                System.out.println("No stores are currently promoting this coffee.");
                conn.commit();
            }
            else
            {
                do
                {
                    System.out.println(rs.getString(1));
                } while(rs.next());
            }
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }
        System.out.println("Inserting promotion for coffee 101");
        try(CallableStatement properCase3 = conn.prepareCall("{ ? = call new_promo( ? , ?, ?, ?) }")) {
            conn.setAutoCommit(false);
            Integer Return3;
            properCase3.registerOutParameter(1, Types.INTEGER);
            properCase3.setString(2, "BOGO caramel");
            properCase3.setDate(3, Date.valueOf("2022-02-01"));
            properCase3.setDate(4, Date.valueOf("2022-03-01"));
            properCase3.setInt(5, 101);
            properCase3.execute();
            Return3 = properCase3.getInt(1);
            System.out.println(Return3);
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }
        System.out.println("Inserting offer for store 1001 for coffee 101");
        try(CallableStatement properCase4 = conn.prepareCall("{ ? = call new_offer( ? , ?) }")) {
            conn.setAutoCommit(false);
            Integer Return4;
            properCase4.registerOutParameter(1, Types.INTEGER);
            properCase4.setInt(2, 101);
            properCase4.setInt(3, 1001);
            properCase4.execute();
            Return4 = properCase4.getInt(1);
            System.out.println(Return4);
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }
        System.out.println("List all stores with promotions");
        try(PreparedStatement properCase5 = conn.prepareStatement("select * from list_stores()")){
            conn.setAutoCommit(false);
            ResultSet rs = properCase5.executeQuery();
            if(rs.next() == false)
            {
                System.out.println("No stores are currently promoting this coffee.");
                conn.commit();
            }
            else
            {
                do
                {
                    System.out.println(rs.getString(1));
                } while(rs.next());
            }
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }
        System.out.println("List promotions for store 1000 for coffee 100");
        try(PreparedStatement properCase6 = conn.prepareStatement("select * from check_offers(?, ?)")){
            conn.setAutoCommit(false);
            properCase6.setInt(1, 1000);
            properCase6.setInt(2, 100);
            ResultSet rs6 = properCase6.executeQuery();
            if(rs6.next() == false)
            {
                System.out.println("No promotions for this coffee are currently offered at this store.");
            }
            else
            {
                do
                {
                    System.out.println(rs6.getString(1) + " ");
                    System.out.println(rs6.getString(2));
                } while(rs6.next());
            }
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }
        System.out.println("List all promotions for store 1001");
        try(PreparedStatement properCase6 = conn.prepareStatement("select * from check_offers(?)")){
            conn.setAutoCommit(false);
            properCase6.setInt(1, 1001);
            ResultSet rs6 = properCase6.executeQuery();
            if(rs6.next() == false)
            {
                System.out.println("No promotions are currently offered at this store.");
            }
            else
            {
                do
                {
                    System.out.println(rs6.getString(1) + " ");
                    System.out.println(rs6.getString(2));
                } while(rs6.next());
            }
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }
        System.out.println("Find closest store to coordinates (50.50, 50.50) offering promotion 101");
        try(PreparedStatement properCase7 = conn.prepareStatement("select * from find_store(?, ?, ?)")){
            conn.setAutoCommit(false);
            properCase7.setFloat(1, (float)50.50);
            properCase7.setFloat(2, (float)50.50);
            properCase7.setInt(3, 101);
            ResultSet rs7 = properCase7.executeQuery();
            while(rs7.next())
            {
                System.out.print(rs7.getString(1) + " ");
                System.out.print(rs7.getInt(2) + " ");
                System.out.println(rs7.getFloat(3));
            }
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("Find closest store to coordinates (50.50, 50.50)");
        try(PreparedStatement properCase7 = conn.prepareStatement("select * from find_store(?, ?)")){
            conn.setAutoCommit(false);
            properCase7.setFloat(1, (float)50.50);
            properCase7.setFloat(2, (float)50.50);
            ResultSet rs7 = properCase7.executeQuery();
            while(rs7.next())
            {
                System.out.print(rs7.getString(1) + " ");
                System.out.print(rs7.getInt(2) + " ");
                System.out.println(rs7.getFloat(3));
            }
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("Edit loyalty level diamond's booster to 2");
        try(CallableStatement properCase8 = conn.prepareCall("{ ? = call new_loyalty( ? , ?) }"))
        {
            conn.setAutoCommit(false);
            String Return8;
            properCase8.registerOutParameter(1, Types.VARCHAR);
            properCase8.setString(2, "diamond");
            properCase8.setFloat(3, (float) 2.0);
            properCase8.execute();
            Return8 = properCase8.getString(1);
            System.out.println(Return8);
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("Adding customer Billy Bob");
        try(CallableStatement properCase9 = conn.prepareCall("{ ? = call new_customer( ? , ?, ?, ?, ?, ?, ?) }"))
        {
            conn.setAutoCommit(false);
            Double Return9;
            properCase9.registerOutParameter(1, Types.DOUBLE);
            properCase9.setString(2, "Billy");
            properCase9.setString(3, "Bob");
            properCase9.setString(4, "B");
            properCase9.setString(5, "10");
            properCase9.setString(6, "10");
            properCase9.setString(7, "412-333-4444");
            properCase9.setString(8, "home");
            properCase9.execute();
            Return9 = properCase9.getDouble(1);
            System.out.println(Return9);
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("Adding customer John Smith");
        try(CallableStatement properCase9 = conn.prepareCall("{ ? = call new_customer( ? , ?, ?, ?, ?, ?, ?) }"))
        {
            conn.setAutoCommit(false);
            Double Return9;
            properCase9.registerOutParameter(1, Types.DOUBLE);
            properCase9.setString(2, "John");
            properCase9.setString(3, "Smith");
            properCase9.setString(4, "G");
            properCase9.setString(5, "3");
            properCase9.setString(6, "11");
            properCase9.setString(7, "412-456-6879");
            properCase9.setString(8, "home");
            properCase9.execute();
            Return9 = properCase9.getDouble(1);
            System.out.println(Return9);
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("Get John Smith's points");
        try(CallableStatement properCase10 = conn.prepareCall("{ ? = call customer_points( ? ) }"))
        {
            conn.setAutoCommit(false);
            Float Return10;
            properCase10.registerOutParameter(1, 7);
            properCase10.setInt(2, 1001);
            properCase10.execute();
            Return10 = properCase10.getFloat(1);
            System.out.println(Return10);
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("New purchase for John Smith of 2 Mocha coffees and 1 caramel using no redeem points");
        try(CallableStatement properCase12 = conn.prepareCall("{ ? = call new_purchase( ? , ?, ?::timestamp, ?, ?, ?) }"))
        {
            conn.setAutoCommit(false);
            Integer Return12;
            properCase12.registerOutParameter(1, Types.INTEGER);
            String [] coffee_ids = "100,101".split(",");
            Array coffee_arr = conn.createArrayOf("INTEGER", coffee_ids);
            String [] coffee_quants = "2,1".split(",");
            Array quant_arr = conn.createArrayOf("INTEGER", coffee_quants);
            String [] redeem_quants = "0,0".split(",");
            Array redeem_arr = conn.createArrayOf("INTEGER", redeem_quants);
            properCase12.setInt(2, 1001);
            properCase12.setInt(3, 1000);
            properCase12.setTimestamp(4, Timestamp.valueOf("2021-12-01 10:10:10"));
            properCase12.setArray(5, coffee_arr);
            properCase12.setArray(6, quant_arr);
            properCase12.setArray(7, redeem_arr);
            properCase12.execute();
            Return12 = properCase12.getInt(1);
            if(Return12 == -1)
            {
                conn.commit();
            }
            else
            {
                System.out.println(Return12);
                conn.commit();
            }
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("Get John Smith's points after purchases");
        try(CallableStatement properCase10 = conn.prepareCall("{ ? = call customer_points( ? ) }"))
        {
            conn.setAutoCommit(false);
            Float Return10;
            properCase10.registerOutParameter(1, 7);
            properCase10.setInt(2, 1001);
            properCase10.execute();
            Return10 = properCase10.getFloat(1);
            System.out.println(Return10);
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("New purchase for John Smith of 1 caramel using all redeem points");
        try(CallableStatement properCase12 = conn.prepareCall("{ ? = call new_purchase( ? , ?, ?::timestamp, ?, ?, ?) }"))
        {
            conn.setAutoCommit(false);
            Integer Return12;
            properCase12.registerOutParameter(1, Types.INTEGER);
            String [] coffee_ids = "101".split(",");
            Array coffee_arr = conn.createArrayOf("INTEGER", coffee_ids);
            String [] coffee_quants = "1".split(",");
            Array quant_arr = conn.createArrayOf("INTEGER", coffee_quants);
            String [] redeem_quants = "1".split(",");
            Array redeem_arr = conn.createArrayOf("INTEGER", redeem_quants);
            properCase12.setInt(2, 1001);
            properCase12.setInt(3, 1000);
            properCase12.setTimestamp(4, Timestamp.valueOf("2021-12-01 12:12:12"));
            properCase12.setArray(5, coffee_arr);
            properCase12.setArray(6, quant_arr);
            properCase12.setArray(7, redeem_arr);
            properCase12.execute();
            Return12 = properCase12.getInt(1);
            if(Return12 == -1)
            {
                conn.commit();
            }
            else
            {
                System.out.println(Return12);
                conn.commit();
            }
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("Get John Smith's points after trying to purchase using redeem points");
        try(CallableStatement properCase10 = conn.prepareCall("{ ? = call customer_points( ? ) }"))
        {
            conn.setAutoCommit(false);
            Float Return10;
            properCase10.registerOutParameter(1, 7);
            properCase10.setInt(2, 1001);
            properCase10.execute();
            Return10 = properCase10.getFloat(1);
            System.out.println(Return10);
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("New purchase for John Smith of 10 Mocha coffees using no redeem points");
        try(CallableStatement properCase12 = conn.prepareCall("{ ? = call new_purchase( ? , ?, ?::timestamp, ?, ?, ?) }"))
        {
            conn.setAutoCommit(false);
            Integer Return12;
            properCase12.registerOutParameter(1, Types.INTEGER);
            String [] coffee_ids = "100".split(",");
            Array coffee_arr = conn.createArrayOf("INTEGER", coffee_ids);
            String [] coffee_quants = "10".split(",");
            Array quant_arr = conn.createArrayOf("INTEGER", coffee_quants);
            String [] redeem_quants = "0".split(",");
            Array redeem_arr = conn.createArrayOf("INTEGER", redeem_quants);
            properCase12.setInt(2, 1001);
            properCase12.setInt(3, 1000);
            properCase12.setTimestamp(4, Timestamp.valueOf("2021-12-02 10:10:10"));
            properCase12.setArray(5, coffee_arr);
            properCase12.setArray(6, quant_arr);
            properCase12.setArray(7, redeem_arr);
            properCase12.execute();
            Return12 = properCase12.getInt(1);
            if(Return12 == -1)
            {
                conn.commit();
            }
            else
            {
                System.out.println(Return12);
                conn.commit();
            }
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("New purchase for Billy Bob of 3 Mocha coffees and 2 caramel coffees using no redeem points");
        try(CallableStatement properCase12 = conn.prepareCall("{ ? = call new_purchase( ? , ?, ?::timestamp, ?, ?, ?) }"))
        {
            conn.setAutoCommit(false);
            Integer Return12;
            properCase12.registerOutParameter(1, Types.INTEGER);
            String [] coffee_ids = "100,101".split(",");
            Array coffee_arr = conn.createArrayOf("INTEGER", coffee_ids);
            String [] coffee_quants = "3,2".split(",");
            Array quant_arr = conn.createArrayOf("INTEGER", coffee_quants);
            String [] redeem_quants = "0,0".split(",");
            Array redeem_arr = conn.createArrayOf("INTEGER", redeem_quants);
            properCase12.setInt(2, 1000);
            properCase12.setInt(3, 1001);
            properCase12.setTimestamp(4, Timestamp.valueOf("2021-08-01 10:10:10"));
            properCase12.setArray(5, coffee_arr);
            properCase12.setArray(6, quant_arr);
            properCase12.setArray(7, redeem_arr);
            properCase12.execute();
            Return12 = properCase12.getInt(1);
            if(Return12 == -1)
            {
                conn.commit();
            }
            else
            {
                System.out.println(Return12);
                conn.commit();
            }
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("Get John Smith's points after big purchase");
        try(CallableStatement properCase10 = conn.prepareCall("{ ? = call customer_points( ? ) }"))
        {
            conn.setAutoCommit(false);
            Float Return10;
            properCase10.registerOutParameter(1, 7);
            properCase10.setInt(2, 1001);
            properCase10.execute();
            Return10 = properCase10.getFloat(1);
            System.out.println(Return10);
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("Rank Customers");
        try(PreparedStatement properCase11 = conn.prepareStatement("select * from list_customers()")){
            conn.setAutoCommit(false);
            ResultSet rs7 = properCase11.executeQuery();
            while(rs7.next())
            {
                System.out.print(rs7.getInt(1) + " ");
                System.out.print(rs7.getString(2) + " ");
                System.out.println(rs7.getDouble(3));
            }
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("New purchase for John Smith of 1 caramel coffee using all redeem points");
        try(CallableStatement properCase12 = conn.prepareCall("{ ? = call new_purchase( ? , ?, ?::timestamp, ?, ?, ?) }"))
        {
            conn.setAutoCommit(false);
            Integer Return12;
            properCase12.registerOutParameter(1, Types.INTEGER);
            String [] coffee_ids = "101".split(",");
            Array coffee_arr = conn.createArrayOf("INTEGER", coffee_ids);
            String [] coffee_quants = "1".split(",");
            Array quant_arr = conn.createArrayOf("INTEGER", coffee_quants);
            String [] redeem_quants = "1".split(",");
            Array redeem_arr = conn.createArrayOf("INTEGER", redeem_quants);
            properCase12.setInt(2, 1001);
            properCase12.setInt(3, 1000);
            properCase12.setTimestamp(4, Timestamp.valueOf("2021-12-03 12:12:12"));
            properCase12.setArray(5, coffee_arr);
            properCase12.setArray(6, quant_arr);
            properCase12.setArray(7, redeem_arr);
            properCase12.execute();
            Return12 = properCase12.getInt(1);
            if(Return12 == -1)
            {
                conn.commit();
            }
            else
            {
                System.out.println(Return12);
                conn.commit();
            }
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("Get John Smith's points after using his points to purchase");
        try(CallableStatement properCase10 = conn.prepareCall("{ ? = call customer_points( ? ) }"))
        {
            conn.setAutoCommit(false);
            Float Return10;
            properCase10.registerOutParameter(1, 7);
            properCase10.setInt(2, 1001);
            properCase10.execute();
            Return10 = properCase10.getFloat(1);
            System.out.println(Return10);
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("Rank Customers after redemption");
        try(PreparedStatement properCase11 = conn.prepareStatement("select * from list_customers()")){
            conn.setAutoCommit(false);
            ResultSet rs7 = properCase11.executeQuery();
            while(rs7.next())
            {
                System.out.print(rs7.getInt(1) + " ");
                System.out.print(rs7.getString(2) + " ");
                System.out.println(rs7.getDouble(3));
            }
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("List the top store over the last 3 months");
        try(PreparedStatement properCase15 = conn.prepareStatement("select * from top_stores(?, ?)")){
            conn.setAutoCommit(false);
            properCase15.setInt(1, 1);
            properCase15.setInt(2, 3);
            ResultSet rs15 = properCase15.executeQuery();
            while(rs15.next())
            {
                System.out.print(rs15.getInt(1) + " ");
                System.out.println(rs15.getInt(2));
            }
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("List top 2 customers who spent the most money over last 12 months");
        try(PreparedStatement properCase16 = conn.prepareStatement("select * from top_spenders(?, ?)")){
            conn.setAutoCommit(false);
            properCase16.setInt(1, 2);
            properCase16.setInt(2, 12);
            ResultSet rs16 = properCase16.executeQuery();
            while(rs16.next())
            {
                System.out.print(rs16.getInt(1) + " ");
                System.out.println(rs16.getInt(2));
            }
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("Additional Fail Cases");
        System.out.println("Inserting promotion with end date before start date");
        try(CallableStatement properCase3 = conn.prepareCall("{ ? = call new_promo( ? , ?, ?, ?) }")) {
            conn.setAutoCommit(false);
            Integer Return3;
            properCase3.registerOutParameter(1, Types.INTEGER);
            properCase3.setString(2, "1/2 off caramel");
            properCase3.setDate(3, Date.valueOf("2022-03-01"));
            properCase3.setDate(4, Date.valueOf("2022-02-01"));
            properCase3.setInt(5, 101);
            properCase3.execute();
            Return3 = properCase3.getInt(1);
            System.out.println(Return3);
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }

        System.out.println("Search for coffee with intensity 1 (should be 3) and keywords 'mocha' and 'mocha'");
        try(PreparedStatement properCase14 = conn.prepareStatement("select * from show_coffee(?, ?, ?)")){
            conn.setAutoCommit(false);
            properCase14.setInt(1, 6);
            properCase14.setString(2, "mocha");
            properCase14.setString(3, "mocha");
            ResultSet rs14 = properCase14.executeQuery();
            if(rs14.next() == false)
            {
                System.out.println("No coffees satisfied these conditions.");
            }
            else
            {
                do
                {
                    System.out.print(rs14.getInt(1) + " ");
                    System.out.println(rs14.getString(2));
                } while(rs14.next());
            }
            conn.commit();
        }
        catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException err) {
                System.out.println("SQL Error");
                while (err != null) {
                    System.out.println("Message = " + err.getMessage());
                    System.out.println("SQLState = " + err.getSQLState());
                    System.out.println("SQL Code = " + err.getErrorCode());
                    err = err.getNextException();
                }
            }
        }
    }
}
