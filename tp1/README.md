Implementacion en Octave del algoritmo **Cell Index Method**, el cual permite calcular, a partir de un lista de particulas con sus posiciones iniciales, una lista de vecinos que distan a menos de Rc para cada una. Adicionalmente, genera un grafico con la matriz utilizada para dividir el plano en celdas, y la posicion de cada particula.

## Particulas no Puntuales

Como la distancia entre particulas debe medirse considerando el radio de cada una, se debe cumplir: 

    L/M > Rc + 2 * Rp
    
## Condicion de periodicidad

Es posible instruirle al algoritmo que tome los bordes de la matriz generada como periodicos. Es decir, si el radio de una particula excede el tamaño de la matriz, el excedente se lo traslada al borde opuesto.

![Ejemplo de condiciones periodicas](https://github.com/fernandoencaballito/SS/raw/master/periodic-conditions.jpg)

## Eficiencia del Algoritmo (ejercicio2)

Para estudiar la eficiencia del algoritmo se lo probó con distintos M que cumplen con el criterio:

    L/M > Rc + 2 * Rp
    L=20, Rc=1 y Rp=0.25
    
    => M < 13,33

, donde L, Rc y Radio están fijos según solicita el enunciado.    
Luego, las unicas variables son N, y el número de celdas M. Los siguientes son gráficos que comparan al tiempo requerido por el algoritmo CIT con el tiempo requerido por el algoritmo de fuerza bruta. Como se puede ver, se produce una inversion en la tasa de crecimiento de las curvas. Lo cual nos lleva a suponer que con `M = 3` se produce un punto de inflexión. Éste último fenomeno se puede apreciar en el gráfico con M=3, en donde las curvas coinciden.
Además, a medida que se aumenta el M, se mejora el tiempo del algoritmo con respecto al caso de fuerza bruta.

### `M = 2`
![M = 2](https://raw.githubusercontent.com/fernandoencaballito/SS/master/ArchivosEjemplo/bruteForceCompareM%3D2.jpg)
### `M = 3`
![M = 3](https://raw.githubusercontent.com/fernandoencaballito/SS/master/ArchivosEjemplo/bruteForceCompareM%3D3.jpg)
### `M = 4`
![M = 4](https://raw.githubusercontent.com/fernandoencaballito/SS/master/ArchivosEjemplo/bruteForceCompareM%3D4.jpg)

### `M = 5`
![M = 5](https://raw.githubusercontent.com/fernandoencaballito/SS/master/ArchivosEjemplo/bruteForceCompareM%3D5.jpg)

### `M = 6`
![M = 6](https://raw.githubusercontent.com/fernandoencaballito/SS/master/ArchivosEjemplo/bruteForceCompareM%3D6.jpg)

### `M = 7`
![M = 7](https://raw.githubusercontent.com/fernandoencaballito/SS/master/ArchivosEjemplo/bruteForceCompareM%3D7.jpg)

### `M = 8`
![M = 8](https://raw.githubusercontent.com/fernandoencaballito/SS/master/ArchivosEjemplo/bruteForceCompareM%3D8.jpg)

### `M = 9`
![M = 9](https://raw.githubusercontent.com/fernandoencaballito/SS/master/ArchivosEjemplo/bruteForceCompareM%3D9.jpg)

### `M = 10`
![M = 10](https://raw.githubusercontent.com/fernandoencaballito/SS/master/ArchivosEjemplo/bruteForceCompareM%3D10.jpg)

### `M = 11`
![M = 11](https://raw.githubusercontent.com/fernandoencaballito/SS/master/ArchivosEjemplo/bruteForceCompareM%3D11.jpg)

### `M = 12`
![M = 12](https://raw.githubusercontent.com/fernandoencaballito/SS/master/ArchivosEjemplo/bruteForceCompareM%3D12.jpg)

### `M = 13`
![M = 13](https://raw.githubusercontent.com/fernandoencaballito/SS/master/ArchivosEjemplo/bruteForceCompareM%3D13.jpg)

## Valor de M óptimo (ejercicio 3).
 
Como se puede apreciar del siguiente gráfico, a mayor valor de M, menos tiempo toma en obtener los vecinos.

![](https://raw.githubusercontent.com/fernandoencaballito/SS/master/Plots/testOptimo.jpg)


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

