package mx.lania.mrysi.exps.control;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.lania.mrysi.exps.entidades.EstatusExpediente;
import mx.lania.mrysi.exps.entidades.Expediente;
import mx.lania.mrysi.exps.entidades.QPersonaExpediente;
import mx.lania.mrysi.exps.entidades.VExpedientesCerrados;
import mx.lania.mrysi.exps.repositorios.RepositorioExpedientes;
import mx.lania.mrysi.exps.repositorios.RepositorioVExpedientes;
import mx.lania.mrysi.exps.servicios.ServicioExpedientes;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jaguilar
 */
@Controller
public class ControladorExpedientes {

    private RepositorioExpedientes repoExpedientes;
    private ServicioExpedientes servExpedientes;

    //@Autowired
    private RepositorioVExpedientes repoVistaExpedientes;

    public ControladorExpedientes(RepositorioExpedientes repoExpedientes,
            ServicioExpedientes servExpedientes,
            RepositorioVExpedientes repoVExpedientes) {
        this.repoExpedientes = repoExpedientes;
        this.servExpedientes = servExpedientes;
        this.repoVistaExpedientes = repoVExpedientes;
    }

    @RequestMapping(value = "expedientes", method = RequestMethod.GET)
    public ModelAndView getExpedienteAbiertos() {
        ModelAndView mav = new ModelAndView("expedientes.jsp");
        mav.addObject("expedientes", repoExpedientes.findByEstatus(EstatusExpediente.ABIERTO));
        mav.addObject("fechaGeneracion", Instant.now());
        return mav;
    }

    /*
    @RequestMapping(value="expedientes", method = {RequestMethod.POST, RequestMethod.GET}, params = {"idexp"})
    public ModelAndView getExpediente(@RequestParam("idexp") Integer idExp) {
        ModelAndView mav = new ModelAndView("expediente.jsp");
        mav.addObject("expediente", repoExpedientes.getPorId(idExp));
        return mav;
    }
     */
    @RequestMapping(value = "expedientes/{id}", method = RequestMethod.GET)
    public ModelAndView getExpedientePorid(@PathVariable("id") Integer idExp, HttpServletRequest peticion, HttpServletResponse respuesta) {
        ModelAndView mav = new ModelAndView("expediente.jsp");
        Optional<Expediente> exp = repoExpedientes.findById(idExp);
        if (!exp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe expediente con el id indicado");
        }
        mav.addObject("expediente", exp.get());
        return mav;
    }

    @GetMapping(value = "expedientes", params = {"idpersona", "fecha"})
    @ResponseBody
    public List<Expediente> getPorIdPersona(@RequestParam("idpersona") Integer idPersona,
            @DateTimeFormat(pattern = "")
            @RequestParam("fecha") LocalDateTime fecha) {
        return repoExpedientes.buscarPorIdPersona(idPersona);
    }

    @GetMapping(value = "expedientes/por_atender")
    @ResponseBody
    public List<Expediente> getPorAtender() {
        return servExpedientes.getPorAtender();
    }

    @PostMapping("expedientes")
    @ResponseBody
    public Expediente guardarExpediente(@RequestBody Expediente exp) {
        return servExpedientes.guardar(exp);
    }

    @GetMapping("expedientes/cerrados_por_mes")
    @ResponseBody
    public List<VExpedientesCerrados> getExpedientesCerrados() {
        return repoVistaExpedientes.findAll();
    }

    @PatchMapping(value = "expedientes/{id}", params = {"estatus"})
    @ResponseBody
    public Expediente cambiarEstatusExpediente(@PathVariable("id") Integer idExp,
            @RequestParam("estatus") String estatus) {
        return servExpedientes.cambiarEstatus(idExp, estatus);
    }

    @DeleteMapping("expedientes/{id}")
    @ResponseBody
    public Expediente eliminarExpediente(@PathVariable("id") Integer idExp) {
        return servExpedientes.eliminarPorId(idExp);
    }

    @GetMapping("expedientes/con_persona")
    @ResponseBody
    public List<QPersonaExpediente> getPersonasExpedientes() {
        return servExpedientes.consultarExpedientesPersona();
    }

}
