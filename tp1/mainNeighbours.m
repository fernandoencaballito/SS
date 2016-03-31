function neighbours=mainNeighbours(L,N,M,particles,rc,periodic)

matrix = createGridFromParticles(rc,M, particles,N,L);
matrix = setUpGrid(matrix,L,N,M,particles);

neighbours = getNeighboors(N,matrix,particles,rc,M,periodic,L);

endfunction
