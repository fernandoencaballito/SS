package ar.edu.itba.ss.simulation;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Segment;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Wall {

		public static final double TOLERANCE = 0.0001;
		private boolean periodic;


		public boolean isPeriodic() {
			return periodic;
		}


		public Segment getSegment() {
			return segment;
		}


		private double length;

		private Segment segment;
		
		public double getLength() {
			return length;
		}

	
		public Wall(Vector2D start,Vector2D end,boolean periodic) {

			Line l = new Line(start,end,TOLERANCE);
			this.segment = new Segment(start,end,l);
			this.length= end.subtract(start).getNorm();
			this.periodic=periodic;
		}
		
		public Line getLine() {
			return segment.getLine();
		}
		public Vector2D getStart() {
			return segment.getStart();
		}
		public Vector2D getEnd() {
			return segment.getEnd();
		}


	public double getAngle() {
		return segment.getLine().getAngle();
	}
}
