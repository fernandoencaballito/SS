function saveState(particles, t, dynamicFile)

	dlmwrite(dynamicFile, t);
	dlmwrite(dynamicFile, particles(:,1:4), "-append");

endfunction
