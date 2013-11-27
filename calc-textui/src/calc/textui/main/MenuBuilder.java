package calc.textui.main;

import ist.po.ui.Command;
import ist.po.ui.Menu;
import calc.FileManager;

/**
 * Menu builder for the main menu.
 */
public abstract class MenuBuilder {

	/**
	 * @throws ClassNotFoundException 
	 */
	public static void menuFor(FileManager manager) throws ClassNotFoundException {
		Menu menu = new Menu(MenuEntry.TITLE, new Command<?>[] {
				new New(manager),
				new Open(manager),
				new Save(manager),
				new SaveAs(manager),
				new MenuOpenEdit(manager),
				new MenuOpenSearch(manager),
        });
		
		menu.open();
	}

}
