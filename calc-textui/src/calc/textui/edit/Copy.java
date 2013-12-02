/** @version $Id: Copy.java,v 1.2 2013-11-14 19:58:33 ist173639 Exp $ */
package calc.textui.edit;

import java.io.IOException;

import calc.FileManager;
import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;


/**
 * Copy command.
 */
public class Copy extends Command<FileManager> {
	/**
	 * @param sheet
	 */
	public Copy(FileManager manager) {
		super(MenuEntry.COPY, manager);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException {
		String rangeString = IO.readString(Message.addressRequest());
		
		try {
			if(_receiver.copyRange(rangeString) == -1) {
				throw new InvalidCellRange(rangeString);
			}
		}
		
		catch(ArrayIndexOutOfBoundsException e) {
			throw new InvalidCellRange(rangeString); 
		}
	}

}
