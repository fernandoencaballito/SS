function [particles,t] = getState(dynamicFile)
	t = textread(dynamicFile,"%d",1);
	particles = dlmread(dynamicFile,"",1,0);
endfunction
 
