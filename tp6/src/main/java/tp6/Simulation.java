package tp6;

import cellIndexMethod.CellIndexMethod;

import java.io.IOException;
import java.util.List;


public class Simulation {

	private static final double TAU=0.5;
	private double drivingVelocity;
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

    private long previousParticlesCount;
    private CellIndexMethod cim;


    public Simulation(Integrator integrator, double interval
            , ParticleWriter writer, double width
            , double height, double d, double dStart, int n
            , double k_n, double k_t
            , double drop_depth,double drivingVelocity) {
        this.width = width;
        this.height = height;
        this.d = d;
        this.dStart = dStart;

        this.integrator = integrator;
        this.interval = interval;
        this.writer = writer;
        this.drivingVelocity=drivingVelocity;
        this.diameter = 0.3;

        this.particles = Particle.generateRandomParticles(n, this.diameter, width, height, 10000L,drop_depth,TAU,drivingVelocity);

        this.k_n = k_n;
        this.k_t = k_t;

        this.drop_depht = drop_depth;

        this.cim = new CellIndexMethod(width, height+drop_depth, n, 0.3);
        this.previousParticlesCount=n;
    }

    public long simulate() {

    	long flow = 0;
        cim.clearGrid();

        cim.addParticles(particles);

        particles = Collider.collisions(particles, width, height, dStart, d, k_n, k_t, drop_depht, cim);
        //VERSION ORIGINAL
        for (Particle particle : particles) {
            integrator.next(particle, interval);
            if(particle.wasInside()) {
            	double yPos = particle.getPosition().getY();
            	if(yPos < drop_depht) {
            		particle.setOutside();
            		flow++;
            	}
            }
        }

        //VERSION CON THREADS(para N=100 tarda mas)
        // particles.stream().forEach(e->integrator.next(e, interval));
        
        
       
        time = time + interval;
        return flow;
    }


    public void writeData(){
    	try {
			writer.write(time, particles);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


  public double getMeanKineticEnergy(){
	  return getTotalKineticEnergy()/particles.size();
  }
  public double getTotalKineticEnergy(){
		double ans=0.0;

		for(Particle particle:particles){
			ans+=particle.getKineticEnergy();
		}
		return ans;

	}

  public double getMeanPotentialEnergy(){
	  return getTotalPotentialEnergy()/particles.size();
  }

	public double getTotalPotentialEnergy(){
		double ans=0.0;

		for(Particle particle:particles){

			ans+=particle.getPotentialEnergy();
		}
		return ans;

	}

	public void clean() {
		try {
			writer.closeWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	


}
