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


@Service
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
        if (repositorio.size() < id) {
            return null;
        }
        Partida partida = repositorio.get(id - 1);
        return partida;
    }

    // Metodo que procesa las letras introducidas:
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
            String intentos = fallos(partida.getLetrasFalladas());
            partida.setIntentos(intentos);
            respuesta = "Letra " + letra.toUpperCase() +  " fallada";
        }
        return respuesta;
    }

    // metodo que dibuja las letras acertadas;
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
        // Si la letrasAcertadas es igual a la palabra significa que hemos ganado y devuelve un "Has ganado"
        if (aciertos.equals(partida.getPalabraOculta())) {
            partida.setIntentos("Has ganado");
        }
        return aciertos;
    }

    // Este metodo coge las letras falladas y segun el numero de letras devuelve el numero de intentos, si no quedan mas intentos devolvera un "Has fallado"
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

    // Comprueba que tanto las letras como las palabras introducidas no lleven tildes, dieresis o algun caracter que no admitamos
    public String letraSinTilde (String letra) {
        // el metodo usado cambia tambien las ñ, ya que no estan en el estandar UTF-8,
        // si hay una ñ, se recorre el string y se guardan los indices donde aparecen las ñ para luego cambiar las n de esos indices
        // (ya que se cambiaran las ñ por n) por ñ
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
        for (int i = 0; i < indices.size(); i++) {
            sb.setCharAt(indices.get(i).intValue(), 'ñ');
            letrasSinTildes = sb.toString();
        }
        return letrasSinTildes;
    }

    // metodo que recoge una nueva palabra desde un imput comprueba que no esta ya en el repositorio de palabras
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
    // busca una palabra en una pagina web que genera palabras aleatorias
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

    @PostConstruct
    public void init() throws Exception {
        for (int i = 1; i <= 25; i++) {
        String palabra = nuevaPalabraURL();
        repositorio.add(new Partida(i, palabra));
        }
    }
}
