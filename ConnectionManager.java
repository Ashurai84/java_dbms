import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection connection = null;

    private ConnectionManager() {}

    public static Connection getConnection() {
        if (connection == null) {  // Only create a new connection if one doesn't exist
            try {



                // Connect to the database
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/TEST", "root", "Ashutosh");

                System.out.println("✅ Database connected successfully!");

            }  catch (SQLException sq) {
                sq.printStackTrace();
                // Print the actual error message
            }
        }
        return connection;
    }

    public static void main(String[] args) {
        try (Connection connection = ConnectionManager.getConnection()) {
            if (connection != null) {
                System.out.println("🔗 Connection object: " + connection);
            } else {
                System.out.println("❌ Connection failed! Check credentials & database.");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block

        }
    }
}
