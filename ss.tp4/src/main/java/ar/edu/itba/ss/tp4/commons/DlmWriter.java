package ar.edu.itba.ss.tp4.commons;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class DlmWriter {
	private BufferedWriter writer;

	public DlmWriter(String fileName) throws IOException {
		writer = new BufferedWriter(new FileWriter(fileName));
	}

	public void write(double[][] matrix, int rows, int cols) throws IOException {
		
		System.out.println("writing in file");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				writer.write(matrix[i][j]+ "");
				if(j+1 < cols) {
					writer.write(",");
				}
				
			}
			writer.write("\n");
		}
		writer.flush();
	}

	public void closeWriter() throws IOException {
		writer.close();
	}

}
