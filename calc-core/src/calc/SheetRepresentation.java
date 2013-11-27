package calc;

import java.io.Serializable;

public abstract class SheetRepresentation implements Iterable<Cell>, Serializable {

	public abstract Cell getCell(int line, int column);
	
	public abstract void setCell(int line, int column, Cell cell);
	
	
}
