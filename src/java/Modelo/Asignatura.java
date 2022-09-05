package Modelo;

/**
 *
 * @author Steven
 */
public class Asignatura {
    int idAsignatura;
    String nombre;
    String tipo;
    int creditos;
    int matriculados;

    public Asignatura() {
    }

    public Asignatura(int idAsignatura, String nombre, String tipo, int creditos, int matriculados) {
        this.idAsignatura = idAsignatura;
        this.nombre = nombre;
        this.tipo = tipo;
        this.creditos = creditos;
        this.matriculados = matriculados;
    }

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getMatriculados() {
        return matriculados;
    }

    public void setMatriculados(int matriculados) {
        this.matriculados = matriculados;
    }
}
