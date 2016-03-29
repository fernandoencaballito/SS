function [particles,cant] = loadParticles(staticFile, dynamicFile)
	particlesPos = dlmread(dynamicFile,"",1,0); #archivo, delimitador,fila a empezar a leer,columna en la que se empieza a leer
	particlesAttr = dlmread(staticFile,"",2,0);
	
	particles = particlesPos;
	particles(:,3:4)= particlesAttr;#revisar en caso de que incluyan velocidades en el archivo dinamico( las columnas 3 y 4 también estarían ocupadas).
	cant = length(particles(1,:));

endfunction
