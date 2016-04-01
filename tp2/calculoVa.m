#! /bin/octave -qf

N = 300;
L = 25;
defaultVelocity = 0.03;
radius = 0;
periodic = true;
duration=2000;
#n es la amplitud del ruido
n=0.1;
simOutputFile = sprintf("./Data/dynamicFile-N%d_L%d_n%f.txt", N, L,n);
delta_t=10;
rc=1;
#duration = input("Pasos a simular: ");
M=20;

symbol=['s','+','x','^','d']
index=1;
density=40/#densidad del primer caso, se mantiene constante
for N=[40,100]   ## deberia valer esto[40,100,400,400,10000]
  
    for etha=0:0.1:5
          L=N/
          particles=simulate(simOutputFile, N, L, defaultVelocity, duration, periodic, radius,delta_t,M,rc,etha);
    
    
    endfor
endfor;


