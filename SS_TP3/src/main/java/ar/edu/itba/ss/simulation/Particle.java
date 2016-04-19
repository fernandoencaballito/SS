package ar.edu.itba.ss.simulation;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Particle {


    private Vector2D position;
    private Vector2D velocity;


    private double radius;
    private double mass;

    private long collision_count = 0;

    public Particle(double x, double y, double vx, double vy, double radius, double mass) {
        position = new Vector2D(x, y);
        velocity = new Vector2D(vx, vy);
        this.radius = radius;
        this.mass = mass;

    }


    // se avanza la particula con la velocidad actual un delta de tiempo.
    public void advance(double time, SimulationSpace space) {


        double x_pos = position.getX() + velocity.getX() * time;
        double y_pos = position.getY() + velocity.getY() * time;

        Vector2D truncated = space.truncatePosition(x_pos, y_pos);

        double x_pos_truncated = truncated.getX();
        double y_pos_truncated = truncated.getY();

        // BORRAR
        if (x_pos_truncated > 1 || x_pos_truncated < 0 || y_pos_truncated > 0.5 || y_pos_truncated < 0) {
            System.out.println("posiciones invalidas: x_pos=" + x_pos + " ypos=" + y_pos);

            throw new RuntimeException();
        }
        //

        //this.position = new Vector2D(x_pos_truncated, y_pos_truncated);
        this.position = truncated;
    }


    public Vector2D getPosition() {
        return this.position;
    }

    public double getXPosition() {
        return this.position.getX();
    }

    public double getYPosition() {
        return this.position.getY();
    }

    @Override
    public String toString() {
        return "Particle [position=" + position + ", velocityX=" + velocity.getX() + ",velocityY=" +velocity.getY()  + ", radius=" + radius
                + ", mass=" + mass + ", collision_count=" + collision_count + "]";
    }

    public void setVelocity(double vx, double vy) {
        this.velocity = new Vector2D(vx, vy);
    }

    public Vector2D getVelocity() {
        return this.velocity;
    }

    public double getXVelocity() {
        return this.velocity.getX();
    }

    public double getYVelocity() {
        return this.velocity.getY();
    }

    public double getMass() {
        return this.mass;
    }

    public double getRadius() {
        return radius;
    }

    public long getCollisionCount() {
        return collision_count;
    }

    public void incrementCollisionCount() {
        this.collision_count++;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public void setPosition(Vector2D position) {

        double x_pos = position.getX();
        double y_pos = position.getY();
        // BORRAR
        if (x_pos > 1 || x_pos < 0 || y_pos > 0.5 || y_pos < 0) {
            System.out.println("posiciones invalidas: x_pos=" + x_pos + " ypos=" + y_pos);

            throw new RuntimeException();
        }
        //

        this.position = position;
    }

}
