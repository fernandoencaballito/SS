package ar.edu.itba.ss.tp4.commons;

public class EulerIntegration {

	private double previousPosition;
	private double previousVelocity;
	private ForceCalculator1D calculator;

	public EulerIntegration(double initialPosition, double initialVelocity, ForceCalculator1D calculator) {

		this.previousPosition = initialPosition;
		this.previousVelocity = initialVelocity;
		this.calculator = calculator;
	}

	public double[] updatePosition(double mass, double dt) {

		double newPosition;
		double newVelocity;

		double force_t;

		force_t = this.calculator.calculateForce(this.previousPosition, this.previousVelocity);

		newVelocity = this.previousVelocity + dt / (mass) * (force_t);

		newPosition = this.previousPosition + dt * newVelocity + (Math.pow(dt, 2) / (2 * mass)) * force_t;

		this.previousPosition = newPosition;
		this.previousVelocity = newVelocity;
		return new double[] {newPosition,newVelocity};
	}
}
