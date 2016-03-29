##Función para obtener los vecinos mediante fuerza bruta
function neighbours=fuerzaBruta(particles, rc, N, periodic, L)

neighbours = cell(1,N);

for k = 1 : length(particles(:,1))
	for j = k + 1: length(particles(:,1))
			%distancia borde-borde
			x = abs(particles(k,1) - particles(j,1));
			y = abs(particles(k,2) - particles(j,2));
			d = sqrt(x^2+y^2) - particles(k,3) - particles(j,3);
			if(d <= rc)
				neighbours = addNeighbours(neighbours,k,j, particles);
			else 
				if(periodic)
					x = L - x;
					y = L - y;
					d = sqrt(x^2+y^2) - particles(k,3) - particles(j,3);;##distancia periodica
						if(d <= rc)
							neighbours = addNeighbours(neighbours,k,j, particles);
						endif
				endif
			endif
		
	endfor
	
endfor


endfunction

function neighbours = addNeighbours(neighbours, k, j, particles)
		neighbours{1,k} = [neighbours{1,k},j];
		neighbours{1,j} = [neighbours{1,j},k];	
endfunction

#Prueba1: esta prueba pasa correctamente
%!test
%! rc=2.4;
%! N=3;
%! M=2;
%! L=10;
%! particles=[1,1,3,1; 1, 6.5, 0.5,1; 5.5, 5.5,1 ,1];
%!
%! neighbours = fuerzaBruta(particles, rc, N, false) 
%! 
%! assert(length (neighbours{1,1})==2);
%! assert(length (neighbours{1,2})==1);
%! assert(length (neighbours{1,3})==1);
%! assert(any (neighbours{1,1}==2 ) );
%! assert(any (neighbours{1,1}==3 ) );
%! assert(any (neighbours{1,2}==1 ) );
%! assert(any (neighbours{1,3}==1 ) );


#Prueba2:
%!test
%! rc=2.4;
%! N=5;
%! M=2;
%! L=10;
%! matrix = cell(M);
%! matrix{1,1}=[2];
%! matrix{1,2}=[3];
%! matrix{2,1}=[1,4,5];
%! particles=[1,1,3,1; 1, 6.5, 0.5,1; 5.5, 5.5,1 ,1;4.5,1,0.5,1;3,1,0.5,1];
%!
%! neighbours = fuerzaBruta(particles, rc, N, false)  
%! 
%! assert(length (neighbours{1,1})==4);
%! assert(length (neighbours{1,2})==1);
%! assert(length (neighbours{1,3})==1);
%! assert(length (neighbours{1,4})==2);
%! assert(length (neighbours{1,5})==2);
%! assert(any (neighbours{1,1}==2 ) );
%! assert(any (neighbours{1,1}==3 ) );
%! assert(any (neighbours{1,1}==4 ) );
%! assert(any (neighbours{1,1}==5 ) );
%! assert(any (neighbours{1,2}==1 ) );
%! assert(any (neighbours{1,3}==1 ) );
%! assert(any (neighbours{1,4}==1 ) );
%! assert(any (neighbours{1,4}==5 ) );
%! assert(any (neighbours{1,5}==1 ) );
%! assert(any (neighbours{1,5}==4 ) );


#Prueba3:
%!test
%! rc=1.9;
%! N=2;
%! M=5;
%! L=10;
%! matrix = cell(M);
%! matrix{5,1}=[2];
%! matrix{1,5}=[1];
%! particles=[10,10,1.5,1; 0,0,1,1];
%!
%! neighbours = fuerzaBruta(particles, rc, N, true, L) 
%! 
%! assert(length (neighbours{1,1})==1);
%! assert(length (neighbours{1,2})==1);
%! assert(any (neighbours{1,1}==2 ) );
%! assert(any (neighbours{1,2}==1 ) );



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
%! neighbours = fuerzaBruta(particles, rc, N, false) 
%! 
%! assert(length (neighbours{1,9})==0);
%! assert(length (neighbours{1,13})==0);
%! assert(any (neighbours{1,16}==30));
%! assert(length (neighbours{1,25})==0);
%! assert(length (neighbours{1,28})==0);
%! assert(any (neighbours{1,30}==16 ) );
%! assert(any (neighbours{1,35}==42 ) );
%! assert(length (neighbours{1,39})==0);
%! assert(any (neighbours{1,41}==22 ) );
%! assert(any (neighbours{1,41}==91 ) );
%! assert(any (neighbours{1,42}==35 ) );
%! assert(any (neighbours{1,91}==22 ) );
%! assert(any (neighbours{1,91}==41 ) );
%! assert(any (neighbours{1,91}==84 ) );
%! assert(length(neighbours{1,91})==3  );
