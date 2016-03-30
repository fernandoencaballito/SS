##funcion que simula el nuevo estado de la particula

##parametros:
##particles: matriz que contiene por cada fila los datos de las particulas.
##id: identificador de la particula a la que se le calcula el nuevo estado.
##delta_t: variacion de tiempo entre animaciones
##n: amplitud del ruido
## neigbours: tabla de vecinos



#posicion de x en particles
global x_pos=1;
#posicion de y en particles
global y_pos=2;
#posicion del modulo de la velocidad en particles
global v_pos=5;
#posicion del angulo en particles
global angle_pos=6;

#posicion del radio
global radio_pos=3;


function [nextPos,nextAngle]=simulateParicle(particles,id,delta_t,n, neighbours,L)

    #valores actuales
    position=particles(id,x_pos:y_pos);
    speed_modulo=particles(id,v_pos);
    angle=particles(id,angle_pos);
    speed=[speed_modulo*cos(angle),speed_modulo*sin(angle)];
    radio=particles(:,radio_pos);


    #siguientes valores

    nextPos=getNextPeriodicPos(position,speed_modulo,L,delta_t)
    
    current_neighbours=neighbours{1,id};
    #vector que contiene id de particula junto con sus vecinos 
    current_particles_id=[id,current_neighbours];
    
    current_particles_data=particles(current_particles_id,angle_pos);
    
    current_particles_angles=current_particles_data(:,angle_pos);
    
    #calculo de los senos de la formula de tita
    numerator_values=sin(current_particles_angles);
    
    #calculo de los cosenos de la formula de tita
    denominator_values=cos(current_particles_angles);
    
    nextAngle=atan(mean(numerator_values)/mean(denominator_values))  + uniformNoise(n);
    nextAngle=mod(nextAngle,2*pi);
    

endfunction




function next_pos = getNextPeriodicPos(pos, vel, L,delta_t)
      next_pos = pos + vel*delta_t;
      
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

