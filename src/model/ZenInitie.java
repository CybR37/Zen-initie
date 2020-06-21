package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import controller.ActionCGame;

import java.io.Serializable;

/**
 * The game manager
 * @author Théo Koenigs
 */
public class ZenInitie implements Serializable {

    /** The version for serialization and deserialization */
    private static final long serialVersionUID = 1;
	/** Path of saved games */
	public static final String SAVE_PATH = "./saves/";
	/** Game instance */
	private Game currentGame;
	/** The interface mode used */
	private UIMode ui;
	/** Game controller instance */
	private ActionCGame gameController;

	/**
	 * Class constructor, initializes attribute ui with default values
	 * @param menuC menu controller instance
	 * @param gameC game controller instance
	 */
	public ZenInitie(ActionCGame gameC) {
		if(gameC != null){
			this.gameController = gameC;
			this.ui = UIMode.GRAPH;
		} else{
			System.out.println("Erreur ZenInitie(): parametre non valide");
		}
	}

	/**
	 * Loads a game instance from a file
	 * @param fileName the file name
	 */
	public void loadGame(String fileName) {
		try{
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(new File(fileName)));
			this.currentGame = (Game) input.readObject();
			this.currentGame.setUI(this.ui);
			input.close();
		} catch(java.io.FileNotFoundException e){
			System.out.println("Erreur ZenInitie.loadGame(): "+e.getMessage());
		} catch(java.io.IOException e){
			System.out.println("Erreur ZenInitie.loadGame(): erreur dans la gestion du fichier");
		} catch(ClassNotFoundException e){
			System.out.println("Erreur ZenInitie.loadGame(): classe non trouvee");
		}
	}

	/**
	 * Initializes the currentGame attribute with a new instance of the Game class with the selected game mode
	 * @param gameMode selected game mode
	 * @param width number of columns (grid width)
	 * @param height number of rows (grid height)
	 */
	public void newGame(PlayerMode gameMode, int width, int height) {
		this.currentGame = new Game(this.gameController, this.ui, gameMode, width, height);
		this.currentGame.start();
	}

	/**
	 * Returns the interface mode used
	 * @return interface mode used
	 */
	public UIMode getUI() {
		return this.ui;
	}

	/**
	 * Changes the interface mode used
	 * @param ui new interface mode
	 */
	public void setUI(UIMode ui) {
		if(ui != null){
			this.ui = ui;
		} else{
			System.out.println("Erreur Game.setUI(): parametre non valide");
		}
	}

	/**
	 * Returns the game instance
	 * @return game instance
	 */
	public Game getCurrentGame() {
		return this.currentGame;
	}
}