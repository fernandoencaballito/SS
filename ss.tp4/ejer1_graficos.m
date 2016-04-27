%%TP4 - EJERCICIO 1- graficado de los datos

data= csvread ('ejercicio1.csv');
time=data(:,1);
total_steps=rows(time);
pos_analitic=data(:,2);
pos_verlet=data(:,3);

plot(time,pos_analitic,time,pos_verlet);

title('Resorte');
legend('Analitica','Verlet');
xlabel('Tiempo');
ylabel('Posicion');


error_verlet= sum(((pos_analitic -pos_verlet ).^2 )) / total_steps;
y=[error_verlet,error_verlet];
bar([y;y]);
legend('Verlet','Beeman');
axis([0.5 1.5])