## función que calcula y devuelve a los vecinos.
##neighboors: es un arreglo de celdas. Cada celda representa los vecinos de la partícula. La posición dentro del arrreglo de celdas representa el identificador de la partícula.
##N: cantidad de particulas en total.
## grid: es la grilla, en cada celda se tiene un vector que contiene los IDs de las partículas que se ubican en dicha celda.
## particles: datos de cada partícula.
##M: ancho de la grilla en celdas
## periodic: booleano que indica si hay condiciones periódicas de contorno.
function neighboors=getNeighboors(N,grid,particles,rc,M,periodic,L)
	neighboors=cell(1,N);
	for i=(1:M)
		for j=(1:M)
      if(length(grid{i,j})>0)
            if(M<3 )
                  #se evaluan que no se repitan las posiciones de celdas antes de calcular vecinos
                  [next4_x,next4_y]=getPeriodicPosition(i+1,j,periodic,M);
                  if(!(next4_x==-1 || next4_y==-1))
                    nextPositions=[next4_x,next4_y];
                  else
                    nextPositions=[]; 
                  endif;
                   
                  #posicion(i+1,j+1)
                  [next1_x,next1_y]=getPeriodicPosition(i+1,j+1,periodic,M);
                  if(! contains2D(nextPositions,next1_x,next1_y) && !(next1_x==-1 || next1_y==-1) ) 
                           nextPositions=[nextPositions;next1_x,next1_y];
                  endif
              
                  #posicion (i,j+1)
                  [next2_x,next2_y]=getPeriodicPosition(i,j+1,periodic,M);
                  if(! contains2D(nextPositions,next2_x,next2_y) && !(next2_x==-1 || next2_y==-1)) 
                           nextPositions=[nextPositions;next2_x,next2_y];
                  endif
                  #posicion (i-1,j+1)  
                  [next3_x,next3_y]=getPeriodicPosition(i-1,j+1,periodic,M);
                  if(! contains2D(nextPositions,next3_x,next3_y) && !(next3_x==-1 || next3_y==-1)) 
                           nextPositions=[nextPositions;next3_x,next3_y];
                  endif
                  
                  [rows,cols]=size(nextPositions);
                  for nextPositionIndex=(1:rows)
                      next_x=nextPositions(nextPositionIndex,1);
                      next_y=nextPositions(nextPositionIndex,2);
                      neighboors = addNeighboorsBetweenCells(N,grid,neighboors,particles,i, j, next_x, next_y, rc, periodic, M,L);			
              
                  endfor
                  
                 
                  
              
            else #en los demas casos no se repiten las posiciones de celdas
                
                #function neighboors = addNeighboorsBetweenCells(N,grid,previousNeighboors,particles,currentCell_row, currentCell_col, nextCell_row,nextCell_col,rc,periodic,M)
                ##se compara la celda (i,j) contra (i+1,j)
                neighboors = addNeighboorsBetweenCells(N,grid,neighboors,particles,i, j,(i+1) , j, rc, periodic, M,L);			
              
                ##se compara la celda (i,j) contra (i-1,j+1)
                neighboors=addNeighboorsBetweenCells(N,grid,neighboors,particles,i, j,(i+1) ,j+1,rc,periodic,M,L);			
              
                ## se compara la celda (i,j) contra (i,j+1)
                neighboors=addNeighboorsBetweenCells(N,grid,neighboors,particles,i, j,i ,j+1,rc,periodic,M,L);			
              
                ## se compara la celda (i,j) contra (i+1,j+1)
                neighboors=addNeighboorsBetweenCells(N,grid,neighboors,particles,i, j,(i-1) ,j+1,rc,periodic,M,L);			
              
                
            endif
            ##se agregan los vecinos dentro de una misma celda
            neighboors=addNeighboorsSameCell(grid,neighboors,particles,i, j,rc,periodic,M,L);
		  endif
    endfor
	endfor
 
    if(M==2)
         neighboors=eliminateDuplicated(neighboors);
    endif

endfunction

#Prueba1: esta prueba pasa correctamente
%!test
%! rc=2.4;
%! N=3;
%! M=2;
%! L=10;
%! matrix = cell(M);
%! matrix{1,1}=[1];
%! matrix{2,2}=[3];
%! matrix{2,1}=[2];
%! particles=[1,1,3,1; 1, 6.5, 0.5,1; 5.5, 5.5,1 ,1];
%!
%! neighboors=getNeighboors(N,matrix,particles,rc,M,false,L) 
%! 
%! assert(length (neighboors{1,1})==2);
%! assert(length (neighboors{1,2})==1);
%! assert(length (neighboors{1,3})==1);
%! assert(any (neighboors{1,1}==2 ) );
%! assert(any (neighboors{1,1}==3 ) );
%! assert(any (neighboors{1,2}==1 ) );
%! assert(any (neighboors{1,3}==1 ) );


#Prueba2:
%!test
%! rc=2.4;
%! N=5;
%! M=2;
%! L=10;
%! matrix = cell(M);
%! matrix{2,1}=[2];
%! matrix{2,2}=[3];
%! matrix{1,1}=[1,4,5];
%! particles=[1,1,3,1; 1, 6.5, 0.5,1; 5.5, 5.5,1 ,1;4.5,1,0.5,1;3,1,0.5,1];
%!
%! neighboors=getNeighboors(N,matrix,particles,rc,M,false,L) 
%! 
%! assert(length (neighboors{1,1})==4);
%! assert(length (neighboors{1,2})==1);
%! assert(length (neighboors{1,3})==1);
%! assert(length (neighboors{1,4})==2);
%! assert(length (neighboors{1,5})==2);
%! assert(any (neighboors{1,1}==2 ) );
%! assert(any (neighboors{1,1}==3 ) );
%! assert(any (neighboors{1,1}==4 ) );
%! assert(any (neighboors{1,1}==5 ) );
%! assert(any (neighboors{1,2}==1 ) );
%! assert(any (neighboors{1,3}==1 ) );
%! assert(any (neighboors{1,4}==1 ) );
%! assert(any (neighboors{1,4}==5 ) );
%! assert(any (neighboors{1,5}==1 ) );
%! assert(any (neighboors{1,5}==4 ) );


#Prueba3: prueba de condiciones de contorno
%!test
%! rc=1.9;
%! N=2;
%! M=5;
%! L=10;
%! matrix = cell(M);
%! matrix{1,1}=[2];
%! matrix{5,5}=[1];
%! particles=[10,10,1.5,1; 0,0,1,1];
%!
%! neighboors=getNeighboors(N,matrix,particles,rc,M,true,L) 
%! 
%! assert(length (neighboors{1,1})==1);
%! assert(length (neighboors{1,2})==1);
%! assert(any (neighboors{1,1}==2 ) );
%! assert(any (neighboors{1,2}==1 ) );



# Prueba4 esta prueba corresponde con los datos de ejemplo.
%!test
%! rc=6;
%!
%! M=10;
%!
%! [particles,N] = loadParticles("./ArchivosEjemplo/Static100.txt","./ArchivosEjemplo/Dynamic100.txt");
%! [grid,L,N] = createGrid("./ArchivosEjemplo/Static100.txt",rc,M,particles);
%!
%! grid = setUpGrid(grid,L,N,M,particles);
%! 
%! neighboors=getNeighboors(N, grid, particles,rc,M,false,L);
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



# Prueba 5: caso periodico que se compara contra el resultado de fuerza bruta
%!test
%!    M_min=3;
%!    M_max=5; ##tiene que cumplir el criterio L/M>rc+ 2 *r_max
%!              ## es decir hasta 13
%!    stepM = 1;
%!    periodic=true;
%!    radius=0.25;
%!    rc=1;
%!    L=20;
%!    cellCants = M_min:stepM:M_max;
%!      N=3;
%!    particles=[0.781, 19.99408,0.25,1; 0.080092, 18.780664, 0.25, 1; 19.78401,0.44561, 0.25, 1];
%!      #brute force
%!       
%!      neighboursBruteForce=fuerzaBruta(particles, rc, N, periodic, L);
%!    for M=cellCants
%!        M 
%!        #cell index method   
%!         grid= cell(M);
%!         grid = setUpGrid(grid,L,N,M,particles);
%!	       neighboursCIM=getNeighboors(N,grid,particles,rc,M,periodic,L);
%!        assert(compareCell(neighboursCIM,neighboursBruteForce,particles)); 
%!         
%!    endfor
   
   
#Prueba 6:probando muchos ejemplos para ver que coincidan bruteforce con cim
%!test
%! N_min= 100;
%! N_max= 300;
%! stepParticle = 100;
%! 
%! M_min=1;
%! M_max=13; ##tiene que cumplir el criterio L/M>rc+ 2 *r_max
%!           ## es decir hasta 13
%! stepM = 1;
%! 
%!  #periodic=true;
%! radius=0.25;
%! rc=1;
%! L=20;
%! 
%! particlesCant = N_min:stepParticle:N_max;
%! cellCants = M_min:stepM:M_max;
%! 
%! 
%! for periodic=[true,false]
%! periodic
%!   for N=particlesCant
%!     N
%!        particles=generateRandomParticles(N,L, radius);
%!         #brute force
%!            
%!         neighboursBruteForce=fuerzaBruta(particles, rc, N, periodic, L);
%!       for M=cellCants
%!            M 
%!            #cell index method
%!           
%!            grid= cell(M);
%!            grid = setUpGrid(grid,L,N,M,particles);
%!            neighboursCIM=getNeighboors(N,grid,particles,rc,M,periodic,L);
%! 
%!           assert(compareCell(neighboursCIM,neighboursBruteForce,particles)); 
%!           
%!       endfor
%! 
%!       
%!    endfor
%! endfor
 
