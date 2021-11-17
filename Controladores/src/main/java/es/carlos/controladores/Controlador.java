package es.carlos.controladores;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controlador {

    @GetMapping("/")
    public String welcome (Model model){
        model.addAttribute("nombre", "Carlos Bouzas");
        model.addAttribute("mensaje", "¿Quieres ir a A?");
        model.addAttribute("mensajeA", "Pues pulsa aqui.");
        model.addAttribute("href", "/que");
        return "index";
    };

    @GetMapping("/que")
    public String que(Model model){
        return "que";
    };

    @GetMapping("/contacto")
    public String contacto(Model model){
        return "contacto";
    };

}