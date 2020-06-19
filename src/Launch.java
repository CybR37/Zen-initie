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
		ActionCMenu acMenu = new ActionCMenu();
		ActionCGame acGame = new ActionCGame();
		ActionCGSettings acGS = new ActionCGSettings();
		ActionCRules acRules = new ActionCRules();

		Menu vMenu = new Menu();
		Game vGame = new Game();
		GSettings vGSettings = new GSettings();
		Rules vRules = new Rules();

		ZenInitie model = new ZenInitie(acGame);

		acMenu.setMVCClasses(model, vMenu, acGS, acRules);
		acGS.setMVCClasses(model, vGSettings, acMenu);
		acGame.setVCClasses(vGame, acMenu);
		acRules.setMVClasses(model, vRules, acMenu);

		acMenu.printMenuUI();
	}
}