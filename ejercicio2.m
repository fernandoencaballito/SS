#se miden los tiempos segun lo que se pide en el ejercicio 2
warning("off","Octave:broadcast");
N_min= 50;
N_max=100;#poner un limite mayor!!

M_min=13;
M_max=13; ##tiene que cumplir el criterio L/M>rc+ 2 *r_max
periodic=false;
radius=0.25;
rc=1;
L=20;
result_bruteForce=zeros(length(N_min:N_max),length(M_min:M_max));
result_CIM=zeros(length(N_min:N_max),length(M_min:M_max));
for N=(N_min:N_max)
    particles=generateRandomParticles(N,L, radius);
    for M=(M_min:M_max)
         
         
         
         #cell index method
         tic;
         grid= cell(M);
         grid = setUpGrid(grid,L,N,M,particles);
	       neighbours=getNeighboors(N,grid,particles,rc,M,periodic,L);

         
         time=toc;
         
         result_CIM(N-N_min+1,M-M_min+1)=time;
         
         
         #brute force
         tic
         neighbours2=fuerzaBruta(particles, rc, N, periodic);
         
         
         time2=toc;
    
         result_bruteForce(N-N_min+1,M-M_min+1)=time2;
    endfor
   
    
 endfor
 result_CIM
plot(N_min:N_max,result_CIM(:,1),'b')
hold on
plot(N_min:N_max,result_bruteForce(:,1),'r')
hold off
   