package ar.edu.itba.ss.tp4.ej1;


public class AnalyticSpringSolution {

    public static double getPosition(Spring resorte, double mass, double t) {
    	double gamma,  k;
    	gamma = resorte.getGamma();
    	k = resorte.getK();
        return Math.exp(-gamma / (2 * mass) * t) * Math.cos(Math.sqrt(k / mass - Math.pow(gamma, 2) /(4 * Math.pow(mass, 2))) * t);
    }


}
