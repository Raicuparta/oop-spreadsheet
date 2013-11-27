/** @version $Id: ShowValues.java,v 1.2 2013-11-14 19:58:33 ist173639 Exp $ */
package calc.textui.search;

import java.io.IOException;

import calc.FileManager;
import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

/**
 * Class for searching values.
 */
public class ShowValues extends Command<FileManager> {
	/**
	 * @param receiver
	 */
	public ShowValues(FileManager receiver) {
		super(MenuEntry.SEARCH_VALUES, receiver);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
        public final void execute() throws DialogException, IOException {
               //FIXME: implement command
        }

}
