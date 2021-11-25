
#  Examen Desarrollo web en entorno servidor

## Parte Jakarta EE (3 horas y 30 minutos)

### Objetivo 
Construir una aplicación web basada en JEE que represente el juego del ahorcado.

### Desarrollo
Se pretende desarrollar, con un solo servlet y un JSP, el juego del ahorcado. En este juego se elige una palabra de forma aleatoria y se le da al usuario la posibilidad de introducir letras para adivinarla, contando con un número de intentos finitos. Tras introducir una letra, se informa al usuario de si acertó con la letra y, de ser así, su ubicación en la palabra. El usuario siempre sabe el número de intentos que le restan. 

El estado se debe mostrar a través de texto apoyado de las imágenes proporcionadas en el fichero de recursos (la horca vacía, sólo con la cabeza, etc).

El servlet debe ser capaz de mantener el estado del juego mediante sesiones, es decir, que cada vez que se cargue pueda informar al usuario de la situación del juego en ese momento.

Los elementos que habrá que desarrollar por tanto son:
* Un servlet llamado `es.carlos.Ahorcado`, que atiende peticiones por la ruta '/ahorcado' y que hace de controlador frontal.
* Una vista implementada con JSP llamada `vistaAhorcado` y a la que solo se llegará a través del servlet. Se recomienda el uso de etiquetas JSTL en combinación con lenguaje de expresiones (EL) en lugar de código java mezclado con HTML ya que produce un código más limpio e inteligible.

El juego pasa por una serie de pasos o estados: 
1. La primera ejecución, en la que:
    * Se elige aleatoriamente la palabra a adivinar.
    * Se almacena el estado del juego en una sesión.
    * Se envía a la vista información del estado.
    * Se le permite al usuario introducir una letra para ser enviada al servlet.

2. La segunda y sucesivas ejecuciones (antes de terminar el juego), en las que:
    * Se recibe la letra de la ejecución anterior.
    * Se recupera el estado del juego desde la sesión.
    * Se “recalcula” el estado del juego, a partir del estado anterior y la letra recibida, comprobando que no se haya alcanzado un estado de finalización del juego, ganando o perdiendo la partida.
    * Se almacena el estado del juego en la sesión.
    * Se envía a la vista información del estado actual.
    * Se le permite al usuario introducir una letra para ser enviada al servlet.

3. La última ejecución, en la que:
    * Se recibe la letra de la ejecución anterior.
    * Se recupera el estado del juego desde la sesión.
    * Se “recalcula” el estado del juego, a partir del estado anterior y la letra recibida, dándose la circunstancia que se ha alcanzado un estado de finalización del juego, ganando o perdiendo la partida.
    * Se envía a la vista información del estado actual. 
    * La vista mostrará el mensaje del resultado final del juego: ¡Has ganado! o ¡Has perdido!. 
    * Se ofrece la posibilidad de jugar de nuevo.

#### Batería de pruebas
Se deben realizar una serie de comprobaciones para determinar el correcto funcionamiento de la aplicación:

* Sesiones:
    * Control de la primera ejecución.
    * Control de la última ejecución.
* Entrada del usuario:
    * Control de letras ya usadas: se debería informar al usuario, pero no contabilizarse como un error.
    * No sensible a mayúsculas/minúsculas.
    * Procesar letras con tilde.
* Ejecución:
    * Al terminar la partida, ofrecer un enlace/botón de jugar de nuevo.

#### Recursos proporcionados
Fichero zip con los recursos gráficos.

#### Formato de entrega
Se deberá entregar a través del aula virtual el proyecto IntelliJ completo comprimido en formato zip de manera que se pueda importar en otra instalación de IntelliJ.

#### Declaración de honradez académica
Se debe incluir en la entrega un documento de texto con una declaración personal de haber programado el código entregado sin la ayuda de compañeros, por cualquier tipo de medio de comunicación.

En este mismo documento se deben incluir los enlaces de las páginas de internet que se hayan utilizado como referencia durante la realización de la prueba.

