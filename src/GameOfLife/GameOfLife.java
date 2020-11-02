package GameOfLife;

import java.util.Random;
import java.util.Scanner;

public class GameOfLife {

	// sign UI
	public static final String BIRTH = "L"; // your Sign for alive cells
	public static final String DEATH = "D"; // your Sign for dead cells
	public static final String BORDERSIGN = "-"; // your Sign for border Cells

	// declaration
	public static final Scanner SCANBOT = new Scanner(System.in);

	public static int ROW = 3; // how many rows do you like
	public static int COL = 3; // how many columns do you like

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
		GRID = addBorder(GRID);
		return GRID;
	}

	// adding border
	public static String[][] addBorder(String[][] GRID) {

		// margin = BORDERSIGN
		for (int i = 0; i < GRID.length; i++) {
			for (int j = 0; j < GRID[i].length; j++) {
				GRID[i][0] = BORDERSIGN; // margin left
				GRID[i][GRID[i].length - 1] = BORDERSIGN; // margin right
				GRID[0][j] = BORDERSIGN; // margin top
				GRID[GRID.length - 1][j] = BORDERSIGN; // margin bottom
			}
		}
		return GRID;
	}

	// check for neighbours
	public static int checkNeighbours(String[][] GRID, int j, int i) {

		// define all 8 neighbours
		// row = i // column = j
		//
//				  		[-1][-1]	|	[-1][0]		|	[-1][1]
//						 [0][-1]	|	 [0][0]		|	 [0][1]
//						[1][-1]		|	[1][0]		|	[1][1]
		
		int dirs[][] = 	{
							{-1, -1},		// 0-0 0-1	topLeft
							{-1, 0},		// 1-0 1-1	topMiddle
							{-1, 1}, 		// 2-0 2-1	topRight
							{0, 1},			// 3-0 3-1	middleRight
							{1, 1}, 		// 4-0 4-1	bottomRight
							{1, 0},			// 5-0 5-1	bottomMiddle
							{1, -1},		// 6-0 6-1	bottomLeft
							{0, -1}			// 7-0 7-1	middleLeft
						};
		
		
		int trueNeighbours = 0;
		
		if (!GRID[i][j].equals(BORDERSIGN)) {

			for (int n = 0; n < 8; n++) {
									
					if(GRID[i + dirs[n][0]]	[j + dirs[n][1]].equals(BIRTH)) {
						trueNeighbours++;
					}
				
			}
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
		
		// set GRID
		String[][] GRID = new String[ROW + 2][COL + 2];

		// fill GRID
		GRID = gridFill(GRID);
		
		// set Generation and Generation-Count
		int Gen = 1;
		int GenCount = 2;

		// set GRID for next Generation
		while (Gen <= GenCount) {

			String[][] nextGenGrid = GRID;
			System.out.println("Gen.: " + Gen);
			sout();

			// implement grid
			for (int i = 0; i < GRID.length; i++) {
				for (int j = 0; j < GRID[i].length; j++) {

					System.out.print(GRID[i][j] + " ");

					if(!GRID[i][j].equals(BORDERSIGN)) {
						// check if there are any alive neighbours
						int trueNeighbours = checkNeighbours(GRID, j, i);
						
						nextGenGrid[i][j] = GRID[i][j] + "" + trueNeighbours;
				
					}
				}
				System.out.println();
			}
			Gen++;
			GRID = nextGenGrid;

			sout();
		}
	}
}
