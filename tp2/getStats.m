function [staticStats,L,N] = getStats(staticFile)
	staticStats = dlmread(staticFile,"",2,0);
	[N,L] = textread(staticFile,"%f",2);
endfunction
