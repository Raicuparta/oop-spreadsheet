package calc.textui.edit;

import java.io.IOException;

import calc.FileManager;
import calc.Content;
import calc.Reference;

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
	public final void execute() throws IOException {
	
		String referenceString = IO.readString(Message.addressRequest());
		Reference reference = _receiver.getFactory().readReference(referenceString);
		
		String contentString = IO.readString(Message.contentsRequest());
		Content content = _receiver.getFactory().readContent(contentString);
		
		reference.insert(content);
		
		
	}


}
