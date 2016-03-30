function particles = generateRandomSet(N,L, defaultVelocity, radius)

	printf("generating random particle set\n N = %d\nL = %d\n", N, L);
	  
  #generates random uniformed x,y particle position between 0 and L
	positions = unifrnd(0, L, N, 2);
  
	#generates random exponential radius
	direction = unifrnd(0, 2*pi, N, 1);

  # X Y RAD COL MODVEL ANG
  particles=positions;
  
  %Radio
  particles(:,3) = radius;
  
  %Color
  particles(:,4) = 1;
  
  %Modulo Velocidad
  particles(:,5) = defaultVelocity;
  
  %Direccion
  particles=[particles, direction];


endfunction
