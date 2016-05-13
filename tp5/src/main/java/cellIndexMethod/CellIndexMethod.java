package cellIndexMethod;


import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import tp5.Particle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CellIndexMethod {

    private List<List<List<Particle>>> grid;
    private double rc;

    private int Mx;
    private int My;

    public CellIndexMethod(double width, double height, int particleCount, double rc) {

        //L/M > rc
        //Lx/Mx > rc
        //Ly/My > rc

        this.rc = rc;
        this.Mx = (int) Math.ceil(width / rc);
        this.My = (int) Math.ceil(height / rc);

        grid = new ArrayList<List<List<Particle>>>(this.My);

        for (int i = 0; i < this.My; i++) {
            List<List<Particle>> row = new ArrayList<List<Particle>>(this.Mx);

            for (int j = 0; j < this.Mx; j++) {
                row.add(new LinkedList<Particle>());
            }

            grid.add(row);
        }
    }


    public void clearGrid() {

        for (List<List<Particle>> row : grid) {
            for (List<Particle> cell : row) {
                cell.clear();
            }
        }

        //grid.stream().forEach(row->row.stream().forEach(cell->cell.clear()));
    }

    public void addParticles(List<Particle> particles) {

        double xPos, yPos;
        Vector2D pos;

        for (Particle particle : particles) {
            pos = particle.getPosition();
            xPos = pos.getX();
            yPos = pos.getY();

            if (yPos < 0) {
                continue;
            }

            int[] index = getIndexFromPosition(xPos, yPos);

            List<Particle> cell = grid.get(index[0]).get(index[1]);

            cell.add(particle);

        }
    }

    public List<Particle> getNeighbors(Particle p) {

        Vector2D pos = p.getPosition();
        double xPos = pos.getX();
        double yPos = pos.getY();

        List<Particle> neighbors = new ArrayList<Particle>(8);

        int[] index = getIndexFromPosition(xPos, yPos);
        int row = index[0];
        int col = index[1];

        int[][] cells = new int[][]{{row - 1, col}, {row - 1, col + 1}, {row, col + 1}, {row + 1, col + 1}, {row, col}};

        for (int[] npos : cells) {

            int nrow, ncol;

            nrow = npos[0];
            ncol = npos[1];

            if (nrow < 0 || nrow >= My || ncol < 0 || ncol >= Mx) {
                continue;
            }

            List<Particle> cell = grid.get(nrow).get(ncol);

            for (Particle neighbor : cell) {
                if (p == neighbor) {
                    continue;
                }

                neighbors.add(neighbor);
            }
        }

        return neighbors;

    }

    private int[] getIndexFromPosition(double xPos, double yPos) {
        return new int[]{(int) Math.floor(yPos / rc), (int) Math.floor(xPos / rc)};
    }


}
