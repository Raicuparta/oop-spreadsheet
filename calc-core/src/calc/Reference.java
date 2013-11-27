package calc;


import java.io.Serializable;
import java.io.Serializable;

public class Reference extends Content implements Serializable {

	private Cell _cell;
	
	public Reference (Cell cell) {
		_cell = cell;
	}
	
	public int calculate() {
		return _cell.getContent().calculate();
	}
	
	public void insert(Content content) {
		_cell.setContent(content);
	}
	
	public Cell getCell() {
		return _cell;
	}
	
	public String toString() {
		String valueString = "#VALUE";
		
		if (_cell != null) {
			if (_cell.getContent() != null) {
				valueString = "" + calculate();
			}
		} else {
			_cell = new Cell();
		}
		
		return valueString +  "=" + _cell.getLine() + ";" + _cell.getColumn();
	}
}
