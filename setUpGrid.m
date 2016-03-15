function grid = setUpGrid(grid,L,N,M,particles)
	anchoCelda = L/M
	for k=1:length(particles)
		particle = particles(k,1:2);
		i = floor(particle(2)/anchoCelda)+1;	
		j = floor(particle(1)/anchoCelda)+1;
		cellParticles = grid{i,j};
		cellParticles(end+1)=k;
		grid(i,j) = cellParticles;
	endfor
endfunction



#Prueba1
%!test
%! N=5;
%! M=2;
%! L=10;
%! matrix = cell(M);
%! particles=[1,9,3,1; 1, 3.5, 0.5,1; 5.5, 4.5,1 ,1;4.5,9,0.5,1;3,9,0.5,1]
%! matrix = setUpGrid(matrix,L,N,M,particles)
%! assert(any (matrix{1,1}==2 ) );
%! assert(any (matrix{1,2}==3 ) );
%! assert(any (matrix{2,1}==1 ) );
%! assert(any (matrix{2,1}==4 ) );
%! assert(any (matrix{2,1}==5 ) );
%! assert(length (matrix{1,1})==1);
%! assert(length (matrix{1,2})==1);
%! assert(length (matrix{2,1})==3);
%! assert(length (matrix{2,2})==0);
%! plotParticles(particles,L,M)