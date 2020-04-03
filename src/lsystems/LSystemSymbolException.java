package lsystems;

public class LSystemSymbolException extends Exception {
	
	private char symbol;
	public LSystemSymbolException(String msg, char symbol) {
		System.out.println(msg);
		this.symbol = symbol;
	}

	public char getSymbol() {
		return symbol;
	}

}
