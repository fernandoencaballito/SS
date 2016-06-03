graphics_toolkit('gnuplot')

##GRAFICO PUNTO B DEL ENUNCIADO


[time,meanExits1,errorExits1]=getExitData('FLOW_N=100_DrivingVelocity=1.00000.csv');
[time,meanExits2,errorExits2]=getExitData('FLOW_N=100_DrivingVelocity=2.00000.csv');
[time,meanExits3,errorExits3]=getExitData('FLOW_N=100_DrivingVelocity=3.00000.csv');
errorbar(time,meanExits1,errorExits1,'~',time,meanExits2,errorExits2, '~');
legend('v=1.0','v=2.0');
xlabel('t [seg]');
ylabel('Personas evacuadas');


