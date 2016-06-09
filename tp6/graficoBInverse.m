  # graphics_toolkit('gnuplot')
   data= csvread ('N=100_Repeticiones=10/FLOW_N=100_DrivingVelocity=1.00000.csv');
   time=data(:,1);
   exits=data(:,2:end);
  
   peatones = [0:100];
    
  for i = 1:5
    res = getRes([exits(:,i),time],   peatones );
    resFin(:,i) = res(:,2);
  endfor
  
  resProm = mean(resFin')';
  errorTimes = std(resFin')'./2;
 
  
  errorbar(peatones(1:5:end),resProm(1:5:end),errorTimes(1:5:end),'.');
  
  fontsize = 14;
  #legend('v=1.0','fontsize', fontsize);
  ylabel('t [seg]','fontsize', fontsize);
  xlabel('Personas evacuadas','fontsize', fontsize);
  title('v = 1m/s; N = 100; Repeticiones=10', 'fontsize', 20);
  hold on
  plot(peatones,resProm,'r');
  axis([-0.1 101]);
  hold off
  print -dpng "N=100_Repeticiones=10/graficoBinverso.png"
  
  
  
  
