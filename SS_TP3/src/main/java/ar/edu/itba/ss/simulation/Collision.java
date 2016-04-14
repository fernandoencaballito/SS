package ar.edu.itba.ss.simulation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public final class Collision {

	private enum CollisionType {
		WALL, PARTICLE;
	}

	private Particle p1;

	private Particle p2;
	private Wall wall;
	private CollisionType type;
	private double time;

	public Collision(Particle p1, Particle p2, double time) {
		this.p1 = p1;
		this.p2 = p2;
		this.time = time;
		this.type = CollisionType.PARTICLE;
	}

	public Collision(Particle p1,Wall wall, double time) {
		this.p1 = p1;
		this.time = time;
		this.type = CollisionType.WALL;
		this.wall = wall;
	}

	public double getTime() {
		return time;
	}

    public boolean isValid() {
        return p1.getCollisionCount() == p1_collisions && p2.getCollisionCount() == p2_collisions;
    }

	public void setAbsolutTime(double currentTime) {
		this.time += currentTime;
	}

	public CollisionType getType() {
		return type;
	}

	public void collide() {
		if (this.type == CollisionType.PARTICLE)
			collide(this.p1, this.p2);
		else
			collide(this.p1, this.type);

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

	private void collide(Particle p1, CollisionType w) {
		double vx = p1.getXVelocity();
		double vy = p1.getYVelocity();

	/*
		switch (w) {
		case NORTH:
		case SOUTH:
			vy = -vy;
			break;

		case EAST:
		case WEST:
			vx = -vx;
			break;

		}
*/
		p1.setVelocity(vx, vy);
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

	public static double getCollisionTime(Particle p1,Wall wall) {

		Vector2D position = p1.getPosition();
		Vector2D velocity = p1.getVelocity();

		Line trajectory = new Line(position, position.add(velocity), Wall.TOLERANCE);

		Vector2D intercept = trajectory.intersection(wall.getLine());

		Vector2D start = wall.getStart();
		Vector2D end = wall.getEnd();


		if(start.getX() < end.getX()) {
			if(intercept.getX() < start.getX() || intercept.getX() > end.getX()) {
				return -1;
			}
		}else {
			if(intercept.getX() < end.getX() || intercept.getX() > start.getX()) {
				return -1;
			}
		}

		double time = intercept.getNorm() / velocity.getNorm();

		return time;
	}

	public List<Particle> getParticles() {
		List<Particle> particles = new ArrayList<Particle>(2);
		particles.add(p1);
		if(type == CollisionType.PARTICLE)
			particles.add(p2);

		return particles;
	}

}
