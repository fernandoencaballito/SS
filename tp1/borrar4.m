#Prueba2:
 
  rc=2.4;
  N=5;
  M=2;
  L=10;
  matrix = cell(M);
  matrix{1,1}=[2];
  matrix{1,2}=[3];
  matrix{2,1}=[1,4,5];
  particles=[1,1,3,1; 1, 6.5, 0.5,1; 5.5, 5.5,1 ,1;4.5,1,0.5,1;3,1,0.5,1];
 
  neighboors=getNeighboors(N,matrix,particles,rc,M,false) 
  
  length (neighboors{1,1})==4
  length (neighboors{1,2})==1
  length (neighboors{1,3})==1
  length (neighboors{1,4})==2
  length (neighboors{1,5})==2
  any (neighboors{1,1}==2 ) 
  any (neighboors{1,1}==3 ) 
  any (neighboors{1,1}==4 ) 
  any (neighboors{1,1}==5 ) 
 any (neighboors{1,2}==1 ) 
  any (neighboors{1,3}==1 ) 
  any (neighboors{1,4}==1 )
  any (neighboors{1,4}==5 ) 
  any (neighboors{1,5}==1 ) 
  any (neighboors{1,5}==4 ) 

