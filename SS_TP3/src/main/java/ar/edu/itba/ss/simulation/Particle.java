package ar.edu.itba.ss.simulation;

import java.awt.geom.Point2D;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Particle {

	private int id;

	private Vector2D position;
	private Vector2D velocity;
	
	
	private double radius;
	private double mass;
	
	public Particle(double x, double y, double vx, double vy, double radius, double mass) {
		position = new Vector2D(x, y);
		velocity = new Vector2D(vx, vy);
		this.radius = radius;
		this.mass = mass;

	}

	public void advance(double time) {

		double x_pos = position.getX() + velocity.getX() * time;
		double y_pos = position.getY() + velocity.getY() * time;

		this.position = new Vector2D(x_pos, y_pos);
	}


	public Vector2D getPosition() {
		return this.position;
	}

	public double getXPosition() {
		return this.position.getX();
	}
	public double getYPosition() {
		return this.position.getY();
	}

	public void setVelocity(double vx, double vy){
		this.velocity = new Vector2D(vx, vy);
	}

	public Vector2D getVelocity() {
		return this.velocity;
	}

	public double getXVelocity() {
		return this.velocity.getX();
	}
	public double getYVelocity() {
		return this.velocity.getY();
	}

	public double getMass() {
		return this.mass;
	}
	public double getRadius() {
		return radius;
	}
}
