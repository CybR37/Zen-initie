package view;

import java.io.Serializable;

public class GSettings implements Serializable {

    /** The version for serialization and deserialization */
    private static final long serialVersionUID = 1;
	
	/**
	 * Prints the game settings interface
	 */
	public void showShell() {
		System.out.println("------------------------------------------------");
		System.out.println("Mode de jeu");
		System.out.println("1- 1c1 (compatible 2c2, 3c3, ...)");
		System.out.println("2- 1 contre ordi");
		System.out.println();
	}
}