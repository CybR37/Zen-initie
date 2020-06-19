package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Scanner;

import model.UIMode;

public class ActionCGame implements ActionListener, Serializable {

    /** The version for serialization and deserialization */
    private static final long serialVersionUID = 1;
    /** Game model instance */
    private model.Game modelGame;
    /** Game view instance */
    private view.Game viewGame;
    /** Menu controller instance */
    private ActionCMenu controlMenu;

    public void actionPerformed(ActionEvent e){

    }

    public void printStartUI(){
        if(this.modelGame.getUI() == UIMode.TEXT){
            this.viewGame.showStartShell(this.modelGame.getCurrent().getName());
        } else{

        }
    }

    public void printGridUI(){
        if(this.modelGame.getUI() == UIMode.TEXT){
            this.viewGame.showGridShell(this.modelGame.getGrid(), this.modelGame.getListPawn(), this.modelGame.getCurrent().getName());
        } else{
                
        }
    }

    public void printEndUI(){
        if(this.modelGame.getUI() == UIMode.TEXT){
            this.viewGame.showStopShell();
        } else{
                
        }
    }

    public void printWinUI(boolean isDraw){
        if(this.modelGame.getUI() == UIMode.TEXT){
            this.viewGame.showWinShell(isDraw, this.modelGame.getCurrent().getName());
        } else{
                
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

    public void setVCClasses(view.Game vGame, ActionCMenu cMenu){
        if(vGame != null && cMenu != null){
            this.viewGame = vGame;
            this.controlMenu = cMenu;
        } else{
            System.out.println("Erreur ActionCGame(): parametre non valide");
        }
    }

    public void setModelClasses(model.Game mGame){
        if(mGame != null){
            this.modelGame = mGame;
        } else{
            System.out.println("Erreur ActionCGame(): parametre non valide");
        }
    }
}