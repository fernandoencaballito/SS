function getFrames(staticFileName,frameFile,t_init,t_end)

	printf("getting animation frames\n");
	pkg load image

	graphics_toolkit gnuplot
	figure("visible","off");
	colormap cool

	
	
	[stats, L, N] = getStats(staticFileName);
	t = t_init;
	while t <= t_end 
		dynamicFileName = ["./Data/dynamicFile" "_" num2str(N) "_" num2str(L) "_" num2str(t) ".txt"];
		[particles,t] = getState(dynamicFileName);
		particles = [particles, stats];
		h=scatter(particles(:,1),particles(:,2),10*particles(:,5),particles(:,6),"filled");
		frameFileName = [frameFile num2str(t - t_init) ".png"];
		saveas(h,frameFileName);
		t= t+1;
		
	endwhile

endfunction
