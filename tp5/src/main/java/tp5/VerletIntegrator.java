package tp5;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class VerletIntegrator implements Integrator {


    public void next(Particle particle, double dt) {
        Vector2D force = particle.getTotalForces();
        Vector2D previous_position = particle.getPreviousPosition();
        Vector2D acceleration = force.scalarMultiply(1.0/particle.getMass());

        Vector2D pos = particle.getPosition().scalarMultiply(2).subtract(previous_position).add(acceleration.scalarMultiply(Math.pow(dt, 2)));
        Vector2D vel = pos.subtract(previous_position).scalarMultiply(1.0/(2*dt));
        
        particle.setPosition(pos);
        particle.setVelocity(vel);

	}

}
