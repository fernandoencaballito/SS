function appendXYToOutput(outputFile, particles, time)

  particleCount = size(particles, 1);
  
  fprintf(outputFile, "%d\nTime=%d\n", particleCount, time);

  temp = zeros(particleCount, 6);
  
  vel = particles(:,5);
  angle = particles(:,6);
  
  % X Y VEL_X VEL_Y RAD COLOR
  temp = [particles(:,1:2) , vel.*cos(angle), vel.*sin(angle), particles(:,3:4)];
  
  dlmwrite(outputFile, temp, "  ");
  
%  fprintf(outputFile, "\n");

endfunction