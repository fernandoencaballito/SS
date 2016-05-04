package ar.edu.itba.ss.tp4.ej3;


import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

public class Collider {

    private static final Vector2D xAxis =  new Vector2D(1, 0);

	public static List<Particle> collisions(List<Particle> particles, final Particle sun) {

		Particle[] particleVector = particles.toArray(new Particle[particles.size()]);

		int size = particleVector.length;

		for (int i = 0; i < size; i++) {
			Particle p1 = particleVector[i];

			if (p1.getPosition().getNorm() < sun.getRadius()) {
				System.out.println("choco sol");
				particleVector[i] = null;
				continue;
			}

			for (int j = i + 1; j < size; j++) {
				Particle p2 = particleVector[j];

				double distance = p1.getPosition().distance(p2.getPosition());

				if (distance < p1.getRadius() + p2.getRadius()) {
					double totalMass = p1.getMass() + p2.getMass();


					double massCenterX = (p1.getPosition().getX() * p1.getMass() + p2.getPosition().getX() * p2.getMass())/(p1.getMass() + p2.getMass());
					double massCenterY = (p1.getPosition().getY() * p1.getMass() + p2.getPosition().getY() * p2.getMass())/(p1.getMass() + p2.getMass());
				//	Vector2D massCenter = p2.getPosition();
					Vector2D massCenter = new Vector2D(massCenterX, massCenterY);

					double p1_angle = Vector2D.angle(p1.getPosition(), p1.getVelocity());
					double p2_angle = Vector2D.angle(p2.getPosition(), p2.getVelocity());

					double normP1Vel = p1.getVelocity().getNorm();
					double normP2Vel = p2.getVelocity().getNorm();

					double normP1Pos = p1.getPosition().getNorm();
					double normP2Pos = p2.getPosition().getNorm();

					double p1_radial_velocity = Math.cos(p1_angle) * normP1Vel;
					double p1_tan_velocity = Math.sin(p1_angle) * normP1Vel;

					double p2_radial_velocity = Math.cos(p2_angle) * normP2Vel;
					double p2_tan_velocity = Math.sin(p2_angle) * normP2Vel;

					
	
					double vf_radial = (p1.getMass() * p1_radial_velocity + p2.getMass() * p2_radial_velocity)/ totalMass;

					double vf_tan = (p1.getMass() * p1_tan_velocity * normP1Pos
							+ p2.getMass() * p2_tan_velocity * normP2Pos) / (totalMass * massCenter.getNorm());
			
					
                    // r ^ eje X
                    double r_angle = Vector2D.angle(massCenter, xAxis);

                    if(massCenter.getY() < 0){
                        r_angle = -r_angle;
                    }

                    double xComponent = Math.cos(r_angle) * vf_radial+-Math.sin(r_angle) * vf_tan;
                    double yComponent = Math.sin(r_angle) * vf_radial+Math.cos(r_angle) * vf_tan;

					p2.setVelocity(new Vector2D(xComponent, yComponent));

					p2.setMass(totalMass);
					p2.setRadius(Math.sqrt(Math.pow(p1.getRadius(), 2) + Math.pow(p2.getRadius(), 2)));

					particleVector[i] = null;
					System.out.println("choco particula");

				}
			}
		}

		List<Particle> particleList = new ArrayList<Particle>(size);

		for (int i = 0; i < particleVector.length; i++) {
			if (particleVector[i] != null)
				particleList.add(particleVector[i]);
		}

		return particleList;

		//
		//
		//
		// for (Particle particle : particles) {
		// if(particle.getPosition().getNorm() >= sun.getRadius()) {
		// particles2.add(particle);
		//
		// }
		// else {
		// sun.setMass(sun.getMass() + particle.getMass());
		// }
		// }
		//
		// List<Particle> particles3 = new ArrayList<Particle>();
		//
		// for (Particle particlein : particles2) {
		// for (Particle particleout : particles2) {
		//
		// if(particlein==particleout)
		// continue;
		//
		// double distance =
		// particleout.getPosition().distance(particlein.getPosition());
		//
		// if(distance<Math.pow(10, 6)){
		//
		// }
		//
		// }
		// }
		//
		//
		// return particles2;

		// TODO Auto-generated method stub

		// final Iterator<Particle> it = particles.iterator();
		//
		// while (it.hasNext()) {
		// final Particle next = it.next();
		// double distanceSun = next.getPosition().getNorm();
		//
		// if (distanceSun < 2*sun.getRadius()) {
		// System.out.println("sun crash");
		// sun.setMass(sun.getMass()+next.getMass());
		// it.remove();
		// break;
		// }
		// if(distanceSun > Math.pow(10,20)) {
		// //System.out.println("a la mierda");
		// it.remove();
		// break;
		// }
		// it.forEachRemaining(new Consumer<Particle>() {
		//
		// public void accept(Particle t) {
		//
		//
		// double distance = next.getPosition().distance(t.getPosition());
		// // System.out.println(distance);
		//
		// if (distance < next.getRadius() + t.getRadius()) {
		// // System.out.println("particulas crash");
		// t.setMass(t.getMass() + next.getMass());
		// //t.setRadius(Math.sqrt(Math.pow(t.getRadius(), 2) +
		// Math.pow(next.getRadius(), 2)));
		// Vector2D massCenter = next.getPosition() ;
		// t.setPosition(next.getPosition());
		// double next_angle = Vector2D.angle(next.getPosition(),
		// next.getVelocity());
		// double t_angle = Vector2D.angle(t.getPosition(), t.getVelocity());
		//
		// double next_radial_velocity = Math.cos(next_angle) *
		// next.getVelocity().getNorm();
		// double next_tan_velocity = Math.sin(next_angle) *
		// next.getVelocity().getNorm();
		//
		// double t_radial_velocity = Math.cos(t_angle) *
		// t.getVelocity().getNorm();
		// double t_tan_velocity = Math.sin(t_angle) *
		// t.getVelocity().getNorm();
		//
		// // t.mass * t.vel_r + next.mass * next.vell_r =
		// // (next.mass + t.mass)*vf_r
		//
		// double vf_radial = (t.getMass() * t_radial_velocity + next.getMass()
		// * next_radial_velocity)
		// / (next.getMass() + t.getMass());
		//
		// // m1 * v1 * r1 + m2 * v2 * r2 = (m1+m2) * rf * vf;
		//
		// double vf_tan = (t.getMass() * t_tan_velocity *
		// t.getPosition().getNorm()
		// + next.getMass() * next_tan_velocity * next.getPosition().getNorm())
		// / ((t.getMass() + next.getMass()) * massCenter.getNorm());
		//
		// double r_angle = Vector2D.angle(massCenter, new Vector2D(1,0));
		//
		// Vector2D radialVelocity = new Vector2D(Math.cos(r_angle) * vf_radial,
		// Math.sin(r_angle) * vf_radial);
		//
		// Vector2D tanVelocity = new Vector2D(Math.cos(r_angle - Math.PI / 2) *
		// vf_tan,
		// Math.cos(t_angle - Math.PI / 2) * vf_tan);
		//
		// t.setVelocity(radialVelocity.add(tanVelocity));
		// //System.out.println("borre");
		// it.remove();
		// }
		//
		// }
		//
		// });
		//
		// }
		//
		// return particles;

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