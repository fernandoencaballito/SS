function particles = nextState(particles,N,L,periodic,rc, vel_max)
	position = particles(:,1:2);
	velocity = particles(:,3:4);
	radius = particles(:,5);
	rmax = max(radius);
	M = floor(L/(rc + 2*rmax));

	matrix = createGrid(L,N, rc,M, radius);
	matrix = setUpGrid(matrix,L,N,M,position);
	particle_stats = [position , radius];
	neighbours = cellIndexMethod(matrix, particle_stats, periodic, L, rc, N, M);

	
	
	for i = 1:N
		next_x_vel = getNextVel(i,neighbours,position(:,1),velocity(:,1),radius(i),vel_max);
		next_y_vel = getNextVel(i,neighbours,position(:,2),velocity(:,2),radius(i),vel_max);


		if(!periodic)
			[next_x_pos,next_x_vel] = getNextPos(position(i,1),next_x_vel, radius(i),L);
			[next_y_pos,next_y_vel] = getNextPos(position(i,2),next_y_vel, radius(i),L);
		else
			[next_x_pos,next_x_vel] = getNextPeriodicPos(position(i,1),next_x_vel,radius(i),L);
			[next_y_pos,next_y_vel] = getNextPeriodicPos(position(i,2),next_y_vel,radius(i),L);
		endif
		nextPos(i,:) = [next_x_pos,next_y_pos];
		nextVel(i,:) = [next_x_vel,next_y_vel];
	endfor		

	particles = [nextPos, nextVel, radius, particles(:,6)];

endfunction


function next_vel = getNextVel(pid,neighbours, position, velocity, radius, vel_max)
	
	next_vel = velocity(pid);
	for neighbour = neighbours{pid}
		dist = position(pid) - position(neighbour);
		a = 0.1/dist;
		next_vel = next_vel + a;
	endfor

	if(abs(next_vel) > vel_max)
		next_vel = vel_max * sign(next_vel); 
	endif
endfunction



function [next_pos, next_vel] = getNextPos(pos, vel, radius, L)
	next_pos = pos + vel;
	if next_pos + radius > L
		next_pos = L - mod(next_pos,L);
		next_vel = - vel;
	else	if next_pos - radius < 0
			next_pos = mod(next_pos,L);
			next_vel = -vel;
		else
			next_vel = vel;
		endif
	endif

endfunction

function [next_pos, next_vel] = getNextPeriodicPos(pos, vel, radius, L)
	next_pos = pos + vel
	next_vel = vel;
	if next_pos  > L	
			next_pos = (mod(next_pos,L));
		
	else	if next_pos  < 0
				next_pos = (L - mod(abs(next_pos),L) );
			endif
	endif
	if( next_pos > L || next_pos < 0)
		error("#getNextPeriodicPos# la posicion resultatnte es invalida");
	endif
endfunction



