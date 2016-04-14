package ar.edu.itba.ss.simulation;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class EventDrivenSimulation {

    private Queue<Collision> queue;
    private ParticleSet particles;
    double time;

    public EventDrivenSimulation() {
        queue = new PriorityQueue<Collision>();

        queue.addAll(particles.getCollisions());
    }

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

        while (collisions > 0)
            simulate();

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

        return time;

    }

}
