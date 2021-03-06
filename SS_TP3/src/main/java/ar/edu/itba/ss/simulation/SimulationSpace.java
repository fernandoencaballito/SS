package ar.edu.itba.ss.simulation;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;


public class SimulationSpace {
    private double EPSILON;//toleancia que se utiliza para ajustar las posiciones
    private double width;
    private double height;

    public Wall[] getWalls() {
        return walls;
    }

    private Wall[] walls;


    public SimulationSpace(double width, double height, Wall[] bars, double epsilon) {

        int len = 0;
        this.EPSILON = epsilon;
        this.height = height;
        this.width = width;

        if (bars != null) {
            len += bars.length;
        }
        double delta = 0.00001;
        walls = new Wall[len + 8];
        Vector2D[] corners = {new Vector2D(0, 0), new Vector2D(0, height), new Vector2D(width, height), new Vector2D(width, 0)};
        Vector2D[] corners2 = {new Vector2D(delta, 0.005), new Vector2D(delta, height-delta), new Vector2D(width-delta, height-delta), new Vector2D(width-delta, delta)};
        walls[0] = new Wall(corners[0], corners[1], false);
        walls[1] = new Wall(corners[1], corners[2], false);
        walls[2] = new Wall(corners[3], corners[2], true);
        walls[3] = new Wall(corners[0], corners[3], false);
        walls[4] = new Wall(corners2[0], corners2[1], false);
        walls[5] = new Wall(corners2[1], corners2[2], false);
        walls[6] = new Wall(corners2[3], corners2[2], true);
        walls[7] = new Wall(corners2[0], corners2[3], false);


        for (int i = 0; i < len; i++) {
            walls[i + 8] = bars[i];
        }
    }

    public void reinsert(Particle particle) {
        particle.setPosition(ParticleSet.randomReturnPos(height, width));
        particle.setVelocity(new Vector2D(0.05, 0));//segun el enunciado, entran con vx=0.05 y vs=0;
        particle.incrementCollisionCount();

    }

    public Vector2D truncatePosition(double x_pos, double y_pos) {

        double x_pos_truncated = truncateValue(x_pos, width, 0);
        double y_pos_truncated = truncateValue(y_pos, height, 0);

        return new Vector2D(x_pos_truncated, y_pos_truncated);
    }

    private double truncateValue(double value, double max, double min) {

//		double diff_max=value-max;
//		double diff_min=min-value;
//		
//		if(0<diff_max && diff_max<EPSILON){
//			return max;
//		}else if(0<diff_min && diff_min<EPSILON){
//			
//			return min;
//		}
//		
//		return value;

        if (value > max)
            return max ;
        else if (value < min)
            return min ;


        return value;


    }

	public double getWidth() {

		return this.width;
	}
	public double getHeight() {

		return height;
	}

}
