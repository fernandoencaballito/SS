##probando muchos ejemplos para ver que coincidan bruteforce con cim

    M_min=3;
    M_max=5; ##tiene que cumplir el criterio L/M>rc+ 2 *r_max
              ## es decir hasta 13
    stepM = 1;

    periodic=false;
    radius=0.25;
    rc=1;
    L=20;


    cellCants = M_min:stepM:M_max;



      N=3;
      
      
	
    #particles=[0.781, 19.99408,0.25,1; 0.080092, 18.780664, 0.25, 1; 19.78401,0.44561, 0.25, 1];
    particles=[12.85305,   13.35555,    0.25000,    1.00000 ;13.83795, 13.09529,    0.25000,    1.00000;12.86940,14.40276,0.25000, 1.00000]
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
   
    


 
