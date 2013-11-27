package calc.textui.main;

import calc.FileManager;
import ist.po.ui.Command;
import ist.po.ui.ValidityPredicate;

/**
 * Open search menu.
 */
public class MenuOpenSearch extends Command<FileManager> {

	/**
	 * @param receiver
	 */
	public MenuOpenSearch(FileManager receiver) {
		super(MenuEntry.MENU_SEARCH, receiver, new ValidityPredicate<FileManager>(receiver) {
            public boolean isValid() {
                return _receiver.getSheet() != null;
            }
        });
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() {
		calc.textui.search.MenuBuilder.menuFor(_receiver);
	}

}
