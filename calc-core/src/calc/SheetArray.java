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
		
		private int _line, _column;
		
		public SheetArrayIterator() {
			_line = 1;
			_column = 0;
		}
		
		
		public boolean hasNext() {
			
			if ( (_line < (_sheetArray.length)) || (_column < (_sheetArray[0].length)) ) {
				return true;
			}

			return false;
		}

		public Cell next() {
			
			
			if ((_column < (_sheetArray[0].length)) ) { //nao ultima coluna
				_column++;
				return getCell(_line, _column);
			} else if (_line < (_sheetArray.length)){ //ultima coluna, nao ultima linha
				_column = 1;
				_line++;
				return getCell(_line, _column);
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
