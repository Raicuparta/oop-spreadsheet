/** @version $Id: MenuBuilder.java,v 1.2 2013-11-14 19:58:33 ist173639 Exp $ */
package calc.textui.search;

import calc.FileManager;
import ist.po.ui.Command;
import ist.po.ui.Menu;

//FIXME: add project-specific imports here

/**
 * Menu builder for search operations.
 */
public class MenuBuilder {

	/**
	 * FIXME: commands may have one or more receivers
	 */
	public static void menuFor(FileManager manager) {
		Menu menu = new Menu(MenuEntry.TITLE, new Command<?>[] {
				new ShowValues(manager),
				new ShowFunctions(manager),
				});
		menu.open();
	}

}
