function ans=getError( expected_vector, vector,total_steps)
  ans=sum(((expected_vector - vector ).^2 )) / total_steps;
  

endfunction