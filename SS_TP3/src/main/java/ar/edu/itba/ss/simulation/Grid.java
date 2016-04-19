package ar.edu.itba.ss.simulation;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.geometry.Point;

public class Grid {

	private ArrayList<ArrayList<ArrayList<Particle>>> grid;
	private double height;
	private double width;
	private int m_x;
	private int m_y;
	private static final double factor = 10;
	private static final double EPSILON = 0.0000001;
	public Grid(double height, double width, double rc) {
		// TODO Auto-generated constructor stub
		
		this.height=height;
		this.width=width;
		
		this.m_x= (int) Math.floor(factor*width/(rc+1)) +1;
		this.m_y= (int) Math.floor(factor*height/(rc+1)) +1;
		
		grid = new ArrayList<ArrayList<ArrayList<Particle>>>(m_x);
		
		for(int k = 0 ; k < m_x ; k++) {
			grid.add(k,new ArrayList<ArrayList<Particle>>(m_y));
				ArrayList<ArrayList<Particle>> array = grid.get(k);
				for(int k2 = 0 ; k2 < m_y ; k2++)
					array.add(k2,new ArrayList<Particle>());
		}
		
	}

	public void fillGrid(ParticleSet ps) {
		// TODO Auto-generated method stub
		int i, j;
		for (Particle particle : ps) {
			i = (int) Math.floor((particle.getXPosition())*factor) ;
			if(i >= m_x)
				i --;
			j = (int) Math.floor((particle.getYPosition())*factor) ;
			if(j >= m_y)
				j --;
			ArrayList<Particle> vecinos = grid.get(i).get(j);
			if(vecinos == null)
				vecinos = new ArrayList<Particle>();
				
			vecinos.add(particle);
		}
	}

	public void reinsert(ParticleSet ps) {
		// TODO Auto-generated method stub
		this.grid = new ArrayList<ArrayList<ArrayList<Particle>>>();
		for(int k = 0 ; k < m_x ; k++) {
			grid.add(k,new ArrayList<ArrayList<Particle>>(m_y));
				ArrayList<ArrayList<Particle>> array = grid.get(k);
				for(int k2 = 0 ; k2 < m_y ; k2++)
					array.add(k2,new ArrayList<Particle>());
		}
		this.fillGrid(ps);
		
	}

	public List<Particle> getNeighbours(Particle particle) {
		// TODO Auto-generated method stub
		List<Particle> neighbours = new ArrayList<Particle>();
		int i, j;
		i = (int) Math.floor(particle.getXPosition()/m_x);	
		j = (int) Math.floor(particle.getYPosition()/m_y);
		
		int[] x_pos_neighbours = {i-1,i,i+1};
		int[] y_pos_neighbours = {j-1,j,j+1};
		for (int k = 0; k < x_pos_neighbours.length; k++) {
			for (int k2 = 0; k2 < y_pos_neighbours.length; k2++) {
				if (k > 0 && k2 > 0 && k < m_x && k2 < m_y) {
					ArrayList<Particle> vecinos = grid.get(x_pos_neighbours[k]).get(y_pos_neighbours[k2]);
					for (Particle particle2 : vecinos) {
						if(particle != particle2) {
							neighbours.add(particle2);
						}
					}
				}
			}
			
		}

		
		
		
		
		return neighbours;
	}

}
