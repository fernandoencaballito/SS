package ar.edu.itba.ss.simulation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class StateWriter {
	private BufferedWriter writer;

	public StateWriter(String fileName) throws IOException {

		writer = new BufferedWriter(new FileWriter(fileName));

	}

	// formato de output:
	// N
	// tiempo
	// X Y VEL_X VEL_Y RAD moduloVelocidad
	public void writeParticles(ParticleSet particles, long time) throws IOException {
		StringBuffer buffer = new StringBuffer();
		buffer.append(particles.size());
		buffer.append("\n");

		buffer.append("Time=");
		buffer.append(time);
		buffer.append("\n");

		for (Particle p : particles) {
				Vector2D pos=p.getPosition();
				buffer.append(pos.getX());
				buffer.append(" ");
				buffer.append(pos.getY());
				
				buffer.append(" ");
				Vector2D v=p.getVelocity();
				buffer.append(v.getX());
				buffer.append(" ");
				buffer.append(v.getY());
				buffer.append(" ");
				
				double radio=p.getRadius();
				buffer.append(radio);
				buffer.append(" ");
				
				buffer.append(v.getNorm());
				
				buffer.append("\n");
		}
		
		writer.write(buffer.toString());
		writer.flush();
	}
	
	
	public void closeWriter() throws IOException{
		writer.close();
	}

}
