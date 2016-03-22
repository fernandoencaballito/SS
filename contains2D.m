function ans=contains2D(vec, x,y)
 [rows,cols]=size(vec);
  ans=false;
  for i=1:rows  
      current_row=vec(i,:);
      if(current_row(1)==x && current_row(2)==y)
        ans=true;
        break;
      endif
  endfor
  

%!test
%! matrix=[1,1;2,2];
%! assert(contains2D(matrix,1,1));
%! assert(!contains2D(matrix,2,3));