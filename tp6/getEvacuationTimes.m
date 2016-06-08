function ans=getEvacuationTimes(times,exits)

cols=columns(exits);
rows=rows(exits);
ans=zeros(cols,1);
total_people=max(exits(:,1));

for i=1:cols
  
  for j=1:rows
  
    if(exits(j,i)==total_people)
         ans(i)=times(j,1);
         break;
    endif;
  
  endfor
  
  
endfor




endfunction;
