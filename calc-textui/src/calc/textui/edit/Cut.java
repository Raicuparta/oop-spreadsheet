/** @version $Id: Cut.java,v 1.2 2013-11-14 19:58:33 ist173639 Exp $ */
package calc.textui.edit;

import java.io.IOException;

import calc.FileManager;
import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;


/**
 * Cut command.
 */
public class Cut extends Command<FileManager> {
	/**
	 * @param sheet
	 */
	public Cut(FileManager manager) {
		super(MenuEntry.CUT, manager);
	}

	/** 
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException {
		String rangeString = IO.readString(Message.addressRequest());
		
		try {
			
			int cut = _receiver.copyRange(rangeString);
			
			if(cut == -1) {
				throw new InvalidCellRange(rangeString);
			} else {
				_receiver.deleteRange(rangeString);
			}
		}
		
		catch(ArrayIndexOutOfBoundsException e) {
			throw new InvalidCellRange(rangeString); 
		}
	}

}
