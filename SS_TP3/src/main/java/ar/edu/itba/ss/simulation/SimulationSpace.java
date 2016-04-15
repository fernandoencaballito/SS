package ar.edu.itba.ss.simulation;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;


public class SimulationSpace {

	private double width;
	private double height;

    public Wall[] getWalls() {
        return walls;
    }

    private Wall[] walls;

    public SimulationSpace(double width, double height, Wall[] bars) {

        int len = 0;

        this.height=height;
        this.width=width;
        
        if (bars != null) {
            len += bars.length;
        }

        walls = new Wall[len + 4];
        Vector2D[] corners = {new Vector2D(0, 0), new Vector2D(0, height), new Vector2D(width, height), new Vector2D(width, 0)};

        walls[0] = new Wall(corners[0], corners[1],false);
        walls[1] = new Wall(corners[1], corners[2],false);
        walls[2] = new Wall(corners[3], corners[2],true);
        walls[3] = new Wall(corners[0], corners[3],false);


        for (int i = 0; i < len; i++) {
            walls[i+4] = bars[i];
        }
    }

	public void reinsert(Particle particle) {
		particle.setPosition(ParticleSet.randomReturnPos(width, height));
		particle.setVelocity(ParticleSet.randomVelocity(height, width));
		
	}


}
