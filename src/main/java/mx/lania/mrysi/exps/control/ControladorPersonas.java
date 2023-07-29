package mx.lania.mrysi.exps.control;

import java.util.List;
import mx.lania.mrysi.exps.entidades.Persona;
import mx.lania.mrysi.exps.repositorios.RepositorioPersonas;
import mx.lania.mrysi.exps.servicios.ServicioPersonas;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author jaguilar
 */
@Controller
public class ControladorPersonas {
    
    RepositorioPersonas repoPersonas;
    
    ServicioPersonas servPersonas;

    public ControladorPersonas(RepositorioPersonas repoPersonas,
            ServicioPersonas servPersonas) {
        this.repoPersonas = repoPersonas;
        this.servPersonas = servPersonas;
    }
    
    
    @GetMapping("personas")
    @ResponseBody
    public List<Persona> getPersonas() {
        return repoPersonas.findAll();
    }
    
    @GetMapping("personas/{curp}")
    @ResponseBody
    public Persona getPersonaPorCurp(@PathVariable("curp") String curp) {
        List<Persona> personas = repoPersonas.findByCurp(curp);
        if (personas.size() == 1) {
            return personas.get(0);
        }
        else {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "");
        }
    }
    
    @GetMapping(value="personas",params={"curpcadena"})
    @ResponseBody
    public List<Persona> buscarPersonaPorCurp(@RequestParam("curpcadena") String curp) {
        return repoPersonas.findByCurpStartingWithIgnoreCase(curp);
    }
    
    @PostMapping(value="personas")
    @ResponseBody
    public Persona guardarPersona(@RequestBody Persona persona) {
        return servPersonas.guardarPersona(persona);
    }
}
