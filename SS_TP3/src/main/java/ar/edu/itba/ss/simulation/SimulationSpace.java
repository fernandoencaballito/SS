package ar.edu.itba.ss.simulation;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;



public class SimulationSpace {

	
	public Wall[] getWalls() {
		return walls;
	}

	private Wall[] walls;

	public SimulationSpace(double width, double height,Wall[] bars) {
		
		walls = new Wall[4+bars.length];
		Vector2D[] corners = {new Vector2D(0,0),new Vector2D(0,height),new Vector2D(width,height), new Vector2D(width,0)}; 
		
		
		
		walls[0] = new Wall(corners[0], corners[1]);
		walls[1] = new Wall(corners[1], corners[2]);
		walls[2] = new Wall(corners[2], corners[3]);
		walls[3] = new Wall(corners[3], corners[0]);
		
		
		for (int i = 4; i < bars.length; i++) {
			walls[i] = bars[i-4];
		}
		
		
	}

	
	
	
	
}
