package mx.lania.mrysi.exps.otd;

import java.io.Serializable;
import java.time.LocalDate;


/**
 *
 * @author jaguilar
 */
public class PersonaOTD implements Serializable {
    
    private Integer idPersona;
    
    private String nombreCompleto;
    
    private String curp;
    
    private LocalDate fechaNacimiento;
    
    private LocalDate fechaRegistro;

    public PersonaOTD() {
    }

    
    public PersonaOTD(Integer idPersona, String nombres, String apellidoPaterno, String curp, LocalDate fechaNacimiento, LocalDate fechaRegistro) {
        this.idPersona = idPersona;
        this.curp = curp;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
    }

    
    
    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombre) {
        this.nombreCompleto = nombre;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        // Agregar logica validacion
        this.curp = curp;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    public String getEstado() {
        // Logica para obtener estado desde el CURP
        return "";
    }
}
