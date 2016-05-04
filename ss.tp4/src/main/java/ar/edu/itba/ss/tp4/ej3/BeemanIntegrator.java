package ar.edu.itba.ss.tp4.ej3;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.List;

/**
 * Created by Luis on 5/1/2016.
 */
public class BeemanIntegrator implements Integrator {
    public void next(Particle particle, List<Particle> particles, Particle sun, Double dt) {

        Vector2D force = Gravity.gravitationalForceBetween(particle, sun).getForce();
        Vector2D acceleration = force.scalarMultiply(10 / particle.getMass());
        Particle previous = new Particle("", particle.getMass(), particle.getVelocity().subtract(acceleration.scalarMultiply(dt / 2.0)), particle.getPosition().subtract(particle.getVelocity().scalarMultiply(dt / 2.0)));
        Vector2D previousAcceleration = Gravity.gravitationalForceBetween(previous, sun).getForce().scalarMultiply(1.0 / particle.getMass());

        Vector2D newPosition = particle.getPosition().add(particle.getVelocity().scalarMultiply(dt)).add(acceleration.scalarMultiply((2.0 / 3.0) * Math.pow(dt, 2)).subtract(previousAcceleration.scalarMultiply((1.0 / 6.0) * Math.pow(dt, 2))));
        particle.setPosition(newPosition);

        Vector2D a_tmdt = Gravity.gravitationalForceBetween(particle, sun).getForce().scalarMultiply(1.0 / particle.getMass());

        Vector2D newVelocity = particle.getVelocity().add(a_tmdt.scalarMultiply((1.0 / 3.0) * dt).add(a_tmdt.scalarMultiply((5.0 / 6.0) * dt).subtract(previousAcceleration.scalarMultiply((1.0 / 6.0) * dt))));
        particle.setVelocity(newVelocity);
    }
}
