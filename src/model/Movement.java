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
		if(pawn != null && pawnList != null && width == 11 && height == 11){
			this.pawn = pawn;
			this.pawnList = pawnList;
			this.oX = this.pawn.getX();
			this.oY = this.pawn.getY();
			this.nX = newX;
			this.nY = newY;
			this.width = width;
			this.height = height;
			
			int dX = this.nX - this.oX;
			int dY = this.nY - this.oY;
			// North/South
			if(dX == 0 && dY != 0){
				if(dY > 0){ // North
					this.dir = Direction.N;
				} else{ // South
					this.dir = Direction.S;
				}
			} else if(dX != 0 && dY == 0){ // West/East
				if(dX > 0){ // East
					this.dir = Direction.E;
				} else{ // West
					this.dir = Direction.W;
				}
			} else if(dX != 0 && dX == dY){ // SW/NE
				if(dX > 0){ // North East
					this.dir = Direction.NE;
				} else{ // South West
					this.dir = Direction.SW;
				}
			} else if(dX != 0 && Math.abs(dX) == Math.abs(dY)){ // NW/SE
				if(dX > 0 && dY < 0){ // South East
					this.dir = Direction.SE;
				} else{ // North West
					this.dir = Direction.NW;
				}
			}
		} else{
			System.err.println("Erreur Movement(): parametre non valide");
		}
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