package tp4.resorte;

import tp4.resorte.common.ForceCalculator1D;

public class Spring implements ForceCalculator1D {

    private final double k;
    private final double gamma;
    private double position;
    private double mass;

    public Spring(double k, double gamma, double initialPosition) {
        this.k = k;
        this.gamma = gamma;
        this.position = initialPosition;
    }



    public double getPosition() {
        return position;
    }

    public double calculateForce(double position, double velocity) {
        return -k * position - gamma * velocity;
    }
}
