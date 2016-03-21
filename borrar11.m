##probando muchos ejemplos para ver que coincidan bruteforce con cim
N_min= 10;
N_max= 100;#poner un limite mayor!!
stepParticle = 10;

M_min=3;
M_max=5; ##tiene que cumplir el criterio L/M>rc+ 2 *r_max
          ## es decir hasta 13
stepM = 1;

periodic=true;
radius=0.25;
rc=1;
L=20;


particlesCant = N_min:stepParticle:N_max;
cellCants = M_min:stepM:M_max;



for N=particlesCant
	N
	N_i=(N - N_min)/stepParticle +1;
	
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

 

 
