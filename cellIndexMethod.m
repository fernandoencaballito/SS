function cellIndexMethod(staticFile, dynamicFile, M, rc, periodic, outputFile, bruteForce)
	M=20;
	rc = 4;
	periodic = 0;

	particles = loadParticles("./ArchivosEjemplo/Static100.txt","./ArchivosEjemplo/Dynamic100.txt");
	[grid,L,N] = createGrid("./ArchivosEjemplo/Static100.txt",rc,M,particles);
	grid = setUpGrid(grid,L,N,M,particles);
	rc
	maxRc = L/M

	neighbours=getNeighboors(N,grid,particles,rc,M,periodic,L);
	#neighbours = fuerzaBruta(particles, rc, N, periodic)

	writeNeighbours("./ArchivosEjemplo/outputTest.txt",neighbours,N);


	pid=24;
	particles(pid,4)=2;
	for neighbour = neighbours{1,pid}
		particles(neighbour,4)=3;
	endfor

	plotParticles(particles,L,M);
endfunction
