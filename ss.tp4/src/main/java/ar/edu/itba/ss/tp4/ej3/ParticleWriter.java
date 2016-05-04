package ar.edu.itba.ss.tp4.ej3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class ParticleWriter {

	private BufferedWriter writer;

	public ParticleWriter(String fileName) throws IOException {
		writer = new BufferedWriter(new FileWriter(fileName));

	}

	public void write(Double t, Particle sun, List<Particle> particles) throws IOException {

		//System.out.println("writing");
		writer.write(String.format("%d\nTime = %g\n", particles.size() + 1, t));
		writer.write(formatParticle(sun));

		for (Particle particle : particles) {
			writer.write(formatParticle(particle));
		}
		writer.flush();

	}

	private static String formatParticle(Particle p) {

		Vector2D pos = p.getPosition();
		// id posX posY mass radius
		return String.format("%s %f %f %f %f\n", p.getId(), pos.getX(), pos.getY(), p.getMass(), p.getRadius());
	}

	public void closeWriter() throws IOException {
		writer.close();
	}

}
