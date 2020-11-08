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

	public static int ROW;
	public static int COL;
	public static String[][] GRID;

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
	public static void gridFill() {
		for (int i = 0; i < GRID.length; i++) {
			for (int j = 0; j < GRID[i].length; j++) {
				GRID[i][j] = coin();
			}
		}
		addBorder();
	}

	// adding border
	public static String[][] addBorder() {
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
		int dirs[][] = {
			// 	topLeft, topMiddle, topRight, middleRight, bottomRight, bottomMiddle, bottomLeft, middleLeft
				{ -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }
				};
		
		int trueNeighbours = 0;

		if (!GRID[i][j].equals(BORDERSIGN)) {
			for (int n = 0; n < 8; n++) {
				if (GRID[i + dirs[n][0]][j + dirs[n][1]].equals(BIRTH)) {
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
		boolean running = true;
		while (running) {

			System.out.print("rows should be displayed: ");
			ROW = SCANBOT.nextInt();
			System.out.print("columns should be displayed: ");
			COL = SCANBOT.nextInt();

			// set GRID
			GRID = new String[ROW + 2][COL + 2];

			System.out.print("generation should be displayed: ");

			// set Generation and Generation-Count
			int gen = 1;
			int genCount = SCANBOT.nextInt();
			gridFill();

			sout();
			while (gen <= genCount) {
				System.out.println("Gen.: " + gen);
				sout();

				// initialize nextGenGrid
				String[][] nextGenGrid = new String[ROW + 2][COL + 2];

				// implement grid
				for (int i = 0; i < GRID.length; i++) {
					for (int j = 0; j < GRID[i].length; j++) {

						int trueNeighbours = checkNeighbours(GRID, i, j);

						System.out.print(GRID[i][j] + " ");

						// set rules for nextGenGrid for birth, death or stay
						if (GRID[i][j].equals(BIRTH) && trueNeighbours < 2) {
							nextGenGrid[i][j] = DEATH;
						} else if (GRID[i][j].equals(BIRTH) && trueNeighbours > 3) {
							nextGenGrid[i][j] = DEATH;
						} else if (GRID[i][j].equals(DEATH) && trueNeighbours == 3) {
							nextGenGrid[i][j] = BIRTH;
						} else {
							nextGenGrid[i][j] = GRID[i][j];
						}
					}
					System.out.println();
				}

				GRID = nextGenGrid;
				sout();
				gen++;
			}
			System.out.print("want to start over? (y or n) ");
			String oneMore = SCANBOT.next();

			if (oneMore.equals("n")) {
				running = false;
				System.out.println("thx for using this programm!");
			}
			sout();
		}
	}
}