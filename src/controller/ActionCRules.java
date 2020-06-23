package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.UIMode;

/**
 * Rules controller, links view class and model class
 * @author Théo Koenigs
 */
public class ActionCRules implements ActionListener {

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

    /**
     * Prints rules interface
     */
    public void printRulesUI(){
        if(this.modelMenu.getUI() == UIMode.TEXT){
            this.viewRules.showShell();
        } else{
            this.viewRules.showGUI();
        }
    }

    /**
     * Sets the MVC class attibutes
     * @param mMenu menu model class
     * @param vRules rules view class
     * @param cMenu menu controller class
     */
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