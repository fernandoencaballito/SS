%%TP4 - EJERCICIO 1- graficado de los datos

data= csvread ('ejercicio1.csv')
time=data(:,1);
pos_analitic=data(:,2);

plot(time,pos_analitic);
title('Resorte');
legend('Analitica');
xlabel('Tiempo');
ylabel('Posicion');
