package WebService;

import Modelo.Asignatura;
import Modelo.AsignaturaDAO;
import Modelo.Estudiante;
import Modelo.EstudianteDAO;
import java.sql.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Steven
 */
@WebService(serviceName = "Servicios")
public class Servicios {
    EstudianteDAO edao = new EstudianteDAO();
    AsignaturaDAO adao = new AsignaturaDAO();
    
    /**
     * Listar los estudiantes existentes
     * @return 
     */
    @WebMethod(operationName = "listarEstudiantes")
    public List<Estudiante> listarEstudiantes() {
        List datos = edao.listarEstudiantes();
        return datos;
    }

    /**
     * Agregar un nuevo estudiante
     * @param nom
     * @param ident
     * @param pApe
     * @param sApe
     * @param cel
     * @param nac
     * @param carr
     * @return 
     */
    @WebMethod(operationName = "agregarEstudiante")
    public String agregarEstudiante(@WebParam(name = "ident") String ident,
            @WebParam(name = "nom") String nom, 
            @WebParam(name = "pApe") String pApe,
            @WebParam(name = "sApe") String sApe,
            @WebParam(name = "nac") String nac,
            @WebParam(name = "cel") String cel, 
            @WebParam(name = "carr") String carr) {
        String datos = edao.agregarEstudiante(ident, nom, pApe, sApe, nac, cel, carr);
        return datos;
    }

    /**
     * Actualizar un estudiante existente
     * @param id
     * @param ident
     * @param nom
     * @param pApe
     * @param sApe
     * @param nac
     * @param cel
     * @param carr
     * @return 
     */
    @WebMethod(operationName = "actualizarEstudiante")
    public String actualizarEstudiante(@WebParam(name = "id") int id,
            @WebParam(name = "ident") String ident,
            @WebParam(name = "nom") String nom, 
            @WebParam(name = "pApe") String pApe,
            @WebParam(name = "sApe") String sApe,
            @WebParam(name = "nac") String nac,
            @WebParam(name = "cel") String cel, 
            @WebParam(name = "carr") String carr) {
        String datos = edao.actualizarEstudiante(id, ident, nom, pApe, sApe, nac, cel, carr);
        return datos;
    }

    /**
     * Eliminar un estudiante existente
     * @param id
     * @return 
     */
    @WebMethod(operationName = "eliminarEstudiante")
    public String eliminarEstudiante(@WebParam(name = "id") int id) {
        String datos = edao.eliminarEstudiante(id);
        return datos;
    }

    /**
     * Listar las asignaturas registradas
     * @return 
     */
    @WebMethod(operationName = "listarAsignaturas")
    public List<Asignatura> listarAsignaturas() {
        List datos = adao.listarAsignaturas();
        return datos;
    }

    /**
     * Agregar nueva asignatura
     * @param nombre
     * @param tipo
     * @param cred
     * @param matri
     * @return 
     */
    @WebMethod(operationName = "agregarAsignatura")
    public String agregarAsignatura(@WebParam(name = "nombre") String nombre, @WebParam(name = "tipo") String tipo, @WebParam(name = "cred") int cred, @WebParam(name = "matri") int matri) {
        String datos = adao.agregarAsignatura(nombre, tipo, cred, matri);
        return datos;
    }

    /**
     * Actualizar asignatura existente
     * @param id
     * @param nombre
     * @param tipo
     * @param matri
     * @param cred
     * @return 
     */
    @WebMethod(operationName = "actualizarAsignatura")
    public String actualizarAsignatura(@WebParam(name = "id") int id, @WebParam(name = "nombre") String nombre, @WebParam(name = "tipo") String tipo, @WebParam(name = "cred") int cred, @WebParam(name = "matri") int matri) {
        String datos = adao.actualizarAsignatura(id, nombre, tipo, cred, matri);
        return datos;
    }

    /**
     * Eliminar asignatura existente
     * @param id
     * @return 
     */
    @WebMethod(operationName = "eliminarAsignatura")
    public String eliminarAsignatura(@WebParam(name = "id") int id) {
        String datos = adao.eliminarAsignatura(id);
        return datos;
    }

    /**
     * Buscar estudiante por su ID
     * @param id
     * @param ident
     * @param p_ape
     * @return 
     */
    @WebMethod(operationName = "buscarEstudiante")
    public Estudiante buscarEstudiante(@WebParam(name = "id") int id, @WebParam(name = "ident") int ident, @WebParam(name = "p_ape") String p_ape) {
        Estudiante est = edao.buscarEstudiante(id, ident, p_ape);
        return est;
    }

    /**
     * Buscar asignatura por su ID
     * @param id
     * @return 
     */
    @WebMethod(operationName = "buscarAsignatura")
    public Asignatura buscarAsignatura(@WebParam(name = "id") int id) {
        Asignatura asg = adao.buscarAsignatura(id);
        return asg;
    }

    /**
     * Asignar asignatura a un estudiante
     * @param idEst
     * @param idAsg
     * @param acc
     * @return 
     */
    @WebMethod(operationName = "cambioAsignaturas")
    public String cambioAsignaturas(@WebParam(name = "idEst") int idEst, @WebParam(name = "idAsg") int idAsg, @WebParam(name = "acc") int acc) {
        String datos = edao.cambioAsignaturas(idEst, idAsg, acc);
        return datos;
    }

    /**
     * Ver asignaturas del estudiante
     * @param idEst
     * @return 
     */
    @WebMethod(operationName = "verAsignaturas")
    public List<Asignatura> verAsignaturas(@WebParam(name = "idEst") int idEst) {
        List<Asignatura> asignaturas = edao.verAsignaturas(idEst);
        return asignaturas;
    }

    /**
     * Ver estudiantes matriculados en la asignatura
     * @param idAsg
     * @return 
     */
    @WebMethod(operationName = "verEstudiantes")
    public List<Estudiante> verEstudiantes(@WebParam(name = "idAsg") int idAsg) {
        List<Estudiante> estudiantes = adao.verEstudiantes(idAsg);
        return estudiantes;
    }
}
