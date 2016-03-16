function [grid, L, N] = createGrid(staticFile, rc,M, particles)
	[L,N]=textread(staticFile,"%f",2);
	densidad = N/(L^2);
	radios = particles(:,3);
	rmax = max(radios);

	#L/M > rc + 2rmax	
	#calcular M
		if(L/M <= rc + 2*rmax)
			printf("M no cumple con la condición");
			exit(1);
		endif	
	grid = cell(M);

endfunction
