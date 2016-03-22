##probando muchos ejemplos para ver que coincidan bruteforce con cim
N_min= 100;
N_max= 200;
stepParticle = 100;

M_min=3;
M_max=13; ##tiene que cumplir el criterio L/M>rc+ 2 *r_max
          ## es decir hasta 13
stepM = 1;

#periodic=true;
radius=0.25;
rc=1;
L=20;


particlesCant = N_min:stepParticle:N_max;
cellCants = M_min:stepM:M_max;


for periodic=[false,true]
periodic
  for N=particlesCant
    N
    
      particles=generateRandomParticles(N,L, radius);
        #brute force
           
        neighboursBruteForce=fuerzaBruta(particles, rc, N, periodic, L);
      for M=cellCants
           M 
           #cell index method
          
           grid= cell(M);
           grid = setUpGrid(grid,L,N,M,particles);
           neighboursCIM=getNeighboors(N,grid,particles,rc,M,periodic,L);

          assert(compareCell(neighboursCIM,neighboursBruteForce,particles)); 
           
                         
       
      endfor
     
      
   endfor
endfor
 

 
