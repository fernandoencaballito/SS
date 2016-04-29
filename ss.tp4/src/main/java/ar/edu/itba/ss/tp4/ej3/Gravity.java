package ar.edu.itba.ss.tp4.ej3;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Gravity {

	private static final Double G = 6.74 * Math.pow(10, -11);

	public static Force gravitationalForceBetween(Particle p1, Particle p2) {
		Double module;
		Vector2D dir = p1.getPosition().subtract(p2.getPosition()).negate();
		Double r = dir.getNorm();
		module = G * p1.getMass() * p2.getMass() / Math.pow(r, 2);

		Vector2D direction = dir.normalize();

		return new Force(module, direction);

	}


}
