package ar.edu.itba.ss.tp4.ej3;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Force {

	private final Double module;
	private Vector2D direction;

	public Force(Double module, Vector2D direction) {
		super();
		this.module = module;
		this.direction = direction;
	}

	public Double getModule() {
		return module;
	}

	public Vector2D getDirection() {
		return direction;
	}

	public Vector2D getForce() {
		return direction.scalarMultiply(module);
	}
	
	public Double getForceModuleInDirection(Vector2D otherDirection) {
		Double resultant = Vector2D.angle(this.direction, otherDirection);
		return module * Math.cos(resultant);
	}

}
