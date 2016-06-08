function res = getRes(set1,peatones) 
  j=1;
  for peaton = peatones
    peaton;
    for i = 1:length(set1(:,1))
      cantPeatones = set1(i,1);
         if peaton == cantPeatones
                res(j,:) = [peaton,set1(i,2)];
                j++;
                break;
          else
               if cantPeatones > peaton
                 res(j,:) = [peaton,set1(i-1,2)];
                 j++;
                 break;
               endif
          endif 
    endfor  
  endfor
 endfunction