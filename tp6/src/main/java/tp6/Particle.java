package tp6;

import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.euclidean.twod.Segment;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Particle {

	private double TAU;
	private double drivingVelocity;
	private static final double g = 9.8;
	private final int id;
	private final double mass = 0.01;
	private Vector2D position;
	private Double radius;
	private Vector2D velocity;
	private Vector2D previous_position;
	private List<Vector2D> forces;
	private boolean inside;

	public Particle(int id, Vector2D position, Vector2D velocity, double radius, double tau, double velocidadDeseo) {
		super();
		this.id = id;
		this.radius = radius;
		this.velocity = velocity;
		this.position = position;
		this.previous_position = position;
		this.forces = new ArrayList<Vector2D>();
		this.inside = true;
		this.TAU = tau;
		this.drivingVelocity = velocidadDeseo;

	}

	public static List<Particle> generateRandomParticles(int cant, double diameter, double width, double height,
			long timeout, double drop_height, double tau, double drivingVelocity) {

		System.out.println("[generateRandomParticles]: starting");
		List<Particle> particles = new ArrayList<Particle>(cant);

		long maxTime = System.currentTimeMillis() + timeout;

		int i = 0;

		final double RADIUS = diameter / 2.0;

		Random random = new Random();

		while (System.currentTimeMillis() < maxTime && particles.size() < cant) {

			Vector2D position = new Vector2D(width * random.nextDouble(), (height * random.nextDouble()) + drop_height);

			if (position.getX() < RADIUS || (position.getX() + RADIUS) > width) {
				continue;
			}

			if (position.getY() < (RADIUS + drop_height) || (position.getY() + RADIUS) > (height + drop_height)) {
				continue;
			}

			boolean overlaps = false;
			boolean expired = false;

			for (Particle particle : particles) {

				if (System.currentTimeMillis() > maxTime) {
					expired = true;
					break;
				}

				if (particle.overlaps(position)) {
					overlaps = true;
					break;
				}
			}

			if (overlaps)
				continue;

			if (expired)
				break;

			particles.add(new Particle(i, position, new Vector2D(0.0, 0.0), RADIUS, tau, drivingVelocity));
			i++;
		}

		System.out.println("[generateRandomParticles]: done");
		return particles;
	}

	public boolean overlaps(Vector2D pos) {

		double epsilon = this.radius * 2 - pos.subtract(this.getPosition()).getNorm();

		return epsilon > 0;

	}

	public int getId() {
		return id;
	}

	public double getMass() {
		return mass;
	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.previous_position = this.position;
		this.position = position;
	}

	public Vector2D getPreviousPosition() {
		return this.previous_position;
	}

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}

	public Vector2D getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2D velocity) {
		this.velocity = velocity;
	}

	public boolean wasInside() {
		return this.inside;
	}

	public void setOutside() {
		this.inside = false;
	}

	@Override
	public String toString() {
		return "Particle [id=" + id + ", mass=" + mass + ", position=" + position + ", radius=" + radius + ", velocity="
				+ velocity + "]";
	}

	public double getKineticEnergy() {
		return 0.5 * mass * velocity.dotProduct(velocity);
	}

	public void addForce(Vector2D f) {
		forces.add(f);
	}

	public void addForce(double f_x, double f_y) {
		forces.add(new Vector2D(f_x, f_y));

	}

	// metodo que devuelve la suma de todas las fuerzas.
	// la fuerza de gravedad no esta incluida en la lista de fuerzas, sino que
	// la calcula
	public Vector2D getTotalForces() {

		Vector2D target=new Vector2D(10.0,0);
		Vector2D drivingForce=drivingForce(target);
		double ansx = drivingForce.getX();
		double ansy = drivingForce.getY();

		for (Vector2D v : forces) {

			ansx += v.getX();
			ansy += v.getY();
		}

		forces.clear();
		return new Vector2D(ansx, ansy);

	}

	public double getPotentialEnergy() {

		return mass * g * position.getY();
	}

	public Vector2D drivingForce(Vector2D target) {

		Vector2D ans = ((this.getE(target).scalarMultiply(drivingVelocity)).subtract(velocity))
				.scalarMultiply(mass / TAU);

		return ans;

	}

	public Vector2D getE(Vector2D target) {
		return target.subtract(position).normalize();
	}
}
