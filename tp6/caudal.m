 data= csvread ('FLOW_N=100_DrivingVelocity=1.00000.csv');
time=data(:,1);
exits=data(:,2:end);

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
size(time(interval/2:(end-1)-interval/2))
size(c(:,1))

plot(time(interval/2:(end-1)-interval/2),c(:,1),time(interval/2:(end-1)-interval/2),c(:,2))