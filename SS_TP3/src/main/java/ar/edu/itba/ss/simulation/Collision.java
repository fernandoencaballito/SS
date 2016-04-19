package ar.edu.itba.ss.simulation;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.ArrayList;
import java.util.List;

public final class Collision implements Comparable<Collision> {

    private Particle p1;
    private Particle p2;
    private Wall wall;
    private CollisionType type;
    private double time;
    // cantidad de colisiones de las particulas, se usa para determinar si la
    // colisión es válida.
    private long p1_collisions;
    private long p2_collisions;
    private static double EPSILON;

    public Collision(Particle p1, Particle p2, double time) {
        this.p1 = p1;
        this.p2 = p2;
        this.time = time;
        this.type = CollisionType.PARTICLE;
        this.p1_collisions = p1.getCollisionCount();
        this.p2_collisions = p2.getCollisionCount();
    }

    public Collision(Particle p1, Wall wall, double time) {
        this.p1 = p1;
        this.time = time;
        this.type = (wall.isPeriodic()) ? CollisionType.PERIODIC : CollisionType.WALL;
        this.wall = wall;
        this.p1_collisions = p1.getCollisionCount();
    }

    public static double getCollisionTime(Particle p1, Particle p2) {

        Vector2D delta_r = p2.getPosition().subtract(p1.getPosition());
        Vector2D delta_v = p2.getVelocity().subtract(p1.getVelocity());

        double sigma = p1.getRadius() + p2.getRadius();

        return getCollsiionTime(delta_r, delta_v, sigma);
    }

    private static double getCollsiionTime(Vector2D delta_r, Vector2D delta_v, double sigma) {
        double dotProductVR = delta_v.dotProduct(delta_r);
        double dotProductRR = delta_r.dotProduct(delta_r);
        double dotProductVV = delta_v.dotProduct(delta_v);

        double d = Math.pow(dotProductVR, 2) - dotProductVV * (dotProductRR - Math.pow(sigma, 2));

        if (dotProductVR >= 0 || d < 0)
            return -1;

        return -(dotProductVR + Math.sqrt(d)) / dotProductVV;
    }

    public static double getCollisionTime(Particle p1, Wall wall) {
        Vector2D position = p1.getPosition();
        Vector2D velocity = p1.getVelocity();

        Line trajectory = new Line(position, position.add(velocity), Wall.TOLERANCE);

        Vector2D intercept = trajectory.intersection(wall.getLine());


        if (intercept == null)
            return -1;
        

        
        double deltaX = intercept.getX() - position.getX();
        int deltaXCast = (int)(deltaX * 10000);
        deltaX = ((double)deltaXCast)/10000;
        
        double deltaY = intercept.getY() - position.getY();
        int deltaYCast = (int)(deltaY * 10000);
        deltaY = ((double)deltaYCast)/10000;
        
        
        
        if(velocity.getY() < 0 && deltaY >= 0)
        	return -1;
        
        if(velocity.getY() > 0 && deltaY <= 0)
        	return -1;
        
        if(velocity.getX() < 0 && deltaX >= 0)
        	return -1;
        
        if(velocity.getX() > 0 && deltaX <= 0)
        	return -1;
        

        Vector2D start = wall.getStart();
        Vector2D end = wall.getEnd();

        if (start.getX() < end.getX()) {
            if (intercept.getX() < start.getX() || intercept.getX() > end.getX()) {
                return -1;
            }
        } else {
            if (intercept.getX() < end.getX()|| intercept.getX() > start.getX()) {
                return -1;
            }
        }
        if (start.getY() < end.getY()) {
            if (intercept.getY() < start.getY()|| intercept.getY() > end.getY()) {
                return -1;
            }
        } else {
            if (intercept.getY() < end.getY()|| intercept.getY() > start.getY()) {
                return -1;
            }
        }

        Vector2D over = velocity.scalarMultiply(1/velocity.getNorm());
        over = over.scalarMultiply(p1.getRadius());
        
        intercept = intercept.subtract(over);
        double time = intercept.subtract(position).getNorm() / velocity.getNorm();

        return time;
    }

    public int compareTo(Collision o) {
        double ans = this.time - o.time;

        if (ans < 0) {
            return -1;
        } else if (ans > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public double getTime() {
        return time;
    }

    public boolean isValid() {
        // return ( p1.getCollisionCount() == p1_collisions
        // && (type != CollisionType.WALL && p2.getCollisionCount() ==
        // p2_collisions)
        // )
        // || p1.getCollisionCount() == p1_collisions;

        return (p1.getCollisionCount() == p1_collisions)
                && ((type == CollisionType.PARTICLE) ? (p2.getCollisionCount() == p2_collisions) : true);

    }

    public void setAbsolutTime(double currentTime) {
        this.time += currentTime;
    }

    public CollisionType getType() {
        return type;
    }

    public void collide() {
        if (this.type == CollisionType.PARTICLE) {
            collide(this.p1, this.p2);
            p1.incrementCollisionCount();
            p2.incrementCollisionCount();
        } else {
            collide(this.p1, this.wall);
            p1.incrementCollisionCount();
        }
    }

    private void collide(Particle p1, Particle p2) {

        Vector2D delta_r = p2.getPosition().subtract(p1.getPosition());
        Vector2D delta_v = p2.getVelocity().subtract(p1.getVelocity());

        double sigma = p1.getRadius() + p2.getRadius();

        double J = (2 * p1.getMass() * p2.getMass() * delta_v.dotProduct(delta_r))
                / (sigma * (p1.getMass() + p2.getMass()));

        double Jx = J * delta_r.getX() / sigma;
        double Jy = J * delta_r.getY() / sigma;

        double p1_vx = p1.getXVelocity() + Jx / p1.getMass();
        double p1_vy = p1.getYVelocity() + Jy / p1.getMass();

        p1.setVelocity(p1_vx, p1_vy);

        double p2_vx = p2.getXVelocity() - Jx / p2.getMass();
        double p2_vy = p2.getYVelocity() - Jy / p2.getMass();

        p2.setVelocity(p2_vx, p2_vy);

    }

    private void collide(Particle p1, Wall w) {

        double dx = w.getEnd().getX() - w.getStart().getX();
        double dy = w.getEnd().getY() - w.getStart().getY();

        Vector2D normal = null;

        if (p1.getXVelocity() > 0) {

            normal = new Vector2D(-dy, dx);
        } else if (p1.getXVelocity() < 0) {
            normal = new Vector2D(dy, -dx);
        } else {
            return;
        }

        double normalSq = normal.dotProduct(normal);
        double dotProductVN = p1.getVelocity().dotProduct(normal);

        double coef = dotProductVN / normalSq;

        Vector2D u = normal.scalarMultiply(coef);
        Vector2D w1 = p1.getVelocity().subtract(u);

        Vector2D Vf = w1.subtract(u);

        p1.setVelocity(Vf);


//        double vx = p1.getXVelocity();
//        double vy = p1.getYVelocity();
//
//        double angle = w.getAngle();
//
//        double sin = Math.sin(angle);
//        double cos = Math.cos(angle);
//
//        double vxp = vx * cos - vy * sin;
//        double vyp = -(vx * sin + vy * cos);
//
//        sin = Math.sin(-angle);
//        cos = Math.cos(-angle);
//
//        vx = vxp * cos - vyp * sin;
//        vy = vxp * sin + vyp * cos;
//
//        p1.setVelocity(vx, vy);
    }

    public List<Particle> getParticles() {
        List<Particle> particles = new ArrayList<Particle>(2);
        particles.add(p1);
        if (type == CollisionType.PARTICLE)
            particles.add(p2);

        return particles;
    }

    public boolean isPeriodic() {
        return this.type == CollisionType.PERIODIC;
    }

    public Particle getParticle() {
        return this.p1;
    }

    private enum CollisionType {
        WALL, PARTICLE, PERIODIC;
    }

    public static void setEpsilon(double epsilon2) {
        EPSILON = epsilon2;

    }

}
