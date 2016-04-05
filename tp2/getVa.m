#funcion que calcula Va
function ans=getVa(particles,N, speed_modulo)
    angles=particles(:,6);
    speed_vectors=getVectorXY(speed_modulo,angles);
    speed_vectors_sum=sum(speed_vectors);
    
    ans=norm(speed_vectors_sum,2)/(N*speed_modulo);
    
    
endfunction