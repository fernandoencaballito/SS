function particles = generateRandomSet(N,L, defaultVelocity, radius)

	printf("generating random particle set\n N = %d\nL = %d\n", N, L);
	  
  #generates random uniformed x,y particle position between 0 and L
	positions = unifrnd(0, L, N, 2);
  
	#generates random exponential radius
	direction = unifrnd(0, 2*pi, N, 1);

  particles=[positions direction];
  
  particles(:,4) = defaultVelocity;
  particles(:,5) = radius;

endfunction
