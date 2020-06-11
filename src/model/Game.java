package model;

import java.util.ArrayList;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.File;

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
		if(ui != null && mode != null && width == 11 && height == 11){
			this.ui = ui;
			this.mode = mode;
			this.width = width;
			this.height = height;

			this.whitePawn = new ArrayList<Pawn>();
			this.blackPawn = new ArrayList<Pawn>();
			this.zenPawn = new Pawn(0, 0, PawnType.ZEN);
				
			this.initializeGrid();
			this.pawnPlacement();
		} else{
			System.err.println("Erreur Game(): parametre non valide");
		}
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
		File folder = new File(ZenInitie.SAVE_PATH);
		if(!folder.exists() || !folder.isDirectory()){
			if(!folder.mkdirs()){
				System.err.println("Erreur Game.saveState(): echec de la creation du dossier "+ZenInitie.SAVE_PATH);
			}
		}
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(fileName)));
			out.writeObject(this);
			out.close();
		} catch(java.io.FileNotFoundException e){
			System.err.println("Erreur Game.savestate(): pas de partie sauvegardee");
		} catch(java.io.IOException e){
			System.err.println("Erreur Game.savestate(): erreur dans la gestion du fichier");
		}
	}

	/**
	 * Creates and places the pawns according to the model
	 */
	private void pawnPlacement() {
		for(int i=0; i < this.grid.length; i++){
			for(int j=0; j < this.grid[i].length; j++){
				Square sq = this.grid[i][j];
				if(sq != null){
					if(sq.getType() == SymbolSquare.CHINESE){
						this.whitePawn.add(new Pawn(i, j, PawnType.BLACK));
					} else if(sq.getType() == SymbolSquare.GEOMETRIC){
						this.blackPawn.add(new Pawn(i, j, PawnType.WHITE));
					}
				} else{
					System.err.println("Erreur Game.pawnPlacement(): la grille n'est pas initialisee");
				}
			}
		}
	}

	/**
	 * Go to the next round, calls {@link #isWin()}, if true ends the game
	 */
	private void nextMove() {
		// TODO - implement Game.nextMove
	}

	/**
	 * Changes the interface mode used
	 * @param ui new interface mode
	 */
	public void setUI(UIMode ui) {
		if(ui != null){
			this.ui = ui;
		} else{
			System.err.println("Erreur Game.setUI(): parametre non valide");
		}
	}

	/**
	 * Creates the game grid
	 */
	private void initializeGrid() {
		this.grid = new Square[this.width][this.height];
		for(int i=0; i < this.grid.length; i++){
			for(int j=0; j < this.grid[i].length; j++){
				//Positions for black pawns
				if(((i == 0 || i == 5) && j == 0) || ((i == 3 || i == 7) && (j == 2 || j == 8)) || ((i == 1 || i == 9)  && (j == 4 || j == 6)) || ((i == 5 || i == 10)  && j == 10)){
					this.grid[i][j] = new Square(SymbolSquare.CHINESE);
				//Positions for white pawns
				} else if(((j == 10 || j == 5) && i == 0) || ((j == 3 || j == 7) && (i == 2 || i == 8)) || ((j == 1 || j == 9)  && (i == 4 || i == 6)) || ((j == 0 || j == 5)  && i == 10)){
					this.grid[i][j] = new Square(SymbolSquare.GEOMETRIC);
				} else{
					this.grid[i][j] = new Square(SymbolSquare.NONE);
				}
			}
		}
	}

	/**
	 * Prints the game interface
	 */
	public void showGrid() {
		// TODO - implement Game.showGrid
	}
}