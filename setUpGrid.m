function grid = setUpGrid(grid,L,N,M,particles)
	anchoCelda = L/M
	for k=1:length(particles)
		particle = particles(k,1:2);
		i = floor(particle(1)/anchoCelda)+1;	
		j = floor(particle(2)/anchoCelda)+1;
		cellParticles = grid{i,j};
		cellParticles(end+1)=k;
		grid(i,j) = cellParticles;
	endfor
endfunction
