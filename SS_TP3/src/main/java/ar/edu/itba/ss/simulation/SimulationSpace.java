package ar.edu.itba.ss.simulation;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;


public class SimulationSpace {


    public Wall[] getWalls() {
        return walls;
    }

    private Wall[] walls;

    public SimulationSpace(double width, double height, Wall[] bars) {

        int len = 0;

        if (bars != null) {
            len += bars.length;
        }

        walls = new Wall[len + 4];
        Vector2D[] corners = {new Vector2D(0, 0), new Vector2D(0, height), new Vector2D(width, height), new Vector2D(width, 0)};

        walls[0] = new Wall(corners[0], corners[1]);
        walls[1] = new Wall(corners[1], corners[2]);
        walls[2] = new Wall(corners[3], corners[2]);
        walls[3] = new Wall(corners[0], corners[3]);


        for (int i = 4; i < len; i++) {
            walls[i] = bars[i - 4];
        }
    }


}
