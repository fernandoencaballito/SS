package tp6;
import java.io.IOException;
import java.util.Locale;

public class Main {

    private static final double WIDTH = 20.0;
    private static final double HEIGHT = 20.0;
    private static final double D = 1.2;
    private static final int N = 10;
    private static final double DSTART = (WIDTH / 2.0) - (D / 2.0);
    private static final double KN=1.2*Math.pow(10.0, 5.0);
    private static final double KT=2.4*Math.pow(10.0, 5.0);
    private static final double DROP_DEPTH=0.5;//Profundidad que caen las particulas luego de salir del silo. A una profundida mayor, se pierda la particula


   

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
       DlmWriter energyDataWriter=null;
       DlmWriter flowWriter=null;
       double total_time = 50;
        double paso_simulacion = 0.00001;
        double paso_grafico = 0.1;
        int cant_cuadros=(int)Math.ceil( paso_grafico / paso_simulacion);
    	int current_frame=1;

        Locale.setDefault(new Locale("en", "US"));


        ParticleWriter writer = null;
        try {
        	String outputFilename=String.format("tp6_N=%d_deltaSim=%g_L=%g_D=%g.xyz",N,paso_simulacion,HEIGHT,D);
            writer = new ParticleWriter(outputFilename);
            
//             String  ENERGY_OUTPUT_FILE=String.format("system_energy_N=%d_deltaSim=%g_L=%g_D=%g.csv",N,paso_simulacion,HEIGHT,D);
//            energyDataWriter=new DlmWriter(ENERGY_OUTPUT_FILE);
//
//            String  FLOW_OUTPUT_FILE=String.format("FLOW_N=%d_deltaSim=%g_L=%g_D=%g.csv",N,paso_simulacion,HEIGHT,D);
//            flowWriter=new DlmWriter(FLOW_OUTPUT_FILE);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
        Integrator integrator = new VerletIntegrator();
        
        double DRIVING_VELOCITY=1.3;
        Simulation sim = new Simulation(integrator, paso_simulacion, writer, WIDTH, HEIGHT, D, DSTART, N, KN,KT,DROP_DEPTH,DRIVING_VELOCITY);

        long timeStart = System.currentTimeMillis();

        sim.writeData();


        long previousTime=timeStart;
        long currentFlow = 0;
        for (double time = paso_simulacion; time<total_time; time+=paso_simulacion) {

        	currentFlow+=sim.simulate();

            if((current_frame%cant_cuadros)==0){
            	//se graba a archivo
            	double avgFlow = ((double)currentFlow)/cant_cuadros;
            	currentFlow = 0;
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
				
				double[][] flowData={{current_frame*paso_simulacion,avgFlow}};
				flowWriter.write(flowData, 1, 2);

            }
            current_frame++;

        }

        sim.clean();//se liberan recursos


        long elapsedTime = System.currentTimeMillis() - timeStart;

        System.out.println("ElapsedTime: " + elapsedTime);

        energyDataWriter.closeWriter();
        flowWriter.closeWriter();
        

    }

}
