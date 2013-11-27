package calc.textui.main;

import java.io.IOException;

import calc.FileManager;
import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

/**
 * Command to save a file with a new name.
 */
public class SaveAs extends Command<FileManager> {

	/**
	 * @param receiver
	 */
	public SaveAs(FileManager receiver) {
		super(MenuEntry.SAVE_AS, receiver, new ValidityPredicate<FileManager>(receiver) {
            public boolean isValid() {
                return _receiver.getSheet() != null;
            }
        });
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
    public final void execute() throws DialogException, IOException {
		
		try {
			String file = IO.readString(Message.newSaveAs());
			_receiver.saveSheet(file);
		}

    	catch(IOException f) {}
	}
}


