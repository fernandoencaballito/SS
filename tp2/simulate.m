function simulate(simOutputFile, N, L, defaultVelocity, duration, periodic, radius,delta_t)
		 
  particles = generateRandomSet(N, L, defaultVelocity, radius);
  
  outputFile = fopen(simOutputFile, 'w');
  
  appendXYToOutput(outputFile, particles, 0);
  
  
  ## lo de luis

	for t = (delta_t:delta_t:duration)
    neighbours=
		
		[nextPos,nextAngle]=simulateParicle(particles,id,delta_t,n, neighbours,L)
		dynamicFileName = ["./Data/dynamicFile_" num2str(N) "_" num2str(L) "_" num2str(t) ".txt"];
		saveState(particles,t,dynamicFileName)
	endwhile


  
  
  ## ciclo por tiempo
      ##
  

%  
%  
%	[stats, L, N] = getStats(staticFileName);
%	[particles,t] = getState(init_dynamicFile);
%	t_end = t + duration;
%
%	while t < t_end
%		particles = [particles, stats];
%		particles = nextState(particles,N,L,periodic);
%		t = t+1;
%		dynamicFileName = ["./Data/dynamicFile_" num2str(N) "_" num2str(L) "_" num2str(t) ".txt"];
%		saveState(particles,t,dynamicFileName)
%	endwhile


## lo de luis


%[stats, L, N] = getStats(staticFileName);
%	[particles,t] = getState(init_dynamicFile);
%	t_end = t + duration;
%
%	while t < t_end
%		particles = [particles, stats];
%		particles = nextState(particles,N,L,periodic,rc, vel_max);
%		t = t+1;
%		dynamicFileName = ["./Data/dynamicFile_" num2str(N) "_" num2str(L) "_" num2str(t) ".txt"];
%		saveState(particles,t,dynamicFileName)
%	endwhile
%


  fclose(outputFile)

endfunction
