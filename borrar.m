rc=6;



 [matrix,L,N,M] = createGrid("./ArchivosEjemplo/Static100.txt",rc);
 particles = loadParticles("./ArchivosEjemplo/Static100.txt","./ArchivosEjemplo/Dynamic100.txt");


matrix = setUpGrid(matrix,L,N,M,particles);
neighboors=cell(1,N);
neighboors=addNeighboorsBetweenCells(N,matrix,neighboors,particles,3 , 3, 3,4,rc,false,M)
