 graphics_toolkit('gnuplot')
 fileName='FLOW_N=100_DrivingVelocity=1.00000.csv';
  fontsize=14;
 data= csvread (fileName);
 time=data(:,1);
 exits=data(:,2:end);
 
 plot(time,exits(:,1),time,exits(:,2),time,exits(:,3),time,exits(:,4),time,exits(:,5));
 
xlabel('t [seg]', 'fontsize', fontsize);
ylabel('Personas evacuadas', 'fontsize', fontsize);
title('v = 1m/s N = 100', 'fontsize', 20);
axis([0, 60, 0, 110]);