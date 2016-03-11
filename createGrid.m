function [grid, L, N, M] = createGrid(staticFile, rc)
	[L,N]=textread(staticFile,"%f",2);
	densidad = N/(L^2);
	#L/M > rc	
	#calcular M
	M = 10
	grid = cell(M);

endfunction
