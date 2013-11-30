package calc.textui;

import static ist.po.ui.Dialog.IO;

import java.lang.reflect.InvocationTargetException;

import calc.FileManager;

import java.io.*;

//FIXME: include project-specific imports here

/**
 * Class that represents the spreadsheet's textual interface.
 */
public class Calc {
  /**
   * @param args
 * @throws ClassNotFoundException 
 * @throws IOException 
   * 
   */
  @SuppressWarnings("nls")
  public static void main(String[] args) throws ClassNotFoundException, IOException  {

	FileManager manager = new FileManager();
	
	String s;
	String datafile = System.getProperty("import");
	
	if (datafile != null) {

		manager.newFactory();
		BufferedReader in;
		
		try {
			in = new BufferedReader(new FileReader(datafile));
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
			
			in.close();
		}
		
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
				

	} else {
	}
			
	
    
    calc.textui.main.MenuBuilder.menuFor(manager);
    IO.closeDown();
  }

}
