
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.ml.distance.DistanceMeasure;

import java.util.ArrayList;
import java.util.List;

public class Collider {

	private static final Vector2D xAxis = new Vector2D(1, 0);

	public static List<Particle> collisions(List<Particle> particles, double width, double height, double dStart,
			double d,double k_n,double k_t) {

		Particle[] particleVector = particles.toArray(new Particle[particles.size()]);

		int size = particleVector.length;

		for (int i = 0; i < size; i++) {
			Particle p1 = particleVector[i];

			Vector2D position1 = p1.getPosition();

			// choque paredes verticales
			if ((position1.getX() <= p1.getRadius() && p1.getVelocity().getX() < 0)
					|| (position1.getX() + p1.getRadius()) >= width && p1.getVelocity().getX() > 0) {
				Vector2D velocity = p1.getVelocity();
				p1.setVelocity(new Vector2D(-velocity.getX(), velocity.getY()));
			}

			boolean outsideHole = position1.getX() < dStart || position1.getX() > (dStart + d);

			// choques paredes horizontales
			if ((position1.getY() >= 0 && position1.getY() <= p1.getRadius() && outsideHole
					&& p1.getVelocity().getY() < 0) || (position1.getY() + p1.getRadius()) >= height) {
				Vector2D velocity = p1.getVelocity();
				p1.setVelocity(new Vector2D(velocity.getX(), -velocity.getY()));
			}

			// choques con otras particulas
			for (int j = i + 1; j < size; j++) {
				Particle p2 = particleVector[j];
				Vector2D position2=p2.getPosition();
				double centerDistance=Math.abs(position1.distance(position2));
				double superposition = p1.getRadius() + p2.getRadius() - centerDistance;

				// colisión entre particulas
				if (superposition > 0.0) {
					Vector2D positionDifference=position2.subtract(position1);
					double enx=positionDifference.getX()/centerDistance;
					double eny=positionDifference.getY()/centerDistance;
					
					double f_n=-k_n*superposition;
					
					Vector2D rRel=p2.getVelocity().subtract(p1.getVelocity());
					Vector2D tangencialVersor=new Vector2D(-eny	, enx);
					double f_t=-k_t*superposition*(rRel.dotProduct(tangencialVersor));
					
					
					
					
					
					double f_x=f_n*enx +f_t*(-eny);
					double f_y=f_n*eny+ f_t*enx;
					
					p2.addForce(f_x, f_y);
					p1.addForce(-f_x, -f_y);
					

				}

				// double distance =
				// p1.getPosition().distance(p2.getPosition());
				//
				// if (distance < p1.getRadius() + p2.getRadius()) {
				// double totalMass = p1.getMass() + p2.getMass();
				//
				//
				// double massCenterX = (p1.getPosition().getX() * p1.getMass()
				// + p2.getPosition().getX() * p2.getMass()) / (p1.getMass() +
				// p2.getMass());
				// double massCenterY = (p1.getPosition().getY() * p1.getMass()
				// + p2.getPosition().getY() * p2.getMass()) / (p1.getMass() +
				// p2.getMass());
				// // Vector2D massCenter = p2.getPosition();
				// Vector2D massCenter = new Vector2D(massCenterX, massCenterY);
				//
				// double p1_angle = Vector2D.angle(p1.getPosition(),
				// p1.getVelocity());
				// double p2_angle = Vector2D.angle(p2.getPosition(),
				// p2.getVelocity());
				//
				// double normP1Vel = p1.getVelocity().getNorm();
				// double normP2Vel = p2.getVelocity().getNorm();
				//
				// double normP1Pos = p1.getPosition().getNorm();
				// double normP2Pos = p2.getPosition().getNorm();
				//
				// double p1_radial_velocity = Math.cos(p1_angle) * normP1Vel;
				// double p1_tan_velocity = Math.sin(p1_angle) * normP1Vel;
				//
				// double p2_radial_velocity = Math.cos(p2_angle) * normP2Vel;
				// double p2_tan_velocity = Math.sin(p2_angle) * normP2Vel;
				//
				//
				// double vf_radial = (p1.getMass() * p1_radial_velocity +
				// p2.getMass() * p2_radial_velocity) / totalMass;
				//
				// double vf_tan = (p1.getMass() * p1_tan_velocity * normP1Pos
				// + p2.getMass() * p2_tan_velocity * normP2Pos) / (totalMass *
				// massCenter.getNorm());
				//
				//
				// // r ^ eje X
				// double r_angle = Vector2D.angle(massCenter, xAxis);
				//
				// if (massCenter.getY() < 0) {
				// r_angle = -r_angle;
				// }
				//
				// double xComponent = Math.cos(r_angle) * vf_radial +
				// -Math.sin(r_angle) * vf_tan;
				// double yComponent = Math.sin(r_angle) * vf_radial +
				// Math.cos(r_angle) * vf_tan;
				//
				// p2.setVelocity(new Vector2D(xComponent, yComponent));
				//
				// //p2.setMass(totalMass);
				// p2.setRadius(Math.sqrt(Math.pow(p1.getRadius(), 2) +
				// Math.pow(p2.getRadius(), 2)));
				//
				// particleVector[i] = null;
				// System.out.println("choco particula");
				//
				// }
			}
		}

		List<Particle> particleList = new ArrayList<Particle>(size);

		for (int i = 0; i < particleVector.length; i++) {
			if (particleVector[i] != null)
				particleList.add(particleVector[i]);
		}

		return particleList;
	}

}