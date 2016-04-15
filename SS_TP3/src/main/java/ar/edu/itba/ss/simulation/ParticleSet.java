package ar.edu.itba.ss.simulation;

import java.util.*;

public class ParticleSet implements Iterable<Particle> {
    private Set<Particle> particles;

    public ParticleSet(int n) {
        this.particles = new HashSet<Particle>(n);
    }

    private static final double MIN_VELOCITY_X = 0;
    private static final double MAX_VELOCITY_X = 0.1;

    private static final double MIN_VELOCITY_Y = -0.1;
    private static final double MAX_VELOCITY_Y = 0.1;

    private static final double PARTICLE_RADIUS = 0.005;
    private static final double PARTICLE_MASS = 1;

    public static ParticleSet generateRandomParticleSet(final double width, final double height, final int particleCount) {

        ParticleSet particleSet = new ParticleSet(particleCount);

        Random rand = new Random();


        for (int i = 0; i < particleCount; i++) {

            double x = rand.nextDouble() * width;
            double y = rand.nextDouble() * height;
            double vx = MIN_VELOCITY_X + (MAX_VELOCITY_X - MIN_VELOCITY_X) * rand.nextDouble();
            double vy = MIN_VELOCITY_Y + (MAX_VELOCITY_Y - MIN_VELOCITY_Y) * rand.nextDouble();

            particleSet.particles.add(new Particle(x, y, vx, vy, PARTICLE_RADIUS, PARTICLE_MASS));
        }


        return particleSet;
    }

    public List<Collision> getCollisions(SimulationSpace space) {

        List<Collision> ret = new ArrayList<Collision>(particles.size() * particles.size());


        Particle[] particlesArray = particles.toArray(new Particle[particles.size()]);
        int length = particlesArray.length;

        double time;

        for (int i = 0; i < length; i++) {
            Particle p1 = particlesArray[i];
            for (int j = i + 1; j < length; j++) {
                Particle p2 = particlesArray[j];
                time = Collision.getCollisionTime(p1, p2);

                if (time >= 0)
                    ret.add(new Collision(p1, p2, time));
            }

            for (Wall wall : space.getWalls()) {
                time = Collision.getCollisionTime(p1, wall);
                if (time > 0)
                    ret.add(new Collision(p1, wall, time));
            }


        }


        return ret;

    }

    public List<Collision> getCollisions(List<Particle> crash, SimulationSpace space) {

        List<Collision> ret = new ArrayList<Collision>(particles.size() * crash.size());
        double time;
        for (Particle crashed : crash) {
            for (Particle particle : particles) {
                if (!crash.contains(particle)) {
                    time = Collision.getCollisionTime(crashed, particle);
                    if (time >= 0)
                        ret.add(new Collision(crashed, particle, time));

                }
            }
            for (Wall wall : space.getWalls()) {
                time = Collision.getCollisionTime(crashed, wall);
                if (time > 0)
                    ret.add(new Collision(crashed, wall, time));
            }

        }
        return ret;
    }


    public void advance(double time) {
        for (Particle particle : particles) {
            particle.advance(time);
        }
    }

    public Iterator<Particle> iterator() {

        return particles.iterator();
        // TODO Auto-generated method stub
    }

    public long size() {
        return particles.size();
    }
}
