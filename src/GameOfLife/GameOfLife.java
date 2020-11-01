package GameOfLife;

import java.util.Random;
import java.util.Scanner;

public class GameOfLife {

	// sign UI
	public static final String BIRTH = "L"; // your Sign for alive cells
	public static final String DEATH = "D"; // your Sign for dead cells
	public static final String BORDERSIGN = "#"; // your Sign for border Cells

	// declaration
	public static final Scanner SCANBOT = new Scanner(System.in);

	public static int ROW = 3; // how many rows do you like
	public static int COL = 3; // how many columns do you like

	public static final String[][] GRID = new String[ROW + 2][ROW + 2];

	// random value for BIRTH and DEATH
	public static String coin() {
		int coin = new Random().nextInt(2);
		if (coin == 1) {
			return BIRTH;
		} else {
			return DEATH;
		}
	}

	// fill array with coin-method
	public static String[][] gridFill(String[][] GRID) {
		for (int i = 0; i < GRID.length; i++) {
			for (int j = 0; j < GRID[i].length; j++) {
				GRID[i][j] = coin();
			}
		}
		return GRID;
	}

	// adding border
	public static String[][] addBorder(String[][] GRID) {

		// margin = BORDERSIGN
		for (int i = 0; i < GRID.length; i++) {
			for (int j = 0; j < GRID[i].length; j++) {
				GRID[i][0] = BORDERSIGN; 					// margin left
				GRID[i][GRID[i].length - 1] = BORDERSIGN;	// margin right
				GRID[0][j] = BORDERSIGN; 					// margin top
				GRID[GRID.length - 1][j] = BORDERSIGN; 		// margin bottom
			}
		}
		return GRID;
	}

	// check for neighbours
	public static int checkNeighbours(String[][] GRID, int posY, int posX) {

		// define all 8 neighbours
		// posX = j // posY = i
		//
//				  		[i-1][j-1]	|	[i-1][j]	|	[i-1][j+1]
//						 [i][j-1]	|	 [i][j]		|	 [i][j+1]
//						[i+1][j-1]	|	[i+1][j]	|	[i+1][j+1]

		int trueNeighbours = 0;

		// check if not BORDERSIGN
		if (!GRID[posX][posY].equals(BORDERSIGN)) {

			// check top row
			if (GRID[posX - 1][posY - 1].equals(BIRTH)) trueNeighbours++;			// topLeft
			if (GRID[posX - 1][posY].equals(BIRTH)) trueNeighbours++;				// topMiddle
			if (GRID[posX - 1][posY + 1].equals(BIRTH))	trueNeighbours++;			// topRight

			// check middle row
			if (GRID[posX][posY - 1].equals(BIRTH)) trueNeighbours++;				// middleLeft
			if (GRID[posX][posY + 1].equals(BIRTH)) trueNeighbours++;				// middleRight

			// check bottom row
			if (GRID[posX + 1][posY - 1].equals(BIRTH)) trueNeighbours++;			// bottomLeft
			if (GRID[posX + 1][posY].equals(BIRTH))	trueNeighbours++;				// bottomMiddle
			if (GRID[posX + 1][posY + 1].equals(BIRTH)) trueNeighbours++;			// bottomRight

		} else {
			trueNeighbours = 0;
		}
		return trueNeighbours;

	}

	// UI
	public static void sout() {

		String[] ui = new String[COL + 2];

		for (int i = 0; i < ui.length; i++) {
			ui[i] = "=";
			if (i < ui.length - 1) {
				System.out.print(ui[i] + "-");
			} else {
				System.out.print(ui[i]);
			}
		}
		System.out.println();
	}

/////////////////////////////
//////// main method ////////
/////////////////////////////
	public static void main(String[] args) {

		// set Generation and Generation-Count
		int Gen = 1;
		int GenCount = 2;
		String[][] GRID = new String[ROW + 2][COL + 2];

		// fill GRID with gridFill method
		GRID = gridFill(GRID);
		// adding the border with BOR
		GRID = addBorder(GRID);

		// set Grid for next Generation
		while (Gen <= GenCount) {
			String[][] nextGenGrid = GRID;
			System.out.println("Gen.: " + Gen);
			sout();

			// implement grid
			for (int i = 0; i < GRID.length; i++) {
				for (int j = 0; j < GRID[i].length; j++) {
				
					System.out.print(GRID[i][j] + " ");

					// check if there are any alive neighbours
					int trueNeighbours = checkNeighbours(GRID, j, i);
					
//					if (!GRID[i][j].equals(BORDERSIGN)) {
//						
//
//						
//						// 1. rule – Any live cell with two or three live neighbors survives
//						if (GRID[i][j].equals(BIRTH) && trueNeighbours == 2) {
//							nextGenGrid[i][j] = GRID[i][j];
//						}
////	
////						// 3. rule – All other live cells die in the next generation. Similarly, all
////						// other dead cells stay dead
//						else {
//							nextGenGrid[i][j] = DEATH;
//						}
//					} else {
//						
//					}

				}
				System.out.println();
			}
			Gen++;
			GRID = nextGenGrid;
			
			sout();
		}
	}
}
