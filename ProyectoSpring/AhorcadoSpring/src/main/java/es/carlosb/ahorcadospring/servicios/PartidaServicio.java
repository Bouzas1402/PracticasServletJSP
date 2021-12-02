package es.carlosb.ahorcadospring.servicios;

import es.carlosb.ahorcadospring.modelo.Partida;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public Partida findById(Integer id) {
        return repositorio.get(id);
    }

    public boolean procesarLetra (Partida partida, String letra) {
        boolean letraPalabra = partida.getPalabraOculta().contains(letra);
        if (!letraPalabra) {

        } else if (letraPalabra) {
            return letraAcertada(partida, letra);
        }
    }

    public boolean letraAcertada (Partida partida, String letra) {
        boolean letraAcertada = partida.getLetrasAcertadas().contains(letra);
        if (letraAcertada) {
            return false;

        }
    }

    public boolean letraFallida (Partida partida, String letra) {
        boolean letraFallida = true;
    }

    @PostConstruct
    public void init(){
        repositorio.addAll(Arrays.asList(new Partida(1,"coche", "", "", "seis"),
            new Partida(2, "barco", "", "", "seis"),
            new Partida(3,"avion", "", "", "seis")
            )
        );
    }
}
