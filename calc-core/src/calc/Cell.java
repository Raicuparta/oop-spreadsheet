package calc;

import java.io.Serializable;

public class Cell implements Serializable {

	private int _line;
	private int _column;
	private Content _content;

	public Cell(int line, int column) {
		_line = line;
		_column = column;
	}
	
	public int getLine() {
		return _line;
	}

	public void setLine(int line) {
		_line = line;
	}

	public int getColumn() {
		return _column;
	}

	public void setColumn(int column) {
		_column = column;
	}

	public Content getContent() {
		return _content;
	}
	
	public void setContent(Content content) {
		_content = content;
	}
	
	public void removeContent() {
		_content = null;
	}
	
	public String print() {
		String contentString = "";
		
		if(_content != null) {
			contentString = _content.print();
		}
		
		return _line + ";" + _column + "|" + contentString;
	}
	
	public boolean sameLine(Cell other) {
		return (getLine() == other.getLine());
	}
	
	public boolean sameColumn(Cell other) {
		return (getLine() != other.getLine());
	}	
	
}
