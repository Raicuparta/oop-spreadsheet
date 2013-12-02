package calc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;


public class FileManager {

	private Factory _factory;
	private Sheet _currentSheet;
	private ArrayList<Cell> _cutBuffer;
	
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
	
	public ArrayList<Cell> getCutBuffer() {
		return _cutBuffer;
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
		
		_currentSheet.setName(fileName);
		this.newFactory();
		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
		
		out.writeObject(_currentSheet);
		
		out.close();
	}
	
	public int deleteRange(String rangeString) {
		
		if (rangeString.contains(":")) {
			
			Range range = getFactory().readInterval(rangeString);
		
			Iterator<Reference> it = range.iterator(); 
			
			if (!range.getInterval().isEmpty()){
				while(it.hasNext()) {
					it.remove();
					it.next();
				}
				
			} else {
				return -1;
			}
			
		} else {
			
			Reference reference = getFactory().readReference(rangeString);
			reference.getCell().removeContent();
			
		}
		
		return 0;
		
	}
	
	public int insertRange(String rangeString, Content content) throws ArrayIndexOutOfBoundsException {
	
		if (rangeString.contains(":")) {
			Range range = getFactory().readInterval(rangeString);
			
			if (!range.getInterval().isEmpty()){
				for(Reference reference : range) {
					reference.insert(content);
				}
			} else {
				return -1;
			}
		
		} else {
			
			Reference reference = getFactory().readReference(rangeString);
			reference.insert(content);
		}
		
		return 0;
		
		
	}
	
	
	public int copyRange(String rangeString) throws ArrayIndexOutOfBoundsException {
		Range range;
		if (rangeString.contains(":")) {
			range = getFactory().readInterval(rangeString);
			
			if (!range.getInterval().isEmpty()){
				putCutBuffer(range);
			} else {
				return -1;
			}
		
		} else {
			Reference reference = getFactory().readReference(rangeString);
			range = new Range(reference, reference, _currentSheet);
			putCutBuffer(range);
		}
		
		return 0;
		
	}


	public void putCutBuffer(Range range) {
		_cutBuffer = new ArrayList<Cell>();
		int columnInc = 0;
		int lineInc = 0;
		int line = 1;
		int column = 1;
		
		if(range.getFirst().getCell().getLine() != range.getLast().getCell().getLine()) {
			lineInc = 1;
		} else {
			columnInc = 1;
		}
		
		for (Reference reference : range) {
			Cell cell;
			if (reference.hasValue()) {
				cell = new Cell(reference.getCell().getContent(), line, column);
			} else {
				cell = new Cell(line, column);
			}
			
			line += lineInc;
			column += columnInc;
			_cutBuffer.add(cell);
		
		}
	}
	
	public int pasteRange(String rangeString) throws ArrayIndexOutOfBoundsException {

		Range range;
		if (rangeString.contains(":")) {
			boolean canPaste = false;
			range = getFactory().readInterval(rangeString);
			
			if (range.getIntervalSize() == _cutBuffer.size()) {
				if ((range.getFirst().getCell().sameLine(range.getLast().getCell()) && (_cutBuffer.get(0).sameLine(_cutBuffer.get(_cutBuffer.size() - 1)))) ||
					(range.getFirst().getCell().sameColumn(range.getLast().getCell()) && (_cutBuffer.get(0).sameColumn(_cutBuffer.get(_cutBuffer.size() - 1)))) ) {
					
					canPaste = true;
				}
			}
			
			int i = 0;
			if ((!range.getInterval().isEmpty()) && canPaste){
				for (Reference reference : range) {
					reference.insert(_cutBuffer.get(i).getContent());
					i++;
				}
			} else {
				return -1;
			}
		
		} else {
			
			Reference reference = getFactory().readReference(rangeString);
			int line = reference.getCell().getLine();
			int column = reference.getCell().getColumn();
			
			for (Cell cell : _cutBuffer) {
				if (((line + cell.getLine()-1) <= _currentSheet.getMatrix().getLines()) && ((column + cell.getColumn()-1) <= _currentSheet.getMatrix().getColumns())) {
					_currentSheet.getMatrix().getCell(line + cell.getLine()-1, column + cell.getColumn()-1).setContent(cell.getContent());
				} else {
					break;
				}
			}
			
		}
		
		return 0;
		
	}	
	
	
	
}
