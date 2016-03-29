function particles = nextState(particles,N,L,periodic)
	position = particles(:,1:2);
	velocity = particles(:,3:4);
	radius = particles(:,5);
	
	for i = 1:N
		if(!periodic)
			[next_x_pos,next_x_vel] = getNextPos(position(i,1),velocity(i,1), radius(i),L);
			[next_y_pos,next_y_vel] = getNextPos(position(i,2),velocity(i,2), radius(i),L);
		else
			[next_x_pos,next_x_vel] = getNextPeriodicPos(position(i,1),velocity(i,1),radius(i),L);
			[next_y_pos,next_y_vel] = getNextPeriodicPos(position(i,2),velocity(i,2),radius(i),L);
		endif
		nextPos(i,:) = [next_x_pos,next_y_pos];
		nextVel(i,:) = [next_x_vel,next_y_vel];
	endfor		

	particles = [nextPos, nextVel, radius, particles(:,6)];

endfunction

function [next_pos, next_vel] = getNextPos(pos, vel, radius, L)
	next_pos = pos + vel;
	if next_pos + radius > L
		c = next_pos + radius - L;
		next_pos = next_pos - 2*c;
		next_vel = - vel;
	else	if next_pos - radius < 0
			c = -(next_pos - radius);
			next_pos = next_pos + 2*c;
			next_vel = -vel;
		else
			next_vel = vel;
		endif
	endif

endfunction

function [next_pos, next_vel] = getNextPeriodicPos(pos, vel, radius, L)
	next_pos = pos + vel;
	next_vel = vel;
	if next_pos  > L
		if(vel > 0)		
			next_pos = (next_pos - L );
		endif
	else	if next_pos  < 0
			if(vel < 0)
				next_pos = (L + next_pos );
			endif
		endif
	endif

endfunction



