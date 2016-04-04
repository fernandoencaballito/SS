#funcion que dado un angulo y el modulo de la velocidad, devuelve el vector proyectado sobre ejes x e y.
function ans=getVectorXY(vector_modulo, angle)
x=vector_modulo*cos(angle);
y=vector_modulo*sin(angle);
ans=[x,y];


endfunction