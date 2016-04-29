package ar.edu.itba.ss.tp4.ej3;

import java.util.List;

public interface Integrator {

	public void next(Particle particle, List<Particle> particles, Particle sun,Double dt);

}
