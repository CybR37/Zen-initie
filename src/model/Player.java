package model;

import java.util.ArrayList;

/**
 * Super class player, manages the player's moves and pawns
 * @author Théo Koenigs
 */
public abstract class Player implements java.io.Serializable {

	/** The version for serialization and deserialization */
	private static final long serialVersionUID = 1;
	/** The player's pawns */
	protected ArrayList<Pawn> myPawns;
	/** List of game pawns */
	protected ArrayList<Pawn> pawnList;
	/** The interface mode used */
	protected UIMode ui;
	/** Number of columns (grid width) */
	protected int width;
	/** Number of rows (grid height) */
	protected int height;

	/**
	 * Class constructor, initializes attributes with subclass constructors
	 * @param ui the interface mode used
	 * @param playerPawn the player's pawns
	 * @param pawnList list of game pawns
	 * @param width number of columns (grid width)
	 * @param height number of rows (grid height)
	 */
	public Player(UIMode ui, ArrayList<Pawn> playerPawn, ArrayList<Pawn> pawnList, int width, int height) {
		// TODO - implement Player.Player
	}

	/**
	 * Allows the player to make a move
	 * @return the move made
	 */
	public abstract Movement newMove();

	/**
	 * Changes the interface mode used
	 * @param ui new interface mode
	 */
	public void setUI(UIMode ui) {
		// TODO - implement Player.setUI
	}

	/**
	 * Checks if all of the player's pawns are connected
	 * @return true if all of the player's pawns are connected, otherwise false
	 */
	public boolean isAllConnected(){
		// TODO - implement Player.isAllConnected
		return false;
	}

	/**
	 * Removes pawns captured by the opponent
	 */
	public void removeOutPawns(){
		// TODO - implement Player.removeOutPawns
	}
}