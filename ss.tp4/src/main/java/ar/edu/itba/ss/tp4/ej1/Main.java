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
		double mass = 70.0;

		// no me acuerdo las unidades
		double k = 10000.0;

		double gamma = 100.0;// DEBERIA SER 100

		Spring resorte = new Spring(k, gamma);

		// tiempo en segundos
		double tf = 5.0;

		double paso_simulacion = 0.01;// usar 0.00001
		double paso_graph = 0.01;
		int cant_cuadros =(int)Math.ceil( paso_graph / paso_simulacion);
		int current_frame = 1;
		double initialPosition = 1.0;
		
		double initialVelocity =(gamma==0)? 0.0 :( -gamma / mass / 2);

		VelocityVerletIntegration vvintegrator = new VelocityVerletIntegration(initialPosition, initialVelocity,
				resorte);


		double current_pos_analitic = initialPosition;
		double current_pos_aprox_verlet = initialPosition;
		double current_pos_aprox_beeman = initialPosition;
		double current_pos_aprox_gear = initialPosition;

		int rows = (int) ((tf / paso_graph) + 1);
		int cols = 5;

		double[][] posiciones = new double[rows][cols];
		DlmWriter writer = null;

		try {
			writer = new DlmWriter("ejercicio1.csv");
		} catch (IOException io) {
			io.printStackTrace();
		}

		posiciones[0][0] = 0.0;
		posiciones[0][1] = initialPosition;
		posiciones[0][2] = initialPosition;
		posiciones[0][3] = initialPosition;
		posiciones[0][4] = initialPosition;

		int i = 1;

		EulerIntegration euler = new EulerIntegration(initialPosition, initialVelocity, resorte);
		double[] prev = euler.updatePosition(mass, -paso_simulacion);
		double acceleration = resorte.calculateForce(prev[0], prev[1]) / mass;
		BeemanIntegration bee = new BeemanIntegration(initialPosition, initialVelocity, resorte, acceleration);

		GearPredictorCorrectorIntegration gear = new GearPredictorCorrectorIntegration(initialPosition, initialVelocity,
				resorte, mass);
		for (double t = paso_simulacion; t <= tf; t += paso_simulacion) {
			System.out.println("t="+t);
			current_pos_analitic = AnalyticSpringSolution.getPosition(resorte, mass, t);
			current_pos_aprox_verlet = vvintegrator.updatePosition(mass, paso_simulacion);
			current_pos_aprox_beeman = bee.updatePosition(mass, paso_simulacion);
			current_pos_aprox_gear = gear.updatePosition(paso_simulacion, mass);
						
			if ((current_frame % cant_cuadros) == 0) {
				
					current_frame++;
				// se grafica
				posiciones[i][0] = t;
				posiciones[i][1] = current_pos_analitic;
				posiciones[i][2] = current_pos_aprox_verlet;
				posiciones[i][3] = current_pos_aprox_beeman;
				posiciones[i][4] = current_pos_aprox_gear;
				i++;
			} else {

				current_frame++;
			}

		}
		try {
			writer.write(posiciones, rows, cols);
			writer.closeWriter();
		} catch (IOException io) {
			io.printStackTrace();
		}

	}

}
