package mx.lania.mrysi.exps.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jaguilar
 */
@Controller
public class ControladorInicial {
    
    @RequestMapping(value="saludo", method = RequestMethod.GET)
    public ModelAndView primeraPagina() {
        ModelAndView mav = new ModelAndView("inicio.jsp");
        mav.addObject("mensaje", "Hola mundo de Spring");
        return mav;
    }
}
