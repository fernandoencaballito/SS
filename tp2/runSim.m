
N = 100;
L = 50;
defaultVelocity = 0.03;
radius = 1;
periodic = true;

simOutputFile = sprintf("./Data/dynamicFile-N%d_L%d.txt", N, L);

#duration = input("Pasos a simular: ");

simulate(simOutputFile, N, L, defaultVelocity, 1, periodic, radius);



