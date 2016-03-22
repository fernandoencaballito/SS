##probando muchos ejemplos para ver que coincidan bruteforce con cim

    M_min=1;
    M_max=2; ##tiene que cumplir el criterio L/M>rc+ 2 *r_max
              ## es decir hasta 13
    stepM = 1;

    periodic=true;
    radius=0.25;
    rc=1;
    L=20;


    cellCants = M_min:stepM:M_max;



      N=2;
      
      
	
    #particles=[0.781, 19.99408,0.25,1; 0.080092, 18.780664, 0.25, 1; 19.78401,0.44561, 0.25, 1];
    #particles=[12.85305,   13.35555,    0.25000,    1.00000 ;13.83795, 13.09529,    0.25000,    1.00000;12.86940,14.40276,0.25000, 1.00000]
    #particles=[ 16.50452,  17.24022,    0.25000,    1.00000; 16.32869 ,  15.76726,    0.25000,    1.00000] 
    #particles=[  13.08043,    9.95991,    0.25000,    1.00000;  11.74343,  10.49314,    0.25000,    1.00000;14.03021   11.03524    0.25000    1.00000]
    particles=[0.30643,   0.20169 ,  0.25000,   1.00000;19.63674,   19.83714,    0.25000,   1.00000];
    #brute force
         
      neighboursBruteForce=fuerzaBruta(particles, rc, N, periodic, L);
    for M=cellCants
         M 
         #cell index method
        
         matrix= cell(M);
         matrix = setUpGrid(matrix,L,N,M,particles);
	       neighboursCIM=getNeighboors(N,matrix,particles,rc,M,periodic,L);

        assert(compareCell(neighboursCIM,neighboursBruteForce,particles)); 
         
         
      
         
         
         
                             
     
    endfor
   
    


 
