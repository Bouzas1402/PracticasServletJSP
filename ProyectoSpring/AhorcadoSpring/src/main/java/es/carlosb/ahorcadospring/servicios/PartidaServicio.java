package es.carlosb.ahorcadospring.servicios;

import es.carlosb.ahorcadospring.modelo.Partida;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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

    public String letra(Partida partida, String letra) {
        String respuesta = "";
        if (partida.getLetrasAcertadas().contains(letra) || partida.getLetrasFalladas().contains(letra)){
            respuesta = "Ya has introducido la letra " + letra.toUpperCase();
        } else if (partida.getPalabraOculta().contains(letra)) {
            partida.setLetrasAcertadas(dibujarLetrasAcertadas(partida, letra));
            respuesta = "Letra " + letra.toUpperCase() +  " acertada";
        }  else {
            partida.setLetrasFalladas(partida.getLetrasFalladas() + letra);
            partida.setIntentos(fallos(partida.getLetrasFalladas()));
            respuesta = "Letra " + letra.toUpperCase() +  " fallada";
        }
        return respuesta;
    }
    public String dibujarLetrasAcertadas (Partida partida, String letra) {
        String aciertos = "";
        String [] arrayLA = partida.getLetrasAcertadas().split("");
        String [] arrayPO = partida.getPalabraOculta().split("");
        for (int i = 0; i < arrayLA.length; i++) {
            if (arrayPO[i].equals(letra)){
                aciertos += letra;
            } else {
                aciertos +=  arrayLA[i];
            }
        }
        if (aciertos.equals(partida.getPalabraOculta())) {
            partida.setIntentos("Has ganado");
        }
        return aciertos;
    }

    public String fallos (String letrasFalladas) {
        String fallos = "";
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
                fallos = "Has perdido";
                break;
        }
        return fallos;
    }

    public void nuevaPalabra(String palabra) {
        int ultimoId = repositorio.size() + 1;
        repositorio.add(new Partida(ultimoId, palabra));
    }

    @PostConstruct
    public void init(){
        repositorio.addAll(Arrays.asList(new Partida(1,"coche"),
            new Partida(2,"barco"),
            new Partida(3,"avion"),
            new Partida(4,"chalet"),
            new Partida(5,"conjuncion"),
            new Partida(6,"pisapapeles"),
            new Partida(7,"analgesico"),
            new Partida(8,"solarium")
            )
        );
    }
}
