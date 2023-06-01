import java.sql.*;



 public class Conexion {

  public static void main(String[] args) {

    String driver = "org.postgresql.Driver";
    String url = "jdbc:postgresql://localhost:5432/maneja_seguro";
    String username = "postgres";
    String password = "123456";

    try {

      Class.forName(driver);

      Connection connection = DriverManager.getConnection(url, username, password);

      String query = "SELECT * FROM cliente ";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        System.out.println(resultSet.getInt("id"));
      }

    } 
    catch (SQLException e) {
      System.out.println("Error: "+ e);;
    }
  }

}