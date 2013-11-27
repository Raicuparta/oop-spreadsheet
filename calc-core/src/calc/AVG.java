package calc;

public class AVG extends RangedFunction {

	public AVG(Cell cell1, Cell cell2, SheetRepresentation Sheet) {
		super(cell1, cell2, Sheet);
	}

	public int calculate() {
		/*int total = 0;
		for (int value : operators) {
			total += value;
		}
		return total/operators.size();*/
		return 0;
	}
	
	public String toString() {
		return null;
	}
	
	public String getName() {
		return "AVG";
	}
	
}
