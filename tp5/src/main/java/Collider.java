

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Collider {

    private static final Vector2D xAxis = new Vector2D(1, 0);

    public static List<Particle> collisions(List<Particle> particles, double width, double height, double dStart, double d) {

        Particle[] particleVector = particles.toArray(new Particle[particles.size()]);

        int size = particleVector.length;

        for (int i = 0; i < size; i++) {
            Particle p1 = particleVector[i];

            if (p1.getPosition().getX() <= p1.getRadius() ||
                    (p1.getPosition().getX() + p1.getRadius()) >= width) {
                Vector2D velocity = p1.getVelocity();
                p1.setVelocity(new Vector2D(-velocity.getX(), velocity.getY()));
            }

            if ((p1.getPosition().getY() >= 0 && p1.getPosition().getY() <= p1.getRadius()) ||
                    (p1.getPosition().getY() + p1.getRadius()) >= height) {
                Vector2D velocity = p1.getVelocity();
                p1.setVelocity(new Vector2D(velocity.getX(), -velocity.getY()));
            }


            for (int j = i + 1; j < size; j++) {
                Particle p2 = particleVector[j];





//                double distance = p1.getPosition().distance(p2.getPosition());
//
//                if (distance < p1.getRadius() + p2.getRadius()) {
//                    double totalMass = p1.getMass() + p2.getMass();
//
//
//                    double massCenterX = (p1.getPosition().getX() * p1.getMass() + p2.getPosition().getX() * p2.getMass()) / (p1.getMass() + p2.getMass());
//                    double massCenterY = (p1.getPosition().getY() * p1.getMass() + p2.getPosition().getY() * p2.getMass()) / (p1.getMass() + p2.getMass());
//                    //	Vector2D massCenter = p2.getPosition();
//                    Vector2D massCenter = new Vector2D(massCenterX, massCenterY);
//
//                    double p1_angle = Vector2D.angle(p1.getPosition(), p1.getVelocity());
//                    double p2_angle = Vector2D.angle(p2.getPosition(), p2.getVelocity());
//
//                    double normP1Vel = p1.getVelocity().getNorm();
//                    double normP2Vel = p2.getVelocity().getNorm();
//
//                    double normP1Pos = p1.getPosition().getNorm();
//                    double normP2Pos = p2.getPosition().getNorm();
//
//                    double p1_radial_velocity = Math.cos(p1_angle) * normP1Vel;
//                    double p1_tan_velocity = Math.sin(p1_angle) * normP1Vel;
//
//                    double p2_radial_velocity = Math.cos(p2_angle) * normP2Vel;
//                    double p2_tan_velocity = Math.sin(p2_angle) * normP2Vel;
//
//
//                    double vf_radial = (p1.getMass() * p1_radial_velocity + p2.getMass() * p2_radial_velocity) / totalMass;
//
//                    double vf_tan = (p1.getMass() * p1_tan_velocity * normP1Pos
//                            + p2.getMass() * p2_tan_velocity * normP2Pos) / (totalMass * massCenter.getNorm());
//
//
//                    // r ^ eje X
//                    double r_angle = Vector2D.angle(massCenter, xAxis);
//
//                    if (massCenter.getY() < 0) {
//                        r_angle = -r_angle;
//                    }
//
//                    double xComponent = Math.cos(r_angle) * vf_radial + -Math.sin(r_angle) * vf_tan;
//                    double yComponent = Math.sin(r_angle) * vf_radial + Math.cos(r_angle) * vf_tan;
//
//                    p2.setVelocity(new Vector2D(xComponent, yComponent));
//
//                    //p2.setMass(totalMass);
//                    p2.setRadius(Math.sqrt(Math.pow(p1.getRadius(), 2) + Math.pow(p2.getRadius(), 2)));
//
//                    particleVector[i] = null;
//                    System.out.println("choco particula");
//
//                }
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