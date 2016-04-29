package ar.edu.itba.ss.tp4.commons;

public class VelocityVerletIntegration {

	private double previousPosition;
	private double previousVelocity;
	private ForceCalculator1D calculator;

	public VelocityVerletIntegration(double initialPosition, double initialVelocity, ForceCalculator1D calculator) {

		this.previousPosition = initialPosition;
		this.previousVelocity = initialVelocity;
		this.calculator = calculator;
	}

	public double updatePosition(double mass, double dt) {

		double newPosition;
		double newVelocity;

		double force_tmdt;
		double force_t;

		force_t = calculator.calculateForce(previousPosition, previousVelocity);
		newPosition = previousPosition + dt * previousVelocity + (Math.pow(dt, 2.0) / mass) * force_t;

		double v_media = (newPosition - previousPosition)/dt;
		force_tmdt = calculator.calculateForce(newPosition, v_media);
		newVelocity = previousVelocity + (dt / (2.0 * mass)) * (force_t + force_tmdt);

		previousPosition = newPosition;
		previousVelocity = newVelocity;
		return newPosition;
	}

}
