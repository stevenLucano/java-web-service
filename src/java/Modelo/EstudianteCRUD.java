package Modelo;

import java.util.List;

/**
 *
 * @author Steven
 */
public interface EstudianteCRUD {
    public List listarEstudiantes();
    public String agregarEstudiante(String ident, String nom, String p_ape, String s_ape, String nac, String cel, String carr);
    public String actualizarEstudiante(int id, String ident, String nom, String p_ape, String s_ape, String nac, String cel, String carr);
    public String eliminarEstudiante(int id);
    public Estudiante buscarEstudiante(int id, int ident, String p_ape);
    public String cambioAsignaturas(int idEst, int idAsg, int acc);
    public List verAsignaturas(int id);
}