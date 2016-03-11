function [particles,cant] = loadParticles(staticFile, dynamicFile)
	particlesPos = dlmread(dynamicFile,"",1,0); 
	particlesAttr = dlmread(staticFile,"",2,0);
	
	particles = particlesPos;
	particles(:,3:4)= particlesAttr;
	cant = length(particles(1,:));

endfunction
