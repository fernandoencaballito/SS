#se miden los tiempos segun lo que se pide en el ejercicio 2
warning("off","Octave:broadcast");

N_min= 100;#250
N_max=300;#1000
paceParticle = 100;#250

M_min=8;
M_max=13; ##tiene que cumplir el criterio L/M>rc+ 2 *r_max
paceCell = 1;

periodic=true;
radius=0.25;
rc=1;
L=20;

outputFileName = "./ArchivosEjemplo/resultSet2.txt";

#plotVars
plotFileName="./Plots/testOptimoM.png";
plotCells = 4;
plot = 1;


particlesCant = N_min:paceParticle:N_max;
cellsCant = M_min:paceCell:M_max;
iterations = 1#10

result_CIM=zeros(length(particlesCant),length(cellsCant));


for N=particlesCant
	N
	N_i=(N - N_min)/paceParticle +1;
	
	times=zeros(length(cellsCant),iterations);

	for k = 1:iterations
		iteratation = k
		particles=generateRandomParticles(N,L, radius);
		for M=cellsCant
		     M
		     #cell index method
		     tic;
		     grid= cell(M);
		     grid = setUpGrid(grid,L,N,M,particles);
			    neighbours=getNeighboors(N,grid,particles,rc,M,periodic,L);
		     #neighbours = cellIndexMethod(grid, particles, periodic, L, rc, N, M);
			   time = toc
		     times(M-M_min+1,k)=time;

		endfor
	 endfor  


	 result_CIM(N_i,:)=mean(times');
    
 endfor


dlmwrite(outputFileName,result_CIM);

if plot 
  q=L^2;
	plotEj3(plotCells, cellsCant, (N_min/q):(paceParticle/q):(N_max/q), outputFileName, plotFileName, M_min)

endif







