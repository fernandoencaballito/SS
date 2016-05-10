package ar.edu.itba.ss.tp4.ej1;


public class AnalyticSpringSolution {

    public static double getPosition(Spring resorte, double mass, double t) {
    	double gamma,  k;
    	gamma = resorte.getGamma();
    	k = resorte.getK();
    	
    	
    	double term1= Math.exp(- (gamma / (2 * mass) ) * t);
    	double term2_1_1=k / mass;
    	double term2_1_2=Math.pow(gamma, 2) /(4 * Math.pow(mass, 2));
    	double term2_1=Math.sqrt(term2_1_1 - term2_1_2);
    	double term2=Math.cos(term2_1 * t);
        return term1* term2;
    }


}
