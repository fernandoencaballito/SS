package ar.edu.itba.ss.tp4.ej3;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Collider {

	public static List<Particle> collisions(List<Particle> particles, final Particle sun) {
		// TODO Auto-generated method stub

		final Iterator<Particle> it = particles.iterator();

		while (it.hasNext()) {
			final Particle next = it.next();
			double distanceSun = next.getPosition().getNorm();
			//System.out.println(distanceSun);
			if (distanceSun < sun.getRadius()) {
				System.out.println("sun crash");
				sun.setMass(sun.getMass()+next.getMass());
				it.remove();
				break;
			}
			if(distanceSun > Math.pow(10,20)) {
				//System.out.println("a la mierda");
				it.remove();
				break;
			}
			it.forEachRemaining(new Consumer<Particle>() {

				public void accept(Particle t) {


					double distance = next.getPosition().distance(t.getPosition());
				//	System.out.println(distance);

					if (distance < next.getRadius() + t.getRadius()) {
					//	System.out.println("particulas crash");
						t.setMass(t.getMass() + next.getMass());
						//t.setRadius(Math.sqrt(Math.pow(t.getRadius(), 2) + Math.pow(next.getRadius(), 2)));
						Vector2D massCenter = next.getPosition() ;
						t.setPosition(next.getPosition());
						double next_angle = Vector2D.angle(next.getPosition(), next.getVelocity());
						double t_angle = Vector2D.angle(t.getPosition(), t.getVelocity());

						double next_radial_velocity = Math.cos(next_angle) * next.getVelocity().getNorm();
						double next_tan_velocity = Math.sin(next_angle) * next.getVelocity().getNorm();

						double t_radial_velocity = Math.cos(t_angle) * t.getVelocity().getNorm();
						double t_tan_velocity = Math.sin(t_angle) * t.getVelocity().getNorm();

						// t.mass * t.vel_r + next.mass * next.vell_r =
						// (next.mass + t.mass)*vf_r

						double vf_radial = (t.getMass() * t_radial_velocity + next.getMass() * next_radial_velocity)
								/ (next.getMass() + t.getMass());

						// m1 * v1 * r1 + m2 * v2 * r2 = (m1+m2) * rf * vf;

						double vf_tan = (t.getMass() * t_tan_velocity * t.getPosition().getNorm()
								+ next.getMass() * next_tan_velocity * next.getPosition().getNorm())
								/ ((t.getMass() + next.getMass()) * massCenter.getNorm());

						double r_angle = Vector2D.angle(massCenter, new Vector2D(1,0));

						Vector2D radialVelocity = new Vector2D(Math.cos(r_angle) * vf_radial,
								Math.sin(r_angle) * vf_radial);

						Vector2D tanVelocity = new Vector2D(Math.cos(r_angle - Math.PI / 2) * vf_tan,
								Math.cos(t_angle - Math.PI / 2) * vf_tan);

						t.setVelocity(radialVelocity.add(tanVelocity));
						//System.out.println("borre");
						it.remove();
					}

				}

			});

		}

		return particles;

		// List<Particle> newParticles = new ArrayList<Particle>();
		// Particle p1, p2;
		// int cant = particles.size();
		// int collision_count=0;
		// for (int i = 0; i < cant; i++) {
		// p1 = particles.get(i);
		// if(!p1.hasCollided()) {
		// for (int j = i; j < cant; j++) {
		// p2 = particles.get(j);
		//
		// if
		// (!p2.hasCollided()&&p1.getPosition().subtract(p2.getPosition()).getNorm()
		// < p1.getRadius() + p2.getRadius()) {
		//
		//
		//
		// newParticles.add(new Particle("", p1.getMass() + p2.getMass(),
		// p1.getRadius() + p2.getRadius(), p1.getVelocity() ,
		// p1.getPosition().scalarMultiply(p1.getMass())
		// .add(p2.getPosition().scalarMultiply(p2.getMass()))
		// .scalarMultiply(1.0 / (p1.getMass() + p2.getMass()))));
		// collision_count ++;
		// // System.out.println(collision_count);
		// p1.setCollided(true);
		// p2.setCollided(true);
		// break;
		// }
		// }
		// if(!p1.hasCollided()) {
		// newParticles.add(p1);
		// }
		// }
		// }
		// System.out.println(newParticles.size());
		// return newParticles;
	}

}
