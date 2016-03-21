Implementacion en Octave del algoritmo **Cell Index Method**, el cual permite calcular, a partir de un lista de particulas con sus posiciones iniciales, una lista de vecinos que distan a menos de Rc para cada una. Adicionalmente, genera un grafico con la matriz utilizada para dividir el plano en celdas, y la posicion de cada particula.

## Particulas no Puntuales

Como la distancia entre particulas debe medirse considerando el radio de cada una, se debe cumplir: 

    L/M > Rc + 2 * Rp
    
## Condicion de periodicidad

Es posible instruirle al algoritmo que tome los bordes de la matriz generada como periodicos. Es decir, si el radio de una particula excede el tamaño de la matriz, el excedente se lo traslada al borde opuesto.

![Ejemplo de condiciones periodicas](https://github.com/fernandoencaballito/SS/raw/master/periodic-conditions.jpg)

13/3/2016
-----
* El archivo dinamico no tiene velocidades.POR AHORA NO SE USA.
* Definir bien el M.
* ¿que cambia en la animación a lo largo del tiempo?(para la animación en formato avi).POR AHORA NO HAY ANIMACION
* Entre los datos de ejemplo "algunosVecinos_100_rc6.txt", nuestro algorimo no reconoce el 22 cercano al 41 y 91.SOLUCIONADO.
* En el archivo de ejemplo de salida "algunosVecinos_100_rc6.txt", cual es el M? NO IMPORTA EL M.
* En loadParticles.m no se tiene en cuenta los multiples tiempos que pueden aparecer. Tampoco las dos columnas extra para las velocidades.
* setUpGrid.m no tiene ningún tipo de testing. SOLUCIONADO
* ¿hay que comparar particulas dentro de una misma celda? Agregar esto a los testing de getNeighboors.LISTO

15/03 
----
* Acotar en el plot, los decimales de las  leyenda en la grilla
* En el scatter, revisar el tamaño.  IMPORTA EL RADIO.No importa tanto la precision.
* Preguntar manejo de colores( si se pide mostrar vecino se pisan TODOS los colores, luego se resalta la particula, y con otro color se resaltan vecinos). ¿Esta bien pisar asi? NO modifiques los datos estaticos de las particulas, hacelo solo en el graficado.
* En la representacion grafica, ¿los radios tienen que ser exacto?(Hay problema con los radios graficando con la funcion scatter).TRATA QUE SEA MAS O MENOS PROPORCIONAL.
* ¿Hace falta checkear que el M sea valido?SI, CONTROLALO CON LA VERSION NO PUNTUAL DE LA DESIGUALDAD.
* Tenemos en main las funciones que cargan los datos de archivos, buscan vecinos y por ultimo guardan los vecinos.¿Esto deberia estar estar asi separado o todo en una sola funcion a llamar?PUEDE SER
* ¿Que pasa si cae una particula sobre el limite? ¿estaria bien que rompa? ¿Estaria bien un mensaje de error? TIRA ERROR SI ESTAS FUERA DE LOS LIMITES, NO DEBERIA PASAR.

16/03
---
* ENTREGA:¿ informe NO, presentación oral con powerpoint NO, video NO? TENELO EL TP CORRIENDO PARA MOSTRAR EN PC!
* PUNTO 2 ENUNCIADO: ¿radio esta fijo?¿Un grafico <tiempo vs N> y otro <tiempo vs M>? Si. Mejor hace que varien N y M a la vez.N trata que sea mayor que 1000
*  L/M >rC +2*Radio_maximo. ESTA BASTANTE BIEN, PRUEBEN CON ALGUNOS EJEMPLOS.

