package model;

import java.util.ArrayList;

/**
 * Human player class, manages the interaction with the player
 * @author Théo Koenigs
 */
public class HumanP extends Player {

	/** The version for serialization and deserialization */
	private static final long serialVersionUID = 1;

	/**
	 * Class constructor, calls the constructor of the superclass to initialize the attributes
	 * @param ui the interface mode used
	 * @param name player name
	 * @param playerPawn the player's pawns
	 * @param pawnList list of game pawns
	 * @param width number of columns (grid width)
	 * @param height number of rows (grid height)
	 */
	public HumanP(UIMode ui, String name, ArrayList<Pawn> playerPawn, ArrayList<Pawn> pawnList, int width, int height) {
		super(ui, name, playerPawn, pawnList, width, height);
	}

	/**
	 * Creates a new move based on the coordinates
	 * @return the move desired by the caller
	 */
	public Movement newMove(int xPawn, int yPawn, int x, int y) {
		Movement ret = null;
		if(xPawn >= 0 && xPawn < this.width && yPawn >= 0 && yPawn < this.height && x >= 0 && x < this.width && y >= 0 && y < this.height){
			Pawn zenPawn = this.pawnList.get(this.pawnList.size()-1);
			if(zenPawn.getType() == PawnType.ZEN && zenPawn.isAt(xPawn, yPawn)){
				ret = new Movement(zenPawn, x, y, this.pawnList, this.width, this.height);
			} else{
				boolean found = false;
				int i = 0;
				while(i < this.myPawns.size() && !found){
					if(this.myPawns.get(i).isAt(xPawn, yPawn)){
						found = true;
						ret = new Movement(this.myPawns.get(i), x, y, this.pawnList, this.width, this.height);
					}
					i++;
				}
			}
		} else{
			System.err.println("Erreur HumanP.newMove(): parametre non valide");
		}
		return ret;
	}

	/**
	 * Reads coordinates from the player's input
	 */
	public int[] readCoords(String coord) {
		int[] ret = null;
		if(coord != null && coord.length() == 2){
			char firstCoord = coord.charAt(0);
			char secondCoord = coord.charAt(1);

			if(Character.isLetter(firstCoord) && Character.isDigit(secondCoord)){
				firstCoord = Character.toUpperCase(firstCoord);
				int secondCoordInt = Character.digit(secondCoord, Character.MIN_RADIX);
				if(firstCoord >= 'A' && firstCoord <= 'K' && secondCoordInt >= 0 && secondCoordInt < this.height){
					ret = new int[2];
					ret[0] = firstCoord - 'A'; //Convert letter to integer
					ret[1] = secondCoordInt;
				} else{
					System.err.println("Erreur HumanP.readCoords(): coordonnees non valides");
				}

			} else if(Character.isDigit(firstCoord) && Character.isLetter(secondCoord)){
				secondCoord = Character.toUpperCase(secondCoord);
				int firstCoordInt = Character.digit(firstCoord, Character.MIN_RADIX);
				if(secondCoord >= 'A' && secondCoord <= 'K' && firstCoordInt >= 0 && firstCoordInt < this.height){
					ret = new int[2];
					ret[0] = secondCoord - 'A'; //Convert letter to integer
					ret[1] = firstCoordInt;
				} else{
					System.err.println("Erreur HumanP.readCoords(): coordonnees non valides");
				}
			} else {
				System.err.println("Erreur HumanP.readCoords(): coordonnees non valides");
			}
		} else{
			System.err.println("Erreur HumanP.readCoords(): coordonnees non valides");
		}
		return ret;
	}
}