package ar.edu.itba.ss.simulation;

public final class Collision {

    private Particle p1;
    private Particle p2;
    //cantidad de colisiones de las particulas, se usa para determinar si la colisión es válida.
    private long p1_collisions;
    private long p2_collisions;
    //
    private CollisionType type;
    private double time;

    public Collision(Particle p1, Particle p2, double time, CollisionType type) {
        this.p1 = p1;
        this.p2 = p2;
        this.time = time;
        this.type = type;
        p1_collisions = p1.getCollisionCount();
        p2_collisions = p2.getCollisionCount();

    }

    public Particle getP1() {
        return p1;
    }

    public Particle getP2() {
        return p2;
    }

    public double getTime() {
        return time;
    }

    public boolean isValid() {
        return p1.getCollisionCount() == p1_collisions && p2.getCollisionCount() == p2_collisions;
    }

    public void setAbsolutTime(double currentTime) {
        this.time += currentTime;
    }

    public CollisionType getType() {
        return type;
    }


}
