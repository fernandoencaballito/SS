# esta prueba corresponde con los datos de ejemplo.Algunos valores no coinciden con la supuesta respuesta.

 rc=6;

 M=10;

[particles,N] = loadParticles("./ArchivosEjemplo/Static100.txt","./ArchivosEjemplo/Dynamic100.txt");
 [grid,L,N] = createGrid("./ArchivosEjemplo/Static100.txt",rc,M,particles);
 


 grid = setUpGrid(grid,L,N,M,particles);
 
 neighboors=getNeighboors(N, grid, particles,rc,M,false,L);
 
 assert(length (neighboors{1,9})==0);
 assert(length (neighboors{1,13})==0);
 assert(any (neighboors{1,16}==30));
 assert(length (neighboors{1,25})==0);
 assert(length (neighboors{1,28})==0);
 assert(any (neighboors{1,30}==16 ) );
 assert(any (neighboors{1,35}==42 ) );
 assert(length (neighboors{1,39})==0);
 assert(any (neighboors{1,41}==22 ) );
 assert(any (neighboors{1,41}==91 ) );
 assert(any (neighboors{1,42}==35 ) );
 assert(any (neighboors{1,91}==22 ) );
 assert(any (neighboors{1,91}==41 ) );
 assert(any (neighboors{1,91}==84 ) );
 assert(length(neighboors{1,91})==3  );
