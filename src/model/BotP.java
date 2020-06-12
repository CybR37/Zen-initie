package model;

import java.util.ArrayList;

/**
 * Bot player class, makes moves using a specific method
 * @author Théo Koenigs
 */
public class BotP extends Player {

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
	public BotP(UIMode ui, String name, ArrayList<Pawn> playerPawn, ArrayList<Pawn> pawnList, int width, int height) {
		super(ui, name, playerPawn, pawnList, width, height);
	}

	/**
	 * Generates a new valid move
	 * @return the move generated
	 */
	public Movement newMove() {
		// TODO - implement BotP.newMove
		return null;
	}
}