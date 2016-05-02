package ar.edu.itba.ss.tp4.ej3;

import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class SimpleSolarSystem {

	public static final SimpleSolarSystem randomSolarSysyem(String id, Double sunMass, Double sunRadius,
			Integer initialParticlesCant, Double minDistanceFromSun, Double maxDistanceFromSun, Double minParticleMass,
			Double maxParticleMass) {

		Particle sun = new Particle("0", sunMass, new Vector2D(0, 0), new Vector2D(0, 0));
		sun.setRadius(6*Math.pow(12, 7));
		Double angularMomentum= 0.8*Math.pow(2, 140);
		List<Particle> particles = Particle.generateRandomParticles(angularMomentum, minDistanceFromSun,
				maxDistanceFromSun, sunMass, initialParticlesCant);

		return new SimpleSolarSystem(id, sun, particles);
	}

	private final String id;
	private List<Particle> particles;

	private final Particle sun;

	public SimpleSolarSystem(String id, Particle sun, List<Particle> particles) {
		super();
		this.id = id;
		this.sun = sun;
		this.particles = particles;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleSolarSystem other = (SimpleSolarSystem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (particles == null) {
			if (other.particles != null)
				return false;
		} else if (!particles.equals(other.particles))
			return false;
		if (sun == null) {
			if (other.sun != null)
				return false;
		} else if (!sun.equals(other.sun))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public List<Particle> getParticles() {
		return particles;
	}

	public Particle getSun() {
		return sun;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((particles == null) ? 0 : particles.hashCode());
		result = prime * result + ((sun == null) ? 0 : sun.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "SimpleSolarSystem [id=" + id + ", sun=" + sun + ", particles=" + particles + "]";
	}

	public void setParticles(List<Particle> collisions) {
		// TODO Auto-generated method stub
		this.particles = collisions;
	}

}
