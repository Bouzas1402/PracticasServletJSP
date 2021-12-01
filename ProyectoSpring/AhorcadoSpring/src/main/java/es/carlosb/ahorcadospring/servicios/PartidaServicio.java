package es.carlosb.ahorcadospring.servicios;

import es.carlosb.ahorcadospring.modelo.Partida;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void init(){
        repositorio.addAll(Arrays.asList(new Partida(1,"coche", "", "", "seis"),
            new Partida(2, "barco", "", "", "seis"),
            new Partida(3,"avion", "", "", "seis")
            )
        );
    }
}
