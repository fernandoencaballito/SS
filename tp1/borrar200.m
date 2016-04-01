#unica celda2

 M=1;
 row=1;
 col=0;
 [final_row,final_col]=getPeriodicPosition(row, col,true,M);
 assert(final_row==1);
 assert(final_col==1);