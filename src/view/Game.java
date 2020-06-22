package view;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import model.Square;
import model.Pawn;
import model.PawnType;

import controller.ActionCGame;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class Game{

	/** Game controller instance */
	private ActionCGame controlGame;
	/** GUI instance */
	private JFrame frame;
	/** Grid instance */
	private JTable tGrid;
	/** Valid pawn button */
	private JButton bValidPawn;
	/** Valid move button */
	private JButton bValidMove;
	/** Back to menu button */
	private JButton bBackMenu;
	/** Selection pawn textfield */
	private JTextField tSelectPawn;
	/** Selection coordinates textfield */
	private JTextField tSelectCoords;
	/** Selection pawn label */
	private JLabel lSelectPawn;
	/** Selection coordinates textfield */
	private JLabel lSelectMove;
	/** Current player label */
	private JLabel lCurrentPlayer;

	/**
	 * Class constructor, initializes the attributes with the parameters
	 * @param acGame game controller instance
	 * @param frame GUI instance
	 */
	public Game(ActionCGame acGame, JFrame frame){
		if(acGame != null && frame != null){
			this.controlGame = acGame;
			this.frame = frame;

			this.lSelectPawn = new JLabel("Selectionnez un pion");
			this.lSelectMove = new JLabel("Vers quelles coordonnees");
			this.tSelectPawn = new JTextField();
			this.tSelectCoords = new JTextField();
			this.bValidPawn = new JButton("Valider la selection");
			this.bValidMove = new JButton("Valider");
			this.bBackMenu = new JButton("Retour au menu");
			this.bValidPawn.setBackground(new Color(215, 215, 215));
			this.bValidMove.setBackground(new Color(215, 215, 215));
			this.bBackMenu.setBackground(new Color(215, 215, 215));
			this.bValidPawn.addActionListener(this.controlGame);
			this.bValidMove.addActionListener(this.controlGame);
			this.bBackMenu.addActionListener(this.controlGame);
		} else{
			System.out.println("Erreur Game(): parametre non valide");
		}
	}

	/**
     * Prints the graphical sentence at the beginning of the game
     * @param currentPlayerName name of the current player
     */
    public void showStartGUI(String currentPlayerName){
		if(currentPlayerName != null){
        	JOptionPane.showMessageDialog(this.frame, "Le joueur "+currentPlayerName+" commence !", "", JOptionPane.INFORMATION_MESSAGE);
		} else{
			System.out.println("Erreur Game.showStartGUI(): parametre non valide");
		}
	}

	/**
	 * Prints the graphical game interface
     * @param grid game grid
     * @param list pawn list
     * @param currentPlayerName name of the current player
	 */
	public void showGridGUI(Square[][] grid, ArrayList<Pawn> list, String currentPlayerName){
		if(grid != null && list != null && currentPlayerName != null){
			this.lCurrentPlayer = new JLabel("Joueur "+currentPlayerName+"  ", JLabel.RIGHT);
			// Model for the JTable
			TableModel gridModel = new AbstractTableModel(){
				public Object getValueAt(int row, int col){
					ImageIcon img = new ImageIcon(Menu.RES_IMG_PATH+"blank.jpg");
					if(!grid[col][row].isFree()){
						boolean found = false;
						int i = 0;
						while(i < list.size() && !found){
							Pawn p = list.get(i);
							if(p.isAt(col, row)){
								found = true;
								if(p.getType() == PawnType.BLACK){
									img = new ImageIcon(Menu.RES_IMG_PATH+"black.jpg");
								} else if(p.getType() == PawnType.WHITE){
									img = new ImageIcon(Menu.RES_IMG_PATH+"white.jpg");
								} else{
									img = new ImageIcon(Menu.RES_IMG_PATH+"zen.jpg");
								}
							}
							i++;
						}
					}
					return img;
				}

				public int getRowCount(){
					return grid[0].length;
				}

				public int getColumnCount(){
					return grid.length;
				}

				public String getColumnName(int index){
					String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
					String ret;
					if(index/letters.length == 0){
						ret = letters[index];
					} else{
						ret = letters[(index/letters.length)-1]+letters[index-((index/letters.length)*letters.length)];
					}
					return ret;
				}

				public Class<?> getColumnClass(int col) {
					return this.getValueAt(0, col).getClass();
				}
			};
			this.tGrid = new JTable(gridModel);
			this.tGrid.setShowGrid(true);
			this.tGrid.setGridColor(Color.BLACK);
			this.tGrid.setSelectionForeground(new Color(227, 227, 227));
			this.tGrid.setRowHeight(((int) this.frame.getSize().getHeight()-70)/grid.length);
			JScrollPane spGrid = new JScrollPane(this.tGrid);

			JPanel framePane = new JPanel(new GridLayout(1, 2, 10, 0));

			JPanel labelCurrentPlayerPane = new JPanel();
			labelCurrentPlayerPane.add(this.lCurrentPlayer);
			labelCurrentPlayerPane.setBackground(new Color(215, 215, 215));
			labelCurrentPlayerPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			JPanel paneForRightButtons = new JPanel(new BorderLayout(0, 130));
			paneForRightButtons.add(this.bBackMenu, BorderLayout.SOUTH);
			paneForRightButtons.add(this.bValidMove, BorderLayout.CENTER);
			paneForRightButtons.setBackground(new Color(247, 247, 247));
			paneForRightButtons.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
			JPanel extremeRightPane = new JPanel(new BorderLayout(0, 130));
			extremeRightPane.add(labelCurrentPlayerPane, BorderLayout.NORTH);
			extremeRightPane.add(paneForRightButtons, BorderLayout.CENTER);
			extremeRightPane.setBackground(new Color(247, 247, 247));
			extremeRightPane.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
			JPanel midRightPane = new JPanel(new GridLayout(6, 1, 0, 10));
			midRightPane.add(new JLabel());
			midRightPane.add(new JLabel());
			JPanel coordsPawnPane = new JPanel(new BorderLayout(10, 10));
			coordsPawnPane.add(this.lSelectPawn, BorderLayout.NORTH);
			coordsPawnPane.add(this.bValidPawn, BorderLayout.EAST);
			coordsPawnPane.add(this.tSelectPawn, BorderLayout.CENTER);
			coordsPawnPane.setBackground(new Color(247, 247, 247));
			midRightPane.add(coordsPawnPane);
			JPanel coordsMovePane = new JPanel(new GridLayout(2, 1, 0, 10));
			coordsMovePane.add(this.lSelectMove);
			coordsMovePane.add(this.tSelectCoords);
			coordsMovePane.setBackground(new Color(247, 247, 247));
			midRightPane.add(coordsMovePane);
			midRightPane.setBackground(new Color(247, 247, 247));
			JPanel rightPane = new JPanel(new BorderLayout(50, 0));
			rightPane.add(extremeRightPane, BorderLayout.EAST);
			rightPane.add(midRightPane, BorderLayout.CENTER);
			rightPane.setBackground(new Color(247, 247, 247));

			framePane.add(spGrid);
			framePane.add(rightPane);
			framePane.setBackground(new Color(247, 247, 247));

			this.frame.setContentPane(framePane);
			this.frame.revalidate();
			this.frame.repaint();
		} else{
			System.out.println("Erreur Game.showGridGUI(): parametre non valide");
		}
	}

	/**
     * Prints the graphical sentence at the end of the game
     */
    public void showStopGUI(){
		JOptionPane.showMessageDialog(this.frame, "Arret de la partie...", "", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
     * Prints the graphical sentence according the state of the game and if its a draw
     * @param isDraw true if is a draw, otherwise false
     * @param winnerName winner name
     */
    public void showWinGUI(boolean isDraw, String winnerName){
		if(winnerName != null){
			if(isDraw){
				JOptionPane.showMessageDialog(this.frame, "Match nul !", "", JOptionPane.INFORMATION_MESSAGE);
			} else{
				JOptionPane.showMessageDialog(this.frame, "Le joueur "+winnerName+" gagne la partie !", "", JOptionPane.INFORMATION_MESSAGE);
			}
		} else{
			System.out.println("Erreur Game.showWinGUI(): parametre non valide");
		}
	}

    /**
     * Prints the text sentence at the beginning of the game
     * @param currentPlayerName name of the current player
     */
    public void showStartShell(String currentPlayerName){
		if(currentPlayerName != null){
			System.out.println("Le joueur "+currentPlayerName+" commence !");
		} else{
			System.out.println("Erreur Game.showStartShell(): parametre non valide");
		}
    }

    /**
	 * Prints the text game interface
     * @param grid game grid
     * @param list pawn list
     * @param currentPlayerName name of the current player
	 */
	public void showGridShell(Square[][] grid, ArrayList<Pawn> list, String currentPlayerName) {
		if(grid != null && list != null && currentPlayerName != null){
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
		} else{
			System.out.println("Erreur Game.showGridShell(): parametre non valide");
		}
    }
    
    /**
     * Prints the text sentence at the end of the game
     */
    public void showStopShell(){
        System.out.println("Arret de la partie...");
    }

    /**
     * Prints the text sentence according the state of the game and if its a draw
     * @param isDraw true if is a draw, otherwise false
     * @param winnerName winner name
     */
    public void showWinShell(boolean isDraw, String winnerName){
		if(winnerName != null){
			if(isDraw){
				System.out.println("Match nul !");
			} else{
				System.out.println("Le joueur "+winnerName+" gagne la partie !");
			}
		} else{
			System.out.println("Erreur Game.showWinShell(): parametre non valide");
		}
	}

	/**
	 * Returns the grid instance
	 * @return grid instance
	 */	
	public JTable getTGrid() {
		return this.tGrid;
	}

	/**
	 * Returns the valid pawn button instance
	 * @return valid pawn button instance
	 */
	public JButton getBValidPawn() {
		return this.bValidPawn;
	}

	/**
	 * Returns the valid move button instance
	 * @return valid move button instance
	 */
	public JButton getBValidMove() {
		return this.bValidMove;
	}

	/**
	 * Returns the back to menu button instance
	 * @return back to menu button instance
	 */
	public JButton getBBackMenu() {
		return this.bBackMenu;
	}

	/**
	 * Returns the selection pawn textfield instance
	 * @return selection pawn textfield instance
	 */
	public JTextField getTSelectPawn() {
		return this.tSelectPawn;
	}

	/**
	 * Returns the selection move textfield instance
	 * @return selection move textfield instance
	 */
	public JTextField getTSelectCoords() {
		return this.tSelectCoords;
	}

	/**
	 * Returns the selection pawn label instance
	 * @return selection pawn label instance
	 */
	public JLabel getLSelectPawn() {
		return this.lSelectPawn;
	}

	/**
	 * Returns the selection move label instance
	 * @return selection move label instance
	 */
	public JLabel getLSelectMove() {
		return this.lSelectMove;
	}

	/**
	 * Returns the current player label instance
	 * @return current player label instance
	 */
	public JLabel getLCurrentPlayer() {
		return this.lCurrentPlayer;
	}
}