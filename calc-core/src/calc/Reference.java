package calc;


import java.io.Serializable;

public class Reference extends Content implements Serializable {

	private Cell _cell;
	
	public Reference (Cell cell) {
		_cell = cell;
	}
	
	public int calculate() {
		if (_cell.getContent() == null) {
			return 0;		// mudar isto
		}
		else{
			return _cell.getContent().calculate();
		}
	}
	
	public void insert(Content content) {
		_cell.setContent(content);
	}
	
	public Cell getCell() {
		return _cell;
	}
	
	@Override
	public String toString() {
		String valueString = "#VALUE";
		

		if (_cell.getContent() != null) {
			valueString = "" + calculate();
		}

		
		return valueString +  "=" + _cell.getLine() + ";" + _cell.getColumn();
	}
}
