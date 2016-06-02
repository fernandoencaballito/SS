graphics_toolkit('gnuplot')
data= csvread ('FLOW_N=100_DrivingVelocity=1.30000.csv');

time=data(:,1);
flow1=data(:,2);
flow2=data(:,3);
errorbar(time,flow1,flow2);
