function neighboors=eliminateDuplicated(previous_neighboors)
 [rows,cols]=size(previous_neighboors);

neighboors=previous_neighboors;
 for c=1:cols
    currentCell=neighboors{1,c};
    total_elements=length(currentCell);
    duplicated=[];
    for i=1:(total_elements)
        firstElem=currentCell(i); 
        
        for j=(i+1):total_elements
            secondElem=currentCell(j);
            if(firstElem==secondElem)
               duplicated=[duplicated,j];
            endif
                
        endfor
    
    endfor
    
    ##se borran duplicados
    
    currentCell(duplicated)=[];
    neighboors{1,c}=currentCell;
    
    
 endfor
 

endfunction

%!test
%! neighboors=cell(1,5);
%! neighboors{1,1}=[1,2,2]
%! final=eliminateDuplicated(neighboors);
%! assert( length(final{1,1})==2)