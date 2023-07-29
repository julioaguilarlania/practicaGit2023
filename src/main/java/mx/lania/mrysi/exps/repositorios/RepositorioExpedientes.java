package mx.lania.mrysi.exps.repositorios;

import java.time.LocalDate;
import java.util.List;
import mx.lania.mrysi.exps.entidades.EstatusExpediente;
import mx.lania.mrysi.exps.entidades.Expediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jaguilar
 */
public interface RepositorioExpedientes  extends JpaRepository<Expediente, Integer>{
    
      
    //List<Expediente> getPorPersona(Integer idPersona);
    @Query(value="SELECT e FROM Expediente e WHERE e.persona.idPersona = :idPersona")
    List<Expediente> buscarPorIdPersona(@Param("idPersona") Integer idPersona);
    
    @Query(value="SELECT * FROM expedientes WHERE id_persona = :idPersona", nativeQuery = true)
    List<Expediente> buscarPorIdPersona2(@Param("idPersona") Integer idPersona);
    
    List<Expediente> findByEstatus(EstatusExpediente estatus);
    
    
    @Procedure(procedureName = "marcarExpedientesNoAtendidos")
    Long marcarExpedientesPorAtender(LocalDate fechaCorte);
    
    //public Expediente guardarExpediente(Integer idExpediente, String nombrePersona,...);
    //public Expediente guardarExpediente(Expediente exp);

    
    @Query(value="SELECT CONCAT(p.nombres ,' ',p.apellido_paterno) AS nombrePersona,"
            + " e.id_expediente AS idExpediente, e.estatus"
    + " FROM personas p JOIN expedientes e ON e.id_persona = p.id_persona", nativeQuery = true)
    //List<QPersonaExpediente> consultarExpedientesPersona();
    List<Object[]> consultarExpedientesPersona();

    /* Version nativa para Oracle
    @Query(value="SELECT p.nombres  || ' ' || p.apellido_paterno AS nombrePersona,"
            + " e.id_expediente AS idExpediente, e.estatus"
    + " FROM personas p JOIN expedientes e ON e.id_persona = p.id_persona", nativeQuery = true)
    //List<QPersonaExpediente> consultarExpedientesPersona();
    List<Object[]> consultarExpedientesPersonaDos();
    */
    
    @Modifying
    @Query(value="UPDATE expedientes SET estatus='CERRADO' WHERE estatus='RESUELTO'"
            + " AND cambio_estatus > :fecha", nativeQuery = true)
    public void cerrarExpedientesResueltos(@Param("fecha") LocalDate fecha);
    
}
