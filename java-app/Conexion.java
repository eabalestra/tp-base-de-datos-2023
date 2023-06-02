import java.sql.*;

/**
 * 
 */
public class Conexion {
  //Establece la conexion
  private static String url = "jdbc:postgresql://localhost:5432/postgres";
  private static String username = "postgres";
  private static String password = "99428143";
  private static Connection connection = null;

  public static Connection getInstance() throws SQLException{
    if (connection == null){
      connection = DriverManager.getConnection(url, username, password);
    }
    return connection;
  }

} // end Conexion