N = 300;
L = 25;
defaultVelocity = 0.03;
radius = 0;
periodic = true;
duration=1;
#n es la amplitud del ruido
n=0.1;
simOutputFile = sprintf("./Data/dynamicFile-N%d_L%d_n%f.txt", N, L,n);
delta_t=0.1;
rc=1;
#duration = input("Pasos a simular: ");
M=20;


simulate(simOutputFile, N, L, defaultVelocity, duration, periodic, radius,delta_t,M,rc,n);


