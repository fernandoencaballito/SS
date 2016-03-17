function particles = generateRandomParticles(N,L, radius)

positions=rand(N, 2) *L;
color=1;
attributes= ones(N,2) .*[radius,color];
particles(:,1:2)=positions;
particles(:,3:4)=attributes;