package calc.textui.main;

import java.io.IOException;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;
import calc.FileManager;

/**
 * Command to save a file.
 */
public class Save extends Command<FileManager> {

	/**
	 * @param receiver
	 */
	
	public Save(FileManager receiver) {
		super(MenuEntry.SAVE, receiver, new ValidityPredicate<FileManager>(receiver) {
            public boolean isValid() {
                return _receiver.getSheet() != null;
            }
        });
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
    public final void execute() throws DialogException {
		try {
			
			if (_receiver.getSheet().getName() == null) {
				String file = IO.readString(Message.newSaveAs());
				_receiver.saveSheet(file);
			} else {
				_receiver.saveSheet(_receiver.getSheet().getName());
			}
		}

    	catch(IOException f) {}
    }

}
