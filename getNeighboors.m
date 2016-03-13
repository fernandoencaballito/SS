## función que calcula y devuelve a los vecinos.
##neighboors: es un arreglo de celdas. Cada celda representa los vecinos de la partícula. La posición dentro del arrreglo de celdas representa el identificador de la partícula.
##N: cantidad de particulas en total.
## grid: es la grilla, en cada celda se tiene un vector que contiene los IDs de las partículas que se ubican en dicha celda.
## particles: datos de cada partícula.
##M: ancho de la grilla en celdas
## periodic: booleano que indica si hay condiciones periódicas de contorno.
function neighboors=getNeighboors(N,grid,particles,rc,M,periodic)
	neighboors=cell(1,N);
	for i=(1:M)
		for j=(1:M)
			#function neighboors = addNeighboorsBetweenCells(N,grid,previousNeighboors,particles,currentCell_row, currentCell_col, nextCell_row,nextCell_col,rc,periodic,M)
			##se compara la celda (i,j) contra (i-1,j)
			neighboors = addNeighboorsBetweenCells(N,grid,neighboors,particles,i, j,(i-1) , j, rc, periodic, M);			
		
			##se compara la celda (i,j) contra (i-1,j+1)
			neighboors=addNeighboorsBetweenCells(N,grid,neighboors,particles,i, j,(i-1) ,j+1,rc,periodic,M);			
		
			## se compara la celda (i,j) contra (i,j+1)
			neighboors=addNeighboorsBetweenCells(N,grid,neighboors,particles,i, j,i ,j+1,rc,periodic,M);			
		
			## se compara la celda (i,j) contra (i+1,j+1)
			neighboors=addNeighboorsBetweenCells(N,grid,neighboors,particles,i, j,(i+1) ,j+1,rc,periodic,M);			
		
		endfor
	endfor


endfunction

%!test
%! rc=6;
%!
%!
%!
%! [grid,L,N,M] = createGrid("./ArchivosEjemplo/Static100.txt",rc);
%! particles = loadParticles("./ArchivosEjemplo/Static100.txt","./ArchivosEjemplo/Dynamic100.txt");
%!
%!
%! grid = setUpGrid(grid,L,N,M,particles);
%! 
%! neighboors=getNeighboors(N,grid,particles,rc,M,true);
%! 
%! assert(length (neighboors{1,9})==0);
%! assert(length (neighboors{1,13})==0);
%! assert(any (neighboors{1,16}==30));
%! neighboors{1,22}
%! assert(any (neighboors{1,22}==91));
%! assert(any (neighboors{1,22}==41)); 
%! assert(length (neighboors{1,25})==0);
%! assert(length (neighboors{1,28})==0);
%! assert(any (neighboors{1,30}==16 ) );
%!
