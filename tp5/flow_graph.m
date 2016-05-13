graphics_toolkit('gnuplot')
data= csvread ('FLOW_N=500_deltaSim=1.00000e-05_L=10.0000_D=0.500000.csv');

time=data(:,1);
flow=data(:,2);

plot(time,flow);
title('Caudal por tiempo');
xlabel('Tiempo[segundos]');
ylabel('Caudal');
print -dpng flow.png
