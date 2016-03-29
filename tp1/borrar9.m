N=18

palette = jet (N);
h = plot(1:18,18:35)


for i =1:N
set(h(i),"color",palette(i,:))
end
