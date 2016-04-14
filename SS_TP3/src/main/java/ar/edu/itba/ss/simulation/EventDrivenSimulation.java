package ar.edu.itba.ss.simulation;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class EventDrivenSimulation {

	private Queue<Collision> queue;
	private ParticleSet particles;
	private SimulationSpace space;
	double time;

	public EventDrivenSimulation(double width, double height, Wall[] bars) {
		queue = new PriorityQueue<Collision>();
		space = new SimulationSpace(width,height,bars);
		
		queue.addAll(particles.getCollisions(space));
		
	}

	;

	public static void main(String[] args) {

	}

	public ParticleSet simulate(double time) {

		double t = 0;
		do {
			t = simulate();
		} while (t < time);

		return particles;
	}

	public ParticleSet simulate(int collisions) {

		while (collisions > 0) {
			simulate();
			collisions --;
		}

		return particles;
	}

	private double simulate() {

		Collision next_collision;

		do {

			next_collision = queue.poll();
			if (next_collision == null)
				System.exit(123612);
		} while (!next_collision.isValid());

		particles.advance(next_collision);

		next_collision.collide();

		List<Particle> crash = next_collision.getParticles();
		
		List<Collision> collisions = particles.getCollisions(crash,space);
		for (Collision collision : collisions) {
			collision.setAbsolutTime(time);
		}

		queue.addAll(collisions);

		return time;

	}

}
