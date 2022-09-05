package Modelo;

import java.util.List;

/**
 *
 * @author Steven
 */
public interface AsignaturaCRUD {
    public List listarAsignaturas();
    public String agregarAsignatura(String nombre, String tipo, int cred, int matri);
    public String actualizarAsignatura(int id, String nombre, String tipo, int cred, int matri);
    public String eliminarAsignatura(int id);
    public Asignatura buscarAsignatura(int id);
    public List verEstudiantes(int id);
}
