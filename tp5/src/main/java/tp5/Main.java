package tp5;
import java.io.IOException;
import java.util.Locale;

public class Main {

    private static final double WIDTH = 2.0;
    private static final double HEIGHT = 10.0;
    private static final double D = 0.5;
    private static final int N = 100;
    private static final double DSTART = (WIDTH / 2.0) - (D / 2.0);
    private static final double KN=Math.pow(10.0, 5.0);
    private static final double KT=2.0*KN;
    private static final double DROP_DEPTH=5.0;//Profundidad que caen las particulas luego de salir del silo. A una profundida mayor, se pierda la particula


    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	double total_time=10.0;
    	double paso_simulacion=0.0001;
    	double paso_grafico=0.01;
    	int cant_cuadros=(int)Math.ceil( paso_grafico / paso_simulacion);
    	int current_frame=1;

        Locale.setDefault(new Locale("en", "US"));


        ParticleWriter writer = null;
        try {
            writer = new ParticleWriter("tp5.xyz");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integrator integrator = new VerletIntegrator();
        Simulation sim = new Simulation(integrator, paso_simulacion, writer, WIDTH, HEIGHT, D, DSTART, N, KN,KT,DROP_DEPTH);

        long timeStart = System.currentTimeMillis();

        sim.writeData();

        for (double time = paso_simulacion; time<total_time; time+=paso_simulacion) {

            sim.simulate();

            if((current_frame%cant_cuadros)==0){
            	//se graba a archivo

            	sim.writeData();
                long elapsedTime = System.currentTimeMillis() - timeStart;
                System.out.printf("time=%g, prog= %g, remaining= %d seconds\n", time, time/total_time, 0);

            }
            current_frame++;

        }

        //sim.clean();//se liberan recursos
        long elapsedTime = System.currentTimeMillis() - timeStart;

        System.out.println("ElapsedTime: " + elapsedTime);

    }

}
