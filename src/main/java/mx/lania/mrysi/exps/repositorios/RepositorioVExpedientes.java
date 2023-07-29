package mx.lania.mrysi.exps.repositorios;

import mx.lania.mrysi.exps.entidades.VExpedientesCerrados;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jaguilar
 */
public interface RepositorioVExpedientes extends JpaRepository<VExpedientesCerrados, String>{
    
}
