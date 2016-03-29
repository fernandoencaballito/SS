##funcion que simula el nuevo estado de la particula

##parametros:
##particles: matriz que contiene por cada fila los datos de las particulas.
##id: identificador de la particula a la que se le calcula la 
##delta_t: variacion de tiempo entre animaciones
##n: amplitud del ruido

#posicion de x en particles
global x_pos=1;
#posicion de y en particles
global y_pos=2;
#posicion del modulo de la velocidad en particles
global v_pos=3;
#posicion del angulo en particles
global angle_pos=4;


function nextParticleState=simulateParicle(particles,id,delta_t,n)

#valores actuales
position=particles(id,x_pos:y_pos);
speed_modulo=particles(id,v_pos);
angle=particles(id,angle_pos);
speed=[speed_modulo*cos(angle),speed_modulo*sin(angle)];



#siguientes valores

nextPos=position+speed*delta_t;
nextAngle= averageAngle + uniformNoise(n);




endfuction