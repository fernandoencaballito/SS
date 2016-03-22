#se miden los tiempos segun lo que se pide en el ejercicio 2
warning("off","Octave:broadcast");

N_min= 10;
N_max= 1000;#poner un limite mayor!!
stepParticle = 10;

M_min=1;
M_max=13; ##tiene que cumplir el criterio L/M>rc+ 2 *r_max
stepM = 1;

periodic=true;
radius=0.25;
rc=1;
L=20;

#plotVars
fileName1 ="./ArchivosEjemplo/bruteForceCompareM="
fileName2 =".jpg";
Mplot=13;
Nplot=N_max;

particlesCant = N_min:stepParticle:N_max;
cellCants = M_min:stepM:M_max;


result_bruteForce=zeros(length(particlesCant),length(cellCants));
result_CIM=zeros(length(particlesCant),length(cellCants));
for N=particlesCant
	N
	N_i=(N - N_min)/stepParticle +1;
	
    particles=generateRandomParticles(N,L, radius);
    #brute force
    tic
    neighbours2=fuerzaBruta(particles, rc, N, periodic, L);     
    time2=toc;
    
    for M=cellCants
         M 
         #cell index method
         tic;
         grid= cell(M);
         grid = setUpGrid(grid,L,N,M,particles);
	       neighbours=getNeighboors(N,grid,particles,rc,M,periodic,L);

         
         time=toc;
         
         result_CIM(N_i,M-M_min+1)=time;
         
         
         
    
         result_bruteForce(N_i,M-M_min+1)=time2;
    endfor
   
    
 endfor


#plot
	size(particlesCant)
	size(result_CIM)
  
  for M=cellCants
  
       
        filename=strcat(fileName1,num2str(M),fileName2);
        
        p=plot(particlesCant, result_CIM(:,M-M_min+1),'r')
        hold on
        p=plot(particlesCant, result_bruteForce(:,M-M_min+1),'b')
        grid
        title("test fuerza bruta vs CIM");
        xlabel("cantidad de particulas");
        ylabel("tiempo de ejecucion [s]");
        legend("cell index method","brute force");
        hold off
        filename
        saveas(p,filename);
  endfor
