package mx.lania.mrysi.exps.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import mx.lania.mrysi.exps.entidades.Persona;
import mx.lania.mrysi.exps.otd.PersonaOTD;
import mx.lania.mrysi.exps.repositorios.RepositorioPersonas;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author jaguilar
 */
@Service
public class ServicioPersonas {

    private RepositorioPersonas repoPersonas;
    private ModelMapper mapper;

    public ServicioPersonas(RepositorioPersonas repoPersonas,
            ModelMapper mapper) {
        this.repoPersonas = repoPersonas;
        this.mapper = mapper;
    }

    public Persona guardarPersona(Persona persona) {

        persona.setFechaRegistro(LocalDate.now());

        return repoPersonas.save(persona);
    }
    
    public Optional<PersonaOTD> getPersonaPorCurp(String curp) {
        List<Persona> personas = repoPersonas.findByCurp(curp);
        if (personas.isEmpty()) {
            return Optional.empty();
        }
        else {
            return Optional.of(toOTD(personas.get(0)));
        }
    }

    public PersonaOTD guardarPersona(PersonaOTD persona) {
        Persona personaEnt = toEntidad(persona);

        // Ajustes necesarios a la entidad
        personaEnt.setFechaRegistro(LocalDate.now());

        personaEnt = repoPersonas.save(personaEnt);
        return toOTD(personaEnt);
    }
    
    private PersonaOTD toOTD(Persona p) {
        PersonaOTD po = mapper.map(p, PersonaOTD.class);
        po.setNombreCompleto(p.getNombres() + " " + p.getApellidoPaterno() + " " + p.getApellidoMaterno());
        return po;
    }
    
    private Persona toEntidad(PersonaOTD po) {
        Persona p = mapper.map(po, Persona.class);
        String[] partes = po.getNombreCompleto().split(" ");
        // Solo como ejemplo 
        if (partes.length >= 3) {
            p.setNombres(partes[0]);
            p.setApellidoPaterno(partes[1]);
            p.setApellidoMaterno(partes[2]);
        }
        return p;
    }

}
