package model;

import java.util.ArrayList;

/**
 * Super class player, manages the player's moves and pawns
 * @author Théo Koenigs
 */
public abstract class Player implements java.io.Serializable {

	/** The version for serialization and deserialization */
	private static final long serialVersionUID = 1;
	/** Player name */
	protected String name;
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
	 * @param name player name
	 * @param playerPawn the player's pawns
	 * @param pawnList list of game pawns
	 * @param width number of columns (grid width)
	 * @param height number of rows (grid height)
	 */
	public Player(UIMode ui, String name, ArrayList<Pawn> playerPawn, ArrayList<Pawn> pawnList, int width, int height) {
		if(ui != null && playerPawn != null && pawnList != null && width == 11 && height == 11){
			this.ui = ui;
			this.myPawns = playerPawn;
			this.pawnList = pawnList;
			this.width = width;
			this.height = height;
		} else{
			System.err.println("Erreur Player(): parametre non valide");
		}
	}

	/**
	 * Changes the interface mode used
	 * @param ui new interface mode
	 */
	public void setUI(UIMode ui) {
		if(ui != null){
			this.ui = ui;
		} else{
			System.err.println("Erreur Player.setUI(): parametre non valide");
		}
	}

	/**
	 * Checks if all of the player's pawns are connected
	 * @return true if all of the player's pawns are connected, otherwise false
	 */
	public boolean isAllConnected(){
		// TODO - implement Player.isAllConnected
		return false;
	}

	private int nearbyPawns(int x, int y){
		// TODO - implement Player.nearbyPawns
		return 0;
	}
}