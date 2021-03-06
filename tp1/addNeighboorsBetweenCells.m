##
##función que agrega los vecinos para las partículas de la actual celda que se encuentran en la siguiente celda.TIENEN QUE SER CELDAS DISTINTAS.
##N: es la cantidad de partículas.
##grid: es la grilla, en cada celda se tiene un vector que contiene los IDs de las partículas que se ubican en dicha celda.
##previousNeighboors:son los vecinos calculados en pasos anteriores. Debe estar inicializada.
##particles:matriz en la cual cada fila representa a los datos de cada partícula.
##currentCell_row, currentCell_col: posición de la celda actual en la grilla.
##nextCell_row, nextCell_col= posición de la siguiente celda en la grilla.
##
## neighboors: vecinos calculados entre las celdas en cuestión y en pasos anteriores.



function neighboors = addNeighboorsBetweenCells(N,matrix,previousNeighboors,particles,currentCell_row, currentCell_col, nextCell_row,nextCell_col,rc,periodic,M,L)

##constantes, posiciones de los datos de cada partícula
x_pos=1;
y_pos=2;
radius_pos=3;
color_pos=4;
##
[nextCell_row,nextCell_col]=getPeriodicPosition(nextCell_row, nextCell_col,periodic,M);


##nextCell_row
##nextCell_col

neighboors=previousNeighboors;

if(!(nextCell_col==-1 || nextCell_row==-1)  && !(currentCell_col==nextCell_col && currentCell_row==nextCell_row)  )
      currentCell=matrix{currentCell_row, currentCell_col};
      
      nextCell=matrix{nextCell_row, nextCell_col};
      
      
      for currentParticleID=currentCell

        currentParticleData=particles(currentParticleID,:);##vector con los datos de la partícula actual
        currentParticlePosition=[currentParticleData(x_pos), currentParticleData(y_pos)];
        currentParticleRadius=currentParticleData(radius_pos);
        for nextParticleID=nextCell
          
          if(nextParticleID!=currentParticleID)		
                nextParticleData=particles(nextParticleID,:);
                nextParticlePosition=[nextParticleData(x_pos),nextParticleData(y_pos)];
                nextParticleRadius=nextParticleData(radius_pos);
                %distancia borde-borde
                distance=norm(currentParticlePosition-nextParticlePosition,2)-currentParticleRadius-nextParticleRadius;
                if(distance<=rc) %se agrega el id de la particula vecina
                  neighboors{1,currentParticleID}=[neighboors{1,currentParticleID},nextParticleID];
                  neighboors{1,nextParticleID}=[neighboors{1,nextParticleID},currentParticleID];
                
                else
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
endif;




endfunction


%!test
%! rc=6;
%! M=10;
%! [particles,N] = loadParticles("./ArchivosEjemplo/Static100.txt","./ArchivosEjemplo/Dynamic100.txt");
%! [matrix,L,N] = createGrid("./ArchivosEjemplo/Static100.txt",rc,M,particles);
%!
%! matrix = setUpGrid(matrix,L,N,M,particles);
%! neighboors=cell(1,N);
%! neighboors=addNeighboorsBetweenCells (N, matrix, neighboors, particles, 3, 3, 3, 4, rc, true, M,L);
