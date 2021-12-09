package es.carlosb.ahorcadospring.controladores;

import es.carlosb.ahorcadospring.modelo.Partida;
import es.carlosb.ahorcadospring.servicios.PartidaServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Locale;

@Slf4j
@Controller
public class partidaControlador {

    @Autowired // Si no se lo ponemos es posible que no se injecte toda la ingenieria que hay en la anotacion servio de esta clase
    private PartidaServicio servicio;


    private Partida partida;

    @GetMapping("/")
    public String index (Model model) {
        model.addAttribute("partidas", servicio.findAll());
        model.addAttribute("partida", new Partida());
        return "index";
    }

    @GetMapping("/partida/{id}")
    public String partida (@PathVariable("id") int id, Model model) {
        partida = servicio.findById(id);
        model.addAttribute("partida", servicio.findById(id));
        return "partida";
    }

    @PostMapping("partida/{id}")
    public String letraIntroducida (@RequestParam(value = "letra") String letra, @RequestParam(value = "id") int id, Model model) {
        partida = servicio.findById(id);
        if (partida == null){
            return "index";
        }
        String respuesta = servicio.letra(partida, letra.toLowerCase());
        if (partida.getIntentos().equals("Has ganado")) {
            model.addAttribute("ganador", "Felicidades has ganado");
            return "partidaFinalizada";
        } else if (partida.getIntentos().equals("Has perdido")) {
            model.addAttribute("perdedor", "Lo siento has perdido");
            return "partidaFinalizada";
        }
        model.addAttribute("respuesta", respuesta);
        model.addAttribute("partida", partida);
        return "partida";
    }

    @PostMapping("/nueva")
    public String nuevaPartida(@Valid Partida partida, BindingResult bindingResult, @RequestParam(value = "palabra") String palabra) {

        servicio.nuevaPalabra(palabra.toLowerCase());
        if (bindingResult.hasErrors()){
            return "redirect:/";
        }
        return "redirect:/";
    }


  //  @ExceptionHandler
  //  @ResponseStatus()
}
