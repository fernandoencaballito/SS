N=5;
 M=2;
 L=10;
 matrix = cell(M);
 particles=[1,1,0.5,1; 1, 6.5, 0.5,1; 5.5, 5.5,1 ,1;4.5,1,0.5,1;3,1,0.5,1]
 matrix = setUpGrid(matrix,L,N,M,particles)
 
 plotParticles(particles,L,M)