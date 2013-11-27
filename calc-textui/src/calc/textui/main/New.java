package calc.textui.main;

import java.io.IOException;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import calc.FileManager;

/**
 * Create a new spreadsheet.
 */
public class New extends Command<FileManager> {

	/**
	 * @param receiver
	 */
	public New(FileManager receiver) {
		super(MenuEntry.NEW, receiver);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
    public final void execute() throws DialogException, IOException {
		if(_receiver.getSheet() != null) {
			String save = IO.readString(Message.saveBeforeExit());
			if (save == "s") {
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
			else if (save == "n") {}
		}
		
		int lines = IO.readInteger(Message.linesRequest());
		int columns = IO.readInteger(Message.columnsRequest());
		_receiver.newFactory();
		_receiver.newSheet(lines, columns);
    }

}
