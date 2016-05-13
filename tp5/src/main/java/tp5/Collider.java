package tp5;

import cellIndexMethod.CellIndexMethod;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Collider {

    private static final Vector2D xAxis = new Vector2D(1, 0);

    public static List<Particle> collisions(List<Particle> particles, double width, double height, double dStart,
                                            double d, double k_n, double k_t,
                                            double drop_depth, CellIndexMethod cim) {

        Particle[] particleVector = particles.toArray(new Particle[particles.size()]);

        int size = particleVector.length;

        for (int i = 0; i < size; i++) {
            Particle p1 = particleVector[i];

            Vector2D position1 = p1.getPosition();

            Vector2D velocity1 = p1.getVelocity();


            // choque paredes vertical derecha
            if ((position1.getX() + p1.getRadius()) >= width) {

                p1.addForce(getWallForce(p1, k_n, k_t, WallDirection.RIGHT, width));

            }//pared vertical izquierda
            else if (position1.getX() <= p1.getRadius()) {
                p1.addForce(getWallForce(p1, k_n, k_t, WallDirection.LEFT, 0));
            }


            boolean outsideHole = position1.getX() < dStart || position1.getX() > (dStart + d);

            // choques pared horizontal inferior
            if (position1.getY() >= 0 && position1.getY() <= p1.getRadius() && outsideHole
                    && p1.getVelocity().getY() < 0) {
                p1.addForce(getWallForce(p1, k_n, k_t, WallDirection.BOTTOM, 0));


            }//choque pared horizontal superior
            else if ((position1.getY() + p1.getRadius()) >= height) {
                p1.addForce(getWallForce(p1, k_n, k_t, WallDirection.UPPER, height));
            }


            List<Particle> neighbors = cim.getNeighbors(p1);

            for (Particle p2 : neighbors) {
                Vector2D position2 = p2.getPosition();
                double centerDistance = position1.distance(position2);
                double superposition = p1.getRadius() + p2.getRadius() - centerDistance;

                // colisión entre 2 particulas
                if (superposition > 0.0) {
                    Vector2D positionDifference = position2.subtract(position1);
                    double enx = positionDifference.getX() / centerDistance;
                    double eny = positionDifference.getY() / centerDistance;

                    double f_n = k_n * superposition;

                    Vector2D rRel = p2.getVelocity().subtract(p1.getVelocity());
                    Vector2D tangencialVersor = new Vector2D(-eny, enx);
                    double f_t = -k_t * superposition * (rRel.dotProduct(tangencialVersor));


                    double f_x = f_n * enx + f_t * (-eny);
                    double f_y = f_n * eny + f_t * enx;

                    p2.addForce(f_x, f_y);
                    p1.addForce(-f_x, -f_y);


                }
            }


        }

        List<Particle> particleList = new ArrayList<Particle>(size);

        for (int i = 0; i < particleVector.length; i++) {
            Particle current = particleVector[i];
            if (current != null
                    && (current.getPosition().getY() > (-drop_depth)))
                particleList.add(particleVector[i]);
        }

        return particleList;
    }

    //devuelve "Enormal" y "Etangecial" para colisiones con paredes
    private static Vector2D[] getE(Vector2D velocity) {
        double alpha = Vector2D.angle(velocity, xAxis); //angulo respecto al eje x

        double enx = Math.cos(alpha);
        double eny = Math.sin(alpha);
        return new Vector2D[]{new Vector2D(enx, eny), new Vector2D(-eny, enx)};


    }

    private static Vector2D getWallForce(Particle p, double k_n, double k_t, WallDirection wallDirection, double wallPos) {

        Vector2D position1 = p.getPosition();
        Vector2D velocity1 = p.getVelocity();
        Vector2D eNormal;
        Vector2D eTangencial;
        double centerDistance = 0.0;


        if (wallDirection == WallDirection.BOTTOM || wallDirection == WallDirection.UPPER) {
            centerDistance = Math.abs(wallPos - position1.getY());


        } else if (wallDirection == WallDirection.LEFT || wallDirection == WallDirection.RIGHT) {
            centerDistance = Math.abs(wallPos - position1.getX());
        }

        double superposition = p.getRadius() - centerDistance;

        if (superposition > 0.0) {
            double f_n = k_n * superposition;//Formula N1
            double f_t;
            if (wallDirection == WallDirection.BOTTOM || wallDirection == WallDirection.UPPER) {
                f_t = -k_t * superposition * velocity1.getX();

                if (wallDirection == WallDirection.BOTTOM)
                    return new Vector2D(f_t, f_n);

                if (wallDirection == WallDirection.UPPER)
                    return new Vector2D(f_t, -f_n);
            } else if (wallDirection == WallDirection.RIGHT || wallDirection == WallDirection.LEFT) {
                f_t = -k_t * superposition * velocity1.getY();

                if (wallDirection == WallDirection.RIGHT)
                    return new Vector2D(-f_n, f_t);

                if (wallDirection == WallDirection.LEFT)
                    return new Vector2D(f_n, f_t);
            }
        }
        return new Vector2D(0, 0);
    }

    private enum WallDirection {
        LEFT, RIGHT, UPPER, BOTTOM;
    }

}