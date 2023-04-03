package sawant.mihir.jdbcservlet.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {

    private static final String USER_NAME = "root";
    private static final String PASSWORD = "toor";
    public static Connection getDbConnection(){


        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/company?user=%s&password=%s".formatted(USER_NAME, PASSWORD));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
