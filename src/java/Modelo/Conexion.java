package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Steven
 */
public class Conexion {
    Connection conn;
    private final String url = "jdbc:mysql://localhost:3306/bd_institucion";
    private final String user = "root";
    private final String pass = "";
    
    public Conexion(){
    }
    
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
            return conn;
        } catch (Exception e) {
            System.err.println("No se pudo conectar a la base de datos. " + e);
            throw new RuntimeException(e);
        }
    }
    
    public void cerrarConexion(){
        try {
            conn.close();
        } catch (Exception e) {
            System.err.println("Error al cerrar la conexión");
        }
    }
}
