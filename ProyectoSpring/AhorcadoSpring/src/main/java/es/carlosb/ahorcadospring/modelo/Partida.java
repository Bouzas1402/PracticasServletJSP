package es.carlosb.ahorcadospring.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * Esta Clase define un objeto Partida que tiene un long id identificado, un String palabraOculta que recoge la palabra a acertar,
 * un String letrasAcertadas que recoge las letras que se van acertando a lo largo de la partida, otro de letrasFalladas que
 * recoge las letras que se van fallando a lo largo de la partida y un String intentos que recoge el numero de intentos que le
 * quedan al jugador en esa partida.
 * @author Carlos Bouzas Álvaro.
 * @version 21/12/2021.
 */
@Data
@AllArgsConstructor
public class Partida {
    @Min(0)
    private long id;

    @Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ]+$", message = "No es un caracter valido")
    @Size(min = 3, message = "La palabra es muy corta")
    private String palabraOculta;

    private String letrasAcertadas;

    private String letrasFalladas;

    private String intentos;

    /**
     * Constructor vacio de la Clase Partida
     */
    public Partida(){
    }

    /**
     * Constructor de la Clase Partida, letrasAcertadas comienza con una "_" por cada letra de palabraOculta,
     * letrasFalladas comienza en "" y el numero de intentos inicial es seis;
     * @param id Partida: Partida id.
     * @param palabraOculta String: Partida palabraOculta.
     */
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

    /**
     * Getter.
     * @return id long: Partida id.
     */
    public long getId() {
        return id;
    }

    /**
     * Setter.
     * @param id long: Partida id.
     */
    public void setId(long id) { this.id = id; }
    /**
     * Getter.
     * @return palabraOculta String: Partida palabraOculta.
     */
    public String getPalabraOculta() {
        return palabraOculta;
    }

    /**
     * Setter.
     * @param palabraOculta String: Partida palabraOculta.
     */
    public void setPalabraOculta(String palabraOculta) { this.palabraOculta = palabraOculta; }

    /**
     * Getter.
     * @return letrasAcertadas String: Partida letrasAcertdas.
     */
    public String getLetrasAcertadas() {
        return letrasAcertadas;
    }

    /**
     * Setter.
     * @param letrasAcertadas String: Partida letrasAcertadas.
     */
    public void setLetrasAcertadas(String letrasAcertadas) {
        this.letrasAcertadas = letrasAcertadas;
    }

    /**
     * Getter.
     * @return letrasFalladas String: Partida letrasFalladas.
     */
    public String getLetrasFalladas() {
        return letrasFalladas;
    }

    /**
     * Setter.
     * @param letrasFalladas String: Partida letrasFalladas.
     */
    public void setLetrasFalladas(String letrasFalladas) {
        this.letrasFalladas = letrasFalladas;
    }

    /**
     * Getter.
     * @return intentos String: Partida intentos.
     */
    public String getIntentos() {
        return intentos;
    }

    /**
     * Setter.
     * @param intentos String: Partida intentos.
     */
    public void setIntentos(String intentos) {
        this.intentos = intentos;
    }
}
