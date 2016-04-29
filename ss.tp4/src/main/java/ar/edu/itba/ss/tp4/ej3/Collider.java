package ar.edu.itba.ss.tp4.ej3;

import java.util.ArrayList;
import java.util.List;

public class Collider {

	public static List<Particle> collisions(List<Particle> particles, Particle sun) {
		// TODO Auto-generated method stub

		List<Particle> newParticles = new ArrayList<Particle>();
		Particle p1, p2;
		int cant = particles.size();
		int collision_count=0;
		for (int i = 0; i < cant; i++) {
			p1 = particles.get(i);
			if(!p1.hasCollided()) {
				for (int j = i; j < cant; j++) {
					p2 = particles.get(j);
	
					if (!p2.hasCollided()&&p1.getPosition().subtract(p2.getPosition()).getNorm() < p1.getRadius() + p2.getRadius()) {
						
						
						
						newParticles.add(new Particle("", p1.getMass() + p2.getMass(),
								p1.getRadius() + p2.getRadius(), p1.getVelocity() ,
								p1.getPosition().scalarMultiply(p1.getMass())
										.add(p2.getPosition().scalarMultiply(p2.getMass()))
												.scalarMultiply(1.0 / (p1.getMass() + p2.getMass()))));
						collision_count ++;
					//	System.out.println(collision_count);
						p1.setCollided(true);
						p2.setCollided(true);
						break;
					}
				}
				if(!p1.hasCollided()) {
					newParticles.add(p1);
				}
			}
		}
		System.out.println(newParticles.size());
		return newParticles;
	}


}
