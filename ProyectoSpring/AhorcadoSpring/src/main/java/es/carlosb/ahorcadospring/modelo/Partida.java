package es.carlosb.ahorcadospring.modelo;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;


@Data// el data os proporciona los getter y setter, metodos tostring, equals hascode y constructores de todos los parametros
@AllArgsConstructor
// esta nos proporciona un constructor con todos los atributos
public class Partida {
    // Para indentificar las partidas
    @Min(0)
    private long id;


    //@Pattern(regexp = "hola")
    //@Pattern(regexp = "[a-zA-z]")
    @Size(min = 3, message = "La palabra es muy corta")
    private String palabraOculta;

    private String letrasAcertadas;

    private String letrasFalladas;

    private String intentos;

    public Partida(){
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPalabraOculta() {
        return palabraOculta;
    }

    public void setPalabraOculta(String palabraOculta) {
        this.palabraOculta = palabraOculta;
    }

    public String getLetrasAcertadas() {
        return letrasAcertadas;
    }

    public void setLetrasAcertadas(String letrasAcertadas) {
        this.letrasAcertadas = letrasAcertadas;
    }

    public String getLetrasFalladas() {
        return letrasFalladas;
    }

    public void setLetrasFalladas(String letrasFalladas) {
        this.letrasFalladas = letrasFalladas;
    }

    public String getIntentos() {
        return intentos;
    }

    public void setIntentos(String intentos) {
        this.intentos = intentos;
    }
}
