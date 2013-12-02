/** @version $Id: ShowFunctions.java,v 1.2 2013-11-14 19:58:33 ist173639 Exp $ */
package calc.textui.search;

import java.io.IOException;
import java.util.ArrayList;

import calc.Cell;
import calc.FileManager;
import calc.textui.search.Message;
import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;


/**
 * Class for searching functions.
 */
public class ShowFunctions extends Command<FileManager> {
	/**
	 * @param receiver
	 */
	public ShowFunctions(FileManager receiver) {
		super(MenuEntry.SEARCH_FUNCTIONS, receiver);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
        public final void execute() throws DialogException, IOException {
			String requestedString = IO.readString(Message.searchFunction());
			
			ArrayList<Cell> searchResults = _receiver.searchFunction(requestedString);
			
			for(Cell cell : searchResults) {
				IO.println(cell.print());
			}
			
			
        }

}
