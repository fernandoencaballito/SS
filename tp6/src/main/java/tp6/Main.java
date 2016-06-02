package tp6;

import java.io.IOException;
import java.util.Locale;

public class Main {

	private static final double WIDTH = 20.0;
	private static final double HEIGHT = 20.0;
	private static final double D = 1.2;
	private static final int N = 100;
	private static final double DSTART = (WIDTH / 2.0) - (D / 2.0);
	private static final double KN = 1.2 * Math.pow(10.0, 5.0);
	private static final double KT = 2.4 * Math.pow(10.0, 5.0);
	private static final double DROP_DEPTH = 0.5;// Profundidad que caen las
													// particulas luego de salir
													// del silo. A una
													// profundida mayor, se
													// pierda la particula

	private static double total_time = 2;
	private static int REPEAT = 2;
	private static double DRIVING_VELOCITY = 1.3;

	private static double paso_simulacion = 0.00001;
	private static double paso_grafico = 0.1;
	private static int cant_cuadros = (int) Math.ceil(paso_grafico / paso_simulacion);

	private static boolean writeSimulationPositions = false;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DlmWriter flowWriter = null;

		Locale.setDefault(new Locale("en", "US"));

		ParticleWriter writer = null;
		try {
			String outputFilename = String.format("tp6_N=%d_deltaSim=%g_L=%g_D=%g.xyz", N, paso_simulacion, HEIGHT, D);
			writer = new ParticleWriter(outputFilename);

			// String
			// ENERGY_OUTPUT_FILE=String.format("system_energy_N=%d_deltaSim=%g_L=%g_D=%g.csv",N,paso_simulacion,HEIGHT,D);
			// energyDataWriter=new DlmWriter(ENERGY_OUTPUT_FILE);
			//
			String FLOW_OUTPUT_FILE = String.format("FLOW_N=%d_DrivingVelocity=.csv", N, DRIVING_VELOCITY);
			flowWriter = new DlmWriter(FLOW_OUTPUT_FILE);

		} catch (IOException e) {
			e.printStackTrace();
		}
		Integrator integrator = new VerletIntegrator();

		double[][] flow = new double[(int) Math.ceil(total_time / paso_grafico)][(REPEAT + 1)];
		
		
		for (int repeat = 0; repeat < REPEAT; repeat++) {
			int current_frame = 1;
			int current_frame_graph = 0;

			Simulation sim = new Simulation(integrator, paso_simulacion, writer, WIDTH, HEIGHT, D, DSTART, N, KN, KT,
					DROP_DEPTH, DRIVING_VELOCITY);

			long timeStart = System.currentTimeMillis();

			if (writeSimulationPositions)
				sim.writeData(); // ESCRITURA DE PARTICULAS

			long previousTime = timeStart;
			long currentFlow = 0;
			flow[0][repeat] = currentFlow;
			for (double time = paso_simulacion; time < total_time; time += paso_simulacion) {

				currentFlow += sim.simulate();

				if ((current_frame % cant_cuadros) == 0) {
					// se graba a archivo
					double avgFlow = ((double) currentFlow) / cant_cuadros;
					currentFlow = 0;
					if (writeSimulationPositions)
						sim.writeData();

					long currentSystemTime = System.currentTimeMillis();
					long timeStep = currentSystemTime - previousTime;
					previousTime = currentSystemTime;

					System.out.printf("time=%g, prog= %g, remaining= %d seconds, procesing steptime= %d\n", time,
							time / total_time, 0, timeStep);

					flow[current_frame_graph][0] = time;
					flow[current_frame_graph][repeat + 1] = avgFlow;
					current_frame_graph++;
				}
				current_frame++;

			}

			sim.clean();// se liberan recursos

			long elapsedTime = System.currentTimeMillis() - timeStart;

			System.out.println("ElapsedTime: " + elapsedTime);
		}

		flowWriter.write(flow, 1, 2);
		flowWriter.closeWriter();
		//

	}

}
