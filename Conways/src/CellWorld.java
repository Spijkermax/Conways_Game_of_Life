
public class CellWorld {
	Cell[][] world;

	//define the starting width(x) and height(y) for a new CellWorld making 2D array with those values 
	public CellWorld(int x, int y) {
		world = new Cell[y][x];
		createWorld();
		drawWorld();
	}

	public void swapState(int x, int y) {
		world[y][x].swapState();
	}

	private void createWorld() {
		for (int j = 0; j < world.length; j++) {
			for (int i = 0; i < world[j].length; i++) {
				world[j][i] = new Cell(i,j);
				Double d = Math.random();
				if (d < 0.7) {
					world[j][i].swapState();
				}
			}
		}
	}

	private void drawWorld() {
		for (int j = 0; j < world.length; j++) {
			String row = "";
			for (int i = 0; i < world[j].length; i++) {
				row += world[j][i].getSymbol();
			}
			System.out.println(row);
		}
		// System.out.println(" ");
		System.out.println("-----------------------------------------------------------------------------------");
		// System.out.println(" ");
	}
	
	public Cell getCell(int x, int y) {
		return world[y][x];
	}

	public void tick() {
		// first iterate through every cell to see if it should swap or not but do not change living status
		// until we have finished iterating through the entire 2D array of cells
		for (int j = 0; j < world.length; j++) {
			for (int i = 0; i < world[j].length; i++) {
				world[j][i].shouldSwap(this);
			}
		}
		for (int j = 0; j < world.length; j++) {
			for (int i = 0; i < world[j].length; i++) {
				world[j][i].update();
			}
		}
		
		checkBorders();
		drawWorld();
	}
	
//	public void checkNeighbours() {
//		for (int j = 0; j < world.length; j++) {
//			for (int i = 0; i < world[j].length; i++) {
//				world[j][i].checkLiveNeighbours(this);
//			}
//		}
//	}

	
	// this method checks the topmost and bottommost row and the leftmost and rightmost column for
	// presence of at least 1 LIVING cell, should there be one or more live cell(s) it will add an extra
	// row/column such that the cellworld maintains atleast a 1cell thick border of dead/off cells.
	public void checkBorders() {
		if (shouldAddTop()) {
			addTop();
		}
		if (shouldAddBot()) {
			addBot();
		}
		if (shouldAddLeft()) {
			addLeft();
		}
		if (shouldAddRight()) {
			addRight();
		}
	}

	// method checks if there are any live cells in the topmost row of cells
	public boolean shouldAddTop() {
		for (int i = 0; i < world[0].length; i++) {
			if (world[0][i].isAlive()) {
				return true;
			}
		}
		return false;
	}

	// method checks if any live cells in the bottommost row of cells
	public boolean shouldAddBot() {
		for (int i = 0; i < world[world.length - 1].length; i++) {
			if (world[world.length - 1][i].isAlive()) {
				return true;
			}
		}
		return false;
	}

	// method checks if any live cells in the leftmost column of cells
	public boolean shouldAddLeft() {
		for (int j = 0; j < world.length; j++) {
			if (world[j][0].isAlive()) {
				return true;
			}
		}
		return false;
	}
	// method checks if any live cells in the rightmost column of cells
	public boolean shouldAddRight() {
		for (int j = 0; j < world.length; j++) {
			if (world[j][world[j].length - 1].isAlive()) {
				return true;
			}
		}
		return false;
	}

	// create a new 2D array with +1 number of rows of old 2d array
	public void addTop() {
		Cell[][] updatedWorld = new Cell[world.length + 1][world[0].length];

			
		// iterate though all cells of old world 2d array
		for (int j = 0; j < world.length; j++) {
			for (int i = 0; i < world[j].length; i++) {
				//update the y value of old array cells y+=1
				world[j][i].setY(world[j][i].getY()+1);
				// the cell in updated world (with row+1) set to cell of old world 
				// if j = 3, we are in 4th row of old world and since we added a row to the top for new world
				// we want to use j+1 as that cell is now in the 5th row in the new world.
				updatedWorld[j+1][i] = world[j][i];
			}
		}
		
		// this code adds new cells the the newly inserted row in the new 2d array
		// not very efficient way to do it, could be improved
		for (int j = 0; j < updatedWorld.length; j++) {
			for (int i = 0; i < updatedWorld[j].length; i++) {
				if(updatedWorld[j][i] == null) {
					updatedWorld[j][i] = new Cell(i,j);
				}
			}
		}
		
		world = updatedWorld;
	}
	
	public void addBot() {
		Cell[][] updatedWorld = new Cell[world.length + 1][world[0].length];
		
		
		
		for (int j = 0; j < world.length; j++) {
			for (int i = 0; i < world[j].length; i++) {
				updatedWorld[j][i] = world[j][i];
			}
		}
		
		for (int j = 0; j < updatedWorld.length; j++) {
			for (int i = 0; i < updatedWorld[j].length; i++) {
				if(updatedWorld[j][i] == null) {
					updatedWorld[j][i] = new Cell(i,j);
				}
			}
		}
		
		
		
		world = updatedWorld;
	}
	
	public void addLeft() {
		Cell[][] updatedWorld = new Cell[world.length][world[0].length + 1];
		
		

		for (int j = 0; j < world.length; j++) {
			for (int i = 0; i < world[j].length; i++) {
				world[j][i].setX(world[j][i].getX()+1);
				updatedWorld[j][i+1] = world[j][i];
			}
		}
		
		for (int j = 0; j < updatedWorld.length; j++) {
			for (int i = 0; i < updatedWorld[j].length; i++) {
				if(updatedWorld[j][i] == null) {
					updatedWorld[j][i] = new Cell(i,j);
				}
			}
		}
		
		world = updatedWorld;
	}
	
	public void addRight() {
		Cell[][] updatedWorld = new Cell[world.length][world[0].length + 1];
		
		

		for (int j = 0; j < world.length; j++) {
			for (int i = 0; i < world[j].length; i++) {
				updatedWorld[j][i] = world[j][i];
			}
		}
		
		for (int j = 0; j < updatedWorld.length; j++) {
			for (int i = 0; i < updatedWorld[j].length; i++) {
				if(updatedWorld[j][i] == null) {
					updatedWorld[j][i] = new Cell(i,j);
				}
			}
		}
		
		world = updatedWorld;
	}
	
}
