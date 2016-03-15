13/3/2016
-----
* El archivo dinamico no tiene velocidades.
* Definir bien el M.
* ¿que cambia en la animación a lo largo del tiempo?(para la animación en formato avi).
* Entre los datos de ejemplo "algunosVecinos_100_rc6.txt", nuestro algorimo no reconoce el 22 cercano al 41 y 91.SOLUCIONADO.
* En el archivo de ejemplo de salida "algunosVecinos_100_rc6.txt", cual es el M?
* En loadParticles.m no se tiene en cuenta los multiples tiempos que pueden aparecer. Tampoco las dos columnas extra para las velocidades.
* setUpGrid.m no tiene ningún tipo de testing.
* ¿hay que comparar particulas dentro de una misma celda? Agregar esto a los testing de getNeighboors.LISTO

15/03 
----
*Acotar en el plot, los decimales de las  leyenda en la grilla
*En el scatter, revisar el tamaño
*Preguntar manejo de colores( si se pide mostrar vecino se pisan TODOS los colores, luego se resalta la particula, y con otro color se resaltan vecinos). ¿Esta bien pisar asi?
* En la representacion grafica, ¿los radios tienen que ser exacto?(Hay problema con los radios graficando con la funcion scatter).
*¿Hace falta checkear que el M sea valido?
* Tenemos en main las funciones que cargan los datos de archivos, buscan vecinos y por ultimo guardan los vecinos.¿Esto deberia estar estar asi separado o todo en una sola funcion a llamar?
* ¿Que pasa si cae una particula sobre el limite? ¿estaria bien que rompa? ¿Estaria bien un mensaje de error? 