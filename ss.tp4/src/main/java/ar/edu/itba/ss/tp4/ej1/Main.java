package ar.edu.itba.ss.tp4.ej1;

import java.io.IOException;

import ar.edu.itba.ss.tp4.commons.DlmWriter;
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

		double paso = 0.001;

		double initialPosition = 1;
		double initialVelocity = -gamma/mass/2;

		VelocityVerletIntegration vvintegrator = new VelocityVerletIntegration(initialPosition, initialVelocity,
				resorte);

		double r2 = 0;

		double current_pos_analitic = initialPosition;
		double current_pos_aprox = initialPosition;

		
		int rows = 501;
		int cols = 3;
				
		double[][] posiciones = new double[rows][cols];
		DlmWriter writer = null;
		
		try {
			writer = new DlmWriter("ejercicio1.csv");
		}catch(IOException io) {
			io.printStackTrace();
		}
		
		
		posiciones[0][0] = 0;
		posiciones[0][1] = 1;
		posiciones[0][2] = 1;
		
		int i = 1;
		
		for (double t = paso; t <= tf; t += paso) {
		
			current_pos_analitic = AnalyticSpringSolution.getPosition(resorte, mass, t);
			current_pos_aprox = vvintegrator.updatePosition(mass, paso);

			//r2 = Math.pow(Math.abs(current_pos_analitic - current_pos_aprox), 2);

			
			
			posiciones[i][0] = t;
			posiciones[i][1] = current_pos_analitic;
			posiciones[i][2] = current_pos_aprox;
			
			i++;
		}
		try {
		 writer.write(posiciones, rows, cols);
		}catch(IOException io) {
			io.printStackTrace();
		}

	}

}
