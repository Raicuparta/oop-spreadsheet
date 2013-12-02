package calc.textui.edit;

import java.io.IOException;

import calc.Cell;
import calc.FileManager;
import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;


/**
 * Show cut buffer command.
 */
public class ShowCutBuffer extends Command<FileManager> {
	/**
	 * @param sheet
	 */
	public ShowCutBuffer(FileManager manager) {
		super(MenuEntry.SHOW_CUT_BUFFER, manager);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
        public final void execute() throws DialogException, IOException {
			try{
               for (Cell cell : _receiver.getCutBuffer()) {
            	   IO.println(cell.print());
               }
			}
       		catch(Exception e) {
    			e.printStackTrace();
    		}
        }

}
