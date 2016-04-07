#! /bin/octave -qf

defaultVelocity = 0.03;
radius = 0;
periodic = true;
duration=1000;

simOutputFile = sprintf("./Data/va_sim.txt");
delta_t=1;
rc=0.5;
#duration = input("Pasos a simular: ");


#datos de cada una de las curvas.
#se usa los mismos datos y simbolos que en la figura 2.a del paper
#cada fila: N,L, simbolo en grafico,M
#simulation_data_values={40, 3.1, 's',3; 100, 5, '+',4; 400, 10, 'x',8; 4000, 31.6, '^',30; 10000, 50, 'd',40}

simulation_data_values={40, 3.1, 's',6; 100, 5, '+',8; 400, 10, 'x',18}


etha_values=0:0.25:5;


times=20;
hold on;

for i=1:rows(simulation_data_values)
       N=simulation_data_values{i,1};
       L=simulation_data_values{i,2};
       M=simulation_data_values{i,4};
       particles1 = generateRandomSet(N, L, defaultVelocity, radius);
       marker=simulation_data_values{i,3};
       outputFileName= sprintf("./Graphics/va_N=%d.txt",N);
       outputFile = fopen(outputFileName, 'w');
       for etha=etha_values
              etha
              va_values=zeros(1,times);
              for k=1:times
                particles2=simulate(simOutputFile, N, L, defaultVelocity, duration, periodic, radius,delta_t,M,rc,etha,particles1);
                va=getVa(particles2,N, defaultVelocity);
                va_values(k)=va;
              endfor 
              va_mean=mean(va_values); 
              dlmwrite(outputFile, [etha,va_mean], "  "); 
              plot(etha,va_mean,"marker",marker);
        
       endfor
       fclose(outputFile);
endfor;
xlabel('etha');
ylabel('Va');
legend('N=40','N=100','N=400');
hold off;

print -djpg ./Graphics/va_curves.jpg

