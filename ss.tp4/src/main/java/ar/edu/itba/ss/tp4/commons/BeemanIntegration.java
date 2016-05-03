package ar.edu.itba.ss.tp4.commons;

public class BeemanIntegration {
	private double previousPosition;
	private double previousVelocity;
	private ForceCalculator1D calculator;
	private double previousAcceleration;

	public BeemanIntegration(double initialPosition, double initialVelocity, ForceCalculator1D calculator,
			double previousAcceleration) {

		this.previousPosition = initialPosition;
		this.previousVelocity = initialVelocity;
		this.previousAcceleration = previousAcceleration;
		System.out.println(previousAcceleration);
		this.calculator = calculator;
	}

	public double updatePosition(double mass, double dt) {

		double newPosition;
		double newVelocity;

		double a_tmdt;
		double a_t;
		
		a_t = calculator.calculateForce(previousPosition, previousVelocity)/mass;

		newPosition = this.previousPosition + this.previousVelocity * dt + (2.0/3.0) * a_t * Math.pow(dt, 2) - (1.0/6.0) * this.previousAcceleration * Math.pow(dt, 2);
	
		
		a_tmdt = calculator.calculateForce(newPosition, previousVelocity)/mass;
		System.out.println("force in beeman="+calculator.calculateForce(newPosition, previousVelocity) +" ,p="+newPosition+ " v="+previousVelocity);
		newVelocity = this.previousVelocity + (1.0/3.0) * a_tmdt * dt + (5.0/6.0) * a_t * dt - (1.0/6.0) * this.previousAcceleration * dt;

		this.previousPosition = newPosition;
		this.previousVelocity = newVelocity;
		this.previousAcceleration = a_t;
		return newPosition;
	}
}
