package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Scanner;

import model.UIMode;

/**
 * Menu controller, links view class and model class
 * @author Théo Koenigs
 */
public class ActionCMenu implements ActionListener{
 
    /** Menu model instance */
    private model.ZenInitie modelMenu;
    /** Menu view instance  */
    private view.Menu viewMenu;
    /** Game settings controller instance */
    private ActionCGSettings controlGameSettings;
    /** Rules controller instance */
    private ActionCRules controlRules;

    /**
     * Triggered when a button is pressed
     * @param e event generated
     */
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.viewMenu.getBNewGame()){
            this.controlGameSettings.printGameSettingsUI();
        } else if(e.getSource() == this.viewMenu.getBLoadGame()){
            this.modelMenu.loadGame(model.ZenInitie.SAVE_PATH+"saveFile.zenSave");
            if(this.modelMenu.getCurrentGame() != null){
                this.modelMenu.getCurrentGame().start();
            } else{
                System.out.println("Erreur ActionCMenu.actionPerformed(): la partie n'a pas pu etre chargee");
            }
        } else if(e.getSource() == this.viewMenu.getBRules()){
            this.controlRules.printRulesUI();
        } else if(e.getSource() == this.viewMenu.getBTextVersion()){
            this.modelMenu.setUI(UIMode.TEXT);
            for(int i=0; i < 70; i++){
                System.out.println();
            }
            System.out.println(" _____                 ___  _       _ __  _    			");
            System.out.println("/__  /  ___  ____     / ( )(_)___  (_) /_(_)__ 			");
            System.out.println("  / /  / _ \\/ __ \\   / /|// / __ \\/ / __/ / _ \\			");
            System.out.println(" / /__/  __/ / / /  / /  / / / / / / /_/ /  __/			");
            System.out.println("/____/\\___/_/ /_/  /_/  /_/_/ /_/_/\\__/_/\\___/ 			");
            System.out.println();
            this.printMenuUI();
        } else{
            this.viewMenu.closeGUI();
        }
    }

    /**
     * Prints menu interface
     */
    @SuppressWarnings("resource") // I remove the resource leak warning because it's intentional to leave the System.in scanner open
    public void printMenuUI(){
        if(this.modelMenu.getUI() == UIMode.GRAPH){
            this.viewMenu.showGUI();
        }
        
        boolean end = false;
        while(this.modelMenu.getUI() == UIMode.TEXT && !end){
            this.viewMenu.showShell();

            Scanner in = new Scanner(System.in);
            String rep = in.nextLine();
            if(rep.equals("1")){
                this.controlGameSettings.printGameSettingsUI();
            } else if(rep.equals("2")){
                this.modelMenu.loadGame(model.ZenInitie.SAVE_PATH + "saveFile.zenSave");
                if(this.modelMenu.getCurrentGame() != null){
                    this.modelMenu.getCurrentGame().start();
                } else{
                    System.out.println("Erreur ActionCMenu.printMenuUI(): la partie n'a pas pu etre chargee");
                }
            } else if(rep.equals("3")){
                this.controlRules.printRulesUI();
            } else if(rep.equals("4")){
                this.modelMenu.setUI(UIMode.GRAPH);
                this.viewMenu.showGUI();
            } else if(rep.equals("5")){
                end = true;
            }
        }
    }

    /**
     * Sets the MVC class attibutes
     * @param mMenu menu model class
     * @param vMenu menu view class
     * @param cGSettings game settings controller class
     * @param cRules rules controller class
     */
    public void setMVCClasses(model.ZenInitie mMenu, view.Menu vMenu, ActionCGSettings cGSettings, ActionCRules cRules){
        if(mMenu != null && vMenu != null && cGSettings != null && cRules != null){
            this.modelMenu = mMenu;
            this.viewMenu = vMenu;
            this.controlGameSettings = cGSettings;
            this.controlRules = cRules;
        } else{
            System.out.println("Erreur ActionCMenu(): parametre non valide");
        }
    }
}