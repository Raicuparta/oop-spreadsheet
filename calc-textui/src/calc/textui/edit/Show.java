package calc.textui.edit;

import java.io.IOException;

import calc.FileManager;
import calc.Reference;
import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import java.lang.Exception;


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
				Reference[] referenceArray = _receiver.getFactory().readInterval(requestedString);
				
				for(Reference reference : referenceArray) {
					if (reference != null) {
						if(reference.getCell() != null) {
							String outputString = reference.getCell().getLine() + ";" + reference.getCell().getColumn() + "|";
							if(reference.getCell().getContent() != null) {
								IO.println(outputString + reference.getCell().getContent().print());
							} else {
								IO.println(outputString);
							}
						}		
					}
				}
			
			} else {
				
				Reference reference = _receiver.getFactory().readReference(requestedString);
				if (reference != null) {
					String outputString = reference.getCell().getLine() + ";" + reference.getCell().getColumn() + "|";
					if(reference.getCell().getContent() != null) {
						IO.println(outputString + reference.getCell().getContent().print());
					} else {
						IO.println(outputString);
					}
				}
			}
		}
		
		catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			throw new InvalidCellRange(requestedString); 
		}
	
	}
}
