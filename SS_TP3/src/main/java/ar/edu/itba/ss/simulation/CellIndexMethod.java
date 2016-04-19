package ar.edu.itba.ss.simulation;

import java.util.List;

public class CellIndexMethod {

	private ParticleSet ps;
	private SimulationSpace space;
	private Grid grid;

	
	public CellIndexMethod(ParticleSet ps, SimulationSpace space,double rc) {
		this.ps = ps;
		this.space = space;
		this.grid = new Grid(space.getHeight(),space.getWidth(),rc);
		grid.fillGrid(ps);	
	}
		
	public List<Particle> getNeighbours(Particle particle) {
		this.grid.reinsert(ps);
		return grid.getNeighbours(particle);
	}
	
	
}
