package tp4.resorte;

public class Spring {

    private final double k;
    private double position;

    public Spring(double k, double initialPosition) {
        this.k = k;
        this.position = initialPosition;
    }

    public double updatePosition() {

    }

    public double getPosition() {
        return position;
    }
}
