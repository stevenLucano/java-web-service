package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steven
 */
public class EstudianteDAO implements EstudianteCRUD{

    PreparedStatement ps = null;
    ResultSet rs = null; 
    Connection conn = null;
    Conexion conexion = new Conexion();
    
    int resultado;
    String mensaje;
    
    /*
    * Crear una lista de todos los estudiantes de la base de datos
    */
    @Override
    public List listarEstudiantes() {
        String sql = "SELECT * FROM estudiante";
        List<Estudiante> estudiantes = new ArrayList();
        try {
            conn = conexion.getConnection();
            //System.out.println("Conexión cerrada");
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Estudiante est = new Estudiante();
                est.setIdEstudiante(rs.getInt("id_estudiante"));
                est.setIdentificacion(rs.getString("nro_identificacion"));
                est.setNombres(rs.getString("nombres"));
                est.setpApellido(rs.getString("p_apellido"));
                est.setsApellido(rs.getString("s_apellido"));
                est.setNacimiento(rs.getString("nacimiento"));
                est.setCelular(rs.getString("nro_celular"));
                est.setCarrera(rs.getString("carrera"));
                
                estudiantes.add(est);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos: " + e);
        } finally {
            try {rs.close();} catch (SQLException e) {System.err.println("Error al cerrar el resultSet: " + e);}
            try {ps.close();} catch (SQLException e) {System.err.println("Error al cerrar el preparedStatement: " + e);}
            try {conn.close();} catch (SQLException e) {System.err.println("Error al cerrar la conexion: " + e);}
        }
        return estudiantes;
    }

    /*
    * Insertar un nuevo estudiante en la base de datos
    */
    @Override
    public String agregarEstudiante(String ident, String nom, String p_ape, String s_ape, String nac, String cel, String carr) {
        String sql = "INSERT INTO estudiante(nro_identificacion, nombres, p_apellido, s_apellido, nacimiento, nro_celular, carrera)values(?,?,?,?,?,?,?)";
        try {
            conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, ident);
            ps.setString(2, nom);
            ps.setString(3, p_ape);
            ps.setString(4, s_ape);
            ps.setString(5, nac);
            ps.setString(6, cel);
            ps.setString(7, carr);
            
            resultado = ps.executeUpdate();
            
            if(resultado==1){
                mensaje = "Usuario agregado con exito.";
            } else {
                mensaje = "error";
            }
        } catch (SQLException e) {
            mensaje = "error";
            System.err.println("Error al agregar el estudiante: " + e);
        } finally {
            try {rs.close();} catch (SQLException e) {System.err.println("Error al cerrar el resultSet: " + e);}
            try {ps.close();} catch (SQLException e) {System.err.println("Error al cerrar el preparedStatement: " + e);}
            try {conn.close();} catch (SQLException e) {System.err.println("Error al cerrar la conexion: " + e);}
        }
        return mensaje;
    }

    /*
    * Actualizar un estudiante de la base de datos
    */
    @Override
    public String actualizarEstudiante(int id, String ident, String nom, String p_ape, String s_ape, String nac, String cel, String carr) {
        String sql = "UPDATE estudiante SET nro_identificacion=?, nombres=?, p_apellido=?, s_apellido=?, nacimiento=?, nro_celular=?, carrera=? WHERE id_estudiante="+id;
        try {
            conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, ident);
            ps.setString(2, nom);
            ps.setString(3, p_ape);
            ps.setString(4, s_ape);
            ps.setString(5, nac);
            ps.setString(6, cel);
            ps.setString(7, carr);
            
            resultado = ps.executeUpdate();
            
            if(resultado ==1){
                mensaje = "Usuario Actualizado...";
            } else {
                mensaje = "Ocurrió un error";
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el usuario: " + e);
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
    public String eliminarEstudiante(int id) {
        String sql="DELETE FROM estudiante WHERE id_estudiante="+id;
        try {
            conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            resultado = ps.executeUpdate();
            
            if(resultado == 1){
                mensaje = "correcto";
            } else {
                mensaje = "error";
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el estudiante: " + e);
        } finally {
            try {rs.close();} catch (SQLException e) {System.err.println("Error al cerrar el resultSet: " + e);}
            try {ps.close();} catch (SQLException e) {System.err.println("Error al cerrar el preparedStatement: " + e);}
            try {conn.close();} catch (SQLException e) {System.err.println("Error al cerrar la conexion: " + e);}
        }
        return mensaje;
    }
    
    /*
    * Buscar un estudiante en la base de datos por su userId, identificación o primer apellido
    */
    @Override
    public Estudiante buscarEstudiante(int id, int ident, String p_ape){
        String sql;
        
        if(id != 0){
            sql="SELECT * FROM estudiante WHERE id_estudiante="+id;
        } else if(ident != 0){
            sql="SELECT * FROM estudiante WHERE nro_identificacion="+ident;
        } else {
            sql="SELECT * FROM estudiante WHERE p_apellido='"+p_ape+"'";
        }
        
        Estudiante est = new Estudiante();
        try {
            conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                est.setIdEstudiante(rs.getInt("id_estudiante"));
                est.setIdentificacion(rs.getString("nro_identificacion"));
                est.setNombres(rs.getString("nombres"));
                est.setpApellido(rs.getString("p_apellido"));
                est.setsApellido(rs.getString("s_apellido"));
                est.setNacimiento(rs.getString("nacimiento"));
                est.setCelular(rs.getString("nro_celular"));
                est.setCarrera(rs.getString("carrera"));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el estudiante: " + e);
        } finally {
            try {rs.close();} catch (SQLException e) {System.err.println("Error al cerrar el resultSet: " + e);}
            try {ps.close();} catch (SQLException e) {System.err.println("Error al cerrar el preparedStatement: " + e);}
            try {conn.close();} catch (SQLException e) {System.err.println("Error al cerrar la conexion: " + e);}
        }
        return est;
    }

    /*
    * Asignar o desasignar asignaturas a un estudiante
    */
    @Override
    public String cambioAsignaturas(int idEst, int idAsg, int acc) {
        String sql = "";
        // Acc = 1 => Asignar; Acc = 0 => Desasignar
        if(acc == 1){
            // Se busca si existe ya un registro de id_asignatura e id_estudiante
            sql = "SELECT COUNT(*)AS total FROM estudiante_asignatura WHERE id_estudiante=? AND id_asignatura=?;";
            int total = 0;
            try {
                conn = conexion.getConnection();
                ps = conn.prepareStatement(sql);
                ps.setInt(1, idEst);
                ps.setInt(2, idAsg);
                rs = ps.executeQuery();
                while(rs.next()){
                    total = rs.getInt("total");
                }
            } catch (SQLException e) {
                System.err.println("Error al buscar la coincidencia: " + e);
            } finally {
                try {rs.close();} catch (SQLException e) {System.err.println("Error al cerrar el resultSet: " + e);}
                try {ps.close();} catch (SQLException e) {System.err.println("Error al cerrar el preparedStatement: " + e);}
                try {conn.close();} catch (SQLException e) {System.err.println("Error al cerrar la conexion: " + e);}
            }
            // Si no existe (total = 0) se inserta la nueva relación
            if (total != 1){
                sql = "INSERT INTO estudiante_asignatura(id_estudiante,id_asignatura) VALUES (?,?);";
                try {
                    conn = conexion.getConnection();
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1, idEst);
                    ps.setInt(2, idAsg);
                    resultado = ps.executeUpdate();
                } catch (SQLException e) {
                    System.err.println("Error al asignar la asignatura: " + e);
                } finally {
                    try {rs.close();} catch (SQLException e) {System.err.println("Error al cerrar el resultSet: " + e);}
                    try {ps.close();} catch (SQLException e) {System.err.println("Error al cerrar el preparedStatement: " + e);}
                    try {conn.close();} catch (SQLException e) {System.err.println("Error al cerrar la conexion: " + e);}
                }
                
                // Si fue exitosa la nueva relación se incrementa el número de matriculados
                if(resultado==1){
                    mensaje = "Se asignó la asignatura con exito.";

                    sql = "SELECT matriculados FROM `asignatura` WHERE id_asignatura="+idAsg;
                    int matriculados = 0;
                    try {
                        conn = conexion.getConnection();
                        ps = conn.prepareStatement(sql);
                        rs = ps.executeQuery();
                        while(rs.next()){
                            matriculados = rs.getInt("matriculados");
                        }
                    } catch (SQLException e) {
                        System.err.println("Ocurrió un error buscando los matriculados: " + e);
                    } finally {
                        try {rs.close();} catch (SQLException e) {System.err.println("Error al cerrar el resultSet: " + e);}
                        try {ps.close();} catch (SQLException e) {System.err.println("Error al cerrar el preparedStatement: " + e);}
                        try {conn.close();} catch (SQLException e) {System.err.println("Error al cerrar la conexion: " + e);}
                    }

                    sql = "UPDATE asignatura SET matriculados=? WHERE id_asignatura="+idAsg;
                    try {
                        conn = conexion.getConnection();
                        ps = conn.prepareStatement(sql);
                        ps.setInt(1, matriculados + 1);
                        resultado = ps.executeUpdate();

                        if(resultado == 1){
                            mensaje += " Se actualizó los matriculados.";
                        } else {
                            mensaje = "Ocurrió un error al actualizar los matriculados.";
                        }
                    } catch (SQLException e) {
                        System.err.println("Error actualizando los matriculados: " + e);
                    } finally {
                        try {rs.close();} catch (SQLException e) {System.err.println("Error al cerrar el resultSet: " + e);}
                        try {ps.close();} catch (SQLException e) {System.err.println("Error al cerrar el preparedStatement: " + e);}
                        try {conn.close();} catch (SQLException e) {System.err.println("Error al cerrar la conexion: " + e);}
                    }   
                } else {
                    mensaje = "Ocurrió un error.";
                }
            } else {
                mensaje = "Ya se ha asignado la asignatura.";
            }
        } else {
            // Se elimina la relación id_estudiante e id_asignatura
            sql="DELETE FROM estudiante_asignatura WHERE id_estudiante=? AND id_asignatura=?";
            try {
                conn = conexion.getConnection();
                ps = conn.prepareStatement(sql);
                ps.setInt(1, idEst);
                ps.setInt(2, idAsg);
                resultado = ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al borrar la asignatura: " + e);
            } finally {
                try {rs.close();} catch (SQLException e) {System.err.println("Error al cerrar el resultSet: " + e);}
                try {ps.close();} catch (SQLException e) {System.err.println("Error al cerrar el preparedStatement: " + e);}
                try {conn.close();} catch (SQLException e) {System.err.println("Error al cerrar la conexion: " + e);}
            }
            
            // Se decrementa el número de matriculados de la asignatura
            if(resultado==1){
                mensaje = "Se desasignó la asignatura con exito.";

                sql = "SELECT matriculados FROM `asignatura` WHERE id_asignatura="+idAsg;
                int matriculados = 0;
                try {
                    conn = conexion.getConnection();
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();
                    while(rs.next()){
                        matriculados = rs.getInt("matriculados");
                    }
                } catch (SQLException e) {
                    System.err.println("Ocurrió un error buscando los matriculados: " + e);
                } finally {
                    try {rs.close();} catch (SQLException e) {System.err.println("Error al cerrar el resultSet: " + e);}
                    try {ps.close();} catch (SQLException e) {System.err.println("Error al cerrar el preparedStatement: " + e);}
                    try {conn.close();} catch (SQLException e) {System.err.println("Error al cerrar la conexion: " + e);}
                }

                sql = "UPDATE asignatura SET matriculados=? WHERE id_asignatura="+idAsg;
                try {
                    conn = conexion.getConnection();
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1, matriculados - 1);
                    resultado = ps.executeUpdate();

                    if(resultado == 1){
                        mensaje += " Se actualizó los matriculados.";
                    } else {
                        mensaje = "Ocurrió un error al actualizar los matriculados.";
                    }
                } catch (SQLException e) {
                    System.err.println("Error actualizando los matriculados: " + e);
                } finally {
                    try {rs.close();} catch (SQLException e) {System.err.println("Error al cerrar el resultSet: " + e);}
                    try {ps.close();} catch (SQLException e) {System.err.println("Error al cerrar el preparedStatement: " + e);}
                    try {conn.close();} catch (SQLException e) {System.err.println("Error al cerrar la conexion: " + e);}
                }
            } else {
                mensaje = "Ocurrió un error.";
            }
        }
        return mensaje;
    }

    /*
    * Ver las asignaturas que tiene un estudiante
    */
    @Override
    public List verAsignaturas(int id) {
        String sql="SELECT asignatura.*\n" +
                    "FROM estudiante\n" +
                    "INNER JOIN estudiante_asignatura ON estudiante.id_estudiante = estudiante_asignatura.id_estudiante\n" +
                    "INNER JOIN asignatura ON estudiante_asignatura.id_asignatura = asignatura.id_asignatura\n" +
                    "WHERE estudiante.id_estudiante="+id;
        
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
            System.err.println("Error al traer las asignaturas: " + e);
        } finally {
            try {rs.close();} catch (SQLException e) {System.err.println("Error al cerrar el resultSet: " + e);}
            try {ps.close();} catch (SQLException e) {System.err.println("Error al cerrar el preparedStatement: " + e);}
            try {conn.close();} catch (SQLException e) {System.err.println("Error al cerrar la conexion: " + e);}
        }
        return asignaturas;
    }
}
