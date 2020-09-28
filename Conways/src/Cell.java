import java.util.ArrayList;

public class Cell {

	/** Declarations */
	int x;
	int y;
	boolean alive;
	String symbol;
	private boolean shouldSwap;

/** Constructors */
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		alive = false;
		symbol = " ";

		
	}
/** Getters and setters */
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isAlive() {
		return alive;

	}
	public String getSymbol() {
		return symbol;
	}

	/** Methods */

	//Swap a dead cell to an alive cell or swap an alive cell to a dead cell by switching boolean alive from true to false
	public void swapState() {
		alive = !alive;
		if (symbol.equals(" ")) {
			symbol = "O";
		} else if (symbol.equals("O")) {
			symbol = " ";
		}
	}

	// return the number of living cells surrounding this cell
	public int checkLiveNeighbours(CellWorld cw) {
		int countLive = 0;
		int counterY = 0;
		int counterX = 0;
		int iterate = 0;
		for (int i = 1; i < 1 + 1; i++) {
			iterate += i * 8;
		}
		iterate += 1;
		
		//this loops through and gets every x and y combination for the cells which surround THIS cell (x)
		// [x-1y-1][x  y-1][x+1y-1]
		// [x-1y  ][ this ][x+1y  ]
		// [x-1y+1][x  y+1][x+1y+1]
		// will also include itself so we must account for this at the end ***
		
		for (int i = 0; i < iterate; i++) {
			int xx = x - (1 - counterX);
			int yy = y - (1 - counterY);
			
			//try and catch here incase we try to get a cell using an x or y that is outside the 2d array height/width
			try
			{
			if (cw.getCell(xx, yy).isAlive()) {
				countLive++;
			}
			} catch (Exception e) {
				
			}

			counterX++;
			if (counterX == (1 * 2 + 1)) {
				counterX = 0;
				counterY++;
			}

		}
		// *** here we account for the fact that it would add itself to the count if THIS.alive == true
		if (this.isAlive()) {
			countLive--;
		}
		return countLive;
	}
	
	// this method defines the rules of the game and sets a boolean if it should change living state
		// A live cell should become a dead cell if it has less than 2 or more than 3 surrounding live cells
			// else it should remain a live cell
		// A dead cell should become a live cell if it has 3 surrounding live cells
			// else it should remain a dead cell
	// shouldSwap boolean used so as to not change the living status of any cell before we have finished iterating
	
	public void shouldSwap(CellWorld cw) {
		if (isAlive()) {
			if (checkLiveNeighbours(cw) == 2 || checkLiveNeighbours(cw) == 3) {
				return;
			} else {
				shouldSwap = true;
			}
		} else if (!isAlive()) {
			if (checkLiveNeighbours(cw) == 3) {
				shouldSwap = true;
			}
		}
		
	}
	
	public void update() {
		if (shouldSwap) {
			swapState();
		}
		// reset the boolean back to false for use on next CellWorld tick
		shouldSwap = false;
	}
	
	
}
