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

    private int counterx = 0;
    private int countery1 = 0;
    private int countery2 = 0;
    private int counterx2 = 0;


    // se avanza la particula con la velocidad actual un delta de tiempo.
    public void advance(double time, SimulationSpace space) {


        double x_pos = position.getX() + velocity.getX() * time;
        double y_pos = position.getY() + velocity.getY() * time;

        //this.position = new Vector2D(x_pos_truncated, y_pos_truncated);
        this.position = space.truncatePosition(x_pos, y_pos);


        //choque con la pared izquierda
        if (getXPosition() == 0 && this.getXVelocity() < 0) {
            counterx++;
        }

        if (getXPosition() == 0 && counterx > 20) {
            this.velocity = new Vector2D(0.05, this.getYVelocity());
            counterx = 0;
            countery1 = 0;
            countery2 = 0;
            counterx2 = 0;
        }


        //choque con la de abajo
        if (getYPosition() == 0 && this.getYVelocity() < 0) {
            countery1++;
        }

        if (getYPosition() == 0 && countery1 > 20) {
            this.velocity = new Vector2D(getXVelocity(), 0.05);
            counterx = 0;
            countery1 = 0;
            countery2 = 0;
            counterx2 = 0;
        }

        //choque con la de abajo
        if (getYPosition() == space.getHeight() && this.getYVelocity() > 0) {
            countery2++;
        }

        if (getYPosition() == space.getHeight() && countery2 > 20) {
            this.velocity = new Vector2D(getXVelocity(), -0.05);
            counterx = 0;
            countery1 = 0;
            countery2 = 0;
            counterx2 = 0;
        }


        //choque con la pared izquierda
        if (getXPosition() == space.getWidth() && this.getXVelocity() > 0) {
            counterx2++;
        }

        if (getXPosition() == space.getWidth() && counterx2 > 20) {
            this.velocity = new Vector2D(0.05, 0);
            this.position = new Vector2D(0.0001, getYPosition());
            counterx = 0;
            countery1 = 0;
            countery2 = 0;
            counterx2 = 0;
        }

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
        return "Particle [position=" + position + ", velocity=" + velocity + ", radius=" + radius
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
        counterx2 = 0;
    }

}
