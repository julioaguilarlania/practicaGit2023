package mx.lania.mrysi.exps.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author jaguilar
 */
@Entity
@Table(name = "expedientes")
public class Expediente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_expediente")
    private Integer idExpediente;
    
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
    
    @Basic(optional = false)
    @Column(name = "estatus")
    @Enumerated(EnumType.STRING)
    private EstatusExpediente estatus;
    
    @Basic(optional = false)
    @Column(name = "cambio_estatus")
    private LocalDateTime cambioEstatus;
    
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(cascade =CascadeType.PERSIST)
    private Persona persona;

    /*
    // OPCION: Se puede ignorar la relacion con una entidad y mapear la columna
    // como su valor basico
    @Basic(optional = false)
    @Column(name = "id_persona")
    private Integer idPersona;
    */

    public Expediente() {
    }

    public Expediente(Integer idExpediente, Persona persona) {
        this.idExpediente = idExpediente;
        this.persona = persona;
        this.fechaCreacion = LocalDate.now();
        this.estatus = EstatusExpediente.ABIERTO;
        this.cambioEstatus = LocalDateTime.now();
    }

    public Integer getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Integer idExpediente) {
        this.idExpediente = idExpediente;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public EstatusExpediente getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusExpediente estatus) {
        this.estatus = estatus;
    }

    public LocalDateTime getCambioEstatus() {
        return cambioEstatus;
    }

    public void setCambioEstatus(LocalDateTime cambioEstatus) {
        this.cambioEstatus = cambioEstatus;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
