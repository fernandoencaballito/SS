function cellIndexMethod(staticFile, dynamicFile, M, rc, periodic, outputFile, bruteForce)
	M=20;
	rc = 4;
	periodic = 0;

	particles = loadParticles(staticFile,dynamicFile);
	[grid,L,N] = createGrid(staticFile,rc,M,particles);
	grid = setUpGrid(grid,L,N,M,particles);
	rc
	maxRc = L/M
	
	if !bruteForce
		neighbours = getNeighboors(N,grid,particles,rc,M,periodic,L);
	else	
		neighbours = fuerzaBruta(particles, rc, N, periodic)
	endif

	writeNeighbours(otputFile,neighbours,N);

#	pid=24;
#	particles(pid,4)=2;
#	for neighbour = neighbours{1,pid}
#		particles(neighbour,4)=3;
#	endfor
#
#	plotParticles(particles,L,M);

endfunction
