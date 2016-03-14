M=10;
[grid,L,N] = createGrid("./ArchivosEjemplo/Static100.txt",1,M);
particles = loadParticles("./ArchivosEjemplo/Static100.txt","./ArchivosEjemplo/Dynamic100.txt");
grid = setUpGrid(grid,L,N,M,particles)
