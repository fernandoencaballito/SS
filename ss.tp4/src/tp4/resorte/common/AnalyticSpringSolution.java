package tp4.resorte.common;


public class AnalyticSpringSolution {

    public static double getPosition(double gamma, double k, double mass, double t) {
        return Math.exp(-gamma / (2 * mass) * t) * Math.cos(Math.sqrt(k / mass - Math.pow(gamma, 2) / 4 * Math.pow(mass, 2)) * t);
    }


}
