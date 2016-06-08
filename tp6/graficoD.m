
 #graphics_toolkit('gnuplot')
 MAX_VELOCITY=8;
 velocidadades=1:1:MAX_VELOCITY;
 fontsize=14;
 #graph contiene por columna: velocidad, tiempo promedio de evacuacion, error
 graph=zeros(columns(velocidadades),3);
 for k=1:length(velocidadades)
 
         velocidad=velocidadades(k)
         fileName= sprintf('FLOW_N=100_DrivingVelocity=%d.00000.csv',velocidad);
         data= csvread (fileName);
         exits=data(:,2:end);
         totalPeople=max(exits(:,2));
         #tiempos de evacuacion para cada repeticion de una velocidad fija
         evacuationTimes=getEvacuationTimes(data(:,1),exits);
         currentAvg=mean(evacuationTimes);
         error=std(evacuationTimes')'./2;
        graph(k,1)=velocidad;
        graph(k,2)=currentAvg;
        graph(k,3)=error; 
  endfor;
   errorbar(graph(:,1),graph(:,2),graph(:,3));
 
  xlabel('velocidad [m/s]', 'fontsize', fontsize);
  ylabel('Tiempo de evacuacion promedio [seg]', 'fontsize', fontsize);
  title('N=100. Repeticiones=10. Paso entre velocidades:1','fontsize', 20);
  