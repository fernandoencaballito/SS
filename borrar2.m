 rc=6;



 [grid,L,N,M] = createGrid("./ArchivosEjemplo/Static100.txt",rc);
 particles = loadParticles("./ArchivosEjemplo/Static100.txt","./ArchivosEjemplo/Dynamic100.txt");


 grid = setUpGrid(grid,L,N,M,particles);
 
 neighboors=getNeighboors(N,grid,particles,rc,M,true);
 
 length (neighboors{1,9})==0
length (neighboors{1,13})==0
any (neighboors{1,16}==30)
 any (neighboors{1,22}==41)
 any (neighboors{1,22}==91)
 length (neighboors{1,25})==0
length (neighboors{1,28})==0
any (neighboors{1,30}==16 ) 
