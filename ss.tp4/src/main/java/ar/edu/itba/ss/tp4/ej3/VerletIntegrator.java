package ar.edu.itba.ss.tp4.ej3;

import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class VerletIntegrator implements Integrator {

	public void next(Particle particle, List<Particle> particles, Particle sun, Double dt) {
		// TODO Auto-generated method stub

		Vector2D previousPos = particle.getPosition();
		Vector2D previousVel = particle.getVelocity();
		Double mass = particle.getMass();

		Vector2D f = Gravity.gravitationalForceBetween(particle, sun).getForce();
		
//		for (Particle particle2 : particles) {
//			if(!particle.equals(particle2))
//				f.add(Gravity.gravitationalForceBetween(particle, particle2).getForce());
//		}

		Vector2D nextPos = previousPos.add(previousVel.scalarMultiply(dt))
				.add(f.scalarMultiply((Math.pow(dt, 2) / mass)));

		particle.setPosition(nextPos);

		Vector2D f_dt = Gravity.gravitationalForceBetween(particle, sun).getForce();
//		for (Particle particle2 : particles) {
//			if(!particle.equals(particle2))
//				f_dt.add(Gravity.gravitationalForceBetween(particle, particle2).getForce());
//		}

		Vector2D nextVel = previousVel.add((f_dt.add(f_dt)).scalarMultiply(dt / (2 * mass)));

		particle.setVelocity(nextVel);
		
		/*
		 * double newPosition; double newVelocity;
		 * 
		 * double force_tmdt; double force_t;
		 * 
		 * force_t = calculator.calculateForce(previousPosition,
		 * previousVelocity); newPosition = previousPosition + dt *
		 * previousVelocity + (Math.pow(dt, 2) / mass) * force_t;
		 * 
		 * force_tmdt = calculator.calculateForce(newPosition,
		 * previousVelocity); newVelocity = previousVelocity + dt / (2 * mass) *
		 * (force_t + force_tmdt);
		 * 
		 * previousPosition = newPosition; previousVelocity = newVelocity;
		 * return newPosition;
		 */

	}

}
