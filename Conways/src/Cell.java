
public class Cell {
	boolean alive;
	String symbol;
	
	public Cell() {
		alive = false;
		symbol = " ";
		
		Double d = Math.random();
		if (d < 0.2) {
			swapState();
		}
	}
	
	public boolean isAlive() {
		return alive;
		
	}
	
	public void swapState() {
		alive = !alive;
		if (symbol.equals(" ")) {
			symbol = "O";
		} else if (symbol.equals("O")) {
			symbol = " ";
		}
	}
	
	public String getSymbol() {
		return symbol;
	}

}
