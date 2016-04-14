package ar.edu.itba.ss.simulation;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class EventDrivenSimulation {

    private final int particleCount;
    private final double width;
    private final double height;

	private Queue<Collision> queue;
	private ParticleSet particles;
    private SimulationSpace space;
	double time;

    private StateWriter writer = null;

	public EventDrivenSimulation(double width, double height, final int particleCount, Wall[] bars) {
        this.particleCount = particleCount;
        this.width = width;
        this.height = height;

        queue = new PriorityQueue<Collision>();
        space = new SimulationSpace(width,height,bars);

		queue.addAll(particles.getCollisions(space));
	}


    public void setWriter(StateWriter writer) {
        this.writer = writer;
    }
	public ParticleSet simulate(double time) {

        System.out.printf("Simulando hasta %f segundos...\n", time);

        double t = 0;
        do {
            t = simulate();
        } while (t < time);

		return particles;
	}

	public ParticleSet simulate(int collisions) {

        System.out.printf("Simulando %d colisiones...\n", collisions);

        while (collisions > 0) {
            simulate();
            collisions--;
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
        System.out.println("Simulando para t= " + time);

		List<Particle> crash = next_collision.getParticles();

		List<Collision> collisions = particles.getCollisions(crash,space);
		for (Collision collision : collisions) {
			collision.setAbsolutTime(time);
		}

		queue.addAll(collisions);

        if (writer != null) {
            try {
                writer.writeParticles(particles, time);
            } catch (IOException e) {
                System.out.println("No se pudo escribir en el archivo!");
            }
        }

        return time;

	}

    public int getParticleCount() {
        return particleCount;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getBarHeight() {
        return barHeight;
    }

    public String nameFromParams() {
        return String.format("%s_W%f_H%f_B%f_N%d.txt", this.getClass().getSimpleName(), width, height, barHeight, particleCount);
    }

}
