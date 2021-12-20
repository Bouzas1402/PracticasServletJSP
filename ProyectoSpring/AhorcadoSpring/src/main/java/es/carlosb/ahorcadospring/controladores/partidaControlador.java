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

@Slf4j
@Controller
public class partidaControlador {

    @Autowired
    private PartidaServicio servicio;

    @GetMapping("/")
    public String index (Model model) {
        model.addAttribute("partidas", servicio.findAll());
        model.addAttribute("partida", new Partida());
        return "index2";
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
            return "index2";
        }
        boolean nuevaPalabra = servicio.nuevaPalabra(servicio.letraSinTilde(partida.getPalabraOculta().toLowerCase()));
        if (!nuevaPalabra) {
            model.addAttribute("partidas", servicio.findAll());
            model.addAttribute("partida", partida);
            model.addAttribute("palabraRepetida", "Ya has introducido esta palabra");
            return "index2";
        }
        return "redirect:/";
    }

    @PostMapping("/nuevasPalabras")
    public String nuevasPalabras(@RequestParam("numeroPalabras") int numeroPalabras) {
            for (int i = 1; i <= numeroPalabras; i++) {
                String palabra = servicio.nuevaPalabraURL();
                servicio.nuevaPalabra(palabra);
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
