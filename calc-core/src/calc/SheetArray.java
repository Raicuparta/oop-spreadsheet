package calc;
import java.io.Serializable;
import java.util.Iterator;
import java.lang.ArrayIndexOutOfBoundsException;

public class SheetArray extends SheetRepresentation implements Serializable {

	private Cell[][] _sheetArray;
	private int _lines;
	private int _columns;
	
	public SheetArray (int lines, int columns) {
		_sheetArray = new Cell[lines][columns];
		_lines = lines;
		_columns = columns;
	}
	
	public Cell getCell(int line, int column) throws ArrayIndexOutOfBoundsException {
		if (_sheetArray[line-1][column-1] == null) {
			Cell cell = new Cell(line, column);
			_sheetArray[line-1][column-1] = cell;
			return cell;
		} else {
			return _sheetArray[line-1][column-1];
		}
	}
	
	public void setCell(int line, int column, Cell cell) {
		_sheetArray[line-1][column-1] = cell;
	}

	private class SheetArrayIterator implements Iterator<Cell> {
		
		int line, column;
		
		public boolean hasNext() {
			
			if ( (line < _sheetArray.length) || (column < _sheetArray[0].length) ) {
				return true;
			}

			return false;
		}

		public Cell next() {
			if ((column < _sheetArray[0].length) ) { //nao ultima coluna
				return getCell(line, column++);
			} else if (line < _sheetArray.length){ //ultima coluna, nao ultima linha
				column = 0;
				return getCell(line++, column);
			} else {
				return null;
			}
		}

		public void remove() {
			throw new UnsupportedOperationException();
			
		}
	}
	
	public Iterator<Cell> iterator() {
		return new SheetArrayIterator();
	}
	
	public int getLines() {
		return _lines;
	}
	
	public int getColumns() {
		return _columns;
	}
}
