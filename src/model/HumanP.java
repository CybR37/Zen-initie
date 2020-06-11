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
	 * @param playerPawn the player's pawns
	 * @param pawnList list of game pawns
	 * @param width number of columns (grid width)
	 * @param height number of rows (grid height)
	 */
	public HumanP(UIMode ui, ArrayList<Pawn> playerPawn, ArrayList<Pawn> pawnList, int width, int height) {
		super(ui, playerPawn, pawnList, width, height);
	}

	/**
	 * Asks the player a new move
	 * @return the move desired by the player
	 */
	public Movement newMove() {
		// TODO - implement HumanP.newMove
		return null;
	}

	/**
	 * Reads coordinates from the player's input
	 */
	private int[] readCoords() {
		return null;
		// TODO - implement HumanP.readCoords
	}
}