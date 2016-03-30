# ss-tp2. GRUPO 5. Tema: Bandadas de agentes autopropulsados

## particles

|__1__|__2__|___3_____|___4____|______5___________|__6____|
|  x  |  y  |  radio  | color  | modulo velocidad | angulo|


## ACCIONES DEL PROGRAMA

* (1) Generar un mapa al azar.( SEBAS: LISTO). ->se genera una tabla "particles"
* (2)"particles" -> grabar estado inicial en archivo.
* (3)"particles"-> Obtener vecinos(LISTO DEL TP1).
* (4)vecinos -> Calcular nuevas posiciones y velocidades. -> modificar "particles" ( FER/ LUIS)
* (5)grabar datos para graficar( posicion X, pos y, vel x, vel y, radio,¿color?).(SEBAS)
* (6) volver a (3).
* (7) codigo aparte, levanta datos de posiciones y crea la animación.(SEBAS)

## tareas
+ tunear lo de vecinos para que no use archivos.(FER)
+ orquestar la simulacion con todas particulas
+ terminar simulacion de una sola particula("simulateParticle.m").(FER).
+ agregar color simulacion (rgb).(SEBAS)
+ hacer presentacion
+ revisar manejo de angulos en simulacion de particula. 
+ revisar si tiene algun problema la funcion arctg
## PRESENTACION <parte,quien la da>
+ Fundamentos
+ Implementacion
+ Resultados.

## 28/03/2016
* ver como evitar que busqueda de vecinos este levantando de archvio: hacerlo mejor en memoria.
* ¿Formato de los archivos de entrada? ¿Alcanza con pasar los 3 parámetros por consola? PUEDE SER.
* Qué es Va? POLARIZACION.Ojo: primero sumar vectores primero!
* ¿se puede usar el calculo de vecinos del tp1?Si ese es el caso, esta bien usar el M máximo?(en las pruebas del tp1 se lograba mayor desempeño asi). SI, POR SUPUESTO.
* ¿es necesario graficar con flechas indicando dirección? SI
* cuidado: TP1 formato de particles era x,y,radio,color. TP2: ¡x,y, angulo!
* Presentacion, parte implementacion:¿ alcanza con un diagrama de flujo con las funciones que se llaman junto con los parametros que se pasan? bien.
* ruido uniforme: ¿tiene que incluir los limites del intervalo?
* ¿como hcer caso no peridico cuando toca el borde? SOLO CASOS PERIODICOS.
* Presentacion: ¿fundamentos? Describir el modelo, las formula.ES CORTO.
* Enunciado: "Repetir las animaciones anteriores pero cambiando el color (o la escala de grises) de los vectores
según el angulo de la velocidad." USAR COLORES!!!!


##30/03/2016

* FIJAR CAJA DE VISUALIZACIÓN EN OVITO: poner particulas fijas en las 4 equinas o otra forma que encuentres mejor.
* CONFIGURACION DE OVITO PARA NUESTROS DATOS: en la carpeta tp2/data
