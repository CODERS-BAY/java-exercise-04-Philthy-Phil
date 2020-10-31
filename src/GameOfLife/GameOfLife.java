package GameOfLife;

import java.util.Random;
import java.util.Scanner;

public class GameOfLife {

	// sign UI
	public static final String birth = "life";
	public static final String death = "dead";
	public static final String borderSign = "#";

	// declaration
	public static final Scanner scanBot = new Scanner(System.in);
	public static final int count = 3;
	public static final int row = count;
	public static final int col = count;
	public static final String[][] grid = new String[row + 2][col + 2];

	// random value for birth and death
	public static String coin() {
		int coin = new Random().nextInt(2);
		String random;
		if (coin == 1) {
			random = birth;
		} else {
			random = death;
		}
		return random;
	}

	// fill'in array with random-method[]
	public static String[][] GridFillin() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = coin();
			}
		}
		return grid;
	}

	// adding border
	public static String[][] addBorder(String[][] grid) {

		// margin left = borderSign
		for (int marginLeft = 0; marginLeft < row + 2; marginLeft++) {
			grid[marginLeft][0] = borderSign;
		}

		// margin right = borderSign
		for (int marginRight = 0; marginRight < row + 2; marginRight++) {
			grid[marginRight][col + 1] = borderSign;
		}

		// margin top = borderSign
		for (int marginTop = 0; marginTop < col + 2; marginTop++) {
			grid[0][marginTop] = borderSign;
		}
		// margin bottom = borderSign
		for (int marginBottom = 0; marginBottom < col + 2; marginBottom++) {
			grid[row + 1][marginBottom] = borderSign;
		}
		return grid;
	}

	// check for neighbours
	public static int checkNeighbours(int posX, int posY, String[][] grid) {

		String[][] temp = grid;

		// define all 8 neighbours
		// posX = i // posY = j
//
//		  		[i-1][j-1]	|	[i-1][j]	|	[i-1][j+1]
//				 [i][j-1]	|	 [i][j]		|	 [i][j+1]
//				[i+1][j-1]	|	[i+1][j]	|	[i+1][j+1]

		int trueNeighbours = 0;

		if (temp[posX][posY] != borderSign) {

			String topLeft		= temp[posX - 1][posY - 1];
			String topMiddle	= temp[posX - 1][posY];
			String topRight		= temp[posX - 1][posY + 1];
			String middleLeft	= temp[posX][posY - 1];
			String middle		= temp[posX][posY];
			String middleRight	= temp[posX][posY + 1];
			String bottomLeft	= temp[posX + 1][posY - 1];
			String bottomMiddle = temp[posX + 1][posY];
			String bottomRight	= temp[posX + 1][posY + 1];

			// check top row
			if (topLeft.equals(birth)) trueNeighbours++;
			if (topMiddle.equals(birth)) trueNeighbours++;
			if (topRight.equals(birth)) trueNeighbours++;

			// check middle row
			if (middleLeft.equals(birth)) trueNeighbours++;
			if (middleRight.equals(birth)) trueNeighbours++;

			// check bottom row
			if (bottomLeft.equals(birth)) trueNeighbours++;
			if (bottomMiddle.equals(birth)) trueNeighbours++;
			if (bottomRight.equals(birth)) trueNeighbours++;
		
		} else {
			trueNeighbours = 0;
		}
		return trueNeighbours;
	}

	// UI
	public static void sout() {

		String[] ui = new String[count + 2];

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

		// set Generation Count
		int Gen = 1;
		int GenCount = 2;

		// set nextGen
		String[][] nextGenGrid = new String[row + 2][col + 2];

		// implement grid
		String[][] grid = GridFillin();
		grid = addBorder(grid);

		// display grid & check & loop
		while (Gen <= GenCount) {

			System.out.println("Gen.: " + Gen);
			sout();

			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					System.out.print(grid[i][j] + " ");

					int posX = i;
					int posY = j;

					int trueNeighbours = checkNeighbours(posX, posY, grid);

					// 1. rule – Any live cell with two or three live neighbors survives
					if (grid[i][j].equals(birth) && trueNeighbours == 2 || trueNeighbours == 3) {
						nextGenGrid[i][j] = grid[i][j];
					}

					// 2. rule – Any dead cell with three live neighbors becomes a live cell
//					else if (grid[i][j].equals(death) && trueNeighbours == 3) {
//						nextGenGrid[i][j] = birth;
//					}

					// 3. rule – All other live cells die in the next generation. Similarly, all
					// other dead cells stay dead
					else {
						nextGenGrid[i][j] = death;
					}

					nextGenGrid = addBorder(nextGenGrid);
				}
				System.out.println();
			}
			sout();
			System.out.println();
			grid = nextGenGrid;
			Gen++;
		}
	}
}
