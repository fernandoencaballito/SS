package ar.edu.itba.ss.tp4.ej3;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Particle {

	public static List<Particle> generateRandomParticles(Double angularMomentum, Double minDistanceFromSun,
			Double maxDistanceFromSun, Double sunMass, int cant) {
		// TODO Auto-generated method stub

		List<Particle> particles = new LinkedList<Particle>();
		Double meanMass = sunMass / ((1.0) * cant);

		UniformRealDistribution posGen = new UniformRealDistribution(minDistanceFromSun, maxDistanceFromSun);
		UniformRealDistribution angleGen = new UniformRealDistribution(0, 2 * Math.PI);

		Double var = Math.pow(2, 10);

		UniformRealDistribution massGen = new UniformRealDistribution(-1, 1);

		double[] positions = posGen.sample(cant);
		double[] angles = angleGen.sample(cant);
		double[] masses = massGen.sample(cant);

		String id;
		Double velocity_angle;
		Vector2D velocity;
		Vector2D position;
		double velocity_module;
		for (int i = 0; i < cant; i++) {

			velocity_module = angularMomentum / (meanMass * positions[i]);
			velocity_angle = Math.PI / 2.0 + angles[i];
			velocity = new Vector2D(Math.cos(velocity_angle) * velocity_module,
					Math.sin(velocity_angle) * velocity_module);
			position = new Vector2D(Math.cos(angles[i]) * positions[i], Math.sin(angles[i]) * positions[i]);
			id = "" + (i+1);
			particles.add(new Particle(id, meanMass + masses[i] * var, velocity, position));

		}

		return particles;
	}
	private boolean collided;

	private final String id;

	private Double mass;

	private Vector2D position;

	private Double radius;

	private Vector2D velocity;
	public Particle(String id, Double mass, Vector2D velocity, Vector2D position) {
		super();
		this.id = id;
		this.mass = mass;
		this.radius = Math.pow(10, 7);
		this.velocity = velocity;
		this.position = position;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Particle other = (Particle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mass == null) {
			if (other.mass != null)
				return false;
		} else if (!mass.equals(other.mass))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (radius == null) {
			if (other.radius != null)
				return false;
		} else if (!radius.equals(other.radius))
			return false;
		if (velocity == null) {
			if (other.velocity != null)
				return false;
		} else if (!velocity.equals(other.velocity))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public Double getMass() {
		return mass;
	}

	public Vector2D getPosition() {
		return position;
	}

	public Double getRadius() {
		return radius;
	}

	public Vector2D getVelocity() {
		return velocity;
	}

	public boolean hasCollided() {
		return this.collided;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mass == null) ? 0 : mass.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((radius == null) ? 0 : radius.hashCode());
		result = prime * result + ((velocity == null) ? 0 : velocity.hashCode());
		return result;
	}

	public void setCollided(boolean b) {
		// TODO Auto-generated method stub
		this.collided = b;
	}

	public void setMass(Double mass) {
		this.mass = mass;
		//this.radius = Math.pow(this.mass/Math.PI,0.28);
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}

	public void setVelocity(Vector2D velocity) {
		this.velocity = velocity;
	}

	@Override
	public String toString() {
		return "Particle [id=" + id + ", mass=" + mass + ", position=" + position + ", radius=" + radius + ", velocity="
				+ velocity + "]";
	}

}
