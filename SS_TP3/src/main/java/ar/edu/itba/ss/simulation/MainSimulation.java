package ar.edu.itba.ss.simulation;

import java.io.IOException;

public class MainSimulation {

    private static final double RECT_WIDTH = 1;
    private static final double RECT_HEIGHT = 0.5;

    private static final int PARTICLE_COUNT = 1000;

    private static final double[] BAR_HEIGTH = {0.05, 0.1, 0.2};


    public static void main(String[] args) {


      //  for (double aBAR_HEIGTH : BAR_HEIGTH) {

            EventDrivenSimulation sim = new EventDrivenSimulation(RECT_WIDTH, RECT_HEIGHT, PARTICLE_COUNT, null);//FIXME

            StateWriter writer = null;

            try {
                String name = sim.nameFromParams();

                System.out.printf("Guardando datos en: %s\n", name);

                writer = new StateWriter(name, 1);
            } catch (IOException e) {
                System.out.println("No se pudo escribir en el archivo de salida. Abortando!");
                return;
            }

            sim.setWriter(writer);

            sim.simulate(100.0f);


   //     }


    }
}
