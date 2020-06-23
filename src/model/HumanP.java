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
	 * @param xPawn x coordinate of the pawn
	 * @param yPawn y coordinate of the pawn
	 * @param x new x coordinate
	 * @param y new y coordinate
	 * @return the move desired by the caller
	 */
	public Movement newMove(int xPawn, int yPawn, int x, int y) {
		Movement ret = null;
		if(xPawn >= 0 && xPawn < this.width && yPawn >= 0 && yPawn < this.height && x >= 0 && x < this.width && y >= 0 && y < this.height){
			Pawn zenPawn = this.pawnList.get(this.pawnList.size()-1);
			if(zenPawn.getType() == PawnType.ZEN && zenPawn.isAt(xPawn, yPawn)){
				ret = new Movement(zenPawn, this.myPawns.get(0).getType(), x, y, this.pawnList, this.width, this.height);
			} else{
				boolean found = false;
				int i = 0;
				while(i < this.myPawns.size() && !found){
					if(this.myPawns.get(i).isAt(xPawn, yPawn)){
						found = true;
						ret = new Movement(this.myPawns.get(i), this.myPawns.get(0).getType(), x, y, this.pawnList, this.width, this.height);
					}
					i++;
				}
			}
		} else{
			System.out.println("Erreur HumanP.newMove(): parametre non valide");
		}
		return ret;
	}

	/**
	 * Converts coordinates from the raw ones
	 * @param coord raw coordinates
	 * @return converted coordinates
	 */
	public int[] readCoords(String coord) {
		int[] ret = null;
		if(coord != null){
			if(coord.length() == 2){
				char firstCh = coord.charAt(0);
				char secondCh = coord.charAt(1);

				if(Character.isLetter(firstCh) && Character.isDigit(secondCh)){
					char firstCoord = Character.toUpperCase(firstCh);
					int secondCoord = Character.digit(secondCh, Character.MAX_RADIX) - 1;
					if(firstCoord >= 'A' && firstCoord <= 'K' && secondCoord >= 0 && secondCoord < this.height){
						ret = new int[2];
						ret[0] = firstCoord - 'A'; //Convert letter to integer
						ret[1] = secondCoord;
					} else{
						System.out.println("Erreur HumanP.readCoords(): coordonnees non valides");
					}

				} else if(Character.isDigit(firstCh) && Character.isLetter(secondCh)){
					char firstCoord = Character.toUpperCase(secondCh);
					int secondCoord = Character.digit(firstCh, Character.MAX_RADIX) - 1;
					if(firstCoord >= 'A' && firstCoord <= 'K' && secondCoord >= 0 && secondCoord < this.height){
						ret = new int[2];
						ret[0] = firstCoord - 'A'; //Convert letter to integer
						ret[1] = secondCoord;
					} else{
						System.out.println("Erreur HumanP.readCoords(): coordonnees non valides");
					}
				} else {
					System.out.println("Erreur HumanP.readCoords(): coordonnees non valides");
				}
			} else if(coord.length() == 3){
				char firstCh = coord.charAt(0);
				char secondCh = coord.charAt(1);
				char thirdCh = coord.charAt(2);

				if(Character.isLetter(firstCh) && Character.isDigit(secondCh) && Character.isDigit(thirdCh)){
					char firstCoord = Character.toUpperCase(firstCh);
					int secondCoord = Integer.parseInt(String.valueOf(secondCh)+String.valueOf(thirdCh)) - 1;
					if(firstCoord >= 'A' && firstCoord <= 'K' && secondCoord >= 0 && secondCoord < this.height){
						ret = new int[2];
						ret[0] = firstCoord - 'A'; //Convert letter to integer
						ret[1] = secondCoord;
					} else{
						System.out.println("Erreur HumanP.readCoords(): coordonnees non valides");
					}

				} else if(Character.isDigit(firstCh) && Character.isDigit(secondCh) && Character.isLetter(thirdCh)){
					char firstCoord = Character.toUpperCase(thirdCh);
					int secondCoord = Integer.parseInt(String.valueOf(firstCh)+String.valueOf(secondCh)) - 1;
					if(firstCoord >= 'A' && firstCoord <= 'K' && secondCoord >= 0 && secondCoord < this.height){
						ret = new int[2];
						ret[0] = firstCoord - 'A'; //Convert letter to integer
						ret[1] = secondCoord;
					} else{
						System.out.println("Erreur HumanP.readCoords(): coordonnees non valides");
					}
				} else {
					System.out.println("Erreur HumanP.readCoords(): coordonnees non valides");
				}
			} else{
				System.out.println("Erreur HumanP.readCoords(): coordonnees non valides");
			}
		} else{
			System.out.println("Erreur HumanP.readCoords(): coordonnees non valides");
		}
		return ret;
	}
}