package ar.edu.itba.ss.simulation;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.ArrayList;
import java.util.List;

public final class Collision implements Comparable<Collision> {

	private Particle p1;
	private Particle p2;
	private Wall wall;
	private CollisionType type;
	private double time;
	// cantidad de colisiones de las particulas, se usa para determinar si la
	// colisión es válida.
	private long p1_collisions;
	private long p2_collisions;
	private static final double EPSILON = 0.0001;

	public Collision(Particle p1, Particle p2, double time) {
		this.p1 = p1;
		this.p2 = p2;
		this.time = time;
		this.type = CollisionType.PARTICLE;
		this.p1_collisions = p1.getCollisionCount();
		this.p2_collisions = p2.getCollisionCount();
	}

	public Collision(Particle p1, Wall wall, double time) {
		this.p1 = p1;
		this.time = time;
		this.type = CollisionType.WALL;
		this.wall = wall;
		this.p1_collisions = p1.getCollisionCount();
	}

	public static double getCollisionTime(Particle p1, Particle p2) {

		Vector2D delta_r = p1.getPosition().subtract(p2.getPosition());
		Vector2D delta_v = p2.getVelocity().subtract(p2.getVelocity());

		double sigma = p1.getRadius() + p2.getRadius();

		return getCollsiionTime(delta_r, delta_v, sigma);
	}

	private static double getCollsiionTime(Vector2D delta_r, Vector2D delta_v, double sigma) {
		double dotProductVR = delta_v.dotProduct(delta_r);
		double dotProductRR = delta_r.dotProduct(delta_r);
		double dotProductVV = delta_v.dotProduct(delta_v);

		double d = Math.pow(dotProductVR, 2) - dotProductVV * (dotProductRR - Math.pow(sigma, 2));

		if (dotProductVR >= 0 || d < 0)
			return -1;

		double time = -(dotProductVR + Math.sqrt(d)) / dotProductVV;

		return time;
	}

	public static double getCollisionTime(Particle p1, Wall wall) {
		Vector2D position = p1.getPosition();
		Vector2D velocity = p1.getVelocity();

		Line trajectory = new Line(position, position.add(velocity), Wall.TOLERANCE);

		Vector2D intercept = trajectory.intersection(wall.getLine());

		if (intercept == null)
			return -1;

		if (Math.abs(intercept.getX() - position.getX()) < EPSILON) {
			if (velocity.getX() != 0) {
				return -1;
			}
		} else if (intercept.getX() > position.getX()) {
			if (!(velocity.getX() > 0)) {
				return -1;
			}
		} else if (intercept.getX() < position.getX()) {
			if (!(velocity.getX() < 0)) {
				return -1;
			}
		}

		if (Math.abs(intercept.getY() - position.getY()) < EPSILON) {
			if (velocity.getY() != 0) {
				return -1;
			}
		} else if (intercept.getY() > position.getY()) {
			if (!(velocity.getY() > 0)) {
				return -1;
			}
		} else if (intercept.getY() < position.getY()) {
			if (!(velocity.getY() < 0)) {
				return -1;
			}
		}

		Vector2D start = wall.getStart();
		Vector2D end = wall.getEnd();

		if (start.getX() < end.getX()) {
			if (intercept.getX() < start.getX() || intercept.getX() > end.getX()) {
				return -1;
			}
		} else {
			if (intercept.getX() < end.getX() || intercept.getX() > start.getX()) {
				return -1;
			}
		}
		if (start.getY() < end.getY()) {
			if (intercept.getY() < start.getY() || intercept.getY() > end.getY()) {
				return -1;
			}
		} else {
			if (intercept.getY() < end.getY() || intercept.getY() > start.getY()) {
				return -1;
			}
		}

		double time = intercept.subtract(position).getNorm() / velocity.getNorm();

		return time;
	}

	public int compareTo(Collision o) {
		double ans = this.time - o.time;

		if (ans < 0) {
			return -1;
		} else if (ans > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	public double getTime() {
		return time;
	}

	public boolean isValid() {
		return (p1.getCollisionCount() == p1_collisions
				&& (type != CollisionType.WALL && p2.getCollisionCount() == p2_collisions))
				|| p1.getCollisionCount() == p1_collisions;
	}

	public void setAbsolutTime(double currentTime) {
		this.time += currentTime;
	}

	public CollisionType getType() {
		return type;
	}

	public void collide() {
		if (this.type == CollisionType.PARTICLE) {
			collide(this.p1, this.p2);
			p1.incrementCollisionCount();
			p2.incrementCollisionCount();
		} else {
			collide(this.p1, this.wall);
			p1.incrementCollisionCount();
		}
	}

	private void collide(Particle p1, Particle p2) {

		Vector2D delta_r = p1.getPosition().subtract(p2.getPosition());
		Vector2D delta_v = p2.getVelocity().subtract(p2.getVelocity());

		double sigma = p1.getRadius() + p2.getRadius();

		double J = (2 * p1.getMass() * p2.getMass() * delta_r.dotProduct(delta_v))
				/ (sigma * (p1.getMass() + p2.getMass()));

		double Jx = J * delta_r.getX() / sigma;
		double Jy = J * delta_r.getY() / sigma;

		double p1_vx = p1.getXVelocity() + Jx / p1.getMass();
		double p1_vy = p1.getYVelocity() + Jy / p1.getMass();

		p1.setVelocity(p1_vx, p1_vy);

		double p2_vx = p1.getXVelocity() + Jx / p2.getMass();
		double p2_vy = p1.getYVelocity() + Jy / p2.getMass();

		p2.setVelocity(p2_vx, p2_vy);
		;

	}

	private void collide(Particle p1, Wall w) {
		double vx = p1.getXVelocity();
		double vy = p1.getYVelocity();

		
		double angle = w.getAngle();

		double sin = Math.sin(angle);
		double cos = Math.cos(angle);

		double vxp = vx * cos - vy * sin;
		double vyp = -(vx * sin + vy * cos);

		sin = Math.sin(-angle);
		cos = Math.cos(-angle);

		vx = vxp * cos - vyp * sin;
		vy = vxp * sin + vyp * cos;

		p1.setVelocity(vx, vy);
	}

	public List<Particle> getParticles() {
		List<Particle> particles = new ArrayList<Particle>(2);
		particles.add(p1);
		if (type == CollisionType.PARTICLE)
			particles.add(p2);

		return particles;
	}

	public boolean isPeriodic(){
		return this.type == CollisionType.PERIODIC;
	}
	
	public Particle getParticle() {
		return this.p1;
	}
	private enum CollisionType {
		WALL, PARTICLE, PERIODIC;
	}

}
