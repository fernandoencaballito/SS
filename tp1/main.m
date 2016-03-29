
#staticFile: archivo estático
#dynamicFile: archivo dinámico
#M: número de celdas
#rc: radio de interacción
#periodic: 1 si tiene condiciones periodicas 0 si no
#outputFile: archivo donde se guarda el resultado
#bruteForce: 0 si se quiere utilizar el cellIndexMethod, 0 fuerza bruta
#plotParticle: -1 si se quiere graficar los vecinos de todas las particulas que los tengan, el identificador de un particula para graficar los de esa particula, 0 nada

function main(staticFile, dynamicFile, M, rc, periodic, outputFile, bruteForce, plotParticle)

	particles = loadParticles(staticFile,dynamicFile);
	[matrix,L,N] = createGrid(staticFile,rc,M,particles);
	matrix = setUpGrid(matrix,L,N,M,particles);

  matrix{1,10}
	
	if !bruteForce
		neighbours = getNeighboors(N,matrix,particles,rc,M,periodic,L);
	else	
		neighbours = fuerzaBruta(particles, rc, N, periodic,L)
	endif

	writeNeighbours(outputFile,neighbours,N);

	if(plotParticle)
		particles(:,4)=1;
		particles(plotParticle,4)=3;
		for neighbour = neighbours{1,plotParticle}
			particles(neighbour,4)=2;
		endfor
		plotParticles(particles,L,M);
	endif
endfunction


