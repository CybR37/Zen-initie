package view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.BorderLayout;

import controller.ActionCRules;

/**
 * Rules view class, shows interface in graphical or text mode
 * @author Théo Koenigs
 */
public class Rules {

	/** Rules controller instance */
	private ActionCRules controlRules;
	/** GUI instance */
	private JFrame frame;
	/** Back button */
	private JButton bBack;
	/** Rules label */
	private JLabel lRules;

	/**
	 * Class constructor, initializes the attributes with the parameters
	 * @param acRules rules controller instance
	 * @param frame GUI instance
	 */
	public Rules(ActionCRules acRules, JFrame frame){
		if(acRules != null && frame != null){
			this.controlRules = acRules;
			this.frame = frame;

			this.bBack = new JButton("Retour");
			this.bBack.setBackground(new Color(215, 215, 215));
			this.bBack.addActionListener(this.controlRules);
			this.lRules = new JLabel("<html>Regles du jeu: <br/><br/>But du jeu : le vainqueur est le premier joueur qui reussit a former une chaine continue avec la totalite de ses pions se trouvant encore sur le plateau, y compris le \"Z\", si celui-ci est encore en jeu.<br/><br/>Debut de partie : les pions sont disposes sur le plateau selon une figure imposee.<br/>Deroulement de la partie : chaque joueur deplace a son tour un pion de sa couleur ou le \"Z\" en respectant 4 regles tres simples.<br/><br/>Regle 1 : les pions se deplacent en ligne droite dans n'importe quelle direction. Tout pion doit toujours se deplacer d'autant de cases qu'il y a de pions sur la ligne de deplacement choisie (horizontale ou verticale ou diagonale). Tous les pions sont pris en compte y compris le pion deplace.<br/>Regle 2 : tout pion peut passer par-dessus un ou plusieurs pions de sa couleur, mais jamais par-dessus ceux de son adversaire.<br/>Regle 3 : tout pion peut capturer un pion adverse en se placant sur la case occupee par le pion pris en respectant la regle 1. Celui-ci est alors definitivement retire du jeu.<br/>Regle 4 : a chaque coup, le \"Z\" pion commun a tous les joueurs peut etre soit blanc soit noir selon l'interet de celui qui joue. Il se deplace comme les autres pions, mais peut aussi etre pris.<br/>a) Lorsqu'il est deplace par un joueur, son adversaire n'a pas le droit de le replacer sur la meme case le coup suivant.<br/>b) Il est interdit de le deplacer, s'il ne se trouve pas en contact avec au moins un pion.</html>");
		} else{
			System.out.println("Erreur Rules(): parametre non valide");
		}
	}

	/**
	 * Prints the graphical rules interface
	 */
	public void showGUI(){
		JPanel framePane = new JPanel(new BorderLayout(0, 10));
		JPanel btnBackPane = new JPanel(new BorderLayout());
		btnBackPane.add(this.bBack, BorderLayout.WEST);
		btnBackPane.setBackground(new Color(247, 247, 247));

		framePane.add(btnBackPane, BorderLayout.NORTH);
		framePane.add(this.lRules, BorderLayout.CENTER);
		framePane.setBackground(new Color(247, 247, 247));
		framePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		this.frame.setContentPane(framePane);
		this.frame.revalidate();
		this.frame.repaint();
	}

    /**
	 * Prints the text rules interface
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
		System.out.println("a) Lorsqu'il est deplace par un joueur, son adversaire n'a pas le droit de le replacer sur la meme case le coup suivant.");
		System.out.println("b) Il est interdit de le deplacer, s'il ne se trouve pas en contact avec au moins un pion.");
		System.out.println();
	}
}