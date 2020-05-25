package model;

/** 
 * Represents a square of the grid
 * @author Théo Koenigs
 */
public class Square implements java.io.Serializable {

	/** The version for serialization and deserialization */
	private static final long serialVersionUID = 1;
	/** Square type */
	private SymbolSquare type;
	/** Square state */
	private boolean free;

	/**
	 * Class constructor, initializes the type attribute with the parameter
	 * @param type square type
	 */
	public Square(SymbolSquare type) {
		// TODO - implement Square.Square
	}

	/**
	 * Returns the state of the Square
	 * @return square state
	 */
	public boolean isFree() {
		return this.free;
	}

	/**
	 * Changes the state of the square
	 * @param free new state
	 */
	public void setFree(boolean free) {
		this.free = free;
	}

	/**
	 * Returns the type of the Square
	 * @return square type
	 */
	public SymbolSquare getType() {
		return this.type;
	}
}