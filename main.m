
M=20;
rc = 4;
periodic = 0;
[grid,L,N] = createGrid("./ArchivosEjemplo/Static100.txt",rc,M);
particles = loadParticles("./ArchivosEjemplo/Static100.txt","./ArchivosEjemplo/Dynamic100.txt");
grid = setUpGrid(grid,L,N,M,particles);
rc
maxRc = L/M

neighbours=getNeighboors(N,grid,particles,rc,M,periodic,L);
writeNeighbours("./ArchivosEjemplo/outputTest.txt",neighbours,N);

plotParticles(particles,L,M);
