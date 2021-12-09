package es.carlosb.ahorcadospring.modelo;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;


@Data // el data os proporciona los getter y setter, metodos tostring, equals hascode y constructores de todos los parametros
@AllArgsConstructor // esta nos proporciona un constructor con todos los atributos
@Entity
public class Partida {
    // Para indentificar las partidas
    private long id;

    @Pattern(regexp = "[^0-9]")
    private String palabraOculta;

    //private String letras;
    private String letrasAcertadas;
    private String letrasFalladas;

    private String intentos;

    public Partida(long id, String palabraOculta) {
        this.id = id;
        this.palabraOculta = palabraOculta;
        this.letrasAcertadas = "";
        for (int i = 0; i < palabraOculta.length(); i++) {
            this.letrasAcertadas += "_";
        }
        this.letrasFalladas = "";
        this.intentos = "seis";
    }

}
