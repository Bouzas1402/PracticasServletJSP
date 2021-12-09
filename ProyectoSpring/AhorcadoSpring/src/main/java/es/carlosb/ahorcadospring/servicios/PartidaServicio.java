package es.carlosb.ahorcadospring.servicios;

import es.carlosb.ahorcadospring.modelo.Partida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.Part;
import java.util.*;

@Service // para que nos proporcione toda la maquinaria para que se comunique con un servicio.
public class PartidaServicio {


    private List<Partida> repositorio = new ArrayList<>();

    public Partida add(Partida p) {
        repositorio.add(p);
        return p;
    }

    public List<Partida> findAll() {
        return repositorio;
    }

    public Partida findById(int id) {
        return repositorio.get((id - 1));
    }

    public String letrasAcertadas(String letras, String palabra) {
        String [] palabraOculta = palabra.split("");
        String letrasAcertadas = "";
        for (int i = 0; i < palabraOculta.length; i++) {
            if (letras.contains(palabraOculta[i])) {
                letrasAcertadas += palabraOculta[i];
            } else {
                letrasAcertadas += "_";
            }
        }
        if (letrasAcertadas.equals(palabra)) {
            letrasAcertadas = "Has ganado";
        }
        return letrasAcertadas;
    }

    public String letrasFalladas(String letras, String palabra) {
        String letrasFalladas = "";
        String [] letrasIntroducidas = letras.split("");
        for (int i = 0; i < letrasIntroducidas.length; i++) {
            if (!palabra.contains(letrasIntroducidas[i])) {
                letrasFalladas += letrasIntroducidas[i];
            }
        }
        return letrasFalladas;

    }

    public String fallos (String letras, String palabra) {
        String fallos = "";
        String letrasFalladas = letrasFalladas(letras, palabra);
        switch (letrasFalladas.length()) {
            case 0:
                fallos = "seis";
                break;
            case 1:
                fallos = "cinco";
                break;
            case 2:
                fallos = "cuatro";
                break;
            case 3:
                fallos = "tres";
                break;
            case 4:
                fallos = "dos";
                break;
            case 5:
                fallos = "uno";
                break;
            case 6:
                fallos = "cero";
                break;
        }
    return fallos;

    }


    /*public boolean procesarLetra (Partida partida, String letra) {
    *    boolean letraPalabra = partida.getPalabraOculta().contains(letra);
    *    if (!letraPalabra) {
    *
    *    } else if (letraPalabra) {
    *        return letraAcertada(partida, letra);
    *    }
    *}
    *
    *public boolean letraAcertada (Partida partida, String letra) {
    *    boolean letraAcertada = partida.getLetrasAcertadas().contains(letra);
    *    if (letraAcertada) {
    *        return false;
    *
    *    }
    *}
    *
    *public boolean letraFallida (Partida partida, String letra) {
    *    boolean letraFallida = true;
    *}
    */
    @PostConstruct
    public void init(){
        repositorio.addAll(Arrays.asList(new Partida(1,"coche", "", "seis"),
            new Partida(2, "barco", "", "seis"),
            new Partida(3,"avion", "", "seis")
            )
        );
    }
}
