package ar.edu.itba.ss.simulation;

import static org.junit.Assert.assertEquals;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Test;

public class SimulationSpaceTest {

	
	@Test
	public void testTruncate(){
		double eps=0.01;
		SimulationSpace space=new SimulationSpace(1.0, 0.5, new Wall[0],eps);
		
		double x_pos=-0.0022477579013535365;
		double y_pos=5.922504832346004E-4;
		
		Vector2D truncated=space.truncatePosition(x_pos, y_pos);
		
		double x_truncated=truncated.getX();
		double y_truncated=truncated.getY();
		
		assertEquals(0.0,x_truncated,eps);
		assertEquals(y_pos, y_truncated,eps);
		
		
		
		
	}
}
