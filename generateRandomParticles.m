function particles = generateRandomParticles(N,L, radius)

positions=rand(N, 2) *L;
attributes= ones(N,2) *[radius,color];
