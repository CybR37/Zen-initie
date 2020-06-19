package view;

import java.io.Serializable;

public class Rules implements Serializable {

    /** The version for serialization and deserialization */
	private static final long serialVersionUID = 1;
	
    /**
	 * Prints the rule interface
	 */
	public void showShell() {
		System.out.println("------------------------------------------------");
		System.out.println("Regles du jeu: ");
		System.out.println();
		System.out.println("But du jeu : le vainqueur est le premier joueur qui reussit a former une chaine continue avec la totalite de ses pions se trouvant encore sur le plateau, y compris le \"Z\", si celui-ci est encore en jeu.");
		System.out.println();
		System.out.println("Debut de partie : les pions sont disposes sur le plateau selon une figure imposee.");
		System.out.println("Deroulement de la partie : chaque joueur deplace a son tour un pion de sa couleur ou le \"Z\" en respectant 4 regles tres simples.");
		System.out.println();
		System.out.println("Regle 1 : les pions se deplacent en ligne droite dans n'importe quelle direction. Tout pion doit toujours se deplacer d'autant de cases qu'il y a de pions sur la ligne de deplacement choisie (horizontale ou verticale ou diagonale). Tous les pions sont pris en compte y compris le pion deplace.");
		System.out.println("Regle 2 : tout pion peut passer par-dessus un ou plusieurs pions de sa couleur, mais jamais par-dessus ceux de son adversaire.");
		System.out.println("Regle 3 : tout pion peut capturer un pion adverse en se placant sur la case occupee par le pion pris en respectant la regle 1. Celui-ci est alors definitivement retire du jeu.");
		System.out.println("Regle 4 : a chaque coup, le \"Z\" pion commun a tous les joueurs peut etre soit blanc soit noir selon l'interet de celui qui joue. Il se deplace comme les autres pions, mais peut aussi etre pris.");
		System.out.println();
	}
}