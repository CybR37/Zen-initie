package view;

import java.io.Serializable;

public class Menu implements Serializable {

    /** The version for serialization and deserialization */
	private static final long serialVersionUID = 1;
	
    /**
	 * Prints the menu interface
	 */
	public void showShell() {		
		System.out.println("----------------------Menu----------------------");
		System.out.println("1- Nouvelle partie");
		System.out.println("2- Charger la derniere partie");
		System.out.println("3- Regles du jeu");
		System.out.println();
		System.out.println("Entrez \"menu\" a n'importe quel moment pour revenir au menu");
		System.out.println("4- Version graphique");
		System.out.println("5- Quitter");
		System.out.println();
	}
}