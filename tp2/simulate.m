function  simulate(staticFileName,init_dynamicFile,duration,periodic)
		
	[stats, L, N] = getStats(staticFileName);
	[particles,t] = getState(init_dynamicFile);
	t_end = t + duration;

	while t < t_end
		particles = [particles, stats];
		particles = nextState(particles,N,L,periodic);
		t = t+1;
		dynamicFileName = ["./Data/dynamicFile_" num2str(N) "_" num2str(L) "_" num2str(t) ".txt"];
		saveState(particles,t,dynamicFileName)
	endwhile

endfunction
