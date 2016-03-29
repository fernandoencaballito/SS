 rc=6;
M=10;


 [grid,L,N] = createGrid("./ArchivosEjemplo/Static100.txt",rc,M);
 particles = loadParticles("./ArchivosEjemplo/Static100.txt","./ArchivosEjemplo/Dynamic100.txt");


 grid = setUpGrid(grid,L,N,M,particles);
 
 neighboors=getNeighboors(N,grid,particles,rc,M,true);
 
length (neighboors{1,9})==0
length (neighboors{1,13})==0
any (neighboors{1,16}==30)
length (neighboors{1,25})==0
length (neighboors{1,28})==0
any (neighboors{1,30}==16 ) 
neighboors{1,35}
any (neighboors{1,35}==42 ) 
length (neighboors{1,39})==0
any (neighboors{1,41}==22 ) 
any (neighboors{1,41}==91 ) 
neighboors{1,42}
any (neighboors{1,42}==35 ) 
any (neighboors{1,91}==22 ) 
any (neighboors{1,91}==41 ) 
any (neighboors{1,91}==84 )
neighboors{1,91}
length (neighboors{1,91})==3  
