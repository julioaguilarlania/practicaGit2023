package mx.lania.mrysi.exps.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jaguilar
 */
@Entity
@Table(name = "expedientes_cerrados_por_mes")
public class VExpedientesCerrados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "mes")
    private String mes;
    @Basic(optional = false)
    @Column(name = "total")
    private long total;

    public VExpedientesCerrados() {
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
    
}
