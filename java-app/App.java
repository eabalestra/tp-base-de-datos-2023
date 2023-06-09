import java.sql.*;
//import java.util.List;
import java.util.Scanner;

/**
 * 
 */
public class App {

  // CONFIGURACION
  private static final String postgresDriver = "org.postgresql.Driver";
  private static final String EXIT = "5";
  private static final String START = "";
  //private static final List<String> tiposCarnet = List.of("B1","B2","B3");
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
            insertarClase();
            break;
          
          case "2":
            insertarCliente();
            break;

          case "3":
            listarRegistroDeClientes();
            break;

          case "4":
            clienteAsisteClase();
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
      sqle.printStackTrace();
      System.err.println("Error connecting: " + sqle);
    }

  }
  
  /**
   * 
   */
  public static void imprimirMenu(){
    System.out.println("-------------------------------");
    System.out.println(" ESCUELA DE MANEJO ");
    System.out.println("-------------------------------");
    System.out.println("1) Insertar nueva clase.");
    System.out.println("2) Registrar un nuevo cliente.");
    System.out.println("3) Listar registro de clientes que asisten a alguna clase.");
    System.out.println("4) Inscribir cliente a una clase.");
    System.out.println("5) Salir");
    System.out.println("--------------------------------");
    System.out.print("Seleccione una opcion (1 a 5): ");
  }

  /**
   * Funcion que lista los clientes.
   * @throws SQLException
   */
  private static void listarRegistroDeClientes() throws SQLException{
    try {
      Connection connection = Conexion.getInstance();

      String query = "SET search_path TO maneja_seguro; SELECT maneja_seguro.cliente.dni_cliente, maneja_seguro.cliente.nombre, maneja_seguro.cliente.apellido, maneja_seguro.cliente.direccion FROM maneja_seguro.cliente JOIN maneja_seguro.asiste ON (maneja_seguro.asiste.dni_cliente = maneja_seguro.cliente.dni_cliente)";

      Statement statement = connection.createStatement();

      ResultSet resultSet = statement.executeQuery(query);
      
      System.out.println("Listado de clientes: \n");
      while(resultSet.next()){
        System.out.print("* DNI: " + resultSet.getString("dni_cliente"));
        System.out.print("; Nombre: "+resultSet.getString("nombre"));
        System.out.print("; Apellido: "+resultSet.getString("apellido"));
        System.out.print("; Direccion: "+resultSet.getString("direccion"));
        System.out.print("\n\n");
      }
      
      resultSet.close();
    } catch (Exception e) {
      System.out.println("ERROR: "+e);
    }
  }

  private static void insertarCliente() throws SQLException {
    try {
      Connection connection = Conexion.getInstance();
      Statement statement = connection.createStatement();

      System.out.println("INGRESE LOS DATOS DEL CLIENTE: ");
      System.out.print("Inserte DNI\n");
      String dni = sc.next();
      System.out.print("Inserte nombre\n");
      String nombre = sc.next();
      System.out.print("Inserte apellido\n");
      String apellido = sc.next();
      System.out.print("Inserte direccion\n");
      String direccion = sc.next();

      String query = "SET search_path TO maneja_seguro; INSERT INTO maneja_seguro.cliente (dni_cliente, nombre, apellido, direccion) VALUES ('" + dni + "', '" + nombre + "', '" + apellido + "', '" + direccion + "');";

      int resultInsert = statement.executeUpdate(query);

      if (resultInsert == 1) {
          System.out.print("Insertado correctamente \n");
      }

    } catch (Exception e) {
        System.out.println("ERROR: " + e);
    }
  }

  private static void insertarClase(){
    try {
      Connection connection = Conexion.getInstance();
      Statement statement = connection.createStatement();

      System.out.print("Inserte nombre\n");
      String nombre = sc.next();
      sc.nextLine();
      System.out.print("Inserte descripcion\n");
      String descripcion = sc.nextLine();
      System.out.print("Inserte cupo maximo\n");
      String cupo = sc.next();
      System.out.print("Inserte DNI de la secretaria\n");
      String dni_Secretaria = sc.next();
      System.out.print("Inserte DNI del instructor\n");
      String dni_Instructor = sc.next();


      String query = "SET search_path TO maneja_seguro; INSERT INTO maneja_seguro.clase (nombre, descripcion, cupo_max, dni_secretaria, dni_instructor) VALUES ('" + nombre + "', '" + descripcion + "', '" + cupo + "', '" + dni_Secretaria + "', '" + dni_Instructor + "');";

      int resultInsert = statement.executeUpdate(query);

      if(resultInsert==1){
        System.out.print("Insertada correctamente \n");
      }
      else{
        System.out.print("Error: por favor intente de nuevo \n");
      }
      }
      catch(Exception e){ System.out.println(e);} 
    }

    private static void clienteAsisteClase(){
      try{
      Connection connection = Conexion.getInstance();
      Statement statement = connection.createStatement();

     
      System.out.print("Inserte DNI del cliente\n");
      String dni = sc.next();
      System.out.print("Inserte codigo de la clase\n");
      String cod_clase = sc.next();
      
      String query = "INSERT INTO asiste (dni_cliente, cod_clase) VALUES ('"+dni+"', '"+cod_clase+"');";
      
      int resultInsert = statement.executeUpdate(query);
      
      if(resultInsert==1){
        System.out.print("Insertado correctamente \n");
      }
      else{
        System.out.print("Error: por favor intente de nuevo \n");
      }
      }
      catch(Exception e){   System.out.println("Error al insertar el cliente en la clase: " + e.getMessage());
      e.printStackTrace();} 
    }  

}