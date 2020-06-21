package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.Serializable;

import model.UIMode;

public class ActionCRules implements ActionListener, Serializable {

    /** The version for serialization and deserialization */
    private static final long serialVersionUID = 1;
    /** Menu model instance */
    private model.ZenInitie modelMenu;
    /** Rules view instance */
    private view.Rules viewRules;
    /** Menu controller instance */
    private ActionCMenu controlMenu;
    
    /**
     * Triggered when a button is pressed
     * @param e event generated
     */
    public void actionPerformed(ActionEvent e){
        this.controlMenu.printMenuUI();
    }

    public void printRulesUI(){
        if(this.modelMenu.getUI() == UIMode.TEXT){
            this.viewRules.showShell();
        } else{
            this.viewRules.showGUI();
        }
    }

    public void setMVClasses(model.ZenInitie mMenu, view.Rules vRules, ActionCMenu cMenu){
        if(mMenu != null && vRules != null && cMenu != null){
            this.modelMenu = mMenu;
            this.viewRules = vRules;
            this.controlMenu = cMenu;
        } else{
            System.out.println("Erreur ActionCRules(): parametre non valide");
        }
    }
}