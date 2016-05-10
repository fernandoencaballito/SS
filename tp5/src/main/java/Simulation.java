import java.io.IOException;
import java.util.List;

public class Simulation {


    private final Integrator integrator;
    private final double interval;
    private final ParticleWriter writer;
    private double width;
    private double height;
    private double d;
    private double dStart;
    private double diameter = d / 10.0;
    private double time = 0.0;
    private long count = 0;
    private List<Particle> particles;

    public Simulation(Integrator integrator, double interval, ParticleWriter writer, double width, double height, double d, double dStart, int n) {
        this.width = width;
        this.height = height;
        this.d = d;
        this.dStart = dStart;

        this.integrator = integrator;
        this.interval = interval;
        this.writer = writer;

        this.particles = Particle.generateRandomParticles(n, d, width, height, 10000L);
    }

    public void simulate() {

        particles = Collider.collisions(particles, width, height, dStart, d);
        for (Particle particle : particles) {
            integrator.next(particle, interval);
        }
        if (count % 1 == 0) {
            try {
                writer.write(time, particles);


            } catch (IOException e) {
                e.printStackTrace();
            }
            //count = 0;
            //System.out.println(system.getParticles().size());

        }
        count++;
        time = time + interval;
    }

}
