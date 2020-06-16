package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

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
	/** Instance of player 1 (white pawn player) */
	private Player player1;
	/** Instance of player 2 (black pawn player) */
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
				
			this.initializeGrid();
			this.pawnPlacement();

			ArrayList<Pawn> pawnList = new ArrayList<Pawn>();
			pawnList.addAll(this.whitePawn);
			pawnList.addAll(this.blackPawn);
			pawnList.add(this.zenPawn);
			this.player1 = new HumanP(this.ui, "1 (Pions blancs)", this.whitePawn, pawnList, this.width, this.height);
			if(this.mode == PlayerMode.HVH){
				this.player2 = new HumanP(this.ui, "2 (Pions noirs)", this.blackPawn, pawnList, this.width, this.height);
			} else{
				this.player2 = new BotP(this.ui, "2", this.blackPawn, pawnList, this.width, this.height);
			}

			if(Math.random() < 0.5){
				this.current = this.player1;
			} else{
				this.current = this.player2;
			}
		} else{
			System.out.println("Erreur Game(): parametre non valide");
		}
	}

	/**
	 * Starts the game, calls {@link #isWin()}, if true ends the game
	 */
	public void start() {
		boolean end = false;
		while(!end){
			this.showGrid();
			if(this.nextMove()){
				end = true;
				this.saveState("saveFile.zenSave");
			}
			this.changeCurrent();
			if(this.isWin()){
				end = true;
				changeCurrent();
				if(this.isWin()){ // If it's a draw
					System.out.println("Match nul !");
				} else{
					changeCurrent();
					System.out.println("Le joueur "+this.current.name+" gagne la partie !");
				}
			}
		}
	}

	/**
	 * Checks if a player has won the game
	 * @return true if a player has won, otherwise false
	 */
	public boolean isWin(){
		return this.current.isAllConnected();
	}

	/**
	 * Saves the game instance to a file
	 * @param fileName file name
	 */
	public void saveState(String fileName) {
		File folder = new File(ZenInitie.SAVE_PATH);
		if(!folder.exists() || !folder.isDirectory()){
			if(!folder.mkdirs()){
				System.out.println("Erreur Game.saveState(): echec de la creation du dossier "+ZenInitie.SAVE_PATH);
			}
		}
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(ZenInitie.SAVE_PATH+fileName)));
			out.writeObject(this);
			out.close();
		} catch(java.io.FileNotFoundException e){
			System.out.println("Erreur Game.savestate(): pas de partie sauvegardee");
		} catch(java.io.IOException e){
			System.out.println("Erreur Game.savestate(): erreur dans la gestion du fichier");
		}
	}

	/**
	 * Creates and places the pawns according to the model
	 */
	private void pawnPlacement() {
		this.zenPawn = new Pawn(this.width/2, this.height/2, PawnType.ZEN);
		if(this.grid[this.width/2][this.height/2] != null){
			this.grid[this.width/2][this.height/2].setFree(false);
		} else{
			System.out.println("Erreur Game.pawnPlacement(): la grille n'est pas initialisee");
		}

		for(int i=0; i < this.grid.length; i++){
			for(int j=0; j < this.grid[i].length; j++){
				Square sq = this.grid[i][j];
				if(sq != null){
					if(sq.getType() == SymbolSquare.CHINESE){
						this.blackPawn.add(new Pawn(i, j, PawnType.BLACK));
						sq.setFree(false);
					} else if(sq.getType() == SymbolSquare.GEOMETRIC){
						this.whitePawn.add(new Pawn(i, j, PawnType.WHITE));
						sq.setFree(false);
					}
				} else{
					System.out.println("Erreur Game.pawnPlacement(): la grille n'est pas initialisee");
				}
			}
		}
	}

	/**
	 * Prepare the next round
	 * @return true if the move is aborted by "menu" keyword
	 */
	@SuppressWarnings("resource")
	public boolean nextMove() {
		boolean endRequired = false;
		boolean mvtValid = false;
		Movement move;
		Scanner in = new Scanner(System.in);
		String coords;

		do{
			if(this.current instanceof HumanP){
				move = null;
				int[] pawnCoords = null;
				int[] newCoords = null;

				do{
					System.out.println("Coordonnees du pion: ");
					coords = in.nextLine();
					if(coords.equalsIgnoreCase("menu")){
						endRequired = true;
					} else{
						pawnCoords = ((HumanP) this.current).readCoords(coords);
					}
				} while(!endRequired && pawnCoords == null);

				while(!endRequired && newCoords == null){
					System.out.println("Nouvelles coordonnees pour le pion: ");
					coords = in.nextLine();
					if(coords.equalsIgnoreCase("menu")){
						endRequired = true;
					} else{
						newCoords = ((HumanP) this.current).readCoords(coords);
					}
				}

				if(pawnCoords != null && newCoords != null){
					move = ((HumanP) this.current).newMove(pawnCoords[0], pawnCoords[1], newCoords[0], newCoords[1]);
				}
			} else{
				move = ((BotP) this.current).newMove();
			}

			if(move != null){
				if(move.getPawn() == this.zenPawn && this.oldMov != null){ // The Zen pawn cannot return to its previous location
					if(this.oldMov.getPawn() != this.zenPawn || move.getNX() != this.oldMov.getOX() || move.getNY() != this.oldMov.getOY()){
						mvtValid = move.isValid();
					}
				} else{
					mvtValid = move.isValid();
				}

				if(mvtValid){
					if(!this.grid[move.getNX()][move.getNY()].isFree()){
						boolean found = false;
						Pawn p;
						ArrayList<Pawn> pawnList = new ArrayList<Pawn>();
						pawnList.addAll(this.whitePawn);
						pawnList.addAll(this.blackPawn);
						pawnList.add(this.zenPawn);
						Iterator<Pawn> it = pawnList.iterator();
						while(it.hasNext() && !found){
							p = it.next();
							if(p.isAt(move.getNX(), move.getNY())){ // Remove captured pawns
								found = true;
								it.remove();
								if(p.getType() == PawnType.BLACK){
									this.blackPawn.remove(p);
								} else if(p.getType() == PawnType.WHITE){
									this.whitePawn.remove(p);
								}
							}
						}
					}

					move.getPawn().setX(move.getNX());
					move.getPawn().setY(move.getNY());

					this.grid[move.getOX()][move.getOY()].setFree(true);
					this.grid[move.getNX()][move.getNY()].setFree(false);

					this.oldMov = move;
				} else{
					System.out.println("Erreur Game.nextMove(): le mouvement demande n'est pas valide");
				}
			} else if(!endRequired){
				System.out.println("Erreur Game.nextMove(): le mouvement demande n'est pas valide");
				System.out.println("Game.nextMove()");
			}
		} while(!endRequired && !mvtValid);
		return endRequired;
	}

	/**
	 * Changes the interface mode used
	 * @param ui new interface mode
	 */
	public void setUI(UIMode ui) {
		if(ui != null){
			this.ui = ui;
			this.player1.setUI(this.ui);
			this.player2.setUI(this.ui);
		} else{
			System.out.println("Erreur Game.setUI(): parametre non valide");
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
		if(this.grid.length != 0){
			ArrayList<Pawn> list = new ArrayList<Pawn>();
			list.addAll(this.whitePawn);
			list.addAll(this.blackPawn);
			list.add(this.zenPawn);
			boolean found;
			Pawn p;
			int k;

			System.out.println("------------------------------------------------");
			for(int i=this.grid[0].length; i > 0; i--){
				if(i < 10){
					System.out.print(i+"  ");
				} else{
					System.out.print(i+" ");
				}

				for(int j=0; j < this.grid.length; j++){
					if(!this.grid[j][i-1].isFree()){
						found = false;
						k = 0;
						// If the program is executed on Windows
						if(System.getProperty("os.name").toLowerCase().contains("win")){
							while(k < list.size() && !found){ // Print B and W for black and white pawns
								p = list.get(k);
								if(p.isAt(j, i-1)){
									found = true;
									list.remove(p);
									if(p.getType() == PawnType.BLACK){
										System.out.print("N ");
									} else if(p.getType() == PawnType.WHITE){
										System.out.print("B ");
									} else{
										System.out.print("Z ");
									}
								}
								k++;
							}
						} else{
							while(k < list.size() && !found){ // Print dots for black and white pawns
								p = list.get(k);
								if(p.isAt(j, i-1)){
									found = true;
									list.remove(p);
									if(p.getType() == PawnType.BLACK){
										System.out.print("\u25E6 ");
									} else if(p.getType() == PawnType.WHITE){
										System.out.print("\u2022 ");
									} else{
										System.out.print("Z ");
									}
								}
								k++;
							}
						}
					} else{
						System.out.print("x ");
					}
				}
				// Indicators
				if(System.getProperty("os.name").toLowerCase().contains("win")){
					if(i == this.grid[0].length-1){
						System.out.print("\tN Pions noirs");
					} else if(i == this.grid[0].length-2){
						System.out.print("\tB Pions blancs");
					} else if(i == this.grid[0].length-3){
						System.out.print("\tZ Pion Zen");
					} else if(i == this.grid[0].length-5){
						System.out.print("\tJoueur "+this.current.name);
					}
				} else{
					if(i == this.grid[0].length-1){
						System.out.print("\t\u25E6 Pions noirs");
					} else if(i == this.grid[0].length-2){
						System.out.print("\t\u2022 Pions blancs");
					} else if(i == this.grid[0].length-3){
						System.out.print("\tZ Pion Zen");
					} else if(i == this.grid[0].length-5){
						System.out.print("\tJoueur "+this.current.name);
					}
				}
				System.out.print("\n");
			}
			System.out.println("   A B C D E F G H I J K");
			System.out.println();
		} else{
			System.out.println("Erreur Game.showGrid(): la grille n'a pas ete initialisee");
		}
	}

	/**
	 * Returns the instance of the current player
	 * @return the current player
	 */
	public Player getCurrent(){
		return this.current;
	}

	/**
	 * Swap the current player between player 1 and player 2
	 */
	public void changeCurrent(){
		if(this.current == this.player1){
			this.current = this.player2;
		} else {
			this.current = this.player1;
		}
	}
}