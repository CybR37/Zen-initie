package model;

/**
 * Represents a pawn
 * @author Théo Koenigs
 */
public class Pawn implements java.io.Serializable {

	/** The version for serialization and deserialization */
	private static final long serialVersionUID = 1;
	/** Pawn type */
	private PawnType type;
	/** X coordinates */
	private int x;
	/** Y coordinates */
	private int y;
	/** Pawn state */
	private boolean out;

	/**
	 * Class constructor, initializes the attributes with the parameters
	 * @param x x coordinates
	 * @param y y coordinates
	 * @param color pawn type 
	 */
	public Pawn(int x, int y, PawnType color) {
		// TODO - implement Pawn.Pawn
	}

	/**
	 * Checks if the pawn is at the x and y coordinates of the parameters
	 * @param x x coordinates
	 * @param y y coordinates
	 * @return true if the pawn is at the x and y coordinates of the parameters, otherwise false
	 */
	public boolean isAt(int x, int y) {
		// TODO - implement Pawn.isAt
		return false;
	}

	/**
	 * Returns the x coordinate
	 * @return the x coordinate
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Changes x coordinates 
	 * @param x new x coordinates
	 */
	public void setX(int x) {
		if(x >= 0){
			this.x = x;
		} else{
			System.out.println("Erreur Pawn.setX(): la coordonnee X ne peut etre negative");
		}
	}

	/**
	 * Returns the y coordinate
	 * @return the y coordinate
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Changes y coordinates 
	 * @param y new y coordinates
	 */
	public void setY(int y) {
		if(y >= 0){
			this.y = y;
		} else{
			System.out.println("Erreur Pawn.setY(): la coordonnee Y ne peut etre negative");
		}
	}

	/**
	 * Returns the pawn type 
	 * @return the pawn type
	 */
	public PawnType getType() {
		return this.type;
	}

	/**
	 * Returns the pawn state
	 * @return the pawn state
	 */
	public boolean isOut(){
		return this.out;
	}

	/**
	 * Changes the pawn state
	 * @param out new pawn state
	 */
	public void setState(boolean out){
		this.out = out;
	}
}