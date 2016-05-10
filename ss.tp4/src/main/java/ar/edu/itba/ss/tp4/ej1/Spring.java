package ar.edu.itba.ss.tp4.ej1;

import ar.edu.itba.ss.tp4.commons.ForceCalculator1D;

public class Spring implements ForceCalculator1D {

	private final double k;
	private final double gamma;

	public Spring(double k, double gamma) {
		this.k = k;
		this.gamma = gamma;
	}
	
	public double calculateNDeriv(double di, double di_sig) {
		return calculateForce(di,di_sig);
	}
	

	public double calculateForce(double position, double velocity) {
		return -k * position - gamma * velocity;
	}

	public double getGamma() {
		return gamma;
	}

	public double getK() {
		return k;
	}
}
