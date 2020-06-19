package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Scanner;
import java.io.Serializable;

import model.UIMode;
import model.PlayerMode;

public class ActionCGSettings implements ActionListener, Serializable {

    /** The version for serialization and deserialization */
    private static final long serialVersionUID = 1;    
    /** Menu model instance */
    private model.ZenInitie modelMenu;
    /** Rules view instance */
    private view.GSettings viewGS;
    /** Menu controller instance */
    private ActionCMenu controlMenu;

    public void actionPerformed(ActionEvent e){

    }

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

        }
    }

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