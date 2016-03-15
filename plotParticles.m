function plotParticles(particles,L,M)
  particles
	anchoCelda = L/M
	colormap cool
	p = scatter(particles(:,1),particles(:,2),20*particles(:,3),particles(:,4),'filled');

	grid('on')
	set(gca,'xtick',[0:anchoCelda:L])
	set(gca,'ytick',[0:anchoCelda:L])

	saveas(p,"./ArchivosEjemplo/plot.jpg");
  
 endfunction