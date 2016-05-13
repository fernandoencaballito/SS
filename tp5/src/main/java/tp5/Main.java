package tp5;
import java.io.IOException;
import java.util.Locale;

public class Main {

    private static final double WIDTH = 2.0;
    private static final double HEIGHT = 10.0;
    private static final double D = 0.5;
    private static final int N = 500;
    private static final double DSTART = (WIDTH / 2.0) - (D / 2.0);
    private static final double KN=Math.pow(10.0, 5.0);
    private static final double KT=2.0*KN;
    private static final double DROP_DEPTH=5.0;//Profundidad que caen las particulas luego de salir del silo. A una profundida mayor, se pierda la particula


    private static final String  ENERGY_OUTPUT_FILE="system_energy.csv";

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
       DlmWriter energyDataWriter=null;
       double total_time = 60.0;
        double paso_simulacion = 0.00001;
        double paso_grafico = 0.1;
        int cant_cuadros=(int)Math.ceil( paso_grafico / paso_simulacion);
    	int current_frame=1;

        Locale.setDefault(new Locale("en", "US"));


        ParticleWriter writer = null;
        try {
        	String outputFilename=String.format("tp5-N=%d-deltaSim=%g.xyz",N,paso_simulacion);
            writer = new ParticleWriter(outputFilename);
            energyDataWriter=new DlmWriter(ENERGY_OUTPUT_FILE);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Integrator integrator = new VerletIntegrator();
        Simulation sim = new Simulation(integrator, paso_simulacion, writer, WIDTH, HEIGHT, D, DSTART, N, KN,KT,DROP_DEPTH);

        long timeStart = System.currentTimeMillis();

        sim.writeData();


        long previousTime=timeStart;
        for (double time = paso_simulacion; time<total_time; time+=paso_simulacion) {

            sim.simulate();

            if((current_frame%cant_cuadros)==0){
            	//se graba a archivo

            	sim.writeData();
            	long currentSystemTime=System.currentTimeMillis();
                //long elapsedTime = currentSystemTime - timeStart;
                long timeStep=currentSystemTime-previousTime;
                previousTime=currentSystemTime;


                System.out.printf("time=%g, prog= %g, remaining= %d seconds, procesing steptime= %d\n", time, time/total_time, 0,timeStep);

               	double totalKinetic=sim.getMeanKineticEnergy();
				double totalPotential=sim.getMeanPotentialEnergy();
				double totalEnergy=totalKinetic+totalPotential;
				double[][] energyData={{current_frame*paso_simulacion,totalKinetic,totalPotential,totalEnergy}};
				energyDataWriter.write(energyData, 1, 4);


            }
            current_frame++;

        }

        //sim.clean();//se liberan recursos


        long elapsedTime = System.currentTimeMillis() - timeStart;

        System.out.println("ElapsedTime: " + elapsedTime);

        energyDataWriter.closeWriter();


    }

}
