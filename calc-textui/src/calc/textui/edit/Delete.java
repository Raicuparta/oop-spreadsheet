/** @version $Id: Delete.java,v 1.2 2013-11-14 19:58:33 ist173639 Exp $ */
package calc.textui.edit;

import java.io.IOException;

import calc.FileManager;
import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;


/**
 * Delete command.
 */
public class Delete extends Command<FileManager> {
	/**
	 * @param sheet
	 */
	public Delete(FileManager manager) {
		super(MenuEntry.DELETE, manager);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
        public final void execute() throws DialogException, IOException {
               //FIXME: implement command
        }

}
