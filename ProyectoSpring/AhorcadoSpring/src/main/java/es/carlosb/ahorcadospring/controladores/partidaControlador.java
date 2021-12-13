package es.carlosb.ahorcadospring.controladores;

import es.carlosb.ahorcadospring.modelo.Partida;
import es.carlosb.ahorcadospring.servicios.PartidaServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Locale;

@Slf4j
@Controller
public class partidaControlador {

    @Autowired // Si no se lo ponemos es posible que no se injecte toda la ingenieria que hay en la anotacion servio de esta clase
    private PartidaServicio servicio;


   /* @InitBinder
    public void miBinder(WebDataBinder binder) {
        StringTrimmerEditor recortaEspaciosBlanco = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, recortaEspaciosBlanco);
    } */

    @GetMapping("/")
    public String index (Model model) {
        model.addAttribute("partidas", servicio.findAll());
        model.addAttribute("partida", new Partida());
        return "index";
    }

    @GetMapping("/partida/{id}")
    public String partida (@PathVariable("id") int id, Model model) {
        Partida partida = servicio.findById(id);
        if (partida == null){

            return "redirect:/";
        }
        model.addAttribute("partida", partida);
        return "partida";
    }

    @PostMapping("partida/{id}")
    public String letraIntroducida (@RequestParam(value = "letra") String letra, @RequestParam(value = "id") int id, Model model) {
        Partida partida = servicio.findById(id);
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
    public String nuevaPartida(@Valid @ModelAttribute("partida") Partida partida, BindingResult resultados, Model model) {
        if (resultados.hasErrors()){
            model.addAttribute("partidas", servicio.findAll());
            model.addAttribute("partida", partida);
            return "index";
        }
        boolean nuevaPalabra = servicio.nuevaPalabra(servicio.letraSinTilde(partida.getPalabraOculta().toLowerCase()));
        if (!nuevaPalabra) {
            model.addAttribute("partidas", servicio.findAll());
            model.addAttribute("partida", partida);
            model.addAttribute("palabraRepetida", "Ya has introducido esta palabra");
            return "index";
        }
        return "redirect:/";
    }

    @GetMapping("/reiniciar/{id}")
    public String reiniciarPartida(@PathVariable("id") int id){
        Partida partida = servicio.findById(id);
        if (partida == null){
            return "redirect:/";
        }
        String letrasAcertadas = "";
        for (int i = 0; i < partida.getPalabraOculta().length(); i++){
            letrasAcertadas += "_";
        }
        partida.setLetrasAcertadas(letrasAcertadas);
        partida.setLetrasFalladas("");
        partida.setIntentos("seis");
        return "redirect:/";
    }


}
