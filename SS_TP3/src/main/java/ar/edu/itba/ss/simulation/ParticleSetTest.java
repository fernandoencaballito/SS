package ar.edu.itba.ss.simulation;

import static org.junit.Assert.*;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Test;

public class ParticleSetTest {

	private final static double  height=0.5;
	private final static double width=1;
	
	@Test
	public void testRandomPos(){
		
		for(int i=0;i<10000;i++){
			
			Vector2D newPos=ParticleSet.randomReturnPos(height, width);
			double x_pos=newPos.getX();
			double y_pos=newPos.getY();
			
			assertTrue(!(x_pos>width || x_pos<0));
			assertTrue(!(y_pos>height || y_pos<0));
			
		}
		
	}
	
	
	
}
