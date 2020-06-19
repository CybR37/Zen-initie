package view;

import java.util.ArrayList;
import java.io.Serializable;

import model.Square;
import model.Pawn;
import model.PawnType;

public class Game implements Serializable {

    /** The version for serialization and deserialization */
	private static final long serialVersionUID = 1;
	
    /**
     * Prints the sentence at the beginning of the game
     * @param currentPlayerName name of the current player
     */
    public void showStartShell(String currentPlayerName){
        System.out.println("Le joueur "+currentPlayerName+" commence !");
    }

    /**
	 * Prints the game interface
     * @param grid game grid
     * @param list pawn list
     * @param currentPlayerName name of the current player
	 */
	public void showGridShell(Square[][] grid, ArrayList<Pawn> list, String currentPlayerName) {
		if(grid.length != 0){
			boolean found;
			Pawn p;
			int k;

			System.out.println("-------------------------------------------------------------------");
			for(int i=grid[0].length; i > 0; i--){
				System.out.println("-------------------------------------------------");
				System.out.print("|");
				if(i < 10){
					System.out.print(" "+i+" |");
				} else{
					System.out.print(i+" |");
				}

				for(int j=0; j < grid.length; j++){
					if(!grid[j][i-1].isFree()){
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
										System.out.print(" N |");
									} else if(p.getType() == PawnType.WHITE){
										System.out.print(" B |");
									} else{
										System.out.print(" Z |");
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
										System.out.print(" \u25E6 |");
									} else if(p.getType() == PawnType.WHITE){
										System.out.print(" \u2022 |");
									} else{
										System.out.print(" Z |");
									}
								}
								k++;
							}
						}
					} else{
						System.out.print("   |");
					}
				}
				// Indicators
				if(System.getProperty("os.name").toLowerCase().contains("win")){
					if(i == grid[0].length-1){
						System.out.print("\tN Pions noirs");
					} else if(i == grid[0].length-2){
						System.out.print("\tB Pions blancs");
					} else if(i == grid[0].length-3){
						System.out.print("\tZ Pion Zen");
					} else if(i == grid[0].length-5){
						System.out.print("\tJoueur "+currentPlayerName);
					}
				} else{
					if(i == grid[0].length-1){
						System.out.print("\t\u25E6 Pions noirs");
					} else if(i == grid[0].length-2){
						System.out.print("\t\u2022 Pions blancs");
					} else if(i == grid[0].length-3){
						System.out.print("\tZ Pion Zen");
					} else if(i == grid[0].length-5){
						System.out.print("\tJoueur "+currentPlayerName);
					}
				}
				System.out.print("\n");
			}
			System.out.println("-------------------------------------------------");
			System.out.println("    | A | B | C | D | E | F | G | H | I | J | K |");
			System.out.println("    ---------------------------------------------");
			System.out.println();
		} else{
			System.out.println("Erreur Game.showGrid(): la grille n'a pas ete initialisee");
		}
    }
    
    /**
     * Prints the sentence at the end of the game
     */
    public void showStopShell(){
        System.out.println("Arret de la partie...");
    }

    /**
     * Prints the sentence according the state of the game and if its a draw
     * @param isDraw true if is a draw, otherwise false
     * @param winnerName winner name
     */
    public void showWinShell(boolean isDraw, String winnerName){
        if(isDraw){
            System.out.println("Match nul !");
        } else{
            System.out.println("Le joueur "+winnerName+" gagne la partie !");
        }
    }
}