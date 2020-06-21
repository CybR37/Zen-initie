package controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Window controller, manages events related to the frame status
 * @author Théo Koenigs
 */
public class WindowC implements WindowListener {

    /** Menu model instance */
    private model.ZenInitie modelMenu;

    /**
     * Class constructor, initializes the attributes with the parameters
     * @param modelMenu menu model instance
     */
    public WindowC(model.ZenInitie modelMenu){
        if(modelMenu != null){
            this.modelMenu = modelMenu;
        }
    }

    public void windowOpened(WindowEvent e) {}

    /**
     * Triggered when the frame is about to close
     * @param e event generated
     */
    public void windowClosing(WindowEvent e) {
        model.Game currentGame = this.modelMenu.getCurrentGame();
        if(currentGame != null){
            currentGame.saveState("saveFile.zenSave");
        }
    }

    public void windowClosed(WindowEvent e) {}

    public void windowIconified(WindowEvent e) {}

    public void windowDeiconified(WindowEvent e) {}

    public void windowActivated(WindowEvent e) {}

    public void windowDeactivated(WindowEvent e) {}
}