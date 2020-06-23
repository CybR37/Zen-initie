package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

import model.PawnType;
import model.Pawn;
import model.BotP;
import model.HumanP;
import model.Movement;
import model.UIMode;

/**
 * Game controller, links view class and model class
 * @author Théo Koenigs
 */
public class ActionCGame implements ActionListener {
    
    /** Game model instance */
    private model.Game modelGame;
    /** Game view instance */
    private view.Game viewGame;
    /** Menu controller instance */
    private ActionCMenu controlMenu;
    /** Selected pawn by the player */
    private Pawn selectedPawn;

    /**
     * Triggered when a button is pressed
     * @param e event generated
     */
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.viewGame.getBValidPawn()){
            this.selectedPawn = null;
            int[] convertedCoords = ((HumanP) this.modelGame.getCurrent()).readCoords(this.viewGame.getTSelectPawn().getText());
            if(convertedCoords != null){
                int i = 0;
                while(i < this.modelGame.getListPawn().size() && this.selectedPawn == null){
                    Pawn p = this.modelGame.getListPawn().get(i);
                    if(p.isAt(convertedCoords[0], convertedCoords[1])){
                        this.selectedPawn = p;
                        this.viewGame.getTGrid().setRowSelectionInterval(convertedCoords[1], convertedCoords[1]);
                        this.viewGame.getTGrid().setColumnSelectionInterval(convertedCoords[0], convertedCoords[0]);
                    }
                    i++;
                }
                if(this.selectedPawn == null){
                    this.viewGame.getLErrors().setText("[-] Aucun pion ne se trouve ici");
                } else{
                    this.viewGame.getLErrors().setText("");
                }
            }
            this.viewGame.refreshGUI(this.modelGame.getGrid());
        } else if(e.getSource() == this.viewGame.getBValidMove()){
            if(this.modelGame.getCurrent() instanceof HumanP){
                Movement move;
                do{
                    boolean mvtValid = false;
                    move = null;
                    if(this.modelGame.getCurrent() instanceof HumanP){
                        if(this.selectedPawn != null){
                            int[] convertedCoords = ((HumanP) this.modelGame.getCurrent()).readCoords(this.viewGame.getTSelectCoords().getText());
                            if(convertedCoords != null){
                                move = ((HumanP) this.modelGame.getCurrent()).newMove(this.selectedPawn.getX(), this.selectedPawn.getY(), convertedCoords[0], convertedCoords[1]);
                            }
                        }
                    } else{
                        move = ((BotP) this.modelGame.getCurrent()).newMove();
                    }

                    if(move != null){
                        if(move.getPawn() == this.modelGame.getZenPawn() && this.modelGame.getOldMov() != null){ // The Zen pawn cannot return to its previous location
                            if(this.modelGame.getOldMov().getPawn() != this.modelGame.getZenPawn() || move.getNX() != this.modelGame.getOldMov().getOX() || move.getNY() != this.modelGame.getOldMov().getOY()){
                                mvtValid = move.isValid();
                            }
                        } else{
                            mvtValid = move.isValid();
                        }

                        if(mvtValid){
                            if(!this.modelGame.getGrid()[move.getNX()][move.getNY()].isFree()){
                                boolean found = false;
                                Pawn p;
                                ArrayList<Pawn> pawnList = this.modelGame.getListPawn();
                                Iterator<Pawn> it = pawnList.iterator();
                                while(it.hasNext() && !found){
                                    p = it.next();
                                    if(p.isAt(move.getNX(), move.getNY())){ // Remove captured pawns
                                        found = true;
                                        it.remove();
                                        if(p.getType() == PawnType.BLACK){
                                            this.modelGame.getBlackPawn().remove(p);
                                        } else if(p.getType() == PawnType.WHITE){
                                            this.modelGame.getWhitePawn().remove(p);
                                        }
                                    }
                                }
                            }

                            move.getPawn().setX(move.getNX());
                            move.getPawn().setY(move.getNY());

                            this.modelGame.getGrid()[move.getOX()][move.getOY()].setFree(true);
                            this.modelGame.getGrid()[move.getNX()][move.getNY()].setFree(false);

                            this.modelGame.setOldMove(move);
                            this.viewGame.getLErrors().setText("");

                            this.modelGame.changeCurrent();
                            this.viewGame.getLCurrentPlayer().setText("Joueur "+this.modelGame.getCurrent().getName()+"  ");
                            if(this.modelGame.isWin()){
                                this.modelGame.changeCurrent();
                                if(this.modelGame.isWin()){
                                    this.printWinUI(true);
                                } else{
                                    this.printWinUI(false);
                                }
                                this.controlMenu.printMenuUI();
                            }
                        } else{
                            this.viewGame.getLErrors().setText("[-] Le mouvement n'est pas valide");
                        }
                    } else{
                        this.viewGame.getLErrors().setText("[-] Le mouvement n'est pas valide");
                    }
                } while(this.modelGame.getCurrent() instanceof BotP && move != null);
                this.viewGame.refreshGUI(this.modelGame.getGrid());
            }
        } else{
            this.printEndUI();
            this.modelGame.saveState("saveFile.zenSave");
            this.controlMenu.printMenuUI();
        }
    }

    /**
     * Prints the start sentence of the game
     */
    public void printStartUI(){
        if(this.modelGame.getUI() == UIMode.TEXT){
            this.viewGame.showStartShell(this.modelGame.getCurrent().getName());
        } else{
            this.viewGame.showStartGUI(this.modelGame.getCurrent().getName());
        }
    }

    /**
     * Prints the grid interface
     */
    public void printGridUI(){
        if(this.modelGame.getUI() == UIMode.TEXT){
            this.viewGame.showGridShell(this.modelGame.getGrid(), this.modelGame.getListPawn(), this.modelGame.getCurrent().getName());
        } else{
            this.viewGame.showGridGUI(this.modelGame.getGrid(), this.modelGame.getListPawn(), this.modelGame.getCurrent().getName());
        }
    }

    /**
     * Prints the end sentence of the game
     */
    public void printEndUI(){
        if(this.modelGame.getUI() == UIMode.TEXT){
            this.viewGame.showStopShell();
        } else{
            this.viewGame.showStopGUI();
        }
    }

    /**
     * Prints the win sentence if isDraw is false, prints draw sentence if isDraw is true
     * @param isDraw true if is a draw, false otherwise
     */
    public void printWinUI(boolean isDraw){
        if(this.modelGame.getUI() == UIMode.TEXT){
            this.viewGame.showWinShell(isDraw, this.modelGame.getCurrent().getName());
        } else{
            this.viewGame.showWinGUI(isDraw, this.modelGame.getCurrent().getName()); 
        }
    }

    /**
     * Asks for a new move
     * @return the move asked
     */
    @SuppressWarnings("resource")
    public int[] askMove(){
        String coords;
        boolean endRequired = false;
        int[] pawnCoords = null;
        int[] newCoords = null;
        Scanner in = new Scanner(System.in);
        do{
            System.out.println("Coordonnees du pion: ");
            coords = in.nextLine();
            if(coords.equalsIgnoreCase("menu")){
                endRequired = true;
            } else{
                pawnCoords = ((model.HumanP) this.modelGame.getCurrent()).readCoords(coords);
            }
        } while(!endRequired && pawnCoords == null);

        while(!endRequired && newCoords == null){
            System.out.println("Nouvelles coordonnees pour le pion: ");
            coords = in.nextLine();
            if(coords.equalsIgnoreCase("menu")){
                endRequired = true;
            } else{
                newCoords = ((model.HumanP) this.modelGame.getCurrent()).readCoords(coords);
            }
        }
        int[] ret = null;
        if(pawnCoords != null && newCoords != null){
            ret = new int[4];
            ret[0] = pawnCoords[0];
            ret[1] = pawnCoords[1];
            ret[2] = newCoords[0];
            ret[3] = newCoords[1];
        }
        return ret;
    }

    /**
     * Sets the view and controllers attibutes
     * @param vGame game view class
     * @param cMenu menu controller class
     */
    public void setVCClasses(view.Game vGame, ActionCMenu cMenu){
        if(vGame != null && cMenu != null){
            this.viewGame = vGame;
            this.controlMenu = cMenu;
        } else{
            System.out.println("Erreur ActionCGame(): parametre non valide");
        }
    }

    /**
     * Sets the model attribute
     * @param mGame game model class
     */
    public void setModelClasses(model.Game mGame){
        if(mGame != null){
            this.modelGame = mGame;
        } else{
            System.out.println("Erreur ActionCGame(): parametre non valide");
        }
    }
}