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
	public static int checkNeighbours(String[][] GRID, int i, int j) {

//		// define all 8 neighbours
//		
//		// row = i // column = j
//		
//				  		[-1][-1]	|	[-1][0]		|	[-1][1]
//						 [0][-1]	|	 [0][0]		|	 [0][1]
//						[1][-1]		|	[1][0]		|	[1][1]

		// 0-0 0-1	topLeft			// 1-0 1-1	topMiddle		// 2-0 2-1	topRight
		// 7-0 7-1	middleLeft									// 3-0 3-1	middleRight
		// 6-0 6-1	bottomLeft		// 5-0 5-1	bottomMiddle	// 4-0 4-1	bottomRight
		
		int dirs[][] = 	{
		//	topLeft		topMiddle	topRight	middleRight		bottomRight		bottomMiddle	bottomLeft		middleLeft
			{-1, -1},	{-1, 0},	{-1, 1},	{0, 1},			{1, 1},			{1, 0},			{1, -1},		{0, -1}			
						};	
		int trueNeighbours = 0;

		if (!GRID[i][j].equals(BORDERSIGN)) {	
			for (int n = 0; n < 8; n++) {
				if(GRID[i + dirs[n][0]] [j + dirs[n][1]].equals(BIRTH)) {
					trueNeighbours++;
				}	
			}
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
						
					// check all 8 neighbours
					int trueNeighbours = checkNeighbours(GRID, i, j);
					if(!GRID[i][j].equals(BORDERSIGN)) {
						System.out.print(GRID[i][j] + "" + trueNeighbours + " ");
						
//						// rule #1 any live cell with two or three live neighbors survives
						if(trueNeighbours == 2 || trueNeighbours == 3) {
							nextGenGrid[i][j] = BIRTH;							
						}
					
//						// rule #2 any dead cell with three live neighbours becomes a live cell
//						else if(GRID[i][j].equals(DEATH) && trueNeighbours == 3) {
//							nextGenGrid[i][j] = BIRTH;
//						}
					
//						// rule #3 all other live cells die in the next generation - similarly, all other dead cells stay dead
//						else {
//							nextGenGrid[i][j] = DEATH;
//						}	
						
					} else {
						System.out.print(GRID[i][j] + " ");
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
