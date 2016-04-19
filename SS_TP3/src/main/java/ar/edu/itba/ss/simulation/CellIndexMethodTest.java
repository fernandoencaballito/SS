package ar.edu.itba.ss.simulation;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CellIndexMethodTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		double width = 1;
		double height = 0.5;
		int particleCount = 10;
		double rc = 0.0001;
		SimulationSpace space = new SimulationSpace(width, height, null, 0.001);
		ParticleSet ps = ParticleSet.generateRandomParticleSet(width, height, particleCount);
		CellIndexMethod cm = new CellIndexMethod(ps, space, rc);
		
		List<Particle> neighbours;
		
		for (Particle particle : ps) {
			System.out.println("particle: " + particle);
			neighbours = cm.getNeighbours(particle);
			System.out.println("neighbours:");
			for (Particle neighbour : neighbours) {
				System.out.println(neighbour);
			}
		}

	}

}
