import java.io.IOException;

public class Main {

    private static final double WIDTH = 2.0;
    private static final double HEIGHT = 10.0;
    private static final double D = 0.5;
    private static final int N = 10;
    private static final double DSTART = (WIDTH / 2) - (D / 2);


    public static void main(String[] args) {
        // TODO Auto-generated method stub

        long time = System.currentTimeMillis();


        ParticleWriter writer = null;
        try {
            writer = new ParticleWriter("tp5.xyz");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integrator integrator = new BeemanIntegrator();
        Simulation sim = new Simulation(integrator, 0.1, writer, WIDTH, HEIGHT, D, DSTART, N);

        long timeStart = System.currentTimeMillis();

        int STEPS = 1;

        for (int i = 0; i < STEPS; i++) {
            if (i % 1 == 0 && i > 0) {
                long elapsedTime = System.currentTimeMillis() - timeStart;
                System.out.printf("i=%d, prog= %g, remaining= %d seconds\n", i, (double) i / STEPS, ((elapsedTime / i) * (STEPS - i)) / 1000);
            }
            sim.simulate();
        }

        //sim.clean();//se liberan recursos
        time = System.currentTimeMillis() - time;

        System.out.println("Time: " + time);

    }

}
