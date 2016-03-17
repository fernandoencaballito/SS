#se miden los tiempos segun lo que se pide en el ejercicio 2

N_min= 100;
N_max=1000;

M_min=1;
M_max=13; ##tiene que cumplir el criterio L/M>rc+ 2 *r_max

radius=0.25;
L=20;
for N=(N_min:N_max)
    for M=(M_min:M_max)
         particles=generateRandomParticles(N,L, radius);
         
         tic;
         
         time=toc;
    
    endfor
endfor