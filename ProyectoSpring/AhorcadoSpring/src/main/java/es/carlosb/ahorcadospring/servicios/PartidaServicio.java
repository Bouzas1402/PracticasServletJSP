package es.carlosb.ahorcadospring.servicios;

import es.carlosb.ahorcadospring.modelo.Partida;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.Normalizer;
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
        if (repositorio.size() < id) {
            return null;
        }
        Partida partida = repositorio.get(id - 1);
        return partida;
    }

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

    public String letraSinTilde (String letra) {
        String letrasSinTildes = Normalizer.normalize(letra, Normalizer.Form.NFD);
        letrasSinTildes = letrasSinTildes.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "");
        return letrasSinTildes;
    }

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

    @PostConstruct
    public void init() throws Exception {
        for (int i = 1; i <= 200; i++) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://palabras-aleatorias-public-api.herokuapp.com/random"))
                    .build();
            String respuesta =  client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            String[] respuestaProcesada = respuesta.split(":",13);
            String palabra = letraSinTilde(respuestaProcesada[12].split("\"")[1]);
            repositorio.add(new Partida(i,palabra));
        }
    }
}
