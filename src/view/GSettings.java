package view;

import java.io.Serializable;

import javax.swing.JFrame;

import controller.ActionCGSettings;

public class GSettings implements Serializable {

    /** The version for serialization and deserialization */
    private static final long serialVersionUID = 1;
	/** Game settings controller instance */
	private ActionCGSettings controlGS;
	/** GUI instance */
	private JFrame frame;

	/**
	 * Class constructor, initializes the attributes with the parameters
	 * @param acGS game settings controller instance
	 * @param frame GUI instance
	 */
	public GSettings(ActionCGSettings acGS, JFrame frame){
		if(acGS != null && frame != null){
			this.controlGS = acGS;
			this.frame = frame;
		} else{
			System.out.println("Erreur GSettings(): parametre non valide");
		}
	}

	/**
	 * Prints the graphical game settings interface
	 */
	public void showGUI(){

	}

	/**
	 * Prints the text game settings interface
	 */
	public void showShell() {
		System.out.println("------------------------------------------------");
		System.out.println("Mode de jeu");
		System.out.println("1- 1c1 (compatible 2c2, 3c3, ...)");
		System.out.println("2- 1 contre ordi");
		System.out.println();
	}
}