package es.carlosb.ahorcadospring.controladores;

import es.carlosb.ahorcadospring.servicios.PartidaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class partidaControlador {

    @Autowired // Si no se lo ponemos es posible que no se injecte toda la ingenieria que hay en la anotacion servio de esta clase
    private PartidaServicio servicio;

    @GetMapping("/")
    public String index (Model model) {
        model.addAttribute("listaPartidas", servicio.findAll());
        return "index";
    }
}