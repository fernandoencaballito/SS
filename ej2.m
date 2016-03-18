#se miden los tiempos segun lo que se pide en el ejercicio 2
warning("off","Octave:broadcast");

N_min= 30;
N_max= 70;#poner un limite mayor!!
paceParticle = 1;

M_min=10;
M_max=13; ##tiene que cumplir el criterio L/M>rc+ 2 *r_max
paceCell = 1;

periodic=true;
radius=0.25;
rc=1;
L=20;

#plotVars
fileName="./ArchivosEjemplo/bruteForceCompare1.jpg";
Mplot=13;
Nplot=N_max;

particlesCant = N_min:paceParticle:N_max;
cellsCant = M_min:paceCell:M_max;


result_bruteForce=zeros(length(particlesCant),length(cellsCant));
result_CIM=zeros(length(particlesCant),length(cellsCant));
for N=particlesCant
	N
	N_i=(N - N_min)/paceParticle +1;
	
    particles=generateRandomParticles(N,L, radius);
    for M=cellsCant
              
         #cell index method
         tic;
         grid= cell(M);
         grid = setUpGrid(grid,L,N,M,particles);
	       neighbours=getNeighboors(N,grid,particles,rc,M,periodic,L);

         
         time=toc;
         
         result_CIM(N_i,M-M_min+1)=time;
         
         
         #brute force
         tic
         neighbours2=fuerzaBruta(particles, rc, N, periodic, L);
         
         
         time2=toc;
    
         result_bruteForce(N_i,M-M_min+1)=time2;
    endfor
   
    
 endfor


#plot
	size(particlesCant)
	size(result_CIM)
	p=plot(particlesCant, result_CIM(:,Mplot-M_min+1),'r')
	hold on
	p=plot(particlesCant, result_bruteForce(:,Mplot-M_min+1),'b')
	grid
	title("test fuerza bruta");
	xlabel("cantidad de particulas");
	ylabel("tiempo de ejecucion (s)");
	legend("cell index method","brute force");
	hold off
	fileName
	saveas(p,fileName);

