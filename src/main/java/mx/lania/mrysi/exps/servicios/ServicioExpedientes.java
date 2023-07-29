package mx.lania.mrysi.exps.servicios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import mx.lania.mrysi.exps.entidades.EstatusExpediente;
import mx.lania.mrysi.exps.entidades.Expediente;
import mx.lania.mrysi.exps.entidades.Persona;
import mx.lania.mrysi.exps.entidades.QPersonaExpediente;
import mx.lania.mrysi.exps.repositorios.RepositorioExpedientes;
import mx.lania.mrysi.exps.repositorios.RepositorioPersonas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jaguilar
 */
@Service
public class ServicioExpedientes {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ServicioExpedientes.class);

    private RepositorioExpedientes repoExpedientes;
    private RepositorioPersonas repoPersonas;

    public ServicioExpedientes(RepositorioExpedientes repoExpedientes,
            RepositorioPersonas repoPersonas) {
        LOGGER.debug("new ServicioExpedientes");
        this.repoExpedientes = repoExpedientes;
        LOGGER.debug("RepoExpedientes: " + this.repoExpedientes.getClass().getName());
        this.repoPersonas = repoPersonas;
        LOGGER.debug("ServicioExpedientes " + this.getClass().toString());
    }
    
    @Transactional(timeout = 10000) // Hace un rollback si la operacion tarda mas de 10 segundos
    public List<Expediente> getPorAtender() {
        LocalDate fecha = LocalDate.now().minus(2, ChronoUnit.WEEKS);
        Long expedientesMarcados = repoExpedientes.marcarExpedientesPorAtender(fecha);
        return repoExpedientes.findByEstatus(EstatusExpediente.PRIORIDAD_ATENCION);
    }

    @Transactional
    public Expediente guardar(Expediente exp) {
        if (exp.getPersona() != null &&
                exp.getPersona().getIdPersona() != null) {
            Optional<Persona> opersona = repoPersonas.findById(exp.getPersona().getIdPersona());
            if (opersona.isEmpty()) {
                throw new DataIntegrityViolationException("No hay persona con el id " + exp.getPersona().getIdPersona());
            }
            else {
                exp.setPersona(opersona.get());
            }
        }
        else if (exp.getPersona() != null) {
            // Aqui pueden ir validaciones sobre la informacion de persona
            Persona npersona = repoPersonas.save(exp.getPersona());
            exp.setPersona(npersona);
        }
        
        exp.setFechaCreacion(LocalDate.now());
        exp.setEstatus(EstatusExpediente.ABIERTO);
        exp.setCambioEstatus(LocalDateTime.now());
        
        return repoExpedientes.save(exp);
    }

    @Transactional
    public Expediente eliminarPorId(Integer idExp) {
        Optional<Expediente> oexp = repoExpedientes.findById(idExp);
        if (oexp.isEmpty()) {
            throw new DataIntegrityViolationException("No hay expediente con el id " + idExp);
        }
        if (!EstatusExpediente.CERRADO.equals(oexp.get().getEstatus())) {
            throw new ReglaDeNegociosException("No se puede eliminar un expediente no CERRADO");
        }
        repoExpedientes.deleteById(idExp);
        return oexp.get();
    }

    @Transactional
    public Expediente cambiarEstatus(Integer idExp, String estatus) {
        EstatusExpediente nestatus = EstatusExpediente.valueOf(estatus);
        Optional<Expediente> oexp = repoExpedientes.findById(idExp);
        if (oexp.isEmpty()) {
            throw new DataIntegrityViolationException("No hay expediente con el id " + idExp);
        }
        oexp.get().setEstatus(nestatus);
        return repoExpedientes.save(oexp.get());
    }

    @Transactional(readOnly = true) // No usa transacciones para este metodo
    public List<QPersonaExpediente> consultarExpedientesPersona() {
        List<Object[]> resultados = repoExpedientes.consultarExpedientesPersona();
        return resultados
                .stream()
                .map(o-> new QPersonaExpediente((String) o[0],Integer.parseInt(o[1].toString()),(String) o[2]))
                .toList();
    }
}
