package ar.edu.itba.ss.tp4.ej3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ParticleWriter {

	private BufferedWriter writer;

	public ParticleWriter(String fileName) throws IOException {
		writer = new BufferedWriter(new FileWriter(fileName));

	}

	public void write(Double t, Particle sun, List<Particle> particles) throws IOException {


		writer.write((particles.size() + 1) + "\n" + "Time =" + t + "\n");
		writer.write("" + sun.getPosition().getX() + " " + sun.getPosition().getY() + " " + sun.getVelocity().getX()  + " " + sun.getVelocity().getY() + " " +sun.getMass() + "\n");
		for (Particle particle : particles) {
			writer.write("" + particle.getPosition().getX() + " " + particle.getPosition().getY() + " "
					+ particle.getVelocity().getX()  + " " + particle.getVelocity().getY() + " " + particle.getMass() + "\n");
		}
		writer.flush();

	}

	public void closeWriter() throws IOException {
		writer.close();
	}

}
