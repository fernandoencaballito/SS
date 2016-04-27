package ar.edu.itba.ss.tp4.commons;

public class GearPredictorCorrectorIntegration {
	private ForceCalculator1D calculator;
	private double r;
	private double r1;
	private double r2;
	private double r3;
	private double r4;
	private double r5;

	public GearPredictorCorrectorIntegration(double initialPosition, double initialVelocity,
			ForceCalculator1D calculator, double mass) {
		this.calculator = calculator;
		this.r = initialPosition;
		this.r1 = initialVelocity;

		this.r2 = calculator.calculateForce(r, r1) / mass;
		this.r3 = calculator.calculateForce(r1, r2) / mass;
		this.r4 = calculator.calculateForce(r2, r3) / mass;
		this.r5 = calculator.calculateForce(r3, r4) / mass;

	}

	public double updatePosition(double dt, double mass) {

		// predictions

		double rp, r1p, r2p, r3p, r4p, r5p;

		r5p = r5;
		r4p = r4 + r5 * dt;
		r3p = r3 + r4 * dt + r5 * Math.pow(dt, 2) / 2.0;
		r2p = r2 + r3 * dt + r4 * Math.pow(dt, 2) / 2.0 + r5 * Math.pow(dt, 3) / 6.0;
		r1p = r1 + r2 * dt + r3 * Math.pow(dt, 2) / 2.0 + r4 * Math.pow(dt, 3) / 6.0 + r5 * Math.pow(dt, 4) / 24.0;
		rp = r + r1 * dt + r2 * Math.pow(dt, 2) / 2.0 + r3 * Math.pow(dt, 3) / 6.0 + r4 * Math.pow(dt, 4) / 24.0
				+ r5 * Math.pow(dt, 5) / 120.0;

		// evaluar

		double da = calculator.calculateForce(rp, r1p)/mass - r2p;
		double dR2 = da * Math.pow(dt, 2) / 2.0;

		// correct

		// 3/16 251/360 1 11/18 1/6 1/60
		double rc, r1c, r2c, r3c, r4c, r5c;

		rc = rp + (3.0 / 16.0) * dR2;
		r1c = r1p * dt + (251.0 / 360.0) * dR2;
		r2c = r2p * Math.pow(dt, 2) / 2.0 + dR2;
		r3c = r3p * Math.pow(dt, 3) / 6.0 + (11.0 / 18.0) * dR2;
		r4c = r4p * Math.pow(dt, 4) / 24.0 + (1.0 / 6.0) * dR2;
		r5c = r5p * Math.pow(dt, 5) / 120.0 + (1.0 / 60.0) * dR2;

		this.r = rc;
		this.r1 = r1c;
		this.r2 = r2c;
		this.r3 = r3c;
		this.r4 = r4c;
		this.r5 = r5c;

		return r;

	}
}
