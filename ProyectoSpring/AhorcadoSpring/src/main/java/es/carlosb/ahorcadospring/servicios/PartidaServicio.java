package es.carlosb.ahorcadospring.servicios;

import es.carlosb.ahorcadospring.modelo.Partida;

import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de la Clase Partida.
 * @author Carlos Bouzas Álvaro
 * @version 21/12/2021
 */
@Service
public class PartidaServicio {


    private List<Partida> repositorio = new ArrayList<>();

    /**
     * Agrega una partida recibida como parámetro al repositorio de partidas.
     * @param p Partida: Este parametro se introducira en el repositorio de partidas.
     */
    public void add(Partida p) {
        repositorio.add(p);
    }

    /**
     * Método que devuelve en un arrayList con todas las partidas del repositorio.
     * @return repositorio List<Partidas>: ArrayList relleno de las partidas del repositorio.
     */
    public List<Partida> findAll() {
        return repositorio;
    }

    /**
     * Método que recibe un id y devuelve la partida con ese id, si el id es mayor a la longitud del array significara que
     * el id no pertenece al repositorio de partidas.
     * @param id int: Partida id.
     * @return null: Si el id introducido no esta entre los id del repositorio de partidas.
     * @return partida Partida: devuelve la partida del id introducido si se encuentra entre el repositorio de partidas.
     */
    public Partida findById(int id) {
        if (repositorio.size() < id) {
            return null;
        }
        Partida partida = repositorio.get(id - 1);
        return partida;
    }

    /**
     * Método que recibe una Partida y una letra, comprueba si esa letra esta en el String de letrasAcertdas o letrasFalladas
     * de la partida introducida. Si está significa que esa letra ya se ha introducido, si no está, se comprobara si esta en la palabraOculta,
     * si esta significara un acierto, si no está significara un fallo y se agregara la letra al array de letrasAcertadas
     * o letrasFalladas de la partida introducida.
     * @param partida Partida: Partida.
     * @param letra String: letra introducida durante el juego y que se comprobara con el juego en marcha.
     * @return respuesta String: Se enviara un String respuesta que dirá que ocurrió con la letra.
     */
    public String letra(Partida partida, String letra) {
        String respuesta = "";
        String letraSinTilde = letraSinTilde(letra);
        String letrasFalladas = partida.getLetrasFalladas();
        String letrasAcertadas = partida.getLetrasAcertadas();
        String palabraOculta = partida.getPalabraOculta();
        if (letrasAcertadas.contains(letraSinTilde) || letrasFalladas.contains(letraSinTilde)){
            respuesta = "Ya has introducido la letra " + letra.toUpperCase();
        } else if (palabraOculta.contains(letraSinTilde)) {
            letrasAcertadas = dibujarLetrasAcertadas(partida, letraSinTilde);
            partida.setLetrasAcertadas(letrasAcertadas);
            respuesta = "Letra " + letra.toUpperCase() +  " acertada";
        }  else {
            partida.setLetrasFalladas(letrasFalladas + letraSinTilde);
            /**
             * Cuando se suma una letra al String letraFallada se envia letrasFalladas al metodo fallos() que calcula el numero de intentos en
             * funcion de la longitus de letrasFalladas y se envía el valor devuelto al valor de intentos de la partida introducida.
             */
            String intentos = fallos(partida.getLetrasFalladas());
            partida.setIntentos(intentos);
            respuesta = "Letra " + letra.toUpperCase() +  " fallada";
        }
        return respuesta;
    }

    /**
     * Método que recoge una Partida y un String comprueba si la letra aparece en algún lugar del String palabraOculta de la partida introducida
     * y si está introduce esa letra en palabrasAcertadas de la partida introducida en el mismo sitio donde aparece en palabraOculta, sustituyendo en
     * letrasAcertadas "_" por la letra introducida.
     * @param partida Partida: Partida.
     * @param letra String:
     * @return aciertos String: letrasAcertadas. Devuelve un String donde las letras que aun no han sido acertadas valdran "_" y las que se acierten
     * se destaparan.
     */
    public String dibujarLetrasAcertadas (Partida partida, String letra) {
        String aciertos = "";
        String [] arrayLetrasAcertadas = partida.getLetrasAcertadas().split("");
        String [] arrayPalabraOculta = partida.getPalabraOculta().split("");
        for (int i = 0; i < arrayLetrasAcertadas.length; i++) {
            if (arrayPalabraOculta[i].equals(letra)){
                aciertos += letra;
            } else {
                aciertos +=  arrayLetrasAcertadas[i];
            }
        }
        if (aciertos.equals(partida.getPalabraOculta())) {
            partida.setIntentos("Has ganado");
        }
        return aciertos;
    }

    /**
     * Metodo que recibe un String y en función de la longitud de este retorna un String que dara valor a intentos de una Partida.
     * @param letrasFalladas String: Partida letrasFallas.
     * @return fallos String: Partida intentos. Devuelve un String con el número de intentos que le quedan a la partida.
     */    public String fallos (String letrasFalladas) {
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

    /**
     * Metodo que recibe un String y lo devuelve sin caracteres fuera de UTF-8 (excepto la ñ)
     * @param letra String: letra o palabra nueva introducida en la aplicación.
     * @return letrasSinTildes String: la palabra nueva o letra introducida pero sin los caracteres que queremos eliminar.
     */
    public String letraSinTilde (String letra) {
        /**
         * Se comprueba si la ñ aparece o no en el String introducido, si esta se recorre y se guardan las posiciones donde aparece la ñ
         */
        int index = letra.indexOf("ñ");
        ArrayList<Integer> indices = new ArrayList<Integer>();
        if (index >= 0) {
            for (int i = 0; i < letra.length(); i++) {
                if(letra.charAt(i) == 'ñ'){
                    indices.add(i);
                }
            }
        }
        String letrasSinTildes = Normalizer.normalize(letra, Normalizer.Form.NFD);
        letrasSinTildes = letrasSinTildes.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "");
        StringBuilder sb = new StringBuilder(letrasSinTildes);
        /**
         * Una vez convertido el String lo recorremos y sustituimos el caracter en las posiciones guardadas de letrasSinTildes por ñ.
         */
        for (int i = 0; i < indices.size(); i++) {
            sb.setCharAt(indices.get(i).intValue(), 'ñ');
            letrasSinTildes = sb.toString();
        }
        return letrasSinTildes;
    }

    /**
     * Metodo que recibe un String saca el último id del repositorio y crea una nueva partida con el ultimo id +1 y el String introducido.
     * Si la palabra no está en ninguna de las partidas del repositorio se agrega la partida al repositorio, si no, la partida no se agregará.
     * @param palabra String: nueva palabra para un nueva partida.
     * @return boolean: false si el String introducido ya coincide con alguna palabraOculta de algunas de las partidas true si no coincide.
     */
    public boolean nuevaPalabra(String palabra) {
        for (int i = 0; i < repositorio.size(); i++) {
            String palabraOculta = repositorio.get(i).getPalabraOculta();
            if (palabraOculta.equals(palabra)) {
                   return false;
            }
        }
        int ultimoId = repositorio.size() + 1;
        repositorio.add(new Partida(ultimoId, palabra));
        return true;
    }
    /**
     * Metodo que coge devuelve una palabra de una página que genera palabras aleatorias.
     * @return palabra String: palabra generada automaticamente para la palabraOculta de una nueva Partida.
     */
    public String nuevaPalabraURL() {
        String documento = "";
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest respuesta = HttpRequest.newBuilder().uri(URI.create("https://palabras-aleatorias-public-api.herokuapp.com/random")).build();
        documento = cliente.sendAsync(respuesta, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
        String[] respuestaProcesada = documento.split(":",13);
        String palabra = letraSinTilde(respuestaProcesada[12].split("\"")[1]);
        return palabra;
    }

    /**
     * Metodo que se inicia al comienzo del programa y que genera 25 partidas con palabraOculta aleatorias.
     */
    @PostConstruct
    public void init() throws Exception {
        for (int i = 1; i <= 25; i++) {
        String palabra = nuevaPalabraURL();
        this.nuevaPalabra(palabra);
        }
    }
}
