package GameOfLife;

import java.util.Random;

public class GameOfLifeTestingAround {

// sign declaration
	public static final String birth = "•";
	public static final String death = "X";
		
// random value for birth and death
	public static String coin() {
		int random = new Random().nextInt(2);
		String coin;
		if (random == 1) {
			coin = birth;
		} else {
			coin = death;
		}
		return coin;
	}
	
// UI
	public static void sout() {
		System.out.println("=============");
	}

// main method
	public static void main(String[] args) {

//set Generation Count
		int GenCount = 1;
		

		
// set grid 5 x 5
		String[][] grid = { 
				{ "#", "#", "#", "#", "#", "#", "#" }, 
				{ "#", coin(), coin(), coin(), coin(), coin(), "#" },
				{ "#", coin(), coin(), coin(), coin(), coin(), "#" }, 
				{ "#", coin(), coin(), coin(), coin(), coin(), "#" },
				{ "#", coin(), coin(), coin(), coin(), coin(), "#" },
				{ "#", coin(), coin(), coin(), coin(), coin(), "#" },
				{ "#", "#", "#", "#", "#", "#", "#" } };
		
		sout();
		System.out.println("Generation " + GenCount);		
		sout();
		
// display grid
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				
				if(j < grid[i].length - 1 ) {
					System.out.print(grid[i][j] + " ");
				} else {
					System.out.print(grid[i][j] + "\n");
				}
				
			}
		}

		sout();

	}
}
