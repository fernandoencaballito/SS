graphics_toolkit('gnuplot')
data= csvread ('system_energy.csv');
time=data(:,1);
kinetic=data(:,2);
potential=data(:,3);
totalEnergy=data(:,4);

plot(time,kinetic,time,potential,time,totalEnergy);
legend('Ek','Eg','Etotal');
xlabel('Tiempo');
ylabel('Energia[J]');
print -dpng Ejercicio3_energias.png