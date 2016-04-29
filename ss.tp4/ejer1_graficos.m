%%TP4 - EJERCICIO 1- graficado de los datos


data= csvread ('ejercicio1.csv');
time=data(:,1);
total_steps=rows(time);
pos_analitic=data(:,2);
pos_verlet=data(:,3);
pos_beeman=data(:,4);
pos_gear=data(:,5);
h1 = plot(time,pos_analitic,time,pos_verlet,time,pos_beeman,time,pos_gear);

title('Resorte');
legend('Analitica','Verlet','Beeman','Gear');
xlabel('Tiempo');
ylabel('Posicion');

#print("Ejercicio1_posiciones.png");
axis([0 1 -1 1]);
print -dpng Ejercicio1_posiciones.png


#{
error_verlet= getError(pos_analitic,pos_verlet,total_steps);
error_beeman=getError(pos_analitic,pos_beeman,total_steps);
error_gear=getError(pos_analitic,pos_gear,total_steps);
y=[error_verlet,error_beeman,error_gear];
h2 =bar([y;y]);
legend('Verlet','Beeman','gear');
axis([0.5 1.5])
title('Error total');
#print -dpng Ejercicio1_errores.png
print("Ejercicio1_errores.png");

}#