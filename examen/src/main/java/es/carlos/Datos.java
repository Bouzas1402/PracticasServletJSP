package es.carlos;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Datos {
  /* Inicialización con doble llave
    We must try to avoid this initialization technique because it creates an anonymous extra class at every usage,
    holds hidden references to the enclosing object, and might cause memory leak issues.
  */
	public static Map<String,String> mapPaises = new LinkedHashMap<>() {{
		put("ES", "España");
		put("FR", "Francia");
		put("IT", "Italia");
		put("PT", "Portugal");
	}};

  /* Inicialización con bloque static
    The advantage of this kind of initialization is that the map is mutable, but it will only work for static.
    Consequently, entries can be added and removed as and when required.
   */
	public static Map<String,String> mapGeneros;
  static {
    mapGeneros = new LinkedHashMap<>() {{
      put("H", "Hombre");
      put("M", "Mujer");
      put("O", "Otro");
    }};
  }

  // Inicialización con Stream (desde Java 8)

  public static Map<String, String> mapDepartamentos = Stream.of(new String[][] {
    { "10", "Ventas" },
    { "20", "Marketing" },
    { "30", "IT" },
  }).collect(Collectors.toMap(data -> data[0], data -> data[1]));


  public static Map<String,String[]> mapNavegacion;
  static {
    mapNavegacion = new LinkedHashMap<>() {{
      put("1", new String[]{"Paso1_datosPersonales", "Number-1-icon.png", "Datos personales"});
      put("2", new String[]{"Paso2_datosProfesionales", "Number-2-icon.png", "Datos profesionales"});
      put("3", new String[]{"Paso3_datosBancarios", "Number-3-icon.png", "Datos bancarios"});
      put("4", new String[]{"Resumen", "checkered-flag-icon.png", "Resumen"});
    }};
  }

}
