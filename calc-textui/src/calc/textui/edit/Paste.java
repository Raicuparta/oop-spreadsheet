package calc.textui.edit;

import java.io.IOException;

import calc.FileManager;
import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;


/**
 * Paste command.
 */
public class Paste extends Command<FileManager> {
	/**
	 * @param sheet
	 */
	public Paste(FileManager manager) {
		super(MenuEntry.PASTE, manager);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
        public final void execute() throws DialogException, IOException {
               //FIXME: implement command
        }
}
