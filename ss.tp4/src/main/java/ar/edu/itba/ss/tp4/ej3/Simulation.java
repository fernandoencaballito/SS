package ar.edu.itba.ss.tp4.ej3;

import java.io.IOException;

public class Simulation {

	private final SimpleSolarSystem system;
	private final Integrator integrator;
	private final Double interval;
	private Double time = 0.0;
	private final ParticleWriter writer;
	private long count = 0;

	public Simulation(SimpleSolarSystem system, Integrator integrator, Double interval, ParticleWriter writer) {
		super();
		this.system = system;
		this.integrator = integrator;
		this.interval = interval;
		this.writer = writer;
	}

	public Simulation(Integrator integrator, Double interval, ParticleWriter writer) {
		super();

		String id = "sim";
		Double sunMass = 2 * Math.pow(10, 30);
		Double sunRadius = Math.pow(10, 6);
		Integer initialParticlesCant = 1000;
		Double minDistanceFromSun = Math.pow(10, 9);
		Double maxDistanceFromSun = Math.pow(10, 10);
		Double maxParticleMass = null;
		Double minParticleMass = null;

		this.system = SimpleSolarSystem.randomSolarSysyem(id, sunMass, sunRadius, initialParticlesCant,
				minDistanceFromSun, maxDistanceFromSun, minParticleMass, maxParticleMass);
		this.integrator = integrator;
		this.interval = interval;
		this.writer = writer;

	}

	public void simulate() {

		Particle sun = system.getSun();
		system.setParticles(Collider.collisions(system.getParticles(),sun));
		for (Particle particle : system.getParticles()) {
			integrator.next(particle, system.getParticles(), sun, interval);
		}
		if(time % 100 == 0) {
			//count = 0;
			//System.out.println(system.getParticles().size());
			try {
				writer.write(time, sun, system.getParticles());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		count++;
		time = time + interval;
	}

}
