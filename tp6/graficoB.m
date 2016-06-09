graphics_toolkit('gnuplot')

##GRAFICO PUNTO B DEL ENUNCIADO 


[time,meanExits1,errorExits1]=getExitData('N=100_Repeticiones=10/FLOW_N=100_DrivingVelocity=1.00000.csv');
#[time,meanExits2,errorExits2]=getExitData('FLOW_N=100_DrivingVelocity=2.00000.csv');
#[time,meanExits3,errorExits3]=getExitData('FLOW_N=100_DrivingVelocity=3.00000.csv');
errorbar(time(1:50),meanExits1(1:50),errorExits1(1:50),'~');
legend('v=1.0');
xlabel('t [seg]','fontsize', fontsize);
ylabel('Personas evacuadas','fontsize', fontsize);
title('Evacuacion acumuluada (N=100)', 'fontsize', 20);
print -dpng "N=100_Repeticiones=10/graficoB.png";
