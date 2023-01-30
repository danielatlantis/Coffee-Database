import java.util.*;
import java.sql.*;
import java.sql.Date;
public class BCBenchmark {
    public static void main(String [] args) throws
            ClassNotFoundException, SQLException {
        //check if jdbc driver is properly linked
        Class.forName("org.postgresql.Driver");
        Random rand = new Random();
        //connection
        String url = "jdbc:postgresql://localhost:5432/";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "password");
        props.setProperty("escapeSyntaxCallMode", "callIfNoReturn");
        Connection conn = DriverManager.getConnection(url, props);
        System.out.println("Filling DB...");
        for (int i = 1; i < 200; i++){
            // Insert a store
            try (CallableStatement properCase1 = conn.prepareCall("{ ? = call new_store( ?, ?::store_enum, ?::float, ?::float) }")) {
                conn.setAutoCommit(false);
                Integer Return1;
                properCase1.registerOutParameter(1, Types.INTEGER);
                properCase1.setString(2, "Stressy Store "+i);
                properCase1.setString(3, "sitting");
                properCase1.setFloat(4, rand.nextFloat()*100);
                properCase1.setFloat(5, rand.nextFloat()*100);
                properCase1.execute();
                Return1 = properCase1.getInt(1);
                System.out.println(Return1);
                conn.commit();
            } catch (SQLException e1) {
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

            // Enter a coffee
            try(CallableStatement properCase2 = conn.prepareCall("{ ? = call new_coffee( ? , ?, ?, ?, ?, ?, ?) }")) {
                conn.setAutoCommit(false);
                Integer Return2;
                properCase2.registerOutParameter(1, Types.INTEGER);
                properCase2.setString(2, "Stress coffee");
                properCase2.setString(3, "Made to test the DB");
                properCase2.setString(4, "Brazil");
                properCase2.setInt(5, i % 12);
                properCase2.setFloat(6, rand.nextFloat()*50);
                properCase2.setFloat(7, rand.nextFloat());
                properCase2.setFloat(8, rand.nextFloat()*20);
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

            // New promotion
            try(CallableStatement properCase3 = conn.prepareCall("{ ? = call new_promo( ? , ?, ?, ?) }")) {
                conn.setAutoCommit(false);
                Integer Return3;
                properCase3.registerOutParameter(1, Types.INTEGER);
                properCase3.setString(2, "BOGO");
                properCase3.setDate(3, Date.valueOf("2022-12-09"));
                properCase3.setDate(4, Date.valueOf("2022-12-20"));
                properCase3.setInt(5, rand.nextInt(i)+100);
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

            // New offer
            try(CallableStatement properCase4 = conn.prepareCall("{ ? = call new_offer( ? , ?) }")) {
                conn.setAutoCommit(false);
                Integer Return4;
                properCase4.registerOutParameter(1, Types.INTEGER);
                properCase4.setInt(2, rand.nextInt(i)+100);
                properCase4.setInt(3, rand.nextInt(i)+1000);
                properCase4.execute();
                Return4 = properCase4.getInt(1);
                System.out.println(Return4);
                conn.commit();
            }
            catch (SQLException e1) {
                try {
                    System.out.println("Attempted to add offer that already exists, cancelled");
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

            // New customer
            try(CallableStatement properCase9 = conn.prepareCall("{ ? = call new_customer( ? , ?, ?, ?, ?, ?, ?::phone_enum) }"))
            {
                conn.setAutoCommit(false);
                Double Return9;
                properCase9.registerOutParameter(1, Types.DOUBLE);
                properCase9.setString(2, "John");
                properCase9.setString(3, "Doe");
                properCase9.setString(4, "R");
                properCase9.setString(5, "6");
                properCase9.setString(6, "7");
                properCase9.setString(7, "123-123-1234");
                properCase9.setString(8, "mobile");
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

            // New purchase
            try(CallableStatement properCase12 = conn.prepareCall("{ ? = call new_purchase( ? , ?, ?::timestamp, ?, ?, ?) }"))
            {
                conn.setAutoCommit(false);
                Integer Return12;
                properCase12.registerOutParameter(1, Types.INTEGER);
                properCase12.setInt(2, rand.nextInt(i)+1000);
                properCase12.setInt(3, rand.nextInt(i)+1000);
                properCase12.setTimestamp(4, Timestamp.valueOf("2022-12-22 12:34:56"));
                Object [] coffee_ids = new Integer[1];
                coffee_ids[0] = rand.nextInt(i)+100;
                Array coffee_arr = conn.createArrayOf("INTEGER", coffee_ids);
                Object [] quant_ids = new Integer[1];
                quant_ids[0] = 1;
                Array quant_arr = conn.createArrayOf("INTEGER", quant_ids);
                Object [] redeem_ids = new Integer[1];
                redeem_ids[0] = rand.nextInt(1);
                Array redeem_arr = conn.createArrayOf("INTEGER", redeem_ids);
                properCase12.setArray(5, coffee_arr);
                properCase12.setArray(6, quant_arr);
                properCase12.setArray(7, redeem_arr);
                properCase12.execute();
                Return12 = properCase12.getInt(1);
                System.out.println(Return12);
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

//---------------------------------------------------------------------------------------------------------------------------------------------
//                                       END FOR LOOP, BEGIN LIST TESTS
//---------------------------------------------------------------------------------------------------------------------------------------------

        // Search coffee
        try(PreparedStatement properCase5 = conn.prepareStatement("select * from list_stores(?)")){
            conn.setAutoCommit(false);
            System.out.println("SEARCH COFFEE");
            properCase5.setInt(1, 105);
            ResultSet rs = properCase5.executeQuery();
            if(rs.next() == false)
            {
                System.out.println("No stores are currently promoting this coffee.");
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
        // List stores with promos
        try(PreparedStatement properCase5 = conn.prepareStatement("select * from list_stores()")){
            conn.setAutoCommit(false);
            System.out.println("LIST STORES");
            ResultSet rs = properCase5.executeQuery();
            if(rs.next() == false)
            {
                System.out.println("No stores are currently offering any promotions");
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
        // List promos by coffee
        try(PreparedStatement properCase6 = conn.prepareStatement("select * from check_offers(?, ?)")){
            conn.setAutoCommit(false);
            System.out.println("LIST BY COFFEE");
            properCase6.setInt(1, 1101);
            properCase6.setInt(2, 102);
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
        // List promos no coffee
        try(PreparedStatement properCase6 = conn.prepareStatement("select * from check_offers(?)")){
            conn.setAutoCommit(false);
            System.out.println("PROMOS NO COFFEE");
            properCase6.setInt(1, 1023);
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
        // Closest store by promo
        try(PreparedStatement properCase7 = conn.prepareStatement("select * from find_store(?, ?, ?)")){
            conn.setAutoCommit(false);
            System.out.println("CLOSEST STORE BY PROMO");
            properCase7.setFloat(1, (float) 27.03);
            properCase7.setFloat(2, (float) 40.1);
            properCase7.setInt(3, 123);
            ResultSet rs7 = properCase7.executeQuery();
            while(rs7.next())
            {
               System.out.print(rs7.getString(1) + " ");
               System.out.println(rs7.getInt(2) + " ");
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
        // Closest store no promo
        try(PreparedStatement properCase7 = conn.prepareStatement("select * from find_store(?, ?)")){
            conn.setAutoCommit(false);
            System.out.println("CLOSEST STORE NO PROMO");
            properCase7.setFloat(1, (float) 35.12);
            properCase7.setFloat(2, (float) 54.32);
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
        // Insert loyalty
        try(CallableStatement properCase8 = conn.prepareCall("{ ? = call new_loyalty( ? , ?::float) }"))
            {
                conn.setAutoCommit(false);
                System.out.println("INSERT LOYALTY");
                String Return8;
                properCase8.registerOutParameter(1, Types.VARCHAR);
                properCase8.setString(2, "bronze");
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
            // Get customer points
            try(CallableStatement properCase10 = conn.prepareCall("{ ? = call customer_points( ? ) }"))
                {
                    conn.setAutoCommit(false);
                    System.out.println("GET CUSTOMER POINTS");
                    Float Return10;
                    properCase10.registerOutParameter(1, 7);
                    properCase10.setInt(2, 145);
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
                // List customers
                try(PreparedStatement properCase11 = conn.prepareStatement("select * from list_customers()")){
                    conn.setAutoCommit(false);
                    System.out.println("LIST CUSTOMERS");
                    ResultSet rs7 = properCase11.executeQuery();
                    while(rs7.next())
                    {
                       System.out.print(rs7.getInt(1) + " ");
                       System.out.println(rs7.getString(2));
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
                // List coffees
                try(PreparedStatement properCase13 = conn.prepareStatement("select * from list_coffees()")){
                    conn.setAutoCommit(false);
                    System.out.println("LIST COFFEE");
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
                // Grab coffee by keyword and intensity
                try(PreparedStatement properCase14 = conn.prepareStatement("select * from show_coffee(?, ?, ?)")){
                    conn.setAutoCommit(false);
                    System.out.println("SEACH COFFEE INT AND KEYS");
                    properCase14.setInt(1, 4);
                    properCase14.setString(2, "Stress");
                    properCase14.setString(3, "coffee");
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
                // Top stores
                try(PreparedStatement properCase15 = conn.prepareStatement("select * from top_stores(?, ?)")){
                    conn.setAutoCommit(false);
                    System.out.println("TOP STORES");
                    properCase15.setInt(1, 2);
                    properCase15.setInt(2, 1);
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
                // Top customers
                try(PreparedStatement properCase16 = conn.prepareStatement("select * from top_spenders(?, ?)")){
                    conn.setAutoCommit(false);
                    System.out.println("TOP CUSTOMERS");
                    properCase16.setInt(1, 2);
                    properCase16.setInt(2, 1);
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
        System.out.println("Thank you for using the BoutiqueCoffee database!");
    }
}
