package br.dev.johnsiqueira4.nextFix.controller;

import br.dev.johnsiqueira4.nextFix.Service.DirectorService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@AllArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@RequestMapping("/director")
public class DirectorViewController {

    private final DirectorService directorService;


//    @GetMapping("/ruta")
//    @RequestMapping(value = "/ruta", method = RequestMethod.GET)
    @GetMapping("/directores")
    public String listarDirectores(Model model) {
        model.addAttribute("directores", directorService.listarDirectores());

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
