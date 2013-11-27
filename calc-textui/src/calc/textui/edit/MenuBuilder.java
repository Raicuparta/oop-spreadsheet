package calc.textui.edit;

import java.lang.reflect.InvocationTargetException;

import calc.FileManager;
import ist.po.ui.Command;
import ist.po.ui.Menu;

/**
 * Menu builder for edit operations.
 */
public class MenuBuilder {

	/**
	 * FIXME: commands may have one or more receivers
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws ClassNotFoundException 
	 */
	public static void menuFor(FileManager manager) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Menu menu = new Menu(MenuEntry.TITLE, new Command<?>[] {
				new Show(manager),
				new Insert(manager),
				new Copy(manager),
				new Delete(manager),
				new Cut(manager),
				new Paste(manager),
				new ShowCutBuffer(manager),
				});
		menu.open();
	}
}
