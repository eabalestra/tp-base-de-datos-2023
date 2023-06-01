import java.sql.*;
import java.util.List;
import java.util.Scanner;

/**
 * 
 */
public class App {

  // CONFIGURACION
  private static final String postgresDriver = "org.postgresql.Driver";
  private static final String EXIT = "4";
  private static final String START = "";
  private static final List<String> tiposCarnet = List.of("B1","B2","B3");
  private static Scanner sc = new Scanner(System.in);
  //



  public static void main(String[] args) {
    
    try {
      
      //Carga del driver de PostgreSQL si no está cargado.
      Class.forName(postgresDriver);


      String opcion = START;

      //Mostrar menú y leer opción
      while (!opcion.equals(EXIT)) {
        
        imprimirMenu();
        opcion = sc.next();
        System.out.println();

        switch (opcion) {
          case "1":
            //insertarClase();
            break;

          case "2":
            //registrarCliente();
            break;

          case "3":
            listarRegistroDeClientes();
            break;

          case EXIT:
            System.out.println("Muchas gracias!\n");
            break;

          default:
            System.out.println("Opcion no disponible. Intente nuevamente.\n");
            break;
        }
      }

    } catch (ClassNotFoundException cnfe) {

      System.err.println("Error loading driver: " + cnfe);

    } catch (SQLException sqle) {
      try	
        {
         System.err.println("Antes rollback: " + sqle);
         Connection connection = Conexion.getInstance();
         connection.rollback();
         System.err.println("Error Se produjo una Excepcion accediendo a la base de datoas: " + sqle);
         sqle.printStackTrace();
        } 
        catch(Exception e)
        {
          e.printStackTrace();
          System.err.println("Error Ejecutando el rollback de la transaccion: " + e.getMessage());
        }
    }

  }

  /**
   * Funcion que lista los clientes.
   * @throws SQLException
   */
  private static void listarRegistroDeClientes() throws SQLException{
    
    Connection connection = Conexion.getInstance();

    String query = "SELECT dni_cliente, nombre, apellido, direccion FROM asiste as a JOIN cliente as c ON(c.dni_cliente = a.dni_cliente) ORDER BY apellido;";

    PreparedStatement statement = connection.prepareStatement(query);
    ResultSet resultSet = statement.executeQuery();
    System.out.println("Listado de clientes: \n");
    while(resultSet.next()){
      System.out.print("* DNI: " + resultSet.getString("dni_cliente"));
      System.out.print("; Nombre: "+resultSet.getString("nombre"));
      System.out.print("; Apellido: "+resultSet.getString("apellido"));
      System.out.print("; Direccion: "+resultSet.getString("direccion"));
      System.out.print("\n\n");
    }

  }

  public static void imprimirMenu(){
    System.out.println("-------------------------------");
    System.out.println(" ESCUELA DE MANEJO");
    System.out.println("-------------------------------");
    System.out.println("1) Insertar nueva clase.");
    System.out.println("2) Registrar un nuevo cliente.");
    System.out.println("3) Listar registro de clientes.");
    System.out.println("4) Salir");
    System.out.println("--------------------------------");
    System.out.print("Seleccione una opcion (1 a 4): ");
  }

  // public static void insertarCliente(Connection c){
  //   try (Scanner scanner = new Scanner(System.in)) {
  //     System.out.print("Inserte DNI\n");
  //     String dni = scanner.nextLine();
  //     System.out.print("Inserte nombre\n");
  //     String nombre = scanner.nextLine();
  //     System.out.print("Inserte apellido\n");
  //     String apellido = scanner.nextLine();
  //     System.out.print("Inserte direccion\n");
  //     String direccion = scanner.nextLine();
  //     try{
  //     Statement statement = (Statement) c.createStatement();
  //     String query = "INSERT INTO cliente (dni_cliente, nombre, apellido, direccion,) VALUES (\""+dni+"\", \""+nombre+"\", \""+apellido+"\", \""+direccion+"\");";
  //     int resultInsert = ((java.sql.Statement) statement).executeUpdate(query);
  //     if(resultInsert==1){
  //       System.out.print("Insertado correctamente \n");
  //     }
  //     else{
  //       System.out.print("Error: por favor intente de nuevo \n");
  //     }
  //     }
  //     catch(Exception e){ System.out.println(e);}
  //   }
  // }

  // public static void insertarClase(Connection c){
  //   try (Scanner scanner = new Scanner(System.in)) {
  //     System.out.print("Inserte codigo de la clase\n");
  //     String cod_clase = scanner.nextLine();
  //     System.out.print("Inserte nombre\n");
  //     String nombre = scanner.nextLine();
  //     System.out.print("Inserte descripcion\n");
  //     String descripcion = scanner.nextLine();
  //     System.out.print("Inserte cupo maximo\n");
  //     String cupo = scanner.nextLine();
  //     System.out.print("Inserte DNI de la secretaria\n");
  //     String dni_Secretaria = scanner.nextLine();
  //     System.out.print("Inserte DNI del instructor\n");
  //     String dni_Instructor = scanner.nextLine();
  //     try{
  //     Statement statement = (Statement) c.createStatement();
  //     String query = "INSERT INTO clase (cod_clase, nombre, descripcion, cupo_max, dni_secretaria, dni_instructor) VALUES (\""+cod_clase+"\", \""+nombre+"\", \""+descripcion+"\", \""+cupo+"\",\""+dni_Secretaria+"\",\""+dni_Instructor+"\");";
  //     int resultInsert = ((java.sql.Statement) statement).executeUpdate(query);
  //     if(resultInsert==1){
  //       System.out.print("Insertado correctamente \n");
  //     }
  //     else{
  //       System.out.print("Error: por favor intente de nuevo \n");
  //     }
  //     }
  //     catch(Exception e){ System.out.println(e);}
  //   }
  // }

  // public static void clienteAsisteClase(Connection c){
  //   Scanner scanner = new Scanner(System.in);
  //   System.out.print("Inserte DNI del cliente\n");
  //   String dni = scanner.nextLine();
  //   System.out.print("Inserte codigo de la clase\n");
  //   String cod_clase = scanner.nextLine();
  //   try{
  //   Statement statement = c.createStatement();
  //   String query = "INSERT INTO asiste (dni_cliente, cod_clase) VALUES (\""+dni+"\", \""+cod_clase+"\");";
  //   int resultInsert = statement.executeUpdate(query);
  //   if(resultInsert==1){
  //     System.out.print("Insertado correctamente \n");
  //   }
  //   else{
  //     System.out.print("Error: por favor intente de nuevo \n");
  //   }
  //   }
  //   catch(Exception e){ System.out.println(e);}
  // }

}
