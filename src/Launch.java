import javax.swing.JFrame;
import javax.swing.WindowConstants;

import controller.*;
import view.*;
import model.ZenInitie;

/**
 * Launchs the game
 * @author Théo Koenigs
 */
public class Launch {

	/**
	 * Executes the program
	 * @param args no args used
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				ActionCMenu acMenu = new ActionCMenu();
				ActionCGame acGame = new ActionCGame();
				ActionCGSettings acGS = new ActionCGSettings();
				ActionCRules acRules = new ActionCRules();

				JFrame frame = new JFrame("Zen l'initie");
				frame.setSize(820, 433);
				frame.setLocation(200, 100);
				Menu vMenu = new Menu(acMenu, frame);
				Game vGame = new Game(acGame, frame);
				GSettings vGSettings = new GSettings(acGS, frame);
				Rules vRules = new Rules(acRules, frame);

				ZenInitie model = new ZenInitie(acGame);
				frame.addWindowListener(new WindowC(model));
				frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

				acMenu.setMVCClasses(model, vMenu, acGS, acRules);
				acGS.setMVCClasses(model, vGSettings, acMenu);
				acGame.setVCClasses(vGame, acMenu);
				acRules.setMVClasses(model, vRules, acMenu);

				System.out.println(" _____                 ___  _       _ __  _    			");
				System.out.println("/__  /  ___  ____     / ( )(_)___  (_) /_(_)__ 			");
				System.out.println("  / /  / _ \\/ __ \\   / /|// / __ \\/ / __/ / _ \\			");
				System.out.println(" / /__/  __/ / / /  / /  / / / / / / /_/ /  __/			");
				System.out.println("/____/\\___/_/ /_/  /_/  /_/_/ /_/_/\\__/_/\\___/ 			");
				System.out.println();
				acMenu.printMenuUI();
			}
		});
	}
}