package model;

import java.util.ArrayList;

/**
 * Represents a movement of the pawn
 * @author Théo Koenigs
 */
public class Movement implements java.io.Serializable {

	/** The version for serialization and deserialization */
	private static final long serialVersionUID = 1;
	/** Movement direction */
	private Direction dir;
	/** Selected pawn */
	private Pawn pawn;
	/** List of game pawns */
	private ArrayList<Pawn> pawnList;
	/** Old x coordinate */
	private int oX;
	/** Old y coordinate */
	private int oY;
	/** New x coordinate */
	private int nX;
	/** New y coordinate */
	private int nY;
	/** Number of columns (grid width) */
	private int width;
	/** Number of rows (grid height) */
	private int height;

	/**
	 * Class constructor, initializes the attributes with the parameters
	 * @param pawn selected pawn
	 * @param newX new x coordinate
	 * @param newY new y coordinate
	 * @param pawnList list of game pawns
	 * @param width number of columns (grid width)
	 * @param height number of rows (grid height)
	 */
	public Movement(Pawn pawn, int newX, int newY, ArrayList<Pawn> pawnList, int width, int height) {
		// TODO - implement Movement.Movement
	}

	/**
	 * Returns the movement direction
	 * @return the movement direction
	 */
	public Direction getDir() {
		return this.dir;
	}

	/**
	 * Calculates the delta of the movement
	 * @return the delta
	 */
	public int computeDelta() {
		// TODO - implement Movement.computeDelta
		return 0;
	}

	/**
	 * Checks if the movement is valid
	 * @return true if valid, otherwise false
	 */
	public boolean isValid() {
		// TODO - implement Movement.isValid
		return false;
	}

	/**
	 * Returns the old x coordinate
	 * @return old x coordinate
	 */
	public int getOX() {
		return this.oX;
	}

	/**
	 * Returns the old y coordinate
	 * @return old y coordinate
	 */
	public int getOY() {
		return this.oY;
	}

	/**
	 * Returns the new x coordinate 
	 * @return the new x coordinate
	 */
	public int getNX() {
		return this.nX;
	}

	/**
	 * Returns the new y coordinate
	 * @return the new y coordinate
	 */
	public int getNY() {
		return this.nY;
	}
}