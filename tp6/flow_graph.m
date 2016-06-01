graphics_toolkit('gnuplot')
data= csvread ('FLOW_N=4000_deltaSim=1.00000e-05_L=10.0000_D=0.500000.csv');

time=data(:,1);
flow=data(:,2);

#pol = polyfit(time,flow,5); 
#pval = polyval(pol,time);
pol = splinefit(time,flow,10);
pval = ppval(pol,time);
plot(time,flow,"x",time,pval,"-");
title('Caudal por tiempo');
xlabel('Tiempo[segundos]');
ylabel('Caudal');
print -dpng flow2.png
