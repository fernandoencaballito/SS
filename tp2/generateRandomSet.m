function generateRandomSet(outputStaticFile,outputDynamicFile,N,L,t)
	printf("generating random particle set\n staticFileName = %s dynamicFileName = %s\n N = %d L = %d t = %d\n",outputStaticFile,outputDynamicFile,N,L,t);
	#generates random uniformed x,y particle position between 0 and L
	positions = unifrnd(0,L,N,2);

	#generates random exponential radius
	radius = exprnd(1,N,1);

	#generates random normal x y velocity 

	velocity = normrnd(0,1,N,2);
	particles = [positions,velocity];
	dlmwrite(outputDynamicFile,t);
	dlmwrite(outputDynamicFile,particles,"-append");
	
	properties(:,1) = radius;
	properties(:,2) = 1;
	

	dlmwrite(outputStaticFile,[N;L]);
	dlmwrite(outputStaticFile,properties,"-append");
endfunction
