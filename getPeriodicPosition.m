##función que devuelve la posición final de la celda en la grilla teniendo en cuenda las condiciones periódicas de contorno
##row: número de fila
## col: número de columna
## M: limite
function [final_row, final_col] = getPeriodicPosition(row, col,periodic,M)
	 
   if(!periodic && (row>M || col>M || row<1 || col<1))
    #Se invalida la posicion
      final_row=(-1);
      final_col=(-1);
   else
          if (!periodic)
            #fila
            if(row<1)
              final_row=1;
            else
              if(row>M)
                final_row=M;
              else
                final_row=row;
              endif
            
              
            endif

            #columna
            if(col<1)
              final_col=1;
            else
              if(col>M)
                final_col=M;
              else
                final_col=col;

              endif
            endif	
          else #caso con condiciones periódicas
            #fila
            if(row<1)
              final_row=row+M;
            else
              if(row>M)
                final_row=mod(row,M);
              else 
                final_row=row;
              endif
            endif
            
            #columna
            if(col<1)
              final_col=col+M;
            else
              if(col>M)
                final_col=mod(col,M);
              else
                final_col=col;
              endif
            endif
          endif
     endif 


endfunction

#posición sin cambio
%!test
%! row=3;
%! col=3;
%!
%! [final_row,final_col]=getPeriodicPosition(row, col,true,5);
%! assert(final_row==row);
%! assert(final_col==col);
%!
%! [final_row,final_col]=getPeriodicPosition(row, col,false,5);
%! assert(final_row==row);
%! assert(final_col==col);
%!

#posición que se excede en uno en filas, condiciones periódicas

%!test
%! M=5;
%! row=6;
%! col=3;
%! [final_row,final_col]=getPeriodicPosition(row, col,true,M);
%! assert(final_row==1);
%! assert(final_col==col);
 

#posición que se excede en una fila, condiciones no periódicas

%!test
%! M=5;
%! row=6;
%! col=3;
%! [final_row,final_col]=getPeriodicPosition(row, col,false,M);
%! assert(final_row==M);
%! assert(final_col==col);
 
#posición que se excede en uno en columnas, condiciones periódicas

%!test
%! M=5;
%! row=3;
%! col=6;
%! [final_row,final_col]=getPeriodicPosition(row, col,true,M);
%! assert(final_row==row);
%! assert(final_col==1);
 

#posición que se excede en una columna, condiciones no periódicas

%!test
%! M=5;
%! row=3;
%! col=6;
%! [final_row,final_col]=getPeriodicPosition(row, col,false,M);
%! assert(final_row==row);
%! assert(final_col==M);


#posición que se excede en -1 en filas, condiciones periódicas

%!test
%! M=5;
%! row=0;
%! col=3;
%! [final_row,final_col]=getPeriodicPosition(row, col,true,M);
%! assert(final_row==M);
%! assert(final_col==col);
 

#posición que se excede en -1 en filas, condiciones no periódicas

%!test
%! M=5;
%! row=0;
%! col=3;
%! [final_row,final_col]=getPeriodicPosition(row, col,false,M);
%! assert(final_row==1);
%! assert(final_col==col);


#posición que se excede en -1 en columnas, condiciones periódicas

%!test
%! M=5;
%! row=3;
%! col=0;
%! [final_row,final_col]=getPeriodicPosition(row, col,true,M);
%! assert(final_row==row);
%! assert(final_col==M);
 

#posición que se excede en -1 en  columna, condiciones no periódicas

%!test
%! M=5;
%! row=3;
%! col=0;
%! [final_row,final_col]=getPeriodicPosition(row, col,false,M);
%! assert(final_row==row);
%! assert(final_col==1);

#posición que se excede en -1 en columnas y en filas, condiciones periódicas

%!test
%! M=5;
%! row=0;
%! col=0;
%! [final_row,final_col]=getPeriodicPosition(row, col,true,M);
%! assert(final_row==M);
%! assert(final_col==M);
 

#posición que se excede en -1 en  columna y el fila, condiciones no periódicas

%!test
%! M=5;
%! row=0;
%! col=0;
%! [final_row,final_col]=getPeriodicPosition(row, col,false,M);
%! assert(final_row==1);
%! assert(final_col==1);


#posición que se excede en uno en filas y columnas, condiciones periódicas

%!test
%! M=5;
%! row=6;
%! col=6;
%! [final_row,final_col]=getPeriodicPosition(row, col,true,M);
%! assert(final_row==1);
%! assert(final_col==1);
 

#posición que se excede en una fila y una columna, condiciones no periódicas

%!test
%! M=5;
%! row=6;
%! col=6;
%! [final_row,final_col]=getPeriodicPosition(row, col,false,M);
%! assert(final_row==M);
%! assert(final_col==M);
 

#unica celda
%!test
%! M=1;
%! row=0;
%! col=0;
%! [final_row,final_col]=getPeriodicPosition(row, col,false,M);
%! assert(final_row==1);
%! assert(final_col==1);

#unica celda2
%!test
%! M=1;
%! row=1;
%! col=0;
%! [final_row,final_col]=getPeriodicPosition(row, col,true,M);
%! assert(final_row==1);
%! assert(final_col==1);