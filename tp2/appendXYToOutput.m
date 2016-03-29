function appendXYToOutput(outputFile, particles, props, time)

  particleCount = size(particles, 1);
  
  fprintf(outputFile, "%d\nLattice=\"5.44 0.0 0.0 0.0 5.44 0.0 0.0 0.0 5.44\" Properties=pos:R:2%s Time=%d\n", particleCount, props, time)

  dlmwrite(outputFile, particles(:,1:2), "\t\t");
  
  fprintf(outputFile, "\n");

endfunction