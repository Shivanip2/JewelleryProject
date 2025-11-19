import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    static String URL = "jdbc:mysql://localhost:3306/jewellery_db";
    static String USER = "root";
    static String PASS = "mysql@123"; // <-- change this

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}