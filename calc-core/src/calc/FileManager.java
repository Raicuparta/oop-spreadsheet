package calc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;

public class FileManager {

	private Factory _factory;
	private Sheet _currentSheet;
	
	public void newSheet(int lines, int columns) {
		_currentSheet = new Sheet(lines, columns);
		_factory.setSheet(_currentSheet);
	}
	
	public void newFactory() {
		_factory = new Factory();
	}
	
	public Sheet getSheet() {
		return _currentSheet;
	}
	
	public Factory getFactory() {
		return _factory;
	}
	
	public void openSheet(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		this.newFactory();
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
		
		try {
			_currentSheet = (Sheet)in.readObject();
			_factory.setSheet(_currentSheet);
		}

		catch(FileNotFoundException a) {}
		catch(IOException b) {}
		catch(ClassNotFoundException c) {}

		finally {
			in.close();
		}
				
	}
	
	public void saveSheet(String fileName) throws IOException {
		
		this.newFactory();
		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
		
		out.writeObject(_currentSheet);
		
		out.close();

	}
}
