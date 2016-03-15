##Función para obtener los vecinos dentro de una misma celda.
function neighboors=addNeighboorsSameCell(matrix,previousNeighboors,particles,currentCell_row, currentCell_col,rc)

##constantes, posiciones de los datos de cada partícula
x_pos=1;
y_pos=2;
radius_pos=3;
color_pos=4;
##



currentCell=matrix{currentCell_row, currentCell_col};
neighboors=previousNeighboors;

for k=(1:length (currentCell))
	currentParticleID=currentCell(k);
	currentParticleData=particles(currentParticleID,:);##vector con los datos de la partícula actual
        currentParticlePosition=[currentParticleData(x_pos), currentParticleData(y_pos)];
        currentParticleRadius=currentParticleData(radius_pos);
        
	for l=((k+1):length (currentCell))
		nextParticleID=currentCell(l);
	     	nextParticleData=particles(nextParticleID,:);
              	nextParticlePosition=[nextParticleData(x_pos),nextParticleData(y_pos)];
              	nextParticleRadius=nextParticleData(radius_pos);
	      
		%distancia borde-borde
              	distance=norm(currentParticlePosition-nextParticlePosition,2)-currentParticleRadius-nextParticleRadius;
              	if(distance<rc) %se agrega el id de la particula vecina
                	neighboors{1,currentParticleID}=[neighboors{1,currentParticleID},nextParticleID];
                	neighboors{1,nextParticleID}=[neighboors{1,nextParticleID},currentParticleID];
             	 endif
	endfor
	
endfor


endfunction
