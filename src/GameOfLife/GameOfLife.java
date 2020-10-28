package GameOfLife;

import java.util.Random;
import java.util.Scanner;

public class GameOfLife {

	// sign UI
	public static final String birth = "•";
	public static final String death = "X";
	public static final String borderSign = "#";
	
	// declaration
	public static final Scanner scanBot = new Scanner(System.in);
	public static final int row = 5;
	public static final int col = 5;
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

				// margin left = borderSign
				for (int marginLeft = 0; marginLeft < row+2; marginLeft++) {
					grid[marginLeft][0] = borderSign;
				}
				
				// margin right = borderSign
				for (int marginRight = 0; marginRight < row+2; marginRight++) {
					grid[marginRight][col+1] = borderSign;
				}	
				
				// margin top = borderSign
				for (int marginTop = 0; marginTop < col+2; marginTop++) {
					grid[0][marginTop] = borderSign;
				}
				// margin bottom = borderSign
				for (int marginBottom = 0; marginBottom < col+2; marginBottom++) {
					grid[row+1][marginBottom] = borderSign;
				}

			}
		}
		return grid;
	}

	// UI
	public static void sout() {
		
		// noch dynamisch machen
		// noch dynamisch machen
		// noch dynamisch machen
		System.out.println("==============");
	}

	// main method
	public static void main(String[] args) {

		// set Generation Count
		int Gen = 1;

		// implement grid
		String[][] grid = GridFillin();

		// display grid & infos
		sout();
		System.out.println("Gen.: " + Gen);
		sout();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {

				if (j < grid[i].length - 1) {
					System.out.print(grid[i][j] + " ");
				} else {
					System.out.print(grid[i][j] + "\n");
				}

			}
		}

		sout();

	}

}
