package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Steven
 */
public class AsignaturaDAO implements AsignaturaCRUD{
    
    PreparedStatement ps;
    ResultSet rs; 
    Connection conn;
    Conexion conexion = new Conexion();
    
    int resultado;
    String mensaje;

    /*
    * Crear una lista de todas las asignaturas de la base de datos
    */
    @Override
    public List listarAsignaturas() {
        String sql = "SELECT * FROM asignatura";
        List<Asignatura> asignaturas = new ArrayList();
        try {
            conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Asignatura asg = new Asignatura();
                asg.setIdAsignatura(rs.getInt("id_asignatura"));
                asg.setNombre(rs.getString("nombre"));
                asg.setTipo(rs.getString("tipologia"));
                asg.setCreditos(rs.getInt("creditos"));
                asg.setMatriculados(rs.getInt("matriculados"));
                
                asignaturas.add(asg);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos: " + e);
        } finally {
            try {rs.close();} catch (SQLException e) {System.err.println("Error al cerrar el resultSet: " + e);}
            try {ps.close();} catch (SQLException e) {System.err.println("Error al cerrar el preparedStatement: " + e);}
            try {conn.close();} catch (SQLException e) {System.err.println("Error al cerrar la conexion: " + e);}
        }
        return asignaturas;
    }

    /*
    * Insertar una nueva asignatura en la base de datos
    */
    @Override
    public String agregarAsignatura(String nombre, String tipo, int cred, int matri) {
        String sql = "INSERT INTO asignatura(nombre, tipologia, creditos, matriculados)values(?,?,?,?)";
        try {
            conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, nombre);
            ps.setString(2, tipo);
            ps.setInt(3, cred);
            ps.setInt(4, matri);
            
            resultado = ps.executeUpdate();
            
            if(resultado==1){
                mensaje = "Asignatura agregada con exito.";
            } else {
                mensaje = "error";
            }
        } catch (SQLException e) {
            mensaje = "error";
            System.err.println("Error al agregar la asignatura: " + e);
        } finally {
            try {rs.close();} catch (SQLException e) {System.err.println("Error al cerrar el resultSet: " + e);}
            try {ps.close();} catch (SQLException e) {System.err.println("Error al cerrar el preparedStatement: " + e);}
            try {conn.close();} catch (SQLException e) {System.err.println("Error al cerrar la conexion: " + e);}
        }
        return mensaje;
    }

    /*
    * Actualizar una asignatura de la base de datos
    */
    @Override
    public String actualizarAsignatura(int id, String nombre, String tipo, int cred, int matri) {
        String sql = "UPDATE asignatura SET nombre=?, tipologia=?, creditos=?, matriculados=? WHERE id_asignatura="+id;
        try {
            conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, tipo);
            ps.setInt(3, cred);
            ps.setInt(4, matri);
            
            resultado = ps.executeUpdate();
            
            if(resultado ==1){
                mensaje = "Asignatura Actualizada...";
            } else {
                mensaje = "Ocurrió un error";
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar la asignatura: " + e);
        } finally {
            try {rs.close();} catch (SQLException e) {System.err.println("Error al cerrar el resultSet: " + e);}
            try {ps.close();} catch (SQLException e) {System.err.println("Error al cerrar el preparedStatement: " + e);}
            try {conn.close();} catch (SQLException e) {System.err.println("Error al cerrar la conexion: " + e);}
        }
        return mensaje;
    }

    /*
    * Eliminar un estudiante de la base de datos
    */
    @Override
    public String eliminarAsignatura(int id) {
        String sql="DELETE FROM asignatura WHERE id_asignatura="+id;
        try {
            conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            resultado = ps.executeUpdate();
            
            if(resultado == 1){
                mensaje = "Asignatura Eliminada...";
            } else {
                mensaje = "Ocurrió un error";
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar la asignatura");
        } finally {
            try {rs.close();} catch (SQLException e) {System.err.println("Error al cerrar el resultSet: " + e);}
            try {ps.close();} catch (SQLException e) {System.err.println("Error al cerrar el preparedStatement: " + e);}
            try {conn.close();} catch (SQLException e) {System.err.println("Error al cerrar la conexion: " + e);}
        }
        return mensaje;
    }

    /*
    * Buscar una asignatura en la base de datos por su id
    */
    @Override
    public Asignatura buscarAsignatura(int id) {
        String sql = "SELECT * FROM asignatura WHERE id_asignatura="+id;
        Asignatura asg = new Asignatura();
        try {
            conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                asg.setIdAsignatura(Integer.parseInt(rs.getString("id_asignatura")));
                asg.setNombre(rs.getString("nombre"));
                asg.setTipo(rs.getString("tipologia"));
                asg.setCreditos(Integer.parseInt(rs.getString("creditos")));
                asg.setMatriculados(Integer.parseInt(rs.getString("matriculados")));
            }
        } catch (NumberFormatException | SQLException e) {
            System.err.println("Error al buscar la asignatura: " + e);
        } finally {
            try {rs.close();} catch (SQLException e) {System.err.println("Error al cerrar el resultSet: " + e);}
            try {ps.close();} catch (SQLException e) {System.err.println("Error al cerrar el preparedStatement: " + e);}
            try {conn.close();} catch (SQLException e) {System.err.println("Error al cerrar la conexion: " + e);}
        }
        return asg;
    }

    /*
    * Ver los estudiantes que tiene una asignatura
    */
    @Override
    public List verEstudiantes(int id) {
        String sql="SELECT estudiante.*\n" +
                    "FROM asignatura\n" +
                    "INNER JOIN estudiante_asignatura ON asignatura.id_asignatura = estudiante_asignatura.id_asignatura\n" +
                    "INNER JOIN estudiante ON estudiante_asignatura.id_estudiante = estudiante.id_estudiante\n" +
                    "WHERE asignatura.id_asignatura="+ id + "\n" + 
                    "ORDER BY p_apellido";
        
        List<Estudiante> estudiantes = new ArrayList();
        try {
            conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Estudiante est = new Estudiante();
                est.setIdEstudiante(rs.getInt("id_estudiante"));
                est.setNombres(rs.getString("nombres"));
                est.setpApellido(rs.getString("p_apellido"));
                est.setsApellido(rs.getString("s_apellido"));
                est.setCarrera(rs.getString("carrera"));
                
                estudiantes.add(est);
            }
        } catch (SQLException e) {
            System.err.println("Error al traer las asignaturas: " + e);
        } finally {
            try {rs.close();} catch (SQLException e) {System.err.println("Error al cerrar el resultSet: " + e);}
            try {ps.close();} catch (SQLException e) {System.err.println("Error al cerrar el preparedStatement: " + e);}
            try {conn.close();} catch (SQLException e) {System.err.println("Error al cerrar la conexion: " + e);}
        }
        return estudiantes;
    }
}
