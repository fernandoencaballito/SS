## función que calcula y devuelve a los vecinos.
##neighboors: es un arreglo de celdas. Cada celda representa los vecinos de la partícula. La posición dentro del arrreglo de celdas representa el identificador de la partícula.
##N: cantidad de particulas en totoal.
## grid: es la grilla, en cada celda se tiene un vector que contiene los IDs de las partículas que se ubican en dicha celda.
## particles: datos de cada partícula.
##M: ancho de la grilla en celdas
## periodic: booleano que indica si hay condiciones periódicas de contorno.
function neighboors=getNeighboors(N,grid,particles,rc,M,periodic)
	neighboors=cell(N);
	for i=(1:M)
		for j=(1:M)
		
			##se compara la celda (i,j) contra (i-1,j)
			neighboors=addNeighboorsBetweenCells(N,grid,neighboors,particles,i, j,(i-1) ,j,rc)			
		
			##se compara la celda (i,j) contra (i-1,j+1)
			neighboors=addNeighboorsBetweenCells(N,grid,neighboors,particles,i, j,(i-1) ,j+1,rc)			
		
			## se compara la celda (i,j) contra (i,j+1)
			neighboors=addNeighboorsBetweenCells(N,grid,neighboors,particles,i, j,i ,j+1,rc)			
		
			## se compara la celda (i,j) contra (i+1,j+1)
			neighboors=addNeighboorsBetweenCells(N,grid,neighboors,particles,i, j,(i+1) ,j+1,rc)			
		
		endfor
	endfor


endfunction
