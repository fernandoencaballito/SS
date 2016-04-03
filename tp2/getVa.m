#funcion que calcula Va
function ans=getVa(particles,N, speed_modulo)
    angles=particles(6);
    speed_vectors=getVectorXY(angles,speed_modulo);
    speed_vectors_sum=sum(speed_vectors);
    
    ans=norm(speed_vectors_sum,2)/(N*speed_modulo);

endfunction