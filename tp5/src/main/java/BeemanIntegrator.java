
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class BeemanIntegrator implements Integrator {
    public void next(Particle particle, double dt) {

        Vector2D force = particle.getTotalForces();
        Vector2D acceleration = force.scalarMultiply(10 / particle.getMass());
        //Particle previous = new Particle("", particle.getMass(), particle.getVelocity().subtract(acceleration.scalarMultiply(dt / 2.0)), particle.getPosition().subtract(particle.getVelocity().scalarMultiply(dt / 2.0)));
        Vector2D previous_velocity = particle.getPreviousVelocity();
        Vector2D previous_position = particle.getPreviousPosition();

        if (previous_position == null || previous_velocity == null) {
            previous_velocity = particle.getVelocity().subtract(acceleration.scalarMultiply(dt / 2.0));
            previous_position = particle.getPosition().subtract(particle.getVelocity().scalarMultiply(dt / 2.0)).subtract(acceleration).scalarMultiply(Math.pow(dt, 2));
        }

        Particle previous = new Particle(0, previous_position, previous_velocity, particle.getRadius());
        Vector2D previousAcceleration = Gravity.gravitationalForce(previous).scalarMultiply(1.0 / particle.getMass());

        Vector2D newPosition = particle.getPosition().add(particle.getVelocity().scalarMultiply(dt)).add(acceleration.scalarMultiply((2.0 / 3.0) * Math.pow(dt, 2)).subtract(previousAcceleration.scalarMultiply((1.0 / 6.0) * Math.pow(dt, 2))));
        particle.setPosition(newPosition);

        Vector2D a_tmdt = Gravity.gravitationalForce(particle).scalarMultiply(1.0 / particle.getMass());

        Vector2D newVelocity = particle.getVelocity().add(a_tmdt.scalarMultiply((1.0 / 3.0) * dt).add(a_tmdt.scalarMultiply((5.0 / 6.0) * dt).subtract(previousAcceleration.scalarMultiply((1.0 / 6.0) * dt))));
        particle.setVelocity(newVelocity);

    }
}
