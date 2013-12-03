package calc;

import java.io.Serializable;

public class Sheet implements Serializable {

	private SheetRepresentation _matrix;
	private String _name;
	
	public void setName(String name){
		_name = name;
	}
	
	public String getName() {
		return _name;
	}
	
	public Sheet(int lines, int columns) {
		_matrix = new SheetArray(lines, columns);
	}
	
	public void insert(Content content, int line, int column) {
		Cell cell = new Cell(line, column);
		cell.setContent(content);
		_matrix.setCell(line, column, cell);
	}
	
	public SheetRepresentation getMatrix(){
		return _matrix;
	}
	
}

