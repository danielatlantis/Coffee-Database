import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class BoutiqueCoffee {
    public static void main(String [] args) throws
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

        Integer task_num = 1;
        Scanner selector = new Scanner(System.in);
        while(task_num != 0)
        {
            System.out.println("Enter a task number 1-16 (Enter 0 when finished): ");
            task_num = Integer.parseInt(selector.nextLine());
            switch(task_num) {
                case (1):
                    try (CallableStatement properCase1 = conn.prepareCall("{ ? = call new_store( ? , ?, ?, ?) }")) {
                        conn.setAutoCommit(false);
                        Integer Return1;
                        properCase1.registerOutParameter(1, Types.INTEGER);

                        Scanner reader = new Scanner(System.in);
                        System.out.println("Enter store name, store type, latitude, longitude:");
                        String store_name = reader.nextLine();
                        String store_type = reader.nextLine();
                        Float lat = Float.parseFloat(reader.nextLine());
                        Float lon = Float.parseFloat(reader.nextLine());
                        properCase1.setString(2, store_name);
                        properCase1.setString(3, store_type);
                        properCase1.setFloat(4, lat);
                        properCase1.setFloat(5, lon);
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
                    break;
                case(2):
                    try(CallableStatement properCase2 = conn.prepareCall("{ ? = call new_coffee( ? , ?, ?, ?, ?, ?, ?) }")) {
                        conn.setAutoCommit(false);
                        Integer Return2;
                        properCase2.registerOutParameter(1, Types.INTEGER);

                        Scanner reader = new Scanner(System.in);
                        System.out.println("Enter coffee name, description, country, intensity, price, reward points, redeem points:");
                        String coffee_name = reader.nextLine();
                        String description = reader.nextLine();
                        String country = reader.nextLine();
                        Integer intensity = Integer.parseInt(reader.nextLine());
                        Float price = Float.parseFloat(reader.nextLine());
                        Float reward = Float.parseFloat(reader.nextLine());
                        Float redeem = Float.parseFloat(reader.nextLine());
                        properCase2.setString(2, coffee_name);
                        properCase2.setString(3, description);
                        properCase2.setString(4, country);
                        properCase2.setInt(5, intensity);
                        properCase2.setFloat(6, price);
                        properCase2.setFloat(7, reward);
                        properCase2.setFloat(8, redeem);
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
                    break;
                case(3):
                    try(CallableStatement properCase3 = conn.prepareCall("{ ? = call new_promo( ? , ?, ?, ?) }")) {
                        conn.setAutoCommit(false);
                        Integer Return3;
                        properCase3.registerOutParameter(1, Types.INTEGER);

                        Scanner reader = new Scanner(System.in);
                        System.out.println("Enter promotion name, start date, end date, coffee id:");
                        String promo_name = reader.nextLine();
                        Date start_date = Date.valueOf(reader.nextLine());
                        Date end_date = Date.valueOf(reader.nextLine());
                        Integer coffee_id = Integer.parseInt(reader.nextLine());
                        properCase3.setString(2, promo_name);
                        properCase3.setDate(3, start_date);
                        properCase3.setDate(4, end_date);
                        properCase3.setInt(5, coffee_id);
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
                    break;
                case(4):
                    try(CallableStatement properCase4 = conn.prepareCall("{ ? = call new_offer( ? , ?) }")) {
                        conn.setAutoCommit(false);
                        Integer Return4;
                        properCase4.registerOutParameter(1, Types.INTEGER);

                        Scanner reader = new Scanner(System.in);
                        System.out.println("Enter promo id, store id:");
                        Integer promo_id = Integer.parseInt(reader.nextLine());
                        Integer store_id = Integer.parseInt(reader.nextLine());
                        properCase4.setInt(2, promo_id);
                        properCase4.setInt(3, store_id);
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
                    break;
                case(5):
                    Scanner reader = new Scanner(System.in);
                    System.out.println("Do you want to search by coffee? (Y for yes or N for no): ");
                    String search = reader.nextLine();
                    if(search.equals("Y"))
                    {
                        try(PreparedStatement properCase5 = conn.prepareStatement("select * from list_stores(?)")){
                            conn.setAutoCommit(false);
                            System.out.println("Enter coffee id: ");
                            Integer coffee_id = Integer.parseInt(reader.nextLine());
                            properCase5.setInt(1, coffee_id);
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
                    }
                    else
                    {
                        try(PreparedStatement properCase5 = conn.prepareStatement("select * from list_stores()")){
                            conn.setAutoCommit(false);
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
                    }
                    break;
                case(6):
                    Scanner reader6 = new Scanner(System.in);
                    System.out.println("Do you want to search by coffee? (Y for yes or N for no): ");
                    String search6 = reader6.nextLine();
                    if(search6.equals("Y"))
                    {
                        try(PreparedStatement properCase6 = conn.prepareStatement("select * from check_offers(?, ?)")){
                            conn.setAutoCommit(false);
                            System.out.println("Enter store id and coffee id:");
                            Integer store_id2 = Integer.parseInt(reader6.nextLine());
                            Integer coffee_id1 = Integer.parseInt(reader6.nextLine());
                            properCase6.setInt(1, store_id2);
                            properCase6.setInt(2, coffee_id1);
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
                    }
                    else
                    {
                        try(PreparedStatement properCase6 = conn.prepareStatement("select * from check_offers(?)")){
                            conn.setAutoCommit(false);
                            System.out.println("Enter store id:");
                            Integer store_id2 = Integer.parseInt(reader6.nextLine());
                            properCase6.setInt(1, store_id2);
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
                    }
                    break;
                case(7):
                    Scanner reader7 = new Scanner(System.in);
                    System.out.println("Do you want to search by promotion? (Y for yes or N for no): ");
                    String search7 = reader7.nextLine();
                    if(search7.equals("Y"))
                    {
                        try(PreparedStatement properCase7 = conn.prepareStatement("select * from find_store(?, ?, ?)")){
                            conn.setAutoCommit(false);
                            System.out.println("Enter store latitude, longitude, and promotion id: ");
                            reader7 = new Scanner(System.in);
                            Float lat = Float.parseFloat(reader7.nextLine());
                            Float lon = Float.parseFloat(reader7.nextLine());
                            Integer promo_id = Integer.parseInt(reader7.nextLine());
                            properCase7.setFloat(1, lat);
                            properCase7.setFloat(2, lon);
                            properCase7.setInt(3, promo_id);
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
                    }
                    else
                    {
                        try(PreparedStatement properCase7 = conn.prepareStatement("select * from find_store(?, ?)")){
                            conn.setAutoCommit(false);
                            System.out.println("Enter store latitude and longitude: ");
                            reader7 = new Scanner(System.in);
                            Float lat = Float.parseFloat(reader7.nextLine());
                            Float lon = Float.parseFloat(reader7.nextLine());
                            properCase7.setFloat(1, lat);
                            properCase7.setFloat(2, lon);
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
                    }
                    break;
                case(8):
                        try(CallableStatement properCase8 = conn.prepareCall("{ ? = call new_loyalty( ? , ?) }"))
                        {
                            conn.setAutoCommit(false);
                            String Return8;
                            properCase8.registerOutParameter(1, Types.VARCHAR);
                            Scanner reader8 = new Scanner(System.in);
                            System.out.println("Enter new level name and booster amount:");
                            String level = reader8.nextLine();
                            Float boost = Float.parseFloat(reader8.nextLine());
                            properCase8.setString(2, level);
                            properCase8.setFloat(3, boost);
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
                        break;
                case(9):
                    try(CallableStatement properCase9 = conn.prepareCall("{ ? = call new_customer( ? , ?, ?, ?, ?, ?, ?) }"))
                    {
                        conn.setAutoCommit(false);
                        Double Return9;
                        properCase9.registerOutParameter(1, Types.DOUBLE);
                        Scanner reader9 = new Scanner(System.in);
                        System.out.println("Enter customer first name, last name, middle_initial, day of birth, month of birth, phone number, and phone type:");
                        String fname = reader9.nextLine();
                        String lname = reader9.nextLine();
                        String mid = reader9.nextLine();
                        String dob = reader9.nextLine();
                        String mob = reader9.nextLine();
                        String phone_num = reader9.nextLine();
                        String phone_type = reader9.nextLine();
                        properCase9.setString(2, fname);
                        properCase9.setString(3, lname);
                        properCase9.setString(4, mid);
                        properCase9.setString(5, dob);
                        properCase9.setString(6, mob);
                        properCase9.setString(7, phone_num);
                        properCase9.setString(8, phone_type);
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
                    break;
                case(10):
                    try(CallableStatement properCase10 = conn.prepareCall("{ ? = call customer_points( ? ) }"))
                    {
                        conn.setAutoCommit(false);
                        Float Return10;
                        properCase10.registerOutParameter(1, 7);
                        Scanner reader10 = new Scanner(System.in);
                        System.out.println("Enter customer id:");
                        Integer cust = Integer.parseInt(reader10.nextLine());
                        properCase10.setInt(2, cust);
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
                    break;
                case(11):
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
                    break;
                case(12):
                    try(CallableStatement properCase12 = conn.prepareCall("{ ? = call new_purchase( ? , ?, ?::timestamp, ?, ?, ?) }"))
                    {
                        conn.setAutoCommit(false);
                        Integer Return12;
                        properCase12.registerOutParameter(1, Types.INTEGER);
                        Scanner reader12 = new Scanner(System.in);
                        System.out.println("Enter customer id, store id, time of sale, list of coffees (separated by commas), coffee quantities (separated by commas), redeem point quantities (separated by commas): ");
                        Integer cust = Integer.parseInt(reader12.nextLine());
                        Integer store = Integer.parseInt(reader12.nextLine());
                        Timestamp time = Timestamp.valueOf(reader12.nextLine());
                        String [] coffee_ids = reader12.nextLine().split(",");
                        Array coffee_arr = conn.createArrayOf("INTEGER", coffee_ids);
                        String [] coffee_quants = reader12.nextLine().split(",");
                        Array quant_arr = conn.createArrayOf("INTEGER", coffee_quants);
                        String [] redeem_quants = reader12.nextLine().split(",");
                        Array redeem_arr = conn.createArrayOf("INTEGER", redeem_quants);
                        properCase12.setInt(2, cust);
                        properCase12.setInt(3, store);
                        properCase12.setTimestamp(4, time);
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
                    break;
                case(13):
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
                    break;
                case(14):
                    try(PreparedStatement properCase14 = conn.prepareStatement("select * from show_coffee(?, ?, ?)")){
                        conn.setAutoCommit(false);
                        System.out.println("Enter strength of coffee, keyword 1, and keyword 2: ");
                        Scanner reader14 = new Scanner(System.in);
                        Integer str = Integer.parseInt(reader14.nextLine());
                        String key1 = reader14.nextLine();
                        String key2 = reader14.nextLine();
                        properCase14.setInt(1, str);
                        properCase14.setString(2, key1);
                        properCase14.setString(3, key2);
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
                    break;
                case(15):
                    try(PreparedStatement properCase15 = conn.prepareStatement("select * from top_stores(?, ?)")){
                        conn.setAutoCommit(false);
                        System.out.println("Enter number of stores and number of months: ");
                        Scanner reader15 = new Scanner(System.in);
                        Integer num_stores = Integer.parseInt(reader15.nextLine());
                        Integer num_months = Integer.parseInt(reader15.nextLine());
                        properCase15.setInt(1, num_stores);
                        properCase15.setInt(2, num_months);
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
                    break;
                case(16):
                    try(PreparedStatement properCase16 = conn.prepareStatement("select * from top_spenders(?, ?)")){
                        conn.setAutoCommit(false);
                        System.out.println("Enter number of customers and number of months: ");
                        Scanner reader16 = new Scanner(System.in);
                        Integer num_cust = Integer.parseInt(reader16.nextLine());
                        Integer num_months = Integer.parseInt(reader16.nextLine());
                        properCase16.setInt(1, num_cust);
                        properCase16.setInt(2, num_months);
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
                    break;
            }
        }
        System.out.println("Thank you for using the BoutiqueCoffee database!");
    }
}