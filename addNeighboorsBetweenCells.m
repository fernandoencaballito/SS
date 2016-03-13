##
##función que agrega los vecinos para las partículas de la actual celda que se encuentran en la siguiente celda.
##N: es la cantidad de partículas.
##grid: es la grilla, en cada celda se tiene un vector que contiene los IDs de las partículas que se ubican en dicha celda.
##previousNeighboors:son los vecinos calculados en pasos anteriores. Debe estar inicializada.
##particles:matriz en la cual cada fila representa a los datos de cada partícula.
##currentCell_row, currentCell_col: posición de la celda actual en la grilla.
##nextCell_row, nextCell_col= posición de la siguiente celda en la grilla.
##
## neighboors: vecinos calculados entre las celdas en cuestión y en pasos anteriores.
function neighboors = addNeighboorsBetweenCells(N,grid,previousNeighboors,particles,currentCell_row, currentCell_col, nextCell_row,nextCell_col,rc)
##constantes, posiciones de los datos de cada partícula
x_pos=1;
y_pos=2;
radius_pos=3;
color_pos=4;
##
currentCell=grid{currentCell_row,currentCell_col};
nextCell=grid{nextCell_row,nextCell_row};

for currentParticleID=currentCell

	currentParticleData=particles(currentParticleId);##vector con los datos de la partícula actual
	currentParticlePosition=[currenParticleData(x_pos), currentParticleData(y_pos)];
	currentParticleRadius=currentParticleData(radius_pos);
	for nextParticuleID=nextCell
		nextParticleData=particles(nextParticuleID);
		nextParticlePosition=[nextParticleData(x_pos),nextParticleData(y_pos)];
		nextParticleRadius=nextParticleData(radius_pos);
		distance=norm(currentParticlePosition-nextPariclePosition,2)-currentParticleRadius-nextParticleRadius;
		if(distance<rc) %se agrega el id de la particula vecina
			previousNeighboors(currentParticleID)=[previousNeighboors(currentParticleID),nextParticuleID];
		endif
	endfor


endfor


neighboors=previousNeighboors;

endfunction;
