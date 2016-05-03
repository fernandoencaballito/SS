package ar.edu.itba.ss.tp4.ej3;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        long time = System.currentTimeMillis();

        ParticleWriter writer = null;
        try {
            writer = new ParticleWriter("solarSystemBeeman");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integrator integrator = new BeemanIntegrator();
        Simulation sim = new Simulation(integrator, 1.0, writer);

        long timeStart = System.currentTimeMillis();

        int STEPS = 10000;//deberia valer 1000000

        for (int i = 0; i < STEPS; i++) {
            if (i % 100 == 0 && i>0) {
                long elapsedTime = System.currentTimeMillis() - timeStart;
                System.out.printf("i=%d, prog= %g, remaining= %d seconds\n", i, (double) i / STEPS, ((elapsedTime / i) * (STEPS - i))/1000);
            }
            sim.simulate(i);
        }

        sim.clean();//se liberan recursos
        time = System.currentTimeMillis() - time;

        System.out.println("Time: " + time);

    }

}
