package es.carlosb.ahorcadospring.controladores;

import es.carlosb.ahorcadospring.modelo.Partida;
import es.carlosb.ahorcadospring.servicios.PartidaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class partidaControlador {

    @Autowired // Si no se lo ponemos es posible que no se injecte toda la ingenieria que hay en la anotacion servio de esta clase
    private PartidaServicio servicio;

    @GetMapping("/")
    public String index (Model model) {
        model.addAttribute("partidas", servicio.findAll());

        return "index";
    }

    @GetMapping("/partida/{id}")
    public String partida (@PathVariable("id") long id, Model model) {
        model.addAttribute("partida", servicio.findById((int)id));
        return "partida";
    }

    @PostMapping("partida/letra")
    public String letraIntroducida (@RequestParam(value = "letra") String letra, @RequestParam(value = "id") int id, Model model) {


        model.addAttribute("letrasAcertadas", letra);
        model.addAttribute("partida", servicio.findById(id));

        return "partida";
    }
}
