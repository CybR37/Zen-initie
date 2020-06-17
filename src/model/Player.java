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
		if(ui != null && name != null && playerPawn != null && pawnList != null && width == 11 && height == 11){
			this.ui = ui;
			this.name = name;
			this.myPawns = playerPawn;
			this.pawnList = pawnList;
			this.width = width;
			this.height = height;
		} else{
			System.out.println("Erreur Player(): parametre non valide");
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
			System.out.println("Erreur Player.setUI(): parametre non valide");
		}
	}

	/**
	 * Checks if all of the player's pawns are connected
	 * @return true if all of the player's pawns are connected, otherwise false
	 */
	public boolean isAllConnected(){
		ArrayList<Pawn> list = new ArrayList<Pawn>(this.myPawns);
		if(list.size() != 0){
			Pawn zenPawn = this.pawnList.get(this.pawnList.size()-1);
			if(zenPawn.getType() == PawnType.ZEN){
				list.add(zenPawn);
			}

			int i = 0;
			boolean found;
			ArrayList<Pawn> connectedList;
			// Getting the first pawn to check
			int count0Connections = 0;
			int countMore1Connection;
			Pawn pawnChecked;
			do{
				do{
					i = 0;
					found = false;
					do{
						pawnChecked = list.get(i);
						connectedList = this.nearbyPawns(pawnChecked.getX(), pawnChecked.getY(), list);
						if(connectedList.size() == 1){
							found = true;
						} else if(connectedList.size() == 0){
							count0Connections++;
						}
						i++;
					} while(!found && i < list.size());
					// If all pawns have two or more connections, remove one pawn
					if(!found && count0Connections == 0){
						list.remove(pawnChecked);
					}
				} while(!found && count0Connections == 0);

				boolean end = false;
				countMore1Connection = 0;
				while(!end){
					connectedList = this.nearbyPawns(pawnChecked.getX(), pawnChecked.getY(), list);
					list.remove(pawnChecked);
					if(connectedList.size() == 0){
						end = true;
					} else{
						if(connectedList.size() > 1){
							countMore1Connection++; // Check for multiple branches
						}
						pawnChecked = connectedList.get(0);
					}
				}
			} while(countMore1Connection != 0); // Stop checks when all branches have been checked
		}
		return (list.size() == 0);
	}

	/**
	 * Search for pawns next to these coordinates
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param pawnList pawn checklist
	 * @return connected pawns
	 */
	public ArrayList<Pawn> nearbyPawns(int x, int y, ArrayList<Pawn> pawnList){
		ArrayList<Pawn> ret = null;
		if(x >= 0 && x < this.width && y >= 0 && y < this.height && pawnList != null){
			ret = new ArrayList<Pawn>();
			/*boolean found = false;
			int i = 0;
			// Bottom left corner
			if(x > 0 && y > 0){
				while(i < pawnList.size() && !found){
					if(pawnList.get(i).isAt(x-1, y-1)){
						found = true;
						ret.add(pawnList.get(i));
					}
					i++;
				}
			}
			// Bottom side
			if(y > 0){
				found = false;
				i = 0;
				while(i < pawnList.size() && !found){
					if(pawnList.get(i).isAt(x, y-1)){
						found = true;
						ret.add(pawnList.get(i));
					}
					i++;
				}
			}
			// Bottom right corner
			if(x < this.width-1 && y > 0){
				found = false;
				i = 0;
				while(i < pawnList.size() && !found){
					if(pawnList.get(i).isAt(x+1, y-1)){
						found = true;
						ret.add(pawnList.get(i));
					}
					i++;
				}
			}
			// Left side
			if(x > 0){
				found = false;
				i = 0;
				while(i < pawnList.size() && !found){
					if(pawnList.get(i).isAt(x-1, y)){
						found = true;
						ret.add(pawnList.get(i));
					}
					i++;
				}
			}
			// Right side
			if(x < this.width-1){
				found = false;
				i = 0;
				while(i < pawnList.size() && !found){
					if(pawnList.get(i).isAt(x+1, y)){
						found = true;
						ret.add(pawnList.get(i));
					}
					i++;
				}
			}
			// Top left corner
			if(x > 0 && y < this.height-1){
				found = false;
				i = 0;
				while(i < pawnList.size() && !found){
					if(pawnList.get(i).isAt(x-1, y+1)){
						found = true;
						ret.add(pawnList.get(i));
					}
					i++;
				}
			}
			// Top side
			if(y < this.height-1){
				found = false;
				i = 0;
				while(i < pawnList.size() && !found){
					if(pawnList.get(i).isAt(x, y+1)){
						found = true;
						ret.add(pawnList.get(i));
					}
					i++;
				}
			}
			// Top right corner
			if(x < this.width-1 && y < this.height-1){
				found = false;
				i = 0;
				while(i < pawnList.size() && !found){
					if(pawnList.get(i).isAt(x+1, y+1)){
						found = true;
						ret.add(pawnList.get(i));
					}
					i++;
				}
			}*/
			for (Pawn p : pawnList) {
				if(p.isAt(x-1, y-1) // Bottom left corner
				|| p.isAt(x, y-1) // Bottom side
				|| p.isAt(x+1, y-1) // Bottom right corner
				|| p.isAt(x-1, y) // Left side
				|| p.isAt(x+1, y) // Right side
				|| p.isAt(x-1, y+1) // Top left corner
				|| p.isAt(x, y+1) // Top side
				|| p.isAt(x+1, y+1)){ // Top right corner
					ret.add(p);
				}
			}
		} else{
			System.out.println("Erreur Player.nearbyPawns(): parametre non valide");
		}
		return ret;
	}
}