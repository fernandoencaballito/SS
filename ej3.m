#se miden los tiempos segun lo que se pide en el ejercicio 2
warning("off","Octave:broadcast");

N_min= 1000;
N_max= 2000;#poner un limite mayor!!
paceParticle = 100;

M_min=10;
M_max=13; ##tiene que cumplir el criterio L/M>rc+ 2 *r_max
paceCell = 1;

periodic=true;
radius=0.25;
rc=1;
L=20;

#plotVars
fileName="./Plots/testOptimoM.pdf";
plotCells = 4;

particlesCant = N_min:paceParticle:N_max;
cellsCant = M_min:paceCell:M_max;
iterations = 10

result_CIM=zeros(length(particlesCant),length(cellsCant));

for N=particlesCant
	N
	N_i=(N - N_min)/paceParticle +1;
	
	times=zeros(length(cellsCant),iterations);

	for k = 1:iterations
		particles=generateRandomParticles(N,L, radius);
		for M=cellsCant
		          
		     #cell index method
		     tic;
		     grid= cell(M);
		     grid = setUpGrid(grid,L,N,M,particles);
			   neighbours=getNeighboors(N,grid,particles,rc,M,periodic,L);
		     
		     times(M-M_min+1,k)=toc;

		endfor
	 endfor  


	 result_CIM(N_i,:)=mean(times');
    
 endfor

colormap cool


	colors = 1:plotCells;
	k=1;
	color = colormap();	
	color(1,:)
	for M = cellsCant
    M
		color = colors(k);
		q= polyfit(particlesCant,result_CIM(:,M-M_min+1)',1);
		#particlesCant, result_CIM(:,M-M_min+1), "*", 

		plot(particlesCant, polyval(q, particlesCant),"linestyle","-","Color",[rand,rand,rand]);
		hold on
		k=k+1;
	endfor

	title("test optimo M");
	xlabel("cantidad de particulas");
	ylabel("tiempo de ejecucion (s)");
	legend("M=10","M=11","M=12","M=13","location","northwest");
	hold off
	p = gcf();
	print(p,fileName);










