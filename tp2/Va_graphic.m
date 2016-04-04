#! /bin/octave -qf

N = 300;
L = 25;
defaultVelocity = 0.03;
radius = 0;
periodic = true;
duration=1000;
#n es la amplitud del ruido
n=0.1;
simOutputFile = sprintf("./Data/va.txt", N, L,n);
delta_t=10;
rc=1;
#duration = input("Pasos a simular: ");


#datos de cada una de las curvas.
#se usa los mismos datos y simbolos que en la figura 2.a del paper
#cada fila: N,L, simbolo en grafico,M
#simulation_data_values={40, 3.1, 's',3; 100, 5, '+',4; 400, 10, 'x',8; 4000, 31.6, '^',30; 10000, 50, 'd',40}
simulation_data_values={40, 3.1, 's',3};
etha_values=0:0.1:5;

times=5;
hold on;
for i=1:rows(simulation_data_values)
       N=simulation_data_values{i,1};
       L=simulation_data_values{i,2};
       M=simulation_data_values{i,4};
       particles1 = generateRandomSet(N, L, defaultVelocity, radius);
       marker=simulation_data_values{i,3};
       for etha=etha_values
              va_values=zeros(1,times);
              for k=1:times
                particles2=simulate(simOutputFile, N, L, defaultVelocity, duration, periodic, radius,delta_t,M,rc,etha,particles1);
                va=getVa(particles2,N, defaultVelocity);
                va_values(k)=va;
              endfor 
              va_mean=mean(va_values);  
              plot(etha,va_mean,"marker",marker);
        
       endfor
endfor;
hold off;

print -djpg ./Graphics/va_curves.jpg

