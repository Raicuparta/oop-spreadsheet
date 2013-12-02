package calc.textui.edit;

import java.io.IOException;

import calc.FileManager;
import calc.Content;
import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;


/**
 * Insert command.
 */
public class Insert extends Command<FileManager> {
	/**
	 * @param sheet
	 */
	public Insert(FileManager manager) {
		super(MenuEntry.INSERT, manager);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws IOException, DialogException {
	
		String rangeString = IO.readString(Message.addressRequest());
		
		String contentString = IO.readString(Message.contentsRequest());
		Content content = _receiver.getFactory().readContent(contentString);
		
		try {
			if(_receiver.insertRange(rangeString, content) == -1) {
				throw new InvalidCellRange(rangeString);
			}
			
		}
		
		catch(ArrayIndexOutOfBoundsException e) {
			throw new InvalidCellRange(rangeString); 
		}
			
	}

	
	
	
	

}
