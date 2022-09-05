package Modelo;

/**
 *
 * @author Steven
 */
public class Estudiante {
    int idEstudiante;
    String identificacion;
    String nombres;
    String pApellido;
    String sApellido;
    String nacimiento;
    String celular;
    String carrera;

    public Estudiante() {
    }

    public Estudiante(int idEstudiante, String identificacion, String nombres, String pApellido, String sApellido, String nacimiento, String celular, String carrera) {
        this.idEstudiante = idEstudiante;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.pApellido = pApellido;
        this.sApellido = sApellido;
        this.nacimiento = nacimiento;
        this.celular = celular;
        this.carrera = carrera;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getpApellido() {
        return pApellido;
    }

    public void setpApellido(String pApellido) {
        this.pApellido = pApellido;
    }

    public String getsApellido() {
        return sApellido;
    }

    public void setsApellido(String sApellido) {
        this.sApellido = sApellido;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    
    
}
