package calc.textui.main;

import calc.FileManager;
import ist.po.ui.Command;
import ist.po.ui.ValidityPredicate;
import ist.po.ui.DialogException;

/**
 * Open edit menu.
 */
public class MenuOpenEdit extends Command<FileManager> {

	/**
	 * @param receiver
	 */
	public MenuOpenEdit(FileManager receiver) {
		super(MenuEntry.MENU_CALC, receiver, new ValidityPredicate<FileManager>(receiver) {
            public boolean isValid() {
                return _receiver.getSheet() != null;
            }
        });
	}

	/**
	 * @throws ClassNotFoundException 
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException {
		try {
			calc.textui.edit.MenuBuilder.menuFor(_receiver);
		} 
		catch (ClassNotFoundException e) {}
		catch (Exception e) {}
	}

}
