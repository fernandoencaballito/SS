package tp5;

import cellIndexMethod.CellIndexMethod;

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
    private double diameter;
    private double time = 0.0;
    private List<Particle> particles;
    private double k_n;
    private double k_t;
    private double drop_depht;

    private CellIndexMethod cim;


    public Simulation(Integrator integrator, double interval
            , ParticleWriter writer, double width
            , double height, double d, double dStart, int n
            , double k_n, double k_t
            , double drop_depth) {
        this.width = width;
        this.height = height;
        this.d = d;
        this.dStart = dStart;

        this.integrator = integrator;
        this.interval = interval;
        this.writer = writer;

        this.diameter = d / 10.0;

        this.particles = Particle.generateRandomParticles(n, this.diameter, width, height, 10000L);

        this.k_n = k_n;
        this.k_t = k_t;

        this.drop_depht = drop_depth;

        this.cim = new CellIndexMethod(width, height, n, this.diameter);
    }

    public void simulate() {

        cim.clearGrid();

        cim.addParticles(particles);

        particles = Collider.collisions(particles, width, height, dStart, d, k_n, k_t, drop_depht, cim);

        //VERSION ORIGINAL
        for (Particle particle : particles) {
            integrator.next(particle, interval);
        }

        //VERSION CON THREADS(para N=100 tarda mas)
        // particles.parallelStream().forEach(e->integrator.next(e, interval));


        time = time + interval;
    }

    public void writeData() {
        try {
            writer.write(time, particles);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
