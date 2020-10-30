package GameOfLife;

import java.util.Random;
import java.util.Scanner;

public class GameOfLife {

	// sign UI
	public static final String birth = "L";
	public static final String death = "X";
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
	public static String[][] addBorder(String[][]grid) {

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
	public static int checkNeighbours(String[][]grid) {

		int trueNeighbours = 0;
		
		String[][] temp = grid;

		for (int i = 1; i < temp.length - 1; i++) {
			for (int j = 1; j < temp[i].length - 1; j++) {
//				System.out.print(temp[i][j] + " ");

				// define all 8 neighbours
				String topLeft = temp[i - 1][j - 1];
				String topMiddle = temp[i - 1][j];
				String topRight = temp[i - 1][j + 1];
				String middleLeft = temp[i][j - 1];
				String middle = temp[i][j];
				String middleRight = temp[i][j + 1];
				String bottomLeft = temp[i + 1][j - 1];
				String bottomMiddle = temp[i + 1][j];
				String bottomRight = temp[i + 1][j + 1];
				
				// check top row
				if(topLeft.equals(birth)) trueNeighbours++;
				if(topMiddle.equals(birth)) trueNeighbours++;
				if(topRight.equals(birth)) trueNeighbours++;
				
				// check middle row
				if(middleLeft.equals(birth)) trueNeighbours++;
				if(middleRight.equals(birth)) trueNeighbours++;
				
				// check bottom row
				if(bottomLeft.equals(birth)) trueNeighbours++;
				if(bottomMiddle.equals(birth)) trueNeighbours++;
				if(bottomRight.equals(birth)) trueNeighbours++;
				
			}
//			System.out.println();
		}
//		System.out.println();
		return trueNeighbours;
	}

	// UI
	public static void sout() {

		String[] ui = new String[count + 2]; // 5

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
					

					// define rules
					nextGenGrid[i][j] = grid[i][j] + "N";
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
