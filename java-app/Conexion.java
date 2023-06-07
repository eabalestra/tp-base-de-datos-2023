import java.sql.*;

/**
 * 
 */
public class Conexion {
  //Establece la conexion

  private static String url = "jdbc:postgresql://localhost:5432/postgres";
  private static String username = "postgres";
  private static String password = "pepa200108";
  private static Connection connection = null;

  public static Connection getInstance() throws SQLException{
    if (connection == null){
      connection = DriverManager.getConnection(url, username, password);
    }
    return connection;
  }

  public static void main(String[] args) {
    try {
      Connection conn = getInstance();
      if (conn != null) {
        System.out.println("Conexión exitosa a la base de datos.");
        // Realiza aquí tus operaciones con la base de datos
      } else {
        System.out.println("No se pudo establecer la conexión.");
      }
    } catch (SQLException e) {
      System.out.println("Error al establecer la conexión: " + e.getMessage());
    }
  }
  

} // end Conexion