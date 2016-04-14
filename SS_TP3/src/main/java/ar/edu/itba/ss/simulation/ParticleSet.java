package ar.edu.itba.ss.simulation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ParticleSet {
	private Set<Particle> particles;

	public ParticleSet(int n) {
		this.particles = new HashSet<Particle>(n);
	}

	public List<Collision> getCollisions(SimulationSpace space) {

		List<Collision> ret = new ArrayList<Collision>(particles.size() * particles.size());

		
		Particle [] particlesArray = (Particle[]) particles.toArray();
		int	length = particlesArray.length;
		
		double time;
		
		for (int i = 0; i < length; i++) {
			Particle p1 = particlesArray[i];
			for (int j = i + 1; j < length; j++) {
				Particle p2 = particlesArray[j];
				time = Collision.getCollisionTime(p1,p2);
				
				if ( time >= 0)
					ret.add(new Collision(p1, p2, time));
			}
			
			for (Wall wall : space.getWalls()) {
				time = Collision.getCollisionTime(p1, wall);
				if(time > 0)
					ret.add(new Collision(p1,wall,time));
			}
			
			
		} 
		
				
	
		return ret;

	}
	
	public List<Collision> getCollisions(List<Particle> crash, SimulationSpace space) {

		List<Collision> ret = new ArrayList<Collision>(particles.size() * crash.size());
		double time;
		for (Particle crashed : crash) {
			for (Particle particle : particles) {
				if(!crash.contains(particle))
				{
					time = Collision.getCollisionTime(crashed,particle);
					if(time >= 0)
						ret.add(new Collision(crashed,particle,time));
			
				}
			}
			for (Wall wall : space.getWalls()) {
				time = Collision.getCollisionTime(crashed, wall);
				if(time > 0)
					ret.add(new Collision(crashed,wall,time));
			}
			
		}
		return ret;
	}

	
	public void advance(double time) {
		for (Particle particle : particles) {
			particle.advance(time);
		}	
	}
	
	public void advance(Collision next_collision) {
		advance(next_collision.getTime());
	}
}
