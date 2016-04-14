package ar.edu.itba.ss.simulation;

import java.io.IOException;
import java.util.*;

public class EventDrivenSimulation {

    private final int particleCount;
    private final double width;
    private final double height;
    private final double barHeight;
    private Queue<Collision> queue = new PriorityQueue<Collision>();
    private ParticleSet particles;
    private double time;
    private StateWriter writer = null;

    public EventDrivenSimulation(double width, double height, final int particleCount, double barHeight) {
        this.particleCount = particleCount;
        this.width = width;
        this.height = height;
        this.barHeight = barHeight;

        particles = ParticleSet.generateRandomParticleSet(width, height, particleCount);

        //queue.addAll(particles.getCollisions());
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
        }


        return particles;
    }

    private double simulate() {

        Collision next_collision;

        do {
            next_collision = queue.poll();
            if (next_collision == null)
                System.exit(1);
        } while (!next_collision.isValid());

        particles.advance(next_collision);

        Particle p1 = next_collision.getP1();
        Particle p2 = next_collision.getP2();

        double time = next_collision.getTime();
        System.out.println("Simulando para t= " + time);

        if (next_collision.getType() == CollisionType.PARTICLE) {
            p1.collide(p2);
            p2.collide(p1);
        } else
            p1.collide(next_collision.getType());

        Set<Particle> crash = new HashSet<Particle>();

        crash.add(next_collision.getP1());
        crash.add(next_collision.getP2());

        List<Collision> collisions = particles.getCollisions(crash);
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
