function [grid, L, N] = createGrid(staticFile, rc,M)
	[L,N]=textread(staticFile,"%f",2);
	densidad = N/(L^2);
	#L/M > rc	
	#calcular M
	
	grid = cell(M);

endfunction
