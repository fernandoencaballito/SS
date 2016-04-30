package ar.edu.itba.ss.tp4.ej3;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		long time = System.currentTimeMillis();

		ParticleWriter writer = null;
		try {
			writer = new ParticleWriter("solarSystem2");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Integrator integrator = new VerletIntegrator();
		Simulation sim = new Simulation(integrator, 1.0, writer);

		for (int i = 0; i < 100000; i++) {
			if(i%100==0)
				System.out.println("i=" + i);
			sim.simulate();
		}

		time = System.currentTimeMillis()-time;

		System.out.println("Time: "+time);

	}

}
