[grid,L,N,M] = createGrid("ArchivosEjemplo/ArchivosEjemplo/Static100.txt",1);
particles = loadParticles("ArchivosEjemplo/ArchivosEjemplo/Static100.txt","ArchivosEjemplo/ArchivosEjemplo/Dynamic100.txt");
grid = setUpGrid(grid,L,N,M,particles)
