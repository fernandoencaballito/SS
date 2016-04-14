package ar.edu.itba.ss.simulation;

import java.awt.geom.Point2D;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Particle {

    private int id;

    private Vector2D position;
    private Vector2D velocity;


    private double radius;
    private double mass;
    private long collision_count;

    public Particle(double x, double y, double vx, double vy, double radius, double mass) {
        position = new Vector2D(x, y);
        velocity = new Vector2D(vx, vy);
        this.radius = radius;
        this.mass = mass;
        collision_count = 0;
    }

    public void advance(double time) {

        double x_pos = position.getX() + velocity.getX() * time;
        double y_pos = position.getY() + velocity.getY() * time;

        this.position = new Vector2D(x_pos, y_pos);
    }

    public double getCollisionTime(Particle p2) {

        Vector2D delta_r = this.position.subtract(p2.position);
        Vector2D delta_v = this.velocity.subtract(p2.velocity);

        double sigma = this.radius + p2.radius;

        double dotProductVR = delta_v.dotProduct(delta_r);
        double dotProductRR = delta_r.dotProduct(delta_r);
        double dotProductVV = delta_v.dotProduct(delta_v);

        double d = Math.pow(dotProductVR, 2) - dotProductVV * (dotProductRR - Math.pow(sigma, 2));

        if (dotProductVR >= 0 || d < 0)
            return -1;

        double time = -(dotProductVR + Math.sqrt(d)) / dotProductVV;

        return time;
    }


    public void collide(Particle p) {

    }

    public void collide(CollisionType w) {
        collision_count++;
        double vx = velocity.getX();
        double vy = velocity.getY();

        switch (w) {
            case NORTH:
            case SOUTH:
                vy = -vy;
                break;

            case EAST:
            case WEST:
                vx = -vx;
                break;
        }

        setVelocity(vx, vy);

    }

    private void setVelocity(double vx, double vy) {
        this.velocity = new Vector2D(vx, vy);
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public long getCollisionCount() {
        return collision_count;
    }
}
