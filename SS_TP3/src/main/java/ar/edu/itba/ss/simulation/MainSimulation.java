package ar.edu.itba.ss.simulation;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.io.IOException;

public class MainSimulation {

    private static final double RECT_WIDTH = 1;
    private static final double RECT_HEIGHT = 0.5;

    private static final int PARTICLE_COUNT = 2000;

    private static final double[] BAR_HEIGTH = {0.05, 0.1, 0.2};

    private static final double TIME_LIMIT = 300;

    private static final double EPSILON = 0.0001;// TOLERANCIA empleada en el cálculo de posiciones cercanas a las paredes.


    public static void main(String[] args) {

        // for (double aBAR_HEIGTH : BAR_HEIGTH) {

        Wall[] walls = new Wall[4];
        Vector2D start = new Vector2D(0.2, 0.1);
        Vector2D end = new Vector2D(0.2, 0.4);
        walls[0] = new Wall(start, end, false);
        start = new Vector2D(0.2, 0.11);
        end = new Vector2D(0.2, 0.41);
        walls[1] = new Wall(start, end, false);
        start = new Vector2D(0.2, 0.1);
        end = new Vector2D(0.21, 0.11);
        walls[2] = new Wall(start, end, false);
        start = new Vector2D(0.2, 0.4);
        end = new Vector2D(0.21, 0.41);
        walls[3] = new Wall(start, end, false);

        Collision.setEpsilon(EPSILON);
        
        
        
        
        

        EventDrivenSimulation sim = new EventDrivenSimulation(RECT_WIDTH, RECT_HEIGHT, PARTICLE_COUNT, walls, EPSILON);// FIXME

        StateWriter writer = null;

        try {
            String name = sim.nameFromParams();

            System.out.printf("Guardando datos en: %s\n", name);

            writer = new StateWriter(name, 0.05);
        } catch (IOException e) {
            System.out.println("No se pudo escribir en el archivo de salida. Abortando!");
            return;
        }

        sim.setWriter(writer);


        sim.simulate(TIME_LIMIT);

        // }

    }
}
