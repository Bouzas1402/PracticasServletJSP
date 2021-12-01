package es.carlosb.ahorcadospring.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // el data os proporciona los getter y setter, metodos tostring, equals hascode y constructores de todos los parametros
@AllArgsConstructor // esta nos proporciona un constructor con todos los atributos
public class Partida {
    // Para indentificar las partidas
    private long id;

    private String palabraOculta;

    private String letrasAcertadas;

    private String letrasFalladas;

    private String intentos;


}
