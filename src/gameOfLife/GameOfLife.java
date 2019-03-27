package gameOfLife;

public class GameOfLife {
	public static void main(String[] args) {
		int m = 10, n = 10;
		
		//Initialize original generation
		int[][] field = 
				   {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
					{0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
					{0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
		
		//Print out original generation
		System.out.println("Original Generation:");
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(field[i][j] == 0) {
					System.out.print(".");
				}
				else {
					System.out.print("*");
				}
			}
			System.out.print("\n");
		}
		int[][] newField = nextGeneration(field, m, n);
		int counter = 0;
		while(counter < 10) {
			int[][] newerField = nextGeneration(newField, m, n);
			counter += 1;
			newField = newerField;
		}
	}
	public static int[][] nextGeneration(int[][] field, int m, int n) {
		int[][] future = new int[m][n];
		
		//loop through each cell, ignoring edge cells
		//supposed to be infinite plane
		for(int i = 1; i < m-1; i++) {
			for(int j = 1; j < n-1; j++) {
				//search for each cell's alive neighbors
				int aliveNeighbors = 0;
				for(int k = -1; k <= 1; k++) {
					for(int l = -1; l <= 1; l++) {
						aliveNeighbors += field[i+k][j+l];
					}
				}
				//Subtract cell from current neighbors
				aliveNeighbors -= field[i][j];
				//Implement Rules of Life
				//Cell dies of loneliness/under-population
				if(field[i][j] == 1 && aliveNeighbors < 2) {
					future[i][j] = 0;
				}
				//Cell dies of over-population
				else if(field[i][j] == 1 && aliveNeighbors > 3) {
					future[i][j] = 0;
				}
				//A new cell is born
				else if(field[i][j] == 0 && aliveNeighbors == 3) {
					future[i][j] = 1;
				}
				//No change happens
				else {
					future[i][j] = field[i][j];
				}
			}
		}
		//print future generation
		System.out.println("Next Generation");
		for(int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(future[i][j] == 0) {
					System.out.print(".");
				}
				else {
					System.out.print("*");
				}
			}
			System.out.print("\n");
		}
		return future;
	}
}
