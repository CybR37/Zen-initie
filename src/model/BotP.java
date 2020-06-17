package model;

import java.util.ArrayList;

/**
 * Bot player class, makes moves using a specific method
 * @author Théo Koenigs
 */
public class BotP extends Player {

	/** The version for serialization and deserialization */
	private static final long serialVersionUID = 1;
	
	/**
	 * Class constructor, calls the constructor of the superclass to initialize the attributes
	 * @param ui the interface mode used
	 * @param name player name
	 * @param playerPawn the player's pawns
	 * @param pawnList list of game pawns
	 * @param width number of columns (grid width)
	 * @param height number of rows (grid height)
	 */
	public BotP(UIMode ui, String name, ArrayList<Pawn> playerPawn, ArrayList<Pawn> pawnList, int width, int height) {
		super(ui, name, playerPawn, pawnList, width, height);
	}

	/**
	 * Generates a new valid move
	 * @return the move generated
	 */
	public Movement newMove() {
		int choosenArea;
		int mostPawnArea;
		Pawn choosenPawn;
		int choosenDir;
		Movement ret = null;

		int timeout = 0;
		while(ret == null && timeout < 30){
			ArrayList<Pawn> topLeftPawns = new ArrayList<Pawn>();
			ArrayList<Pawn> topRightPawns = new ArrayList<Pawn>();
			ArrayList<Pawn> botLeftPawns = new ArrayList<Pawn>();
			ArrayList<Pawn> botRightPawns = new ArrayList<Pawn>();

			// Group pawns into areas
			for(Pawn p : this.myPawns){
				if(p.getX() < this.width/2 && p.getY() < this.height/2){ botLeftPawns.add(p); }
				else if(p.getX() >= this.width/2 && p.getY() < this.height/2){ botRightPawns.add(p); }
				else if(p.getX() < this.width/2 && p.getY() >= this.height/2){ topLeftPawns.add(p); }
				else{ topRightPawns.add(p); }
			}
			Pawn zen = this.pawnList.get(this.pawnList.size()-1);
			if(zen.getType() == PawnType.ZEN){
				if(zen.getX() < this.width/2 && zen.getY() < this.height/2){ botLeftPawns.add(zen); }
				else if(zen.getX() >= this.width/2 && zen.getY() < this.height/2){ botRightPawns.add(zen); }
				else if(zen.getX() < this.width/2 && zen.getY() >= this.height/2){ topLeftPawns.add(zen); }
				else{ topRightPawns.add(zen); }
			}
			// Select the area with the largest number of pawns
			int maxBot = Math.max(botLeftPawns.size(), botRightPawns.size());
			int maxTop = Math.max(topLeftPawns.size(), topRightPawns.size());
			int max = Math.max(maxBot, maxTop);
			if(max == topLeftPawns.size()){ mostPawnArea = 0; }
			else if(max == topRightPawns.size()){ mostPawnArea = 1; }
			else if(max == botLeftPawns.size()){ mostPawnArea = 2; }
			else{ mostPawnArea = 3; }

			choosenArea = (int) (Math.random()*4);
			choosenDir = (int) (Math.random()*2);
			if(choosenArea == 0){ // Top left area
				choosenPawn = topLeftPawns.get((int) (Math.random()*topLeftPawns.size()));
				if(mostPawnArea == 0){ // Random mouvement
					choosenDir = (int) (Math.random()*8);
					if(choosenDir == 0){
						ret = findValidMove(choosenPawn, Direction.N);
					} else if(choosenDir == 1){
						ret = findValidMove(choosenPawn, Direction.NW);
					} else if(choosenDir == 2){
						ret = findValidMove(choosenPawn, Direction.NE);
					} else if(choosenDir == 3){
						ret = findValidMove(choosenPawn, Direction.W);
					} else if(choosenDir == 4){
						ret = findValidMove(choosenPawn, Direction.E);
					} else if(choosenDir == 5){
						ret = findValidMove(choosenPawn, Direction.S);
					} else if(choosenDir == 6){
						ret = findValidMove(choosenPawn, Direction.SW);
					} else{
						ret = findValidMove(choosenPawn, Direction.SE);
					}
				} else if(mostPawnArea == 1){ // Right & diagonal direction
					if(choosenDir == 0){
						ret = findValidMove(choosenPawn, Direction.E);
					} else{
						ret = findValidMove(choosenPawn, Direction.SE);
					}
				} else if(mostPawnArea == 2){ // Bottom & diagonal direction
					if(choosenDir == 0){
						ret = findValidMove(choosenPawn, Direction.S);
					} else{
						ret = findValidMove(choosenPawn, Direction.SE);
					}
				} else{ // Diagonal direction
					ret = findValidMove(choosenPawn, Direction.SE);
				}
			} else if(choosenArea == 1){ // Top right area
				choosenPawn = topRightPawns.get((int) (Math.random()*topRightPawns.size()));
				if(mostPawnArea == 0){ // Left & diagonal direction
					if(choosenDir == 0){
						ret = findValidMove(choosenPawn, Direction.W);
					} else{
						ret = findValidMove(choosenPawn, Direction.SW);
					}
				} else if(mostPawnArea == 1){ // Random mouvement
					choosenDir = (int) (Math.random()*8);
					if(choosenDir == 0){
						ret = findValidMove(choosenPawn, Direction.N);
					} else if(choosenDir == 1){
						ret = findValidMove(choosenPawn, Direction.NW);
					} else if(choosenDir == 2){
						ret = findValidMove(choosenPawn, Direction.NE);
					} else if(choosenDir == 3){
						ret = findValidMove(choosenPawn, Direction.W);
					} else if(choosenDir == 4){
						ret = findValidMove(choosenPawn, Direction.E);
					} else if(choosenDir == 5){
						ret = findValidMove(choosenPawn, Direction.S);
					} else if(choosenDir == 6){
						ret = findValidMove(choosenPawn, Direction.SW);
					} else{
						ret = findValidMove(choosenPawn, Direction.SE);
					}
				} else if(mostPawnArea == 2){ // Diagonal direction
					ret = findValidMove(choosenPawn, Direction.SW);
				} else{ // Bottom & diagonal direction
					if(choosenDir == 0){
						ret = findValidMove(choosenPawn, Direction.S);
					} else{
						ret = findValidMove(choosenPawn, Direction.SW);
					}
				}
			} else if(choosenArea == 2){ // Bottom left area
				choosenPawn = botLeftPawns.get((int) (Math.random()*botLeftPawns.size()));
				if(mostPawnArea == 0){ // Top & diagonal direction
					if(choosenDir == 0){
						ret = findValidMove(choosenPawn, Direction.N);
					} else{
						ret = findValidMove(choosenPawn, Direction.NE);
					}
				} else if(mostPawnArea == 1){ // Diagonal direction
					ret = findValidMove(choosenPawn, Direction.NE);
				} else if(mostPawnArea == 2){ // Random mouvement
					choosenDir = (int) (Math.random()*8);
					if(choosenDir == 0){
						ret = findValidMove(choosenPawn, Direction.N);
					} else if(choosenDir == 1){
						ret = findValidMove(choosenPawn, Direction.NW);
					} else if(choosenDir == 2){
						ret = findValidMove(choosenPawn, Direction.NE);
					} else if(choosenDir == 3){
						ret = findValidMove(choosenPawn, Direction.W);
					} else if(choosenDir == 4){
						ret = findValidMove(choosenPawn, Direction.E);
					} else if(choosenDir == 5){
						ret = findValidMove(choosenPawn, Direction.S);
					} else if(choosenDir == 6){
						ret = findValidMove(choosenPawn, Direction.SW);
					} else{
						ret = findValidMove(choosenPawn, Direction.SE);
					}
				} else{ // Right & diagonal direction
					if(choosenDir == 0){
						ret = findValidMove(choosenPawn, Direction.E);
					} else{
						ret = findValidMove(choosenPawn, Direction.NE);
					}
				}
			} else{ // Bottom right area
				choosenPawn = botRightPawns.get((int) (Math.random()*botRightPawns.size()));
				if(mostPawnArea == 0){ // Diagonal direction
					ret = findValidMove(choosenPawn, Direction.NW);
				} else if(mostPawnArea == 1){ // Top & diagonal direction
					if(choosenDir == 0){
						ret = findValidMove(choosenPawn, Direction.N);
					} else{
						ret = findValidMove(choosenPawn, Direction.NW);
					}
				} else if(mostPawnArea == 2){ // Left & diagonal direction
					if(choosenDir == 0){
						ret = findValidMove(choosenPawn, Direction.W);
					} else{
						ret = findValidMove(choosenPawn, Direction.NW);
					}
				} else{ // Random mouvement
					choosenDir = (int) (Math.random()*8);
					if(choosenDir == 0){
						ret = findValidMove(choosenPawn, Direction.N);
					} else if(choosenDir == 1){
						ret = findValidMove(choosenPawn, Direction.NW);
					} else if(choosenDir == 2){
						ret = findValidMove(choosenPawn, Direction.NE);
					} else if(choosenDir == 3){
						ret = findValidMove(choosenPawn, Direction.W);
					} else if(choosenDir == 4){
						ret = findValidMove(choosenPawn, Direction.E);
					} else if(choosenDir == 5){
						ret = findValidMove(choosenPawn, Direction.S);
					} else if(choosenDir == 6){
						ret = findValidMove(choosenPawn, Direction.SW);
					} else{
						ret = findValidMove(choosenPawn, Direction.SE);
					}
				}
			}
			timeout++; // A protection to avoid infinite loops
		}

		if(ret == null){
			System.out.println("Erreur BotP.newMove(): l'ordinateur n'a pas pu jouer car aucune possibilite n'etait valide");
		}
		return ret;
	}

	/**
	 * Checks for each box in the direction if the movement is valid
	 * @param pawn base for the coordinates
	 * @param dir direction to check
	 * @return valid movement
	 */
	private Movement findValidMove(Pawn pawn, Direction dir){
		Movement ret = null;
		if(dir == Direction.N){
			int j = pawn.getY() + 1;
			do{
				ret = new Movement(pawn, this.myPawns.get(0).getType(), pawn.getX(), j, this.pawnList, this.width, this.height);
				j++;
			} while(!ret.isValid() && j < this.height);
		} else if(dir == Direction.NW){
			int i = pawn.getX() - 1;
			int j = pawn.getY() + 1;
			do{
				ret = new Movement(pawn, this.myPawns.get(0).getType(), i, j, this.pawnList, this.width, this.height);
				i--;
				j++;
			} while(!ret.isValid() && i >= 0 && j < this.height);
		} else if(dir == Direction.NE){
			int i = pawn.getX() + 1;
			int j = pawn.getY() + 1;
			do{
				ret = new Movement(pawn, this.myPawns.get(0).getType(), i, j, this.pawnList, this.width, this.height);
				i++;
				j++;
			} while(!ret.isValid() && i < this.width && j < this.height);
		} else if(dir == Direction.W){
			int i = pawn.getX() - 1;
			do{
				ret = new Movement(pawn, this.myPawns.get(0).getType(), i, pawn.getY(), this.pawnList, this.width, this.height);
				i--;
			} while(!ret.isValid() && i >= 0);
		} else if(dir == Direction.E){
			int i = pawn.getX() + 1;
			do{
				ret = new Movement(pawn, this.myPawns.get(0).getType(), i, pawn.getY(), this.pawnList, this.width, this.height);
				i++;
			} while(!ret.isValid() && i < this.width);
		} else if(dir == Direction.S){
			int j = pawn.getY() - 1;
			do{
				ret = new Movement(pawn, this.myPawns.get(0).getType(), pawn.getX(), j, this.pawnList, this.width, this.height);
				j--;
			} while(!ret.isValid() && j >= 0);
		} else if(dir == Direction.SW){
			int i = pawn.getX() - 1;
			int j = pawn.getY() - 1;
			do{
				ret = new Movement(pawn, this.myPawns.get(0).getType(), i, j, this.pawnList, this.width, this.height);
				i--;
				j--;
			} while(!ret.isValid() && i >= 0 && j >= 0);
		} else if(dir == Direction.SE){
			int i = pawn.getX() + 1;
			int j = pawn.getY() - 1;
			do{
				ret = new Movement(pawn, this.myPawns.get(0).getType(), i, j, this.pawnList, this.width, this.height);
				i++;
				j--;
			} while(!ret.isValid() && i < this.width && j >= 0);
		}
		if(!ret.isValid()){ ret = null; }
		return ret;
	}
}