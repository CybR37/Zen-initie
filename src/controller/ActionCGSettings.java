package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Scanner;

import model.UIMode;
import model.PlayerMode;

/**
 * Game settings controller, links view class and model class
 * @author Théo Koenigs
 */
public class ActionCGSettings implements ActionListener {
   
    /** Menu model instance */
    private model.ZenInitie modelMenu;
    /** Rules view instance */
    private view.GSettings viewGS;
    /** Menu controller instance */
    private ActionCMenu controlMenu;

    /**
     * Triggered when a button is pressed
     * @param e event generated
     */
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.viewGS.getBStart()){
            this.modelMenu.newGame((PlayerMode) this.viewGS.getCMode().getSelectedItem(), 11, 11);
        } else{
            this.controlMenu.printMenuUI();
        }
    }

    /**
     * Prints the game settings interface
     */
    @SuppressWarnings("resource") // I remove the resource leak warning because it's intentional to leave the System.in scanner open
    public void printGameSettingsUI(){
        if(this.modelMenu.getUI() == UIMode.TEXT){
            boolean validInput = false;
            do{
                this.viewGS.showShell();

                Scanner in = new Scanner(System.in);
                String rep = in.nextLine();
                if(rep.equals("1")){
                    this.modelMenu.newGame(PlayerMode.HVH, 11, 11);
                    validInput = true;
                } else if(rep.equals("2")){
                    this.modelMenu.newGame(PlayerMode.HVB, 11, 11);
                    validInput = true;
                } else if(rep.equalsIgnoreCase("menu")){
                    validInput = true;
                }
            } while(!validInput);
        } else{
            this.viewGS.showGUI();
        }
    }

    /**
     * Sets the MVC class attibutes
     * @param mMenu menu model class
     * @param vGSettings game settings view class
     * @param cMenu menu controller class
     */
    public void setMVCClasses(model.ZenInitie mMenu, view.GSettings vGSettings, ActionCMenu cMenu){
        if(mMenu != null && vGSettings != null && cMenu != null){
            this.modelMenu = mMenu;
            this.viewGS = vGSettings;
            this.controlMenu = cMenu;
        } else{
            System.out.println("Erreur ActionCGSettings(): parametre non valide");
        }
    }
}