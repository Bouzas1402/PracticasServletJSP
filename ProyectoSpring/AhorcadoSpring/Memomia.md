# Memoria AHORACADO SPRING:

## DataTable:
    Use dataTables para la presentación de datos en el index del proyecto por que viene con ciertas funciones ya incorporadas 
    como puede ser ordenacion, cuadro de busqueda, paginacion por distintas longitudes,... La presentacion queda visualmente mejor 
    y la funcionalidad y la facilidad de eleccion de partidas por parte del jugador tambien resulta mas sencilla.
## Generar palabras iniciales:
    Decidí, a partir de un ejercicio propuesto por Fernando, sacar las palabras de de una pagina web que genera aleatoriamente un JSON
    con distintas palabras e información sobre ella.
    Mediante una petición a la pagina web recibo el JSON y elijo solo la palabra del JSON recibido y la agrego al repositorio.
    
    Esto hace que el proyecto tarde en arrancar, entre 12 y 13 segundos normalmente.

## Generar nuevas palabras:
    Hay dos modos de generar nuevas palabras, o bien introduciendolas tu, o bien por el mismo metodo que generamos las palabras
    del principio generar 1,3,5 o 20 palabras aleatorias que se convertiran en nuevas partidas.

## Tratar la palabras, si reciben con ´ ¨ y demas pero en la comprobación es donde se ignoran: