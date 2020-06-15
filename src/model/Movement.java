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
	/** Movement author */
	private PawnType author;
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
	public Movement(Pawn pawn, PawnType author, int newX, int newY, ArrayList<Pawn> pawnList, int width, int height) {
		if(pawn != null && author != null && pawnList != null && width == 11 && height == 11){
			this.pawn = pawn;
			this.author = author;
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
		int ret = 0;

		if(this.dir == Direction.W || this.dir == Direction.E){
			ret = this.nX - this.oX;
		} else if(this.dir == Direction.N || this.dir == Direction.S){
			ret = this.nY - this.oY;
		} else if(this.dir == Direction.NW || this.dir == Direction.NE || this.dir == Direction.SW || this.dir == Direction.SE){
			ret = this.nX - this.oX;
		}

		return Math.abs(ret);
	}

	/**
	 * Checks if the movement is valid
	 * @return true if valid, otherwise false
	 */
	public boolean isValid() {
		boolean valid = false;
		if(this.nX >= 0 && this.nX < this.width 
		&& this.nY >= 0 && this.nY < this.height){
			if(this.pawn.getType() == this.author || (this.pawn.getType() == PawnType.ZEN && this.nearbyPawns(this.pawn.getX(), this.pawn.getY(), this.pawnList) > 0)){
				if(this.dir != null){ // No problem for the direction
					int pawnCounter = 0;
					boolean ennemyPawnBlocking = false;
					PawnType ennemyColor = null;
					if(this.author == PawnType.BLACK){
						ennemyColor = PawnType.WHITE;
					} else if(this.author == PawnType.WHITE){
						ennemyColor = PawnType.BLACK;
					}

					int g;
					int h;
					int j;
					boolean found;
					// Count the number of pawns according to the direction of the movement
					// Plus, detect if an enemy pawn is blocking movement
					if(this.dir == Direction.N || this.dir == Direction.S){
						for(int i=0; i < this.height; i++){
							found = false;
							j = 0;
							while(j < this.pawnList.size() && !found && !ennemyPawnBlocking){
								if(this.pawnList.get(j).isAt(this.oX, i)){
									if(this.dir == Direction.N && i > this.oY && i < this.nY){
										if(this.pawnList.get(j).getType() == ennemyColor){
											ennemyPawnBlocking = true;
										}
									} else if(this.dir == Direction.S && i > this.nY && i < this.oY){
										if(this.pawnList.get(j).getType() == ennemyColor){
											ennemyPawnBlocking = true;
										}
									}
									found = true;
									pawnCounter++;
								}
								j++;
							}
						}
					} else if(this.dir == Direction.NW || this.dir == Direction.SE){
						g = this.oX;
						h = this.oY;
						while(g < this.width && h >= 0){ // Explore SE line
							found = false;
							j = 0;
							while(j < this.pawnList.size() && !found && !ennemyPawnBlocking){
								if(this.pawnList.get(j).isAt(g, h)){
									if(g > this.oX && g < this.nX){
										if(this.pawnList.get(j).getType() == ennemyColor){
											ennemyPawnBlocking = true;
										}
									}
									found = true;
									pawnCounter++;
								}
								j++;
							}
							g++;
							h--;
						}

						// Avoid counting the pawn twice
						g = this.oX-1;
						h = this.oY+1;
						while(g >= 0 && h < this.height){ // Explore NW line
							found = false;
							j = 0;
							while(j < this.pawnList.size() && !found && !ennemyPawnBlocking){
								if(this.pawnList.get(j).isAt(g, h)){
									if(g > this.nX && g < this.oX){
										if(this.pawnList.get(j).getType() == ennemyColor){
											ennemyPawnBlocking = true;
										}
									}
									found = true;
									pawnCounter++;
								}
								j++;
							}
							g--;
							h++;
						}
					} else if(this.dir == Direction.NE || this.dir == Direction.SW){
						g = this.oX;
						h = this.oY;
						while(g < this.width && h < this.height){ // Explore NE line
							found = false;
							j = 0;
							while(j < this.pawnList.size() && !found && !ennemyPawnBlocking){
								if(this.pawnList.get(j).isAt(g, h)){
									if(g > this.oX && g < this.nX){
										if(this.pawnList.get(j).getType() == ennemyColor){
											ennemyPawnBlocking = true;
										}
									}
									found = true;
									pawnCounter++;
								}
								j++;
							}
							g++;
							h++;
						}

						// Avoid counting the pawn twice
						g = this.oX-1;
						h = this.oY-1;
						while(g >= 0 && h >= 0){ // Explore SW line
							found = false;
							j = 0;
							while(j < this.pawnList.size() && !found && !ennemyPawnBlocking){
								if(this.pawnList.get(j).isAt(g, h)){
									if(g > this.nX && g < this.oX){
										if(this.pawnList.get(j).getType() == ennemyColor){
											ennemyPawnBlocking = true;
										}
									}
									found = true;
									pawnCounter++;
								}
								j++;
							}
							g--;
							h--;
						}
					} else{ // W/E
						for(int i=0; i < this.width; i++){
							found = false;
							j = 0;
							while(j < this.pawnList.size() && !found && !ennemyPawnBlocking){
								if(this.pawnList.get(j).isAt(i, this.oY)){
									if(this.dir == Direction.E && i > this.oX && i < this.nX){
										if(this.pawnList.get(j).getType() == ennemyColor){
											ennemyPawnBlocking = true;
										}
									} else if(this.dir == Direction.W && i > this.nX && i < this.oX){
										if(this.pawnList.get(j).getType() == ennemyColor){
											ennemyPawnBlocking = true;
										}
									}
									found = true;
									pawnCounter++;
								}
								j++;
							}
						}
					}

					if(!ennemyPawnBlocking && pawnCounter == this.computeDelta()){
						found = false;
						int i = 0;
						while(i < this.pawnList.size() && !found){
							if(this.pawnList.get(i).isAt(this.nX, this.nY) && this.pawnList.get(i).getType() == this.author){
								found = true;
							}
							i++;
						}
						if(!found){
							valid = true;
						}
					}
				}
			}
		}
		return valid;
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

	/**
	 * Search for pawns next to these coordinates
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param pawnList pawn checklist
	 * @return number of connected pawns
	 */
	public int nearbyPawns(int x, int y, ArrayList<Pawn> pawnList){
		int ret = 0;
		if(x >= 0 && x < this.width && y >= 0 && y < this.height && pawnList != null){
			for (Pawn p : pawnList) {
				if(p.isAt(x-1, y-1) // Bottom left corner
				|| p.isAt(x, y-1) // Bottom side
				|| p.isAt(x+1, y-1) // Bottom right corner
				|| p.isAt(x-1, y) // Left side
				|| p.isAt(x+1, y) // Right side
				|| p.isAt(x-1, y+1) // Top left corner
				|| p.isAt(x, y+1) // Top side
				|| p.isAt(x+1, y+1)){ // Top right corner
					ret++;
				}
			}
		} else{
			System.err.println("Erreur Player.nearbyPawns(): parametre non valide");
		}
		return ret;
	}
}