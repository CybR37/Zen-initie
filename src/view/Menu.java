package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ActionCMenu;

/**
 * Menu view class, shows interface in graphical or text mode
 * @author Théo Koenigs
 */
public class Menu {

	/** Path to resources images files */
	static final String RES_IMG_PATH = "./res/img/";
	/** Menu controller instance */
	private ActionCMenu controlMenu;
	/** GUI instance */
	private JFrame frame;
	/** New game button */
	private JButton bNewGame;
	/** Load game button */
	private JButton bLoadGame;
	/** Rules button */
	private JButton bRules;
	/** Change version button */
	private JButton bTextVersion;
	/** Quit button */
	private JButton bQuit;
	/** Logo label */
	private JLabel lLogo;

	/**
	 * Class constructor, initializes the attributes with the parameters
	 * @param acMenu menu controller instance
	 * @param frame GUI instance
	 */
	public Menu(ActionCMenu acMenu, JFrame frame){
		if(acMenu != null && frame != null){
			this.controlMenu = acMenu;
			this.frame = frame;

			this.bNewGame = new JButton("Nouvelle partie");
			this.bLoadGame = new JButton("Charger la derniere partie");
			this.bRules = new JButton("Regles du jeu");
			this.bTextVersion = new JButton("Version texte");
			this.bQuit = new JButton("Quitter");
			this.lLogo = new JLabel(new ImageIcon(RES_IMG_PATH+"logo.jpg"));
			this.bNewGame.setBackground(new Color(215, 215, 215));
			this.bLoadGame.setBackground(new Color(215, 215, 215));
			this.bRules.setBackground(new Color(215, 215, 215));
			this.bTextVersion.setBackground(new Color(215, 215, 215));
			this.bQuit.setBackground(new Color(215, 215, 215));
			this.bNewGame.addActionListener(this.controlMenu);
			this.bLoadGame.addActionListener(this.controlMenu);
			this.bRules.addActionListener(this.controlMenu);
			this.bTextVersion.addActionListener(this.controlMenu);
			this.bQuit.addActionListener(this.controlMenu);
		} else{
			System.out.println("Erreur Menu(): parametre non valide");
		}
	}

	/**
	 * Prints the graphical menu interface
	 */
	public void showGUI(){
		JPanel framePane = new JPanel(new BorderLayout(50, 0));
		JPanel buttonsPane = new JPanel(new GridLayout(6, 1, 0, 20));
		buttonsPane.add(this.bNewGame);
		buttonsPane.add(this.bLoadGame);
		buttonsPane.add(this.bRules);
		buttonsPane.add(new JLabel());
		buttonsPane.add(new JLabel());
		buttonsPane.add(this.bQuit);
		buttonsPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 40));
		buttonsPane.setBackground(new Color(247, 247, 247));

		JPanel btnTextVersionPane = new JPanel(new BorderLayout());
		btnTextVersionPane.add(this.bTextVersion, BorderLayout.EAST);
		btnTextVersionPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
		btnTextVersionPane.setBackground(new Color(247, 247, 247));

		JPanel logoPane = new JPanel(new BorderLayout(0, 20));
		logoPane.add(btnTextVersionPane, BorderLayout.SOUTH);
		logoPane.add(this.lLogo, BorderLayout.CENTER);
		logoPane.setBackground(new Color(247, 247, 247));

		framePane.add(buttonsPane, BorderLayout.WEST);
		framePane.add(logoPane, BorderLayout.CENTER);
		framePane.setBackground(new Color(247, 247, 247));

		this.frame.setContentPane(framePane);
		this.frame.revalidate();
		this.frame.repaint();
		this.frame.setVisible(true);
	}

	/**
	 * Releases the memory used by the frame by destroying it
	 */
	public void closeGUI(){
		this.frame.dispose();
	}

    /**
	 * Prints the text menu interface
	 */
	public void showShell() {
		this.closeGUI();
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

	/**
	 * Returns the new game button
	 * @return new game button
	 */
	public JButton getBNewGame() {
		return this.bNewGame;
	}

	/**
	 * Returns the load game button
	 * @return load game button
	 */
	public JButton getBLoadGame() {
		return this.bLoadGame;
	}

	/**
	 * Returns the rules button
	 * @return rules button
	 */
	public JButton getBRules() {
		return this.bRules;
	}

	/**
	 * Returns the text version button
	 * @return text version button
	 */
	public JButton getBTextVersion() {
		return this.bTextVersion;
	}

	/**
	 * Returns the quit button
	 * @return quit button
	 */
	public JButton getBQuit() {
		return this.bQuit;
	}

}