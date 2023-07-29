package mx.lania.mrysi.exps.repositorios;

import java.util.List;
import mx.lania.mrysi.exps.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author jaguilar
 */
public interface RepositorioPersonas extends JpaRepository<Persona, Integer>{
    
    public List<Persona> findByCurp(String curp);
    
    public List<Persona> findByCurpStartingWithIgnoreCase(String cadena);
    
    //public List<Persona> findByNombreContainsIgnoreCaseAndFechaRegistroAfter(String n, Date f);
    
    @Query("SELECT p.nombres,p.apellidoPaterno,p.apellidoMaterno FROM Persona p")
    public List<Persona> consultarNombres();
}
