[grid,L,N,M] = createGrid("./ArchivosEjemplo/Static100.txt",1);
particles = loadParticles("./ArchivosEjemplo/Static100.txt","./ArchivosEjemplo/Dynamic100.txt");
grid = setUpGrid(grid,L,N,M,particles)
