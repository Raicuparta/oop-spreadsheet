/** @version $Id: Open.java,v 1.4 2013-11-15 10:19:03 ist173639 Exp $ */
package calc.textui.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import calc.FileManager;
import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

/**
 * Command to open a file.
 */
public class Open extends Command<FileManager> {

	/**
	 * @param receiver
	 */
	public Open(FileManager receiver) {
		super(MenuEntry.OPEN, receiver);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
    public final void execute() throws DialogException, IOException {
		
		String fileName = null;
		
		try {
			fileName = IO.readString(Message.openFile());
        	_receiver.openSheet(fileName);
		}
		
		catch(FileNotFoundException a) {IO.println(Message.fileNotFound(fileName));}
		catch(IOException b) {}		
		catch(ClassNotFoundException c) {} 
		catch(Exception d) {}
		
	}
}
