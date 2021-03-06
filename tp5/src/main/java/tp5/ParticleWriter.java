package tp5;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ParticleWriter {

    private BufferedWriter writer;

    public ParticleWriter(String fileName) throws IOException {
        writer = new BufferedWriter(new FileWriter(fileName));

    }

    public void write(double t, List<Particle> particles) throws IOException {

        //System.out.println("writing");
        writer.write(String.format("%d\nTime = %g\n", particles.size(), t));

        for (Particle particle : particles) {
            writer.write(formatParticle(particle));
        }
        //writer.flush();

    }

    private static String formatParticle(Particle p) {

        Vector2D pos = p.getPosition();
        Vector2D vel = p.getVelocity();
        // id posX posY mass radius
        return String.format("%s %f %f %f %f %f %f\n", p.getId(), pos.getX(), pos.getY(), p.getMass(), p.getRadius(), vel.getX(), vel.getY());
    }

    public void closeWriter() throws IOException {
        writer.close();
    }

}
