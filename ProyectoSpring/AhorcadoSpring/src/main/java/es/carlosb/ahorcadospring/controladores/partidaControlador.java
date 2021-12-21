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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *  Controlador de la Clase Partida.
 * @author Carlos Bouzas Álvaro.
 * @version 21/12/2021.
 */
@Slf4j
@Controller
public class partidaControlador {

    @Autowired
    private PartidaServicio servicio;

    /**
     * Controlador de la pantalla principal, se recupera el repositorio de partidas y una partida con el constructor vacío
     * para que nos haga de modelo y se envia al index del proyecto.
     * @return index.html.
     */
    @GetMapping("/")
    public String index (Model model) {
        model.addAttribute("partidas", servicio.findAll());
        model.addAttribute("partida", new Partida());
        return "index";
    }

    /**
     * Controlador que recoge un id enviado se busca la partida correspondiente a ese id y se envia esa partida a partida.html
     * @return index.html si la partida buscada no existe (es null), partida.html si la partida con el id introducido si se encuentra en el repositorio.
     */
    @GetMapping("/partida/{id}")
    public String partida (@PathVariable("id") int id, Model model) {
        Partida partida = servicio.findById(id);
        if (partida == null){

            return "redirect:/";
        }
        model.addAttribute("partida", partida);
        return "partida";
    }

    /**
     * Controlador que viene de partida.html con una letra enviada y un id, comprueba que la letra cumple las restricciones impuestas
     * y si las cumple lo envía al método letra de PartidaServicios para procesarla.
     * @return partida.html si la partida aun está en curso a partidaFinalizada.html si la partida terminó ya sea por derrota o por victoria
     */
    @PostMapping("partida/{id}")
    public String letraIntroducida (@RequestParam(value = "letra") String letra, @RequestParam(value = "id") int id, Model model) {
        Partida partida = servicio.findById(id);
        Pattern pat = Pattern.compile("^[a-zA-ZÀ-ÖØ-öø-ÿ]$");
        Matcher mat = pat.matcher(letra);
        if (!mat.matches()) {
            model.addAttribute("partida", partida);
            model.addAttribute("respuesta", "No es una letra valida");
            return "partida";
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

    /**
     * Controlador que recibe un String que tendrá las restricciones marcadas por la notación de Spring de palabraOculta
     * y lo envía al método nuevaPalabra de PartidaServicio para agregarla o no al repositorio de partidas. Se enviará tambien una
     * respuesta al html que variará en función de si la partida se agregó al repositorio o no.
     * @return index.html.
     */
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

    /**
     * Controlador que recibe un int numeroPalabras que serán el número de palabras nuevas que se generaran por el método nuevaPalabraURL
     * de PartidaServicios
     * @return index.html.
     */
    @PostMapping("/nuevasPalabras")
    public String nuevasPalabras(@RequestParam("numeroPalabras") int numeroPalabras) {
            for (int i = 1; i <= numeroPalabras; i++) {
                String palabra = servicio.nuevaPalabraURL();
                servicio.nuevaPalabra(palabra);
            }
        return "redirect:/";
    }

    /**
     * Controlador que recibe un id y pone los valores de la partida que se corresponda con ese id como el principio para poder volver a jugar la partida
     * @return index.html.
     */
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
