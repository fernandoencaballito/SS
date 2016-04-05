a=load("./Graphics/va_N=40.txt")
hold on;

 plot(a(:,1),a(:,2),"marker",'s');
 
 b=load("./Graphics/va_N=100.txt");
 
  plot(b(:,1),b(:,2),"marker",'+');
  
   
 c=load("./Graphics/va_N=400.txt");
 
  plot(c(:,1),c(:,2),"marker",'x');
  
  xlabel('etha');
ylabel('Va');
legend('N=40','N=100','N=400');
hold off;

print -djpg ./Graphics/borrar_curves.jpg