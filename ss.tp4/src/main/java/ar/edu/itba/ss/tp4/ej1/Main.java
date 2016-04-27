package ar.edu.itba.ss.tp4.ej1;

import java.io.IOException;

import ar.edu.itba.ss.tp4.commons.BeemanIntegration;
import ar.edu.itba.ss.tp4.commons.DlmWriter;
import ar.edu.itba.ss.tp4.commons.EulerIntegration;
import ar.edu.itba.ss.tp4.commons.GearPredictorCorrectorIntegration;
import ar.edu.itba.ss.tp4.commons.VelocityVerletIntegration;

public class Main {

	public static void main(String[] args) {

		// mass in kg
		double mass = 70;

		// no me acuerdo las unidades
		double k = 10000;

		double gamma = 100;

		Spring resorte = new Spring(k, gamma);

		// tiempo en segundos
		double tf = 5;

		double paso = 0.01;

		double initialPosition = 1;
		double initialVelocity = -gamma / mass / 2;

		VelocityVerletIntegration vvintegrator = new VelocityVerletIntegration(initialPosition, initialVelocity,
				resorte);

		double r2 = 0;

		double current_pos_analitic = initialPosition;
		double current_pos_aprox_verlet = initialPosition;
		double current_pos_aprox_beeman = initialPosition;
		double current_pos_aprox_gear = initialPosition;

		int rows = (int)(tf/paso)+1;
		int cols = 5;

		double[][] posiciones = new double[rows][cols];
		DlmWriter writer = null;

		try {
			writer = new DlmWriter("ejercicio1.csv");
		} catch (IOException io) {
			io.printStackTrace();
		}

		posiciones[0][0] = 0;
		posiciones[0][1] = 1;
		posiciones[0][2] = 1;
		posiciones[0][3] = 1;
		posiciones[0][4] = 1;

		int i = 1;

		EulerIntegration euler = new EulerIntegration(initialPosition, initialVelocity, resorte);
		double[] prev = euler.updatePosition(mass, -paso);
		double acceleration = resorte.calculateForce(prev[0], prev[1]) / mass;
		BeemanIntegration bee = new BeemanIntegration(initialPosition, initialVelocity, resorte, acceleration);

		GearPredictorCorrectorIntegration gear = new GearPredictorCorrectorIntegration(initialPosition, initialVelocity, resorte, mass);
		for (double t = paso; t <= tf; t += paso) {

			current_pos_analitic = AnalyticSpringSolution.getPosition(resorte, mass, t);
			current_pos_aprox_verlet = vvintegrator.updatePosition(mass, paso);
			current_pos_aprox_beeman = bee.updatePosition(mass, paso);
			current_pos_aprox_gear = gear.updatePosition(paso,mass);

			// r2 = Math.pow(Math.abs(current_pos_analitic - current_pos_aprox),
			// 2);

			posiciones[i][0] = t;
			posiciones[i][1] = current_pos_analitic;
			posiciones[i][2] = current_pos_aprox_verlet;
			posiciones[i][3] = current_pos_aprox_beeman;
			posiciones[i][4] = current_pos_aprox_gear;

			i++;
		}
		try {
			writer.write(posiciones, rows, cols);
		} catch (IOException io) {
			io.printStackTrace();
		}

	}

}
