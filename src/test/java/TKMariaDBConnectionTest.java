import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class TKMariaDBConnectionTest {
    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    private static final String LOCAL_DATABASE_URL = "jdbc:mariadb://localhost:3306/bongsamoa_test";
    private static final String REMOTE_DATABASE_URL = "";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Test
    public void testLocalConnection() throws Exception {
        Class.forName(DRIVER);

        try (Connection connection = DriverManager.getConnection(LOCAL_DATABASE_URL, USER, PASSWORD)) {
            System.out.println(connection);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testRemoteConnection() throws Exception {
        Class.forName(DRIVER);

//        try {
//
//        } catch (Exception e) {
//
//        }
    }
}