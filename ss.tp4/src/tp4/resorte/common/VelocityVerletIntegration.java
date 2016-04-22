package tp4.resorte.common;


public class VelocityVerletIntegration {

    private double previousPosition;
    private double previousVelocity;

    public VelocityVerletIntegration(double initialPosition, double initialVelocity) {

        this.previousPosition = initialPosition;
        this.previousVelocity = initialVelocity;
    }

    public double[] updatePosition(double force_t, double force_tmdt, double mass, double dt) {

        double newPosition;
        double newVelocity;

        newPosition = previousPosition + dt * previousVelocity + (Math.pow(dt, 2) / mass) * force_t;

        newVelocity = previousVelocity + dt / (2 * mass) * (force_t + force_tmdt);

        return new double[]{newPosition, newVelocity};
    }


}
