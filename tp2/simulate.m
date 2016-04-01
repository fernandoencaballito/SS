
#radius: radio de las particulas.Para el tp2 deberia ser 0.
#rc: radio de interaccion de las particulas.



function simulate(simOutputFile, N, L, defaultVelocity, duration, periodic, radius,delta_t,M,rc,n)
  addpath('../tp1/') 
  angle_pos=6;
  particles = generateRandomSet(N, L, defaultVelocity, radius);
  disp('#simulate# generado el conjunto inicial random');
  outputFile = fopen(simOutputFile, 'w');
  
  appendXYToOutput(outputFile, particles, 0);#se graba el estado inicial de las particulas en archivo a graficar
  
  
  ## lo de luis

  
	for t = (delta_t:delta_t:duration)
        t
        newParticles=particles;
        neighbours=mainNeighbours(L,N,M,particles,rc,periodic);
        
        for id=(1:columns(neighbours))
              [nextPos,nextAngle]=simulateParicle(particles,id,delta_t,n, neighbours,L);
              newParticles(id,1:2)=nextPos;
              newParticles(id,angle_pos)=nextAngle;
        
        endfor
        
        appendXYToOutput(outputFile, newParticles, t);
        particles=newParticles;
	endfor


  
  
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
