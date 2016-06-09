 data= csvread ('N=100_Repeticiones=10/FLOW_N=100_DrivingVelocity=1.00000.csv');
time=data(:,1);
exits=data(:,2);

row = length(exits(:,1));
col = length(exits(1,:));

interval = 10

c = zeros(row - interval, col);
i=1;
while (i + interval <= row)
  for j = 1 : col
    c(i,j) = (exits(i + interval,j) - exits(i,j)) / interval;
   endfor
   i++;
endwhile

#caudalMean = mean(c')';
#errorCaudal = std(c')'./2;

fontsize=15;

maxTime=max(time);
plot(1:length(c(:,1)), c);
axis([-1 50 -0.1 3])
xlabel('Tiempo [seg]','fontsize',fontsize);
ylabel('Caudal promedio [personas/ tiempo]','fontsize',fontsize);
title('N=100; Velocidad deseada=1.0; Repeticiones=1','fontsize',20);
print -dpng 'N=100_Repeticiones=10/graficoC_unica.png'