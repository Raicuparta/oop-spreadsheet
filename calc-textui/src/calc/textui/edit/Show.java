package calc.textui.edit;

import java.io.IOException;

import calc.FileManager;
import calc.Reference;
import calc.Range;
import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;


/**
 * Show cells command.
 */
public class Show extends Command<FileManager> {
	/**
	 * @param sheet
	 */
	public Show(FileManager manager) {
		super(MenuEntry.SHOW, manager);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException {
			
		String requestedString = IO.readString(Message.addressRequest());
		try {
			if (requestedString.contains(":")) {
				Range range = _receiver.getFactory().readInterval(requestedString);
				
				if (!range.getInterval().isEmpty()){
					for(Reference reference : range) {
						IO.println(reference.getCell().print());
					}
				} else {
					throw new InvalidCellRange(requestedString);
				}
			
			} else {
				Reference reference = _receiver.getFactory().readReference(requestedString);
				IO.println(reference.getCell().print());
			}
		}
		
		catch(ArrayIndexOutOfBoundsException e) {
			throw new InvalidCellRange(requestedString); 
		}
	}
}
