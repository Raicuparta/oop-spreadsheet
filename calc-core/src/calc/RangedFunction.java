package calc;
import java.util.ArrayList;

public abstract class RangedFunction extends Function {

	private ArrayList<Integer> operators;
	
	public ArrayList<Integer> getOperators() {
		return operators;
	}


	public RangedFunction(Cell cell1, Cell cell2, SheetRepresentation Sheet) {
		
		/*if(cell1.getLine()==cell2.getLine()) {
			for(int i = cell1.getColumn(); i <= cell2.getColumn(); i++) { 
				this.operators.add(Sheet.getCell(cell1.getLine(), i).getContent().calculate());
			}
			
		} else if (cell1.getColumn()==cell2.getColumn()) {
			for(int i = cell1.getLine(); i <= cell2.getLine(); i++) {
				this.operators.add(Sheet.getCell(cell1.getColumn(), i).getContent().calculate());
			}
		} else {
			
		}*/
	}
	
	public abstract int calculate();
	
	public abstract String toString();
	
}
