rc=2.4;
N=3;
 M=2;
 L=10;
 matrix = cell(M);
 matrix{1,1}=[2];
 matrix{1,2}=[3];
 matrix{2,1}=[1];
particles=[1,1,3,1; 1, 6.5, 0.5,1; 5.5, 5.5,1 ,1];

 neighboors=getNeighboors(N,matrix,particles,rc,M,false) 
 
length (neighboors{1,1})==2
length (neighboors{1,2})==1
length (neighboors{1,3})==1
any (neighboors{1,1}==2 )
any (neighboors{1,1}==3 )
any (neighboors{1,2}==1 ) 
any (neighboors{1,3}==1 ) 

