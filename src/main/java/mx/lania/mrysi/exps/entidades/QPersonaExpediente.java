package mx.lania.mrysi.exps.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author jaguilar
 */
@Entity
public class QPersonaExpediente {
    private String nombrePersona;
    @Id
    private Integer idExpediente;
    private String estatus;

    public QPersonaExpediente() {
    }

    public QPersonaExpediente(String nombrePersona, Integer idExpediente, String estatus) {
        this.nombrePersona = nombrePersona;
        this.idExpediente = idExpediente;
        this.estatus = estatus;
    }

    
    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public Integer getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Integer idExpediente) {
        this.idExpediente = idExpediente;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
