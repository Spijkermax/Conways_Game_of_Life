public class CellWorld {
	Cell[][] world;

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
				world[j][i] = new Cell();
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

	public void tick() {
		for (int j = 0; j < world.length; j++) {
			for (int i = 0; i < world[j].length; i++) {
				world[j][i].swapState();
			}
		}
		drawWorld();

	}
	
	public void checkBorders() {
		if (shouldAddTop()) {
			addTop();
		}
		if (shouldAddBot()) {
//			addBot();
		}
		if (shouldAddLeft()) {
//			addLeft();
		}
		if (shouldAddRight()) {
//			addRight();
		}
	}
	
	//method checks if there are any live cells in the topmost row of cells
	public boolean shouldAddTop() {
		for (int i = 0; i < world[0].length; i++) {
			if (world[0][i].isAlive()) {
				return true;
			}
		}
		return false;
	}
	
	//method checks if any live cells in the bottommost row of cells
	public boolean shouldAddBot() {
		for (int i = 0; i < world[world.length-1].length; i++) {
			if (world[world.length-1][i].isAlive()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean shouldAddLeft() {
		for (int j = 0; j < world.length; j++) {
			if (world[j][0].isAlive()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean shouldAddRight() {
		for (int j = 0; j < world.length; j++) {
			if (world[j][world[j].length-1].isAlive()) {
				return true;
			}
		}
		return false;
	}
	
	public void addTop() {
		Cell[][] updatedWorld = new Cell[world.length+1][world[0].length];
		
		
	}
}