package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import java.util.Scanner;

/**
 * The game manager
 * @author Théo Koenigs
 */
public class ZenInitie {

	/** Path of saved games */
	static final String SAVE_PATH = "../saves/";
	/** Game instance */
	private Game currentGame;
	/** The interface mode used */
	private UIMode ui;

	/**
	 * Class constructor, initializes attribute ui with default values
	 */
	public ZenInitie() {
		this.ui = UIMode.TEXT;
		this.showMenu();
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
			System.err.println("Erreur ZenInitie.loadGame(): "+e.getMessage());
		} catch(java.io.IOException e){
			System.err.println("Erreur ZenInitie.loadGame(): erreur dans la gestion du fichier");
		} catch(ClassNotFoundException e){
			System.err.println("Erreur ZenInitie.loadGame(): classe non trouvee");
		}
	}

	/**
	 * Prints the menu interface
	 */
	@SuppressWarnings("resource") // I remove the resource leak warning because it's intentional to leave the System.in scanner open
	public void showMenu() {
		if(ui == UIMode.TEXT){
			boolean end = false;
			System.out.println(" _____                 ___  _       _ __  _    			");
			System.out.println("/__  /  ___  ____     / ( )(_)___  (_) /_(_)__ 			");
			System.out.println("  / /  / _ \\/ __ \\   / /|// / __ \\/ / __/ / _ \\			");
			System.out.println(" / /__/  __/ / / /  / /  / / / / / / /_/ /  __/			");
			System.out.println("/____/\\___/_/ /_/  /_/  /_/_/ /_/_/\\__/_/\\___/ 			");
			
			while(ui == UIMode.TEXT && !end){
				System.out.println();
				System.out.println("----------------------Menu----------------------");
				System.out.println("1- Nouvelle partie");
				System.out.println("2- Charger la derniere partie");
				System.out.println("3- Regles du jeu");
				System.out.println();
				System.out.println("Entrez \"menu\" a n'importe quel moment pour revenir au menu");
				System.out.println("4- Version graphique");
				System.out.println("5- Quitter");
				System.out.println();

				Scanner in = new Scanner(System.in);
				String rep = in.nextLine();
				if(rep.equals("1")){
					this.showGameSettings();
				} else if(rep.equals("2")){
					this.loadGame(SAVE_PATH + "saveFile.zenSave");
				} else if(rep.equals("3")){
					this.showRules();
				} else if(rep.equals("4")){
					this.setUI(UIMode.GRAPH);
					// TODO - call controller method to start GUI
				} else if(rep.equals("5")){
					end = true;
				}
			}
		}
	}

	/**
	 * Prints the game settings interface
	 */
	@SuppressWarnings("resource") // I remove the resource leak warning because it's intentional to leave the System.in scanner open
	public void showGameSettings() {
		boolean validInput = false;
		do{
			System.out.println("------------------------------------------------");
			System.out.println("Mode de jeu");
			System.out.println("1- 1c1 (compatible 2c2, 3c3, ...)");
			System.out.println("2- 1 contre ordi");
			System.out.println();

			Scanner in = new Scanner(System.in);
			String rep = in.nextLine();
			if(rep.equals("1")){
				this.newGame(PlayerMode.HVH, 11, 11);
				validInput = true;
			} else if(rep.equals("2")){
				this.newGame(PlayerMode.HVB, 11, 11);
				validInput = true;
			} else if(rep.equalsIgnoreCase("menu")){
				validInput = true;
			}
		} while(!validInput);
	}

	/**
	 * Prints the rule interface
	 */
	public void showRules() {
		System.out.println("------------------------------------------------");
		System.out.println("Regles du jeu: ");
		System.out.println();
		System.out.println("But du jeu : le vainqueur est le premier joueur qui reussit a former une chaine continue avec la totalite de ses pions se trouvant encore sur le plateau, y compris le \"Z\", si celui-ci est encore en jeu.");
		System.out.println();
		System.out.println("Debut de partie : les pions sont disposes sur le plateau selon une figure imposee.");
		System.out.println("Deroulement de la partie : chaque joueur deplace a son tour un pion de sa couleur ou le \"Z\" en respectant 4 regles tres simples.");
		System.out.println();
		System.out.println("Regle 1 : les pions se deplacent en ligne droite dans n'importe quelle direction. Tout pion doit toujours se deplacer d'autant de cases qu'il y a de pions sur la ligne de deplacement choisie (horizontale ou verticale ou diagonale). Tous les pions sont pris en compte y compris le pion deplace.");
		System.out.println("Regle 2 : tout pion peut passer par-dessus un ou plusieurs pions de sa couleur, mais jamais par-dessus ceux de son adversaire.");
		System.out.println("Regle 3 : tout pion peut capturer un pion adverse en se placant sur la case occupee par le pion pris en respectant la regle 1. Celui-ci est alors definitivement retire du jeu.");
		System.out.println("Regle 4 : a chaque coup, le \"Z\" pion commun a tous les joueurs peut etre soit blanc soit noir selon l'interet de celui qui joue. Il se deplace comme les autres pions, mais peut aussi etre pris.");
		System.out.println();
	}

	/**
	 * Initializes the currentGame attribute with a new instance of the Game class with the selected game mode
	 * @param gameMode selected game mode
	 */
	public void newGame(PlayerMode gameMode, int width, int height) {
		this.currentGame = new Game(this.ui, gameMode, width, height);
		this.currentGame.start();
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
}