package view;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.PlayerMode;

import controller.ActionCGSettings;

/**
 * Game settings view class, shows interface in graphical or text mode
 * @author Théo Koenigs
 */
public class GSettings {

	/** Game settings controller instance */
	private ActionCGSettings controlGS;
	/** GUI instance */
	private JFrame frame;
	/** Back button */
	private JButton bBack;
	/** Start button */
	private JButton bStart;
	/** Mode label */
	private JLabel lMode;
	/** Mode combo box */
	private JComboBox<PlayerMode> cMode;

	/**
	 * Class constructor, initializes the attributes with the parameters
	 * @param acGS game settings controller instance
	 * @param frame GUI instance
	 */
	public GSettings(ActionCGSettings acGS, JFrame frame){
		if(acGS != null && frame != null){
			this.controlGS = acGS;
			this.frame = frame;

			this.lMode = new JLabel("Mode de jeu", JLabel.CENTER);
			this.bBack = new JButton("   Retour   ");
			this.bStart = new JButton("   Demarrer la partie   ");
			this.bBack.setBackground(new Color(215, 215, 215));
			this.bStart.setBackground(new Color(215, 215, 215));
			this.bBack.addActionListener(this.controlGS);
			this.bStart.addActionListener(this.controlGS);
			PlayerMode[] list = {PlayerMode.HVH, PlayerMode.HVB};
			this.cMode = new JComboBox<PlayerMode>(list);
			this.cMode.setBackground(new Color(215, 215, 215));
		} else{
			System.out.println("Erreur GSettings(): parametre non valide");
		}
	}

	/**
	 * Prints the graphical game settings interface
	 */
	public void showGUI(){
		JPanel framePane = new JPanel(new BorderLayout(130, 0));
		JPanel leftPane = new JPanel(new BorderLayout());
		JPanel rightPane = new JPanel(new BorderLayout());
		JPanel centerPane = new JPanel(new GridLayout(9, 1, 0, 10));
		framePane.setBackground(new Color(247, 247, 247));
		leftPane.setBackground(new Color(247, 247, 247));
		rightPane.setBackground(new Color(247, 247, 247));
		centerPane.setBackground(new Color(247, 247, 247));

		leftPane.add(this.bBack, BorderLayout.NORTH);
		leftPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
		rightPane.add(this.bStart, BorderLayout.SOUTH);
		rightPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
		centerPane.add(new JLabel());
		centerPane.add(new JLabel());
		centerPane.add(new JLabel());
		centerPane.add(this.lMode);
		centerPane.add(this.cMode);
		centerPane.setSize(80, 40);
		framePane.add(leftPane, BorderLayout.WEST);
		framePane.add(rightPane, BorderLayout.EAST);
		framePane.add(centerPane, BorderLayout.CENTER);

		this.frame.setContentPane(framePane);
		this.frame.revalidate();
		this.frame.repaint();
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

	/**
	 * Returns the back button instance
	 * @return back button instance
	 */
	public JButton getBBack() {
		return this.bBack;
	}

	/**
	 * Returns the start button instance
	 * @return start button instance
	 */
	public JButton getBStart() {
		return this.bStart;
	}

	/**
	 * Returns the combobox instance
	 * @return combobox instance
	 */
	public JComboBox<PlayerMode> getCMode() {
		return this.cMode;
	}
}