package calc;


import java.io.Serializable;

public class Reference extends Content implements Serializable {

	private Cell _cell;
	
	public Reference (Cell cell) {
		_cell = cell;
	}
	
	public int calculate() {
		if (hasValue()) {
			return _cell.getContent().calculate();
		} else {
			return 0; //Este valor nunca e usado
		}
	}
	
	public void insert(Content content) {
		_cell.setContent(content);
	}
	
	public Cell getCell() {
		return _cell;
	}
	
	public boolean hasValue() {
		return _cell.getContent() != null;
	}
	
	public String print() {
		String valueString = "#VALUE";
		

		if (_cell.getContent() != null) {
			valueString = "" + calculate();
		}

		
		return valueString +  "=" + _cell.getLine() + ";" + _cell.getColumn();
	}
}
