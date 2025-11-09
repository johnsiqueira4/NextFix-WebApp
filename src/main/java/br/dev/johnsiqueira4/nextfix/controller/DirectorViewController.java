package br.dev.johnsiqueira4.nextfix.controller;

import br.dev.johnsiqueira4.nextfix.service.CustomUserDetailsService;
import br.dev.johnsiqueira4.nextfix.service.DirectorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
//@RequestMapping("/director")
public class DirectorViewController {

    private final DirectorService directorService;

    private final CustomUserDetailsService  customUserDetailsService;


//    @GetMapping("/ruta")
//    @RequestMapping(value = "/ruta", method = RequestMethod.GET)
    @GetMapping("/directores")
    public String listarDirectores(Model model) {
        model.addAttribute("directores", directorService.listarDirectores());
        model.addAttribute("userService", customUserDetailsService);

        return "listaDirectores";
    }

//    Another possible way :
//
//    @GetMapping("/directores2")
//    public ModelAndView listarDirectores() {
//        ModelAndView mav = new ModelAndView("listaDirectores");
//        mav.addObject("directores", directorService.listarDirectores());
//        return mav;
//    }



}
