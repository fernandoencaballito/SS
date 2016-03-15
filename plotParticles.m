function plotParticles(particles,L,M)
	anchoCelda = L/M
	p = scatter(particles(:,1),particles(:,2),2*particles(:,3),particles(:,4));

	grid('on')
	set(gca,'xtick',[0:anchoCelda:L])
	set(gca,'ytick',[0:anchoCelda:L])

	saveas(p,"./ArchivosEjemplo/plot.jpg");
endfunction
