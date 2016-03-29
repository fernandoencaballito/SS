function ans=compareCell(cell_1,cell_2,particles)
  ans=true;
  for i=1:length(cell_1)
        if (!compareVector(cell_1{1,i},cell_2{1,i}))
            disp("fallo la comparacion")
            i
            cell_1
            cell_2
           disp("posiciones de particulas que son vecinos de la anterior")
           for i=cell_2{1,i}
              particles(i)
           endfor
                        
            
            
            ans=false;
            break;
        endif;
  endfor 
           


endfunction