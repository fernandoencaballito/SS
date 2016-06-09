 graphics_toolkit('gnuplot')
 fileName='N=100_Repeticiones=10/FLOW_N=100_DrivingVelocity=1.00000.csv';
  fontsize=14;
 data= csvread (fileName);
 time=data(:,1);
 exits=data(:,2:end);
 
 plot(time(1:50),exits(1:50,1),time(1:50),exits(1:50,2),time(1:50),exits(1:50,3),time(1:50),exits(1:50,4),time(1:50),exits(1:50,5));
 
xlabel('t [seg]', 'fontsize', fontsize);
ylabel('Personas evacuadas', 'fontsize', fontsize);
title('Egresos para v = 1m/s N = 100', 'fontsize', 20);
legend('Egreso1','Egreso2', 'Egreso3','Egreso4','Egreso5');
axis([0, 60, 0, 110]);

print -dpng "N=100_Repeticiones=10/graficoA.png"