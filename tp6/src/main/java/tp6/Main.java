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

	private static double total_time =60;//poner 60
	private static int REPEAT =10;
	//private static double DRIVING_VELOCITY = 1.3;

	private static double paso_simulacion = 0.00001;
	private static double paso_grafico = 0.1;
	private static double paso_flow = 1;
	private static int cant_cuadros = (int) Math.ceil(paso_grafico / paso_simulacion);
	private static int cant_flow = (int) Math.ceil(paso_flow / paso_simulacion);
	private static boolean writeSimulationPositions =false;
	


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		for (double DRIVING_VELOCITY = 7.0; DRIVING_VELOCITY <= 8.0; DRIVING_VELOCITY++) {
			DlmWriter flowWriter = null;

			Locale.setDefault(new Locale("en", "US"));

			ParticleWriter writer = null;
			try {
				String outputFilename = String.format("tp6_N=%d_DrivingVelocity=%g.xyz", N,DRIVING_VELOCITY);
				writer = new ParticleWriter(outputFilename);

				// String
				// ENERGY_OUTPUT_FILE=String.format("system_energy_N=%d_deltaSim=%g_L=%g_D=%g.csv",N,paso_simulacion,HEIGHT,D);
				// energyDataWriter=new DlmWriter(ENERGY_OUTPUT_FILE);
				//
				String FLOW_OUTPUT_FILE = String.format("FLOW_N=%d_DrivingVelocity=%g.csv", N, DRIVING_VELOCITY);
				flowWriter = new DlmWriter(FLOW_OUTPUT_FILE);

			} catch (IOException e) {
				e.printStackTrace();
			}
			Integrator integrator = new VerletIntegrator();
			int flowRows = (int) Math.ceil(total_time / paso_flow) + 1;
			int flowCols = (REPEAT + 1);
			double[][] exitsData = new double[flowRows][flowCols];

			for (int repeat = 0; repeat < REPEAT; repeat++) {
				int current_frame = 1;
				int current_frame_graph = 1;

				Simulation sim = new Simulation(integrator, paso_simulacion, writer, WIDTH, HEIGHT, D, DSTART, N, KN,
						KT, DROP_DEPTH, DRIVING_VELOCITY);

				long timeStart = System.currentTimeMillis();

				if (writeSimulationPositions)
					sim.writeData(); // ESCRITURA DE PARTICULAS

				long previousTime = timeStart;
				long exits = 0;
				exitsData[0][repeat] = exits;
				for (double time = paso_simulacion; time < total_time; time += paso_simulacion) {

					exits += sim.simulate();

					if ((current_frame % cant_cuadros) == 0) {
						// se graba a archivo
						
						if (writeSimulationPositions) {
							sim.writeData();
						}
						long currentSystemTime = System.currentTimeMillis();
						long timeStep = currentSystemTime - previousTime;
						previousTime = currentSystemTime;

						System.out.printf("time=%g, prog= %g, remaining= %d seconds, procesing steptime= %d\n", time,
								time / total_time, 0, timeStep);


					}
					if (current_frame % cant_flow == 0) {		
						exitsData[current_frame_graph][0] = time;
						exitsData[current_frame_graph][repeat + 1] = exits;
						current_frame_graph++;
					}
					current_frame++;

				}

				

				long elapsedTime = System.currentTimeMillis() - timeStart;

				System.out.println("ElapsedTime: " + elapsedTime);
			}
			
			try {
				writer.closeWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			flowWriter.write(exitsData, flowRows, flowCols);
			flowWriter.closeWriter();
			//

		}
	}
}
