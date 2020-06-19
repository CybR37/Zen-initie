package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Scanner;
import java.io.Serializable;

import model.UIMode;

public class ActionCMenu implements ActionListener, Serializable {

    /** The version for serialization and deserialization */
    private static final long serialVersionUID = 1;    
    /** Menu model instance */
    private model.ZenInitie modelMenu;
    /** Menu view instance  */
    private view.Menu viewMenu;
    /** Game settings controller instance */
    private ActionCGSettings controlGameSettings;
    /** Rules controller instance */
    private ActionCRules controlRules;

    public void actionPerformed(ActionEvent e){

    }

    @SuppressWarnings("resource") // I remove the resource leak warning because it's intentional to leave the System.in scanner open
    public void printMenuUI(){
        boolean end = false;
			System.out.println(" _____                 ___  _       _ __  _    			");
			System.out.println("/__  /  ___  ____     / ( )(_)___  (_) /_(_)__ 			");
			System.out.println("  / /  / _ \\/ __ \\   / /|// / __ \\/ / __/ / _ \\			");
			System.out.println(" / /__/  __/ / / /  / /  / / / / / / /_/ /  __/			");
			System.out.println("/____/\\___/_/ /_/  /_/  /_/_/ /_/_/\\__/_/\\___/ 			");
            System.out.println();
            
        while(this.modelMenu.getUI() == UIMode.TEXT && !end){
            this.viewMenu.showShell();

            Scanner in = new Scanner(System.in);
            String rep = in.nextLine();
            if(rep.equals("1")){
                this.controlGameSettings.printGameSettingsUI();
            } else if(rep.equals("2")){
                this.modelMenu.loadGame(model.ZenInitie.SAVE_PATH + "saveFile.zenSave");
                this.modelMenu.getCurrentGame().start();
            } else if(rep.equals("3")){
                this.controlRules.printRulesUI();
            } else if(rep.equals("4")){
                this.modelMenu.setUI(UIMode.GRAPH);
                // TODO - call controller method to start GUI
            } else if(rep.equals("5")){
                end = true;
            }
        }
    }

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