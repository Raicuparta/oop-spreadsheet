package calc.textui;

import static ist.po.ui.Dialog.IO;

import java.lang.reflect.InvocationTargetException;

import calc.Factory;
import calc.FileManager;
import java.io.*;

//FIXME: include project-specific imports here

/**
 * Class that represents the spreadsheet's textual interface.
 */
public class Calc {
  /**
   * @param args
 * @throws InvocationTargetException 
 * @throws IllegalArgumentException 
 * @throws IllegalAccessException 
 * @throws InstantiationException 
 * @throws SecurityException 
 * @throws NoSuchMethodException 
 * @throws ClassNotFoundException 
   */
  @SuppressWarnings("nls")
  public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException /* FIXME: optionally, declare thrown exceptions here */ {

	FileManager manager = new FileManager();
	
	String s;
	String datafile = System.getProperty("import");
	
	if (datafile != null) {

		manager.newFactory();
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(datafile));
			int lines = 0, columns = 0;
		
			if ((s = in.readLine()) != null) {
				lines = manager.getFactory().readDimension(s);
			}
			if ((s = in.readLine()) != null) {
				columns = manager.getFactory().readDimension(s);
			}
			
			if ((lines != 0) && (columns != 0)) {
				manager.newSheet(lines, columns);
			}
			
			while ((s = in.readLine()) != null) {
				manager.getFactory().readLine(s);
			}
			
		}
		catch (IOException e) {}
		//catch (FileNotFoundException e) {}
		

	} else {
	}
			
	
    
    calc.textui.main.MenuBuilder.menuFor(manager);
    IO.closeDown();
  }

}
