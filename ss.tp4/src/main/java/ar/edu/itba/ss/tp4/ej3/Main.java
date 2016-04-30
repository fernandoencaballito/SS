package ar.edu.itba.ss.tp4.ej3;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ParticleWriter writer = null;
		try {
			writer = new ParticleWriter("solarSystem");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Integrator integrator = new VerletIntegrator();
		Simulation sim = new Simulation(integrator, 1.0, writer);

		for (int i = 0; i < 10000; i++) {
			System.out.println("i=" + i);
			sim.simulate();
		}

	}

}
