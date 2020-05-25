package model;

import java.util.ArrayList;

/**
 * Game class, the core of a game
 * @author Théo Koenigs
 */
public class Game implements java.io.Serializable {

	/** The version for serialization and deserialization */
	private static final long serialVersionUID = 1;
	/** List of white pawns */
	private ArrayList<Pawn> whitePawn;
	/** List of black pawns */
	private ArrayList<Pawn> blackPawn;
	/** Zen pawn */
	private Pawn zenPawn;
	/** Current player instance */
	private Player current;
	/** Player 1 instance */
	private Player player1;
	/** Player 2 instance */
	private Player player2;
	/** Game grid */
	private Square[][] grid;
	/** Game mode */
	private PlayerMode mode;
	/** The interface mode used */
	private UIMode ui;
	/** Previous move */
	private Movement oldMov;
	/** Number of columns (grid width) */
	private int width;
	/** Number of rows (grid height) */
	private int height;

	/**
	 * Class constructor, initializes the attributes with the parameters
	 * @param ui the interface mode used
	 * @param mode game mode
	 * @param width number of columns (grid width)
	 * @param height number of rows (grid height)
	 */
	public Game(UIMode ui, PlayerMode mode, int width, int height) {
		// TODO - implement Game.Game
	}

	/**
	 * Starts the game
	 */
	public void start() {
		// TODO - implement Game.start
	}

	/**
	 * Stops the game
	 */
	public void stop() {
		// TODO - implement Game.stop
	}

	/**
	 * Checks if a player has won the game
	 * @return true if a player has won, otherwise false
	 */
	public boolean isWin(){
		// TODO - implement Game.isWin
		return false;
	}

	/**
	 * Saves the game instance to a file
	 * @param fileName file name
	 */
	public void saveState(String fileName) {
		// TODO - implement Game.saveState
	}

	/**
	 * Places the pawns according to the model
	 */
	private void pawnPlacement() {
		// TODO - implement Game.pawnPlacement
	}

	/**
	 * Go to the next round, calls {@link #isWin()}, if true ends the game
	 */
	public void nextMove() {
		// TODO - implement Game.nextMove
	}

	/**
	 * Changes the interface mode used
	 * @param ui new interface mode
	 */
	public void setUI(UIMode ui) {
		// TODO - implement Game.setUI
	}

	/**
	 * Creates the game grid
	 */
	private void initializeGrid() {
		// TODO - implement Game.initializeGrid
	}

	/**
	 * Prints the game interface
	 */
	public void showGrid() {
		// TODO - implement Game.showGrid
	}
}