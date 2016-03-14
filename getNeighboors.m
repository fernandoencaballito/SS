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

#esta prueba pasa correctamente
%!test
%! rc=2.4;
%! N=3;
%! M=2;
%! L=10;
%! matrix = cell(M);
%! matrix{1,1}=[2];
%! matrix{1,2}=[3];
%! matrix{2,1}=[1];
%! particles=[1,1,3,1; 1, 6.5, 0.5,1; 5.5, 5.5,1 ,1];
%!
%! neighboors=getNeighboors(N,matrix,particles,rc,M,false) 
%! 
%! assert(length (neighboors{1,1})==2);
%! assert(length (neighboors{1,2})==1);
%! assert(length (neighboors{1,3})==1);
%! assert(any (neighboors{1,1}==2 ) );
%! assert(any (neighboors{1,1}==3 ) );
%! assert(any (neighboors{1,2}==1 ) );
%! assert(any (neighboors{1,3}==1 ) );

# esta prueba corresponde con los datos de ejemplo.Algunos valores no coinciden con la supuesta respuesta.
%!test
%! rc=6;
%!
%! M=10;
%!
%! [grid,L,N] = createGrid("./ArchivosEjemplo/Static100.txt",rc,M);
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
%! assert(length (neighboors{1,25})==0);
%! assert(length (neighboors{1,28})==0);
%! assert(any (neighboors{1,30}==16 ) );
%! assert(any (neighboors{1,35}==42 ) );
%! assert(length (neighboors{1,39})==0);
%! assert(any (neighboors{1,41}==22 ) );
%! assert(any (neighboors{1,41}==91 ) );
%! assert(any (neighboors{1,42}==35 ) );
%! assert(any (neighboors{1,91}==22 ) );
%! assert(any (neighboors{1,91}==41 ) );
%! assert(any (neighboors{1,91}==84 ) );
%! assert(length(neighboors{1,91})==3  );
