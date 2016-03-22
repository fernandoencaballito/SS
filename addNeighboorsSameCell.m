##Función para obtener los vecinos dentro de una misma celda.
function neighboors=addNeighboorsSameCell(matrix,previousNeighboors,particles,currentCell_row, currentCell_col,rc,periodic,M,L)

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
              	if(distance<=rc) %se agrega el id de la particula vecina
                	neighboors{1,currentParticleID}=[neighboors{1,currentParticleID},nextParticleID];
                	neighboors{1,nextParticleID}=[neighboors{1,nextParticleID},currentParticleID];
                  
                else
                        if(M==1 && periodic)
                               deltaX= abs(nextParticlePosition(1)-currentParticlePosition(1));
                               deltaY=abs(nextParticlePosition(2)-currentParticlePosition(2));
                                periodic_distance=sqrt((L-deltaX)^2 +(L-deltaY)^2 )-currentParticleRadius-nextParticleRadius;
                                ##caso en el que sean vecinas por condiciones periodicas
                                if(periodic && (periodic_distance<=rc))
                                  neighboors{1,currentParticleID}=[neighboors{1,currentParticleID},nextParticleID];
                                  neighboors{1,nextParticleID}=[neighboors{1,nextParticleID},currentParticleID];
                            
                                endif
                        
                        endif
             	 endif
	endfor
	
endfor


endfunction
